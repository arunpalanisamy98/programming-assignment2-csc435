package csc435.app;

public class FileRetrievalEngine 
{
    public static void main( String[] args )
    {
        IndexStore store = new IndexStore();
        RetrievalEngine engine = new RetrievalEngine(store);
        ApplicationInterface appInterface = new ApplicationInterface(engine);

        appInterface.readCommands();
    }
}
