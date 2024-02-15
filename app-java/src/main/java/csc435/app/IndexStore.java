package csc435.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexStore {
    static HashMap<String,Map<String,Integer>> globalIndex=new HashMap<>();
    public IndexStore() {}


    public void insertIndex(String key, HashMap<String,Integer> localIndex) {
        globalIndex.put(key,localIndex);
        ProcessingEngine.foldersAccessed.remove(key);
    }

    public void lookupIndex(String word1, String word2) {
        Map<String,Integer> ans = new HashMap<>();
        //iterate global index
        for (Map.Entry<String,Map<String,Integer>> entry :globalIndex.entrySet()){
            Map<String,Integer> localIndex = entry.getValue();
            if(localIndex.containsKey(word1)&&localIndex.containsKey(word2)){
                ans.put(entry.getKey(),localIndex.get(word1)+localIndex.get(word2));
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(ans.entrySet());
        list.sort(Map.Entry.comparingByValue());

        //print the top 10 ans
        for(int i=0;i<10 && i<list.size();i++){
            String s = list.get(i).getKey();
            String key = "* Dataset-"+s.substring(0,1)+"/ "+s.substring(1,s.length());
            String value = list.get(i).getValue().toString();

            System.out.println(key+" "+value);
        }
    }

    public void lookupIndex(String word) {
        Map<String,Integer> ans = new HashMap<>();
        //iterate global index
        for (Map.Entry<String,Map<String,Integer>> entry :globalIndex.entrySet()){
            Map<String,Integer> localIndex = entry.getValue();
            if(localIndex.containsKey(word)){
                ans.put(entry.getKey(),localIndex.get(word));
            }
        }

        //sort the ans in descending order based on the value
        List<Map.Entry<String, Integer>> list = new ArrayList<>(ans.entrySet());
        list.sort(Map.Entry.comparingByValue());

        //print the top 10 ans
        for(int i=0;i<10 && i<list.size();i++){
            String s = list.get(i).getKey();
            String key = "* Dataset-"+s.substring(0,1)+"/ "+s.substring(1,s.length());
            String value = list.get(i).getValue().toString();

            System.out.println(key+" "+value);
        }


        // TO-DO implement index lookup method
    }
}
