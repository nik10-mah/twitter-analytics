package com.ml.epic.ta.model;

import java.util.stream.Stream;

// TODO: Auto-generated Javadoc
/**
 * The Enum SampleQuery.
 */
public enum SampleQuery {

	SAMPLE_QUERY1("select lang, count(*) cnt from socialanalyticsblog.tweets  where event_id='830b9614-fee3-40b3-a25a-cab49066a679' group by lang order by cnt desc "),
	
	SAMPLE_QUERY2(
			"select ts.text, ts.originaltext from socialanalyticsblog.tweet_sentiments ts join socialanalyticsblog.tweets t on (ts.tweetid = t.id) where lang = \'en\' and ts.text like \'%good%\' "),
	
	SAMPLE_QUERY3(
			"select ts.text, ts.originaltext from socialanalyticsblog.tweet_sentiments ts join socialanalyticsblog.tweets t on (ts.tweetid = t.id) where lang = \'ar\' "),
	
	SAMPLE_QUERY4("select * from socialanalyticsblog.tweet_sentiments where sentiment = \'POSITIVE\' limit 20 "),

	SAMPLE_QUERY5("select * from socialanalyticsblog.tweets where event_id=\'830b9614-fee3-40b3-a25a-cab49066a679\' limit 20 ");

	/** The sample query. */
	private String sampleQuery;

	/**
	 * Instantiates a new sample query.
	 *
	 * @param sampleQuery the sample query
	 */
	private SampleQuery(String sampleQuery) {
		this.sampleQuery = sampleQuery;
	}

	/**
	 * Stream.
	 *
	 * @return the stream
	 */
	public static Stream<SampleQuery> stream() {
		return Stream.of(SampleQuery.values());
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.sampleQuery;
	}

}
