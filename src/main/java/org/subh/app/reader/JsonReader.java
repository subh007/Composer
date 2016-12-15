package org.subh.app.reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	 
    private static File toFile(String fileName) {
        return new File(JsonReader.class.getClassLoader().getResource(fileName).getFile());
    }

    public static Job[] getJobsAsArray(String fileName) throws JsonParseException, JsonMappingException, IOException {
        return new ObjectMapper().readValue(toFile(fileName), Job[].class);
    }

    public static List<Job> getJobsAsList(String fileName)
            throws JsonParseException, JsonMappingException, IOException {
        return new ObjectMapper().readValue(toFile(fileName), new TypeReference<List<Job>>() {});
    }

    public static Job[] getJonbs(String jsonString) {
        return null;
    }
}
