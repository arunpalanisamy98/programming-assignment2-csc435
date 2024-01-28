#include "ProcessingEngine.hpp"

ProcessingEngine::ProcessingEngine(std::shared_ptr<IndexStore> store) : store(store) {
    // TO-DO implement constructor
}

void ProcessingEngine::indexFiles() {
    // TO-DO implement index files method
}

void ProcessingEngine::searchFiles() {
    // TO-DO implement search files method
}

void ProcessingEngine::stopWorkers() {
    // TO-DO implement gracefully stop workers
}