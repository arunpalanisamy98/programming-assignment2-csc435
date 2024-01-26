#ifndef RETRIEVAL_ENGINE_H
#define RETRIEVAL_ENGINE_H

#include <memory>

#include "IndexStore.hpp"

class RetrievalEngine {
    // TO-DO Keep track of index and search workers
    std::shared_ptr<IndexStore> store;

    public:
        // constructor
        RetrievalEngine(std::shared_ptr<IndexStore> store);

        // default virtual destructor
        virtual ~RetrievalEngine() = default;

        // TO-DO re-declare index files and search files methods
        void indexFiles();
        void searchFiles();
        
        // gracefully stop index and search workers
        void stopWorkers();
};

#endif