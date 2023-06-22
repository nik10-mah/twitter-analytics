package com.ml.epic.ta.utils;

//import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;

public class CloudWatchLogging {
	//@Autowired
	//AmazonCloudWatchClient amazonCloudWatchClient;

	static void info(String message, Object className) {
		/*final Context mockContext = mock(Context.class);

	    final PutLogEventsResult mockResult = mock(PutLogEventsResult.class);
	    when(mockResult.getNextSequenceToken()).thenReturn("2");


	    final CloudWatchAppender appender = new CloudWatchAppender();
	    appender.setContext(mockContext);
	    appender.setAwsLogs(mockAwsLogs);
	    appender.start();
	    appender.doAppend(new LoggingEvent());
	    appender.stop();*/
	}
	
	/* final AWSLogs awsLogs = mock(AWSLogs.class);
	    final String logGroupName = "myGroup";
	    final String logStreamName = "myStream";

	    final CloudWatchAppender appender = new CloudWatchAppender();
	    appender.setLayout(layout);
	    appender.setAwsLogs(awsLogs);
	    appender.setLogGroupName(logGroupName);
	    appender.setLogStreamName(logStreamName);*/
	
	static void error() {
		
	}
	
	static void debug() {
		
	}
	

	static void warning() {
	
	}

	
}
