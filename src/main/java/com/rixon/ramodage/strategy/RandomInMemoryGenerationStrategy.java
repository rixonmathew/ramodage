package com.rixon.ramodage.strategy;

import com.rixon.ramodage.strategy.record.RecordCreationContext;
import com.rixon.ramodage.strategy.record.RecordCreationStrategy;

import java.io.IOException;

/**
 * User: rixonmathew
 * Date: 16/06/14
 * Time: 1:28 AM
 */
public class RandomInMemoryGenerationStrategy<TYPE> extends AbstractDataGenerationStrategy<TYPE> {
    /**
     * This method will do the work required for populating the split. The AbstractDataGenerationStrategy takes
     * care of creating the thread pool for executing the tasks. Each of the worker thread will call this method
     * for creating data for splits. All subclasses needs to provide an implementation as per the variation they
     * expect
     *
     * @param split  the split for which data is to be generated
     * @param taskId the id of the task for which data is being generated
     */
    @Override
    protected void populateDataForSplit(String split, String taskId) throws IOException {
        RecordCreationStrategy<TYPE> recordCreationStrategy = RecordCreationContext.strategyFor(schema.getType());
        long maxRecords = options.getNumberOfRecordsPerSplit();
        for (int i = 0; i < maxRecords; i++) {
            TYPE object = recordCreationStrategy.createRecordAsObject(schema,options,i);
            this.dataDestination.addObject(split,object);
            progressReporter.updateThreadProgress(taskId, (i + 1) * 100.0f / maxRecords);
        }
    }
}
