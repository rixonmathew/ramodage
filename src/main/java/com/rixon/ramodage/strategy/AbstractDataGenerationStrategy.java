package com.rixon.ramodage.strategy;

import com.rixon.ramodage.configuration.Options;
import com.rixon.ramodage.configuration.Schema;
import com.rixon.ramodage.destination.DataDestination;
import com.rixon.ramodage.model.DataGenerationStatus;
import com.rixon.ramodage.model.DataGenerationStatusImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The base class for DataGenerationStrategy. This class encapsulates all common activities as part of data generation.
 * This class provides a framework for distributing data generation among multiple threads. The work to be done in
 * each thread is to be implemented by specific strategies.
 * User: rixon
 * Date: 7/3/13
 * Time: 7:06 PM
 */
public abstract class AbstractDataGenerationStrategy<TYPE> implements DataGenerationStrategy<TYPE> {

    protected final Logger LOG = LoggerFactory.getLogger(AbstractDataGenerationStrategy.class);
    protected Schema schema;
    protected Options options;
    protected DataDestination dataDestination;
    protected ProgressReporter progressReporter;
    protected PrintStream progressDestination;

    public AbstractDataGenerationStrategy(){
      progressDestination = System.out;
      progressReporter = new ProgressReporter();
    }

    @Override
    public void generateData(Schema schema, Options options,DataDestination<TYPE> dataDestination) {
        this.schema = schema;
        this.options = options;
        this.dataDestination = dataDestination;
        prepareForDataGeneration();
        populateDataUsingWorkers(null);
        cleanup();
    }

    /**
     * This method is responsible for generating the data required for the files as per the strategy
     * in a non blocking manner.
     *
     * @param schema          the schema that represents the structure of the file
     * @param options         options required for generating file
     * @param dataDestination represents the destination where the generated data should be placed
     */
    @Override
    public DataGenerationStatus<TYPE> generateDataAsynchronously(final Schema schema, final Options options, final DataDestination<TYPE> dataDestination) {
        final DataGenerationStatus<TYPE> dataGenerationStatus = new DataGenerationStatusImpl<>();
        dataGenerationStatus.setProgressReporter(progressReporter);
        dataGenerationStatus.setDataDestination(dataDestination);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                AbstractDataGenerationStrategy.this.schema = schema;
                AbstractDataGenerationStrategy.this.options = options;
                AbstractDataGenerationStrategy.this.dataDestination = dataDestination;
                prepareForDataGeneration();
                populateDataUsingWorkers(dataGenerationStatus);
                cleanup();
            }
        });
        executorService.shutdown();
        return dataGenerationStatus;
    }

    /**
     * This method can be used by specific strategies to do any pre-processing
     * before data generation. Subclasses can override for specific behaviour
     */
    protected void prepareForDataGeneration() {
       dataDestination.prepareForDataGeneration();
    }

    /**
     * This method can be used by specific strategies to do clean up
     * after data generation.Subclasses can override for specific behaviour
     */
    protected  void cleanup() {
        dataDestination.clear();
    }


    protected void populateDataUsingWorkers(DataGenerationStatus<TYPE> dataGenerationStatus) {


        ExecutorService executorService = Executors.newFixedThreadPool(options.getNumberOfThreads());
        List<String> splitNames = dataDestination.getDestinationMetaData().getSplitNames();
        for (String split : splitNames) {
            Worker worker = new Worker(split,progressReporter);
            executorService.execute(worker);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                updateProgress(progressReporter.overallProgress());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        updateProgress(100.0d);
        if(dataGenerationStatus!=null){
            dataGenerationStatus.setDatatGenerationComplete(true);
        }
        progressDestination.println();
    }

    private void updateProgress(double progressPercentage) {
        final int width = 90; // progress bar width in chars
        progressDestination.print("\r[");
        int i = 0;
        for (; i <= (int)(progressPercentage*width*0.01f); i++) {
            progressDestination.print("=");
        }
        for (; i < width; i++) {
            progressDestination.print(" ");
        }
        progressDestination.print("] ==>" + progressPercentage + "%");
    }

    /**
     * This method will do the work required for populating the split. The AbstractDataGenerationStrategy takes
     * care of creating the thread pool for executing the tasks. Each of the worker thread will call this method
     * for creating data for splits. All subclasses needs to provide an implementation as per the variation they
     * expect
     * @param split the split for which data is to be generated
     * @param taskId the id of the task for which data is being generated
     */
    protected abstract void populateDataForSplit(String split, String taskId) throws IOException;

    class Worker implements Runnable {
        final String split;
        final ProgressReporter progressReporter;
        final String taskId;

        Worker(String split, ProgressReporter progressReporter) {
            this.progressReporter = progressReporter;
            this.split = split;
            this.taskId = "task:" + split;
            progressReporter.updateThreadProgress(taskId, 0.0f);
        }

        @Override
        public void run() {
            try {
                populateDataForSplit(split,taskId);
            } catch (IOException e) {
                LOG.error("An error occurred while populating data for split: "+split);
                e.printStackTrace();
            }
        }
    }
}
