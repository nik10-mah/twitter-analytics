# Athena POC

### How to run

Requires JDK 1.8+

Extract zip file and run followng command in the extracted folder. Make sure to have java on your path variable ton run following command.

```sh
$ java -jar .\athena-poc.jar
```
Application will run port 9090 at following url:-
```sh
http://localhost:9090
```

### Sample Queries to run
```
select lang, count(*) cnt from socialanalyticsblog.tweets group by lang order by cnt desc

select ts.text, ts.originaltext from socialanalyticsblog.tweet_sentiments ts join socialanalyticsblog.tweets t on (ts.tweetid = t.id) where lang = 'en'  and ts.text like '%good%'

select ts.text, ts.originaltext from socialanalyticsblog.tweet_sentiments ts join socialanalyticsblog.tweets t on (ts.tweetid = t.id) where lang = 'ar'

select * from socialanalyticsblog.tweet_sentiments where sentiment = 'POSITIVE' limit 20
```