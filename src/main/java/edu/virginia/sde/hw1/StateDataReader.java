package main.java.edu.virginia.sde.hw1;

import java.util.*;
public interface StateDataReader {

    static List<String> getStateDataList(String filePath) {
        return new ArrayList<String>();
    }

    static Map<String, Integer> getStateDataMap(String filepath) {
        return new HashMap<String, Integer>();
    }

}

