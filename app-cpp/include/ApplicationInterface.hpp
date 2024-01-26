#ifndef APPLICATION_INTERFACE_H
#define APPLICATION_INTERFACE_H

#include <memory>

#include "RetrievalEngine.hpp"

class ApplicationInterface
{
    std::shared_ptr<RetrievalEngine> engine;

    public:
        // constructor
        ApplicationInterface(std::shared_ptr<RetrievalEngine> engine);

        // default virtual destructor
        virtual ~ApplicationInterface() = default;

        void readCommands();
};

#endif