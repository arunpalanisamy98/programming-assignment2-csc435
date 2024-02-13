package csc435.app;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProcessingEngine {
    private IndexStore store;
    private String rawFile;
    private String cleanedFiles;
    private String countedFiles;
    private int datasetNo;

    public ProcessingEngine(IndexStore store) {
        this.store = store;
        
        // TO-DO implement constructor
    }

    public void indexFiles(String rawfile) throws Exception{
        this.rawFile=rawfile;
        this.datasetNo=Integer.parseInt(rawfile.substring(rawfile.length()-1));
        cleanedFiles=rawfile+"/CleanedDataset"+datasetNo;
        countedFiles=rawfile+"/SortedDataset"+datasetNo;
        new File(cleanedFiles).mkdirs();
        start(rawfile,cleanedFiles);
        //index
    }

    public void start(String inputDir, String outputDir) throws Exception {
        File input = new File(inputDir);
        if(input.exists()&&input.isDirectory()){
            File[] directories = input.listFiles(File::isDirectory);
            for(File dir:directories){
                if(dir.getName().equals(cleanedFiles)||dir.getName().equals(countedFiles))
                    continue;
                new File(outputDir+"/"+dir.getName()).mkdirs();
                createFiles(inputDir+"/"+dir.getName(), outputDir+"/"+dir.getName());
            }
        }
    }

    public void createFiles(String inputDir, String outputDir) throws Exception{
        File input = new File(inputDir);
        if(input.exists()&&input.isDirectory()){
            File[] files = input.listFiles();
            for(File file:files){
                if(file.isFile()){
                    cleanFiles(file.getAbsolutePath(),outputDir+"/"+ file.getName());
                    countFiles(outputDir+"/"+file.getName(),file.getName());
                }
            }
        }
    }

    public void cleanFiles(String fileName, String output) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        FileWriter fw=new FileWriter(output);
        BufferedWriter writer=new BufferedWriter(fw);
        while((line= reader.readLine())!=null){
            line=line.replace("\r","");
            line=line.replace("\\s+","");
            line=line.replace("[^\\w\\s","");
            line=line.replaceAll("[^a-zA-Z0-9 ]","");
            writer.write(line);
        }
        writer.flush();
        writer.close();
    }

    public void countFiles(String fileName, String name) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        Map<String, Map<String,Integer>> count = new HashMap<>();
        while((line = reader.readLine())!=null){
            String[] str = line.split(" ");
            for(String s:str){
                if(s.equals("")){
                    continue;
                }
                //if word is not present in map, add it with count 1
                if(!count.containsKey(s)){
                    Map<String, Integer> val= new HashMap<>();
                    val.put(datasetNo+name,1);
                    count.put(s,val);
                } else {
                    Map<String, Integer> val= count.get(s);
                    if(!val.containsKey(datasetNo+name)){
                        Map<String, Integer> newVal= new HashMap<>();
                        newVal.put(datasetNo+name,1);
                    }else{
                        val.put(datasetNo + name, val.get(datasetNo + fileName) + 1);
                        count.put(s, val);
                    }
                }
            }
        }
        store.insertIndex(count);
    }


    
    public void searchFiles() {
        // TO-DO implement search files method
    }

    public void stopWorkers() {
        // TO-DO implement gracefully stop workers
    }
}
