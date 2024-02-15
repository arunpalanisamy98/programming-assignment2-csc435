package csc435.app;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ProcessingEngine extends Thread{
    private IndexStore store;
    private String rawFile;
    private String datasetNo;
    static Set<String> foldersAccessed = new HashSet<>();

    public ProcessingEngine(IndexStore store) {
        this.store = store;
    }

    public ProcessingEngine(ProcessingEngine engine, String rawfile, String datasetNo)  {
        this.store = engine.store;
        this.rawFile = rawfile;
        this.datasetNo = datasetNo;
    }

    public void run()  {
        try{
            start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // TO-DO implement the run method
    }



    public void start() {
        File input = new File(rawFile);
        if (input.exists() && input.isDirectory()) {
            File[] directories = input.listFiles(File::isDirectory);
            for (File dir : directories) {
                try{
                    processFiles(rawFile + "/" + dir.getName());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }


    public void processFiles(String inputDir) throws Exception {
        File input = new File(inputDir);
        if (input.exists() && input.isDirectory()) {
            File[] files = input.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    //for threads
                    if(foldersAccessed.contains(file.getAbsolutePath())) continue;
                    cleanFiles(file.getAbsolutePath(), file.getName() );
                }
            }
        }
    }

    public void cleanFiles(String fileName, String name) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        foldersAccessed.add(datasetNo+name);
        String line;
        HashMap<String, Integer> wordCount = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            line = line.replace("\r", "");
            line = line.replace("\\s+", "");
            line = line.replace("[^\\w\\s", "");
            line = line.replaceAll("[^a-zA-Z0-9 ]", "");
            String[] str = line.split(" ");
            for (String s : str) {
                if(s.equals("")) continue;
                if(wordCount.containsKey(s)){
                    wordCount.put(s, wordCount.get(s) + 1);
                }
                else{
                    wordCount.put(s, 1);
                }
            }
        }
        store.insertIndex(datasetNo+name,wordCount);
    }

    /*fileName is absolute path
    * name is the name of the file*/
    public void countFiles(String fileName, String name) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        Map<String, Map<String, Integer>> count = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            String[] str = line.split(" ");
            for (String s : str) {
                if (s.equals("")) {
                    continue;
                }
                // if word is not present in map, add it with count 1
                if (!count.containsKey(s)) {
                    Map<String, Integer> val = new HashMap<>();
                    val.put(datasetNo + name, 1);
                    count.put(s, val);
                } else { // if word is present in map, increment its count
                    Map<String, Integer> val = count.get(s);
                    //if the word is not present in the file, add it with count 1
                    if (!val.containsKey(datasetNo + name)) {
                        val.put(datasetNo + name, 1);
                        count.put(s, val);
                        //Map<String, Integer> newVal = new HashMap<>();
                        //newVal.put(datasetNo + name, 1);
                    } else {
                        val.put(datasetNo + name, val.get(datasetNo + name) + 1);
                        count.put(s, val);
                    }
                }
            }
        }

    }

    public void searchFiles(List<String> words) {
        String str1,str2;
        if(words.size() == 1){
            str1 = words.get(0);
            store.lookupIndex(str1);
        }
        else{
            str1 = words.get(0);
            str2 = words.get(1);
            store.lookupIndex(str1,str2);
        }
    }



    public void stopWorkers() {
        System.exit(0);
    }
}
