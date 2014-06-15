package com.ramodage.strategy;

import com.ramodage.configuration.Schema;
import com.ramodage.configuration.Options;
import com.ramodage.configuration.Schema;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The base class for DataGenerationStrategy. This class encapsulates all common activities as part of data generation.
 * This class provides a framework for distributing data generation among multiple threads. The work to be done in
 * each thread is to be implemented by specific strategies.
 * Created with IntelliJ IDEA.
 * User: rixon
 * Date: 7/3/13
 * Time: 7:06 PM
 */
public abstract class AbstractDataGenerationStrategy implements FileGenerationStrategy {

    protected final Logger LOG = Logger.getLogger(AbstractDataGenerationStrategy.class);
    protected Schema schema;
    protected Options options;
    protected File outputDirectory;
    protected final Map<Long, File> filesForSplit;
    protected ProgressReporter progressReporter;
    protected PrintStream progressDestination;

    public AbstractDataGenerationStrategy(){
      filesForSplit = new HashMap<Long, File>();
      progressDestination = System.out;
    }

    @Override
    public void generateFileData(Schema schema, Options options) {
        this.schema = schema;
        this.options = options;
        try {
            prepareForDataGeneration();
            generateOutputDirectories();
            createFilesForSplits();
            populateDataUsingWorkers();
            cleanup();
        } catch (IOException e) {
            LOG.error("An error occurred while creating files for splits");
        }
    }

    public PrintStream getProgressDestination() {
        return progressDestination;
    }

    public void setProgressDestination(PrintStream progressDestination) {
        this.progressDestination = progressDestination;
    }


    /**
     * This method can be used by specific strategies to do any pre-processing
     * before data generation. Subclasses can override for specific behaviour
     */
    protected void prepareForDataGeneration() {

    }

    /**
     * This method can be used by specific strategies to do clean up
     * after data generation.Subclasses can override for specific behaviour
     */
    protected  void cleanup() {

    }

    protected void generateOutputDirectories() {
        if (options.getOutputDirectory() == null || options.getOutputDirectory().isEmpty()) {
            String name = "output_" + System.currentTimeMillis();
            outputDirectory = new File(name);
        } else {
            outputDirectory = new File(options.getOutputDirectory());
        }
        outputDirectory.mkdirs();
    }

    protected void createFilesForSplits() throws IOException {
        String fileName = schema.getName() + "-part";
        for (int split = 0; split < options.getNumberOfFileSplits(); split++) {
            String splitFileName = fileName + "-" + split;
            File outputFile = new File(outputDirectory.getAbsolutePath() + File.separator + splitFileName);
            outputFile.createNewFile();
            filesForSplit.put((long) split, outputFile);
        }
    }

    protected void populateDataUsingWorkers() {
        progressReporter = new ProgressReporter();
        ExecutorService executorService = Executors.newFixedThreadPool(options.getNumberOfThreads());
        for (Long split : filesForSplit.keySet()) {
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
    protected abstract void populateDataForSplit(long split, String taskId) throws IOException;

    public class ProgressReporter{
        private final Map<String,Float> taskProgress = new HashMap<String, Float>();

        public synchronized void updateThreadProgress(String taskId,Float progress) {
            taskProgress.put(taskId,progress);
        }

        synchronized float overallProgress() {
            int total=0;
            int count=0;
            for(Float value:taskProgress.values()) {
                total+=value;
                count++;
            }

            float overallProgress=0;
            if(count>0)
                overallProgress=total/count;
            return overallProgress;
        }
    }

    class Worker implements Runnable {
        final Long split;
        final ProgressReporter progressReporter;
        final String taskId;

        Worker(Long split, ProgressReporter progressReporter) {
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
