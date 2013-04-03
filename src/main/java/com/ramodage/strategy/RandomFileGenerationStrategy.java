package com.ramodage.strategy;

import com.ramodage.strategy.record.RecordCreationContext;
import com.ramodage.strategy.record.RecordCreationStrategy;
import com.ramodage.strategy.record.RecordCreationContext;
import com.ramodage.strategy.record.RecordCreationStrategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is the simplest strategy for generating the files. Each field in the record is independent of the other. The file is generated by
 * randomly generating each field value
 * User: rixon
 * Date: 20/01/13
 * Time: 8:33 PM

 */
public class RandomFileGenerationStrategy extends AbstractDataGenerationStrategy {

    @Override
    protected void populateDataForSplit(long split, String taskId) throws IOException {
        File outputFile = filesForSplit.get(split);
        //TODO think about injecting the writer instead of hardcoding a giver writer.
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        RecordCreationStrategy recordCreationStrategy = RecordCreationContext.strategyFor(schema.getType());
        long maxRecords = options.getNumberOfRecordsPerSplit();
        for (int i = 0; i < maxRecords; i++) {
            String record = recordCreationStrategy.createRecord(schema, options, i);
            writer.write(record);
            writer.newLine();
            progressReporter.updateThreadProgress(taskId, (i + 1) * 100.0f / maxRecords);
        }
        writer.close();
    }
}