package org.subh.app.reader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Job {
	
	public String jobId;
    public String jobName;
    public String classname;
    public List<String> dependencies;
    public int order; 
    
    public String toString() {
    	return "jobId : " + jobId
    			+ "\njobName :" + jobName
    			+ "\nclassname : " + classname
    			+ "\ndependencies : " + dependencies
    	        + "\n order : " + order;
    }    
}
