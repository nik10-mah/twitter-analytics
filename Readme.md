# Twitter Analytics System
A twitter analytics system scraps data from Twitter and saves it on Amazon S3 bucket. Then Using web pages user can query the tweets from data stored on S3 using Athen API using different languages and see the corresponding results. [AWS ATHENA SDK](https://docs.aws.amazon.com/athena/latest/ug/code-samples.html) for  has been used to query database on S3

## Project Set Up
This is java based web app based on **Spring Boot** framework with **Thymeleaf** and **Jquery**. The project skeleton has been created using [Spring Initialzr](https://start.spring.io/)

### Prequisites
 - Requires JDK 1.8+ to be installed and can be downloaded from [here](http://www.foo.com/)
 - Download and set [Maven](https://maven.apache.org/download.cgi) on your local system environment `path` variable. Refer [this](https://www.tutorialspoint.com/maven/maven_environment_setup.htm) for setting **Java** and **Maven** paths
 - Download [STS](https://www.tutorialspoint.com/maven/maven_environment_setup.htm) based on your OS. **STS 3.8.4** is preferred. It should work with Latest version as well.
 - Downlaod and install [GIT](https://git-scm.com/downloads). THis will provide command line tools for for communicating wit source code on **Gitlab**. It would be helpful to go through some git tutorials.
  
 ### Setting up GIT
 - Once the git is installed you need to confiure it to start cloning projects using your gitlab credentials.
 - Run following commands to set your configure your gitlab account provided to you. `git config --global user.name "Your Gitlab Username"` and `git config --global user.email your-email-associated-to-gitlab`
 
### Setting up development environment
- Unzip the downloaded STS package inot a folder and then run the **STS** executable file under **sts-bundle**
- Startiing SST will take time and It will also create a **workspace** folder for you where you will keep your project.
- Once STS is started, go to your workspace location and run follwoing command to clone the project `git clone http://gitlab.insonix.com:8888/java/athena-poc.git`
- A folder with name `athena-poc` wiil be created with all of the source files
- Then in STS go to `File -> Import -> Maven -> Existing Maven Projects` `Browse` select `athena-poc` folder in your workspace created after git clone in previous step and clikc `Finish`. After some time yoour project will be imported in STS.

### To run the project
Using maven on command line
```sh
$ mvn spring-boot:run
```
In STS, Right click the project and then got to `Run As -> Spring Boot App`

### To generate the jar file
Using maven on command line
```sh
$ mvn clean install
```
In STS, Right click the project and then got to `Run As -> Maven Clean` and after this do `Run As -> Maven Install`

Jar file wiil be generated in the **target** folder with the name **twitter-analytics.jar**

Use following command to run the jar file
```sh
$ java -jar .\twitter-analytics.jar
```

Application will run port 5000 at following url:-
```sh
http://localhost:5000
```

### Sample Queries to run
- `select lang, count(*) cnt from socialanalyticsblog.tweets group by lang order by cnt desc`
- `select ts.text, ts.originaltext from socialanalyticsblog.tweet_sentiments ts join socialanalyticsblog.tweets t on (ts.tweetid = t.id) where lang = 'en'  and ts.text like '%good%'`
- `select ts.text, ts.originaltext from socialanalyticsblog.tweet_sentiments ts join socialanalyticsblog.tweets t on (ts.tweetid = t.id) where lang = 'ar'`
- `select * from socialanalyticsblog.tweet_sentiments where sentiment = 'POSITIVE' limit 20`
Thank You!!