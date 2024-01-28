#include "AppInterface.hpp"

#include <iostream>
#include <string>

AppInterface::AppInterface(std::shared_ptr<ProcessingEngine> engine) : engine(engine) {
    // TO-DO implement constructor
}

void AppInterface::readCommands() {
    // TO-DO implement the read commands method
    std::string command;
    
    while (true) {
        std::cout << "> ";
        
        // read from command line
        std::cin >> command;

        // if the command is quit, terminate the program       
        if (command == "quit") {
            engine->stopWorkers();
            break;
        }
        
        // if the command begins with index, index the files from the specified directory
        if (command.size() >= 5 && command.substr(0, 5) == "index") {
            // TO-DO implement index operation
            continue;
        }

        // if the command begins with search, search for files that matches the query
        if (command.size() >= 6 && command.substr(0, 6) == "search") {
            // TO-DO implement index operation
            continue;
        }

        std::cout << "unrecognized command!" << std::endl;
    }
}