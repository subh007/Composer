package org.subh.app.composer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.subh.app.reader.Job;
import org.subh.app.reader.JsonReader;

public class App {

    public static void main(String[] args) throws Exception {

        List<Job> jobs = JsonReader.getJobsAsList("jobs.json");
        jobs.forEach(t -> {
            try {
                executeTask(t.classname);
            } catch(ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public static void executeTask(String classname)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("executing task : " + classname);

        Class<?> clazz = Class.forName(classname);
        Constructor<?> constructor = clazz.getConstructor();
        Object newInstance = constructor.newInstance(null);
        Method executeMethod = clazz.getDeclaredMethod("executeTask", null);

        executeMethod.invoke(newInstance, null);
    }
}

