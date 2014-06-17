package com.rixon.ramodage.strategy;

import java.util.HashMap;
import java.util.Map;

/**
* User: rixonmathew
* Date: 16/06/14
* Time: 7:42 PM
*/
public class ProgressReporter {
    private final Map<String,Float> taskProgress = new HashMap<String, Float>();

    public synchronized void updateThreadProgress(String taskId,Float progress) {
        taskProgress.put(taskId,progress);
    }

    public synchronized float overallProgress() {
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
