package csc435.app;

import java.lang.System;
import java.util.Scanner;

public class ApplicationInterface {
    private RetrievalEngine engine;

    public ApplicationInterface(RetrievalEngine engine) {
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
            command = sc.next();

            // if the command is quit, terminate the program       
            if (command.compareTo("quit") == 0) {
                engine.stopWorkers();
                break;
            }
            
            // if the command begins with index, index the files from the specified directory
            if (command.length() >= 5 && command.substring(0, 5).compareTo("index") == 0) {
                // TO-DO implement index operation
                continue;
            }

            // if the command begins with search, search for files that matches the query
            if (command.length() >= 6 && command.substring(0, 6).compareTo("search") == 0) {
                // TO-DO implement index operation
                continue;
            }

            System.out.println("unrecognized command!");
        }
    }
}
