# Simple Search
This application enables the user to perform interactive searches of all text files located in a specified directory. The user is prompted to give a search string containing words, whereupon each text file within the folder and its corresponding rank will be printed. The rank is calculated by the intersection between words in the text file and the search string. Only files with a rank greater than zero will be printed (maximum 10). 

## Requirements
In order to build and run the application, you need to:
* Install Java Development Kit (JDK)
* Install sbt

## Getting Started
Once you have all the requirements up and running, head over to your favourite shell and grab a cup of coffee. Now, navigate to the root file of the project (where the build.sbt file is located) and start the application by typing: 

```
sbt
> runMain test.SimpleSearch directoryContainingTextFiles
```

For example, a session may look like the example given below: 

```
sbt
> runMain test.SimpleSearch Serenader/
9 files found in directory Serenader/
search> Sätt maskinen igång!
no matches found
search> fjärran stjärnorna du
serenad.txt : 66% kristallen.txt : 33%
search> :quit
```
