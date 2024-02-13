package csc435.app;

import java.util.Map;

public class IndexStore {
    static Map<String,Map<String,Integer>> index;
    public IndexStore() {

    }

    public void insertIndex(Map<String, Map<String,Integer>> newIndex) {
        //add the new index to the existing index
        index.putAll(newIndex);
    }

    public void lookupIndex() {
        // TO-DO implement index lookup method
    }
}
