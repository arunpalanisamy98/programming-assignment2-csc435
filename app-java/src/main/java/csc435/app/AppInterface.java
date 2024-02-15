
package csc435.app;

import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppInterface {
    private ProcessingEngine engine;

    public AppInterface(ProcessingEngine engine) {
        this.engine = engine;

        // TO-DO implement constructor
    }

    public void readCommands() {
        // TO-DO implement the read commands method
        Scanner sc = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("> ");

            // read from command line
            command = sc.nextLine();

            // if the command is quit, terminate the program
            if (command.compareTo("quit") == 0) {
                System.out.println("shutting down the application");
                engine.stopWorkers();
                break;
            }

            // if the command begins with index, index the files from the specified
            // directory
            if (command.length() >= 5 && command.substring(0, 5).compareTo("index") == 0) {
                String[] arr = command.split(" ");
                if(arr.length != 3) {
                    System.out.println("Invalid command");
                    continue;
                }
                String path = arr[1].trim();
                String datasetNo = path.substring(path.length() - 1);
                int threadCount = Integer.parseInt(arr[2].trim());
                try {
                    long startTime = System.currentTimeMillis();
                    engine = new ProcessingEngine(engine, path, datasetNo);
                    for(int i = 0; i < threadCount; i++) {
                        engine.start();
                    }
                    long endTime = System.currentTimeMillis();
                    System.out.println("Indexing took " + (endTime - startTime)/1000 + " seconds");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }

            // if the command begins with search, search for files that matches the query
            if (command.length() >= 6 && command.substring(0, 6).compareTo("search") == 0) {
                String[] arr = command.split(" ");
                List<String> arr2=new ArrayList<>();
                if(arr.length < 2) {
                    System.out.println("Invalid command");
                    continue;
                }
                for(String s : arr) {
                    if(s.trim().equals("search")||s.trim().equals("AND")) continue;
                    arr2.add(s.trim());
                }
                long startTime = System.currentTimeMillis();
                engine.searchFiles(arr2);
                long endTime = System.currentTimeMillis();
                System.out.println("Searching took " + (endTime - startTime)/1000 + " seconds");
            }

        }
    }
}
