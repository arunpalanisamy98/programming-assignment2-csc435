#ifndef PROCESSING_ENGINE_H
#define PROCESSING_ENGINE_H

#include <memory>

#include "IndexStore.hpp"

class ProcessingEngine {
    // TO-DO Keep track of index and search workers
    std::shared_ptr<IndexStore> store;

    public:
        // constructor
        ProcessingEngine(std::shared_ptr<IndexStore> store);

        // default virtual destructor
        virtual ~ProcessingEngine() = default;

        // TO-DO re-declare index files and search files methods
        void indexFiles();
        void searchFiles();
        
        // gracefully stop index and search workers
        void stopWorkers();
};

#endif