/**
 * 
 */
package com.insonix.athenapoc.utils;

/**
 * @author Nikhil Mahajan
 * 
 * @since Oct 27, 2018
 */
public interface IConstants {

	public interface AwsAthena {
		public static final String ATHENA_OUTPUT_BUCKET = "s3://aws-athena-query-results-378389876301-us-east-1";
		public static final Integer CLIENT_EXECUTION_TIMEOUT = 10000;
		public static final Integer SLEEP_AMOUNT_IN_MS = 10000;
//		public static final String ATHENA_SAMPLE_QUERY = "SELECT elb_name, count(1) FROM elb_logs_orc Where elb_response_code = '200' GROUP BY elb_name ORDER BY 2 DESC limit 10";
//		public static final String ATHENA_DEFAULT_DATABASE = "sampledb";
		
		public static final String ATHENA_SAMPLE_QUERY = "select * from socialanalyticsblog.tweet_sentiments where sentiment = 'POSITIVE' limit 20";
		public static final String ATHENA_DEFAULT_DATABASE = "socialanalyticsblog";
		
		
		
	}
}
