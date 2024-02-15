# File Retrieval Engine

File retrieval engine is a java application to index all the alphanumerical words in datasets. It has an additional feature to search/query words post indexing

## Installation

install jdk and maven.

```bash
sudo apt install openjdk-17-jdk maven
```




## download the datasets, unzip and give access to the datasets

```bash
chmod 777 /path/to/dataset 
```

## git clone or download the repository
```bash
git clone git@github.com:neurobazaar/csc435-pa2-arunpalanisamy98.git
```

## compilation

```bash
cd /path/to/repo/csc435-pa2-arunpalanisamy98/app-java/
mvn clean compile
mvn package
```

## running the application
```bash
cd /path/to/repo/csc435-pa2-arunpalanisamy98/app-java/
mvn exec:java -Dexec.mainClass="csc435.app.FileRetrievalEngine"
```

## indexing
```bash
number of threards needs to be entered as the last $arg in the commands else, it will give a stdout "Invalid command"
>index /path/to/dataset $numberOfThreads
```

## searching
```bash
>search $arg1
>search $arg1 AND $arg2
```

## quitting
```bash
>quit
```

Please make sure to update tests as appropriate.

## Credits

implemented by apalanis@depaul.edu for Programming Assignment-2 of CSC 435.
Instructor name: Dr. Alexandru Orhean