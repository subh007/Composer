package org.subh.app.composer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.subh.app.reader.Job;
import org.subh.app.reader.JobUtil;
import org.subh.app.reader.JsonReader;

public class App {

    public static void main(String[] args) throws Exception {

        List<Job> jobs = JsonReader.getJobsAsList("jobs.json");
        JobUtil.sortJobs(jobs);
        jobs.forEach(t -> {
            try {
            	System.out.println(t);
                JobUtil.executeTask(t.classname);
            } catch(ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    
}

