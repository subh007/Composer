package org.subh.app.reader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class JobUtil {
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

	 public static List<Job> sortJobs(List<Job> unprocessedQ) {
	        LinkedList<Job> processingQ = new LinkedList<>();
	        List<Job> processedQ = new ArrayList<>();

	        Job tjob = unprocessedQ.get(unprocessedQ.size() - 1);
	        unprocessedQ.forEach(processingQ::add);

	        while(!processingQ.isEmpty()) {

	            Job djob = processingQ.remove();

	            // if job doesn't have dependency then move it to
	            // processedQ with order equal to 1.
	            if(djob.dependencies == null || djob.dependencies.size() == 0) {
	                djob.order = 1;
	                processedQ.add(djob);
	                continue;
	            }

	            // if job have parent in processed
	            boolean allParentProcessed = djob.dependencies.stream().allMatch(
	                    pjobid -> processedQ.stream().filter(job -> job.jobId.equals(pjobid)).findFirst().isPresent());

	            // allParentProcessed = true;
	            // for(String pjobId : djob.dependsOn) {
	            // if(!processedQ.stream().filter(j -> j.jobId.equals(pjobId)).findFirst().isPresent())
	            // {
	            // allParentProcessed = false;
	            // break;
	            // }
	            // }

	            if(allParentProcessed) {
	                // if all parent processed
	                int maxorder = 0;
	                for(String jobid : djob.dependencies) {
	                    Optional<Job> findFirstOpt =
	                            unprocessedQ.stream().filter(job -> jobid.equals(job.jobId)).findFirst();
	                    if(findFirstOpt.isPresent()) {
	                        Job job = findFirstOpt.get();
	                        if(maxorder < job.order) {
	                            maxorder = job.order;
	                        }
	                    }
	                }

	                djob.order = maxorder + 1;
	                processedQ.add(djob);

	            } else {
	                // all parent are not processed push it back to queue.
	                processingQ.add(djob);
	            }
	        }

	        processedQ.forEach(p -> System.out.println(p));

	        return processedQ;
	    }
}
