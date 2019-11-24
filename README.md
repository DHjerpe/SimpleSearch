# Simple Search
The 'Simple Search' ranks how well a set of words are represented in text files given a certain folder. The Application is intended to be used with .txt files formatted in Unicode (UTF-8). The ranking is given by the ammount of words in the search string that are present in the text file 

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

After this, the user is prompted to give a search string containing words. After doing so, each file with its corresponding rank will be printed (if the rank is greater than zero). At maximum, the rank of ten files will be printed.  

An example session is given below: 

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
