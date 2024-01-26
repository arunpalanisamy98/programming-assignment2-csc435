#include <iostream>

#include "IndexStore.hpp"
#include "RetrievalEngine.hpp"
#include "ApplicationInterface.hpp"

int main(int argc, char** argv)
{
    std::shared_ptr<IndexStore> store = std::make_shared<IndexStore>();
    std::shared_ptr<RetrievalEngine> engine = std::make_shared<RetrievalEngine>(store);
    std::shared_ptr<ApplicationInterface> interface = std::make_shared<ApplicationInterface>(engine);

    interface->readCommands();

    return 0;
}