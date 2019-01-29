package com.ml.epic.ta;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.athena.AmazonAthena;
import com.amazonaws.services.athena.model.CreateNamedQueryRequest;
import com.amazonaws.services.athena.model.CreateNamedQueryResult;
import com.amazonaws.services.athena.model.ListNamedQueriesRequest;
import com.amazonaws.services.athena.model.ListNamedQueriesResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.ml.epic.ta.config.AwsProperties;

public class BucketTest extends BaseTest{
	@Autowired
	AwsProperties awsProperties;
	
	@Autowired
	AmazonAthena athenaClient;
	
	AmazonS3 s3Client = null;
	
    String bucketName = "socialmediaanalyticsblogpost-tweetsbucket";

	
	public BucketTest(){
		System.out.println("lll");
		/*this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
        				new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey())))
                .withRegion(Regions.US_EAST_1)
                .build();*/
	}
	//@Test
	public void displayData() {
	        
	        try {
	        	
	        	this.s3Client = AmazonS3ClientBuilder.standard()
	                    .withCredentials(new AWSStaticCredentialsProvider(
	            				new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey())))
	                    .withRegion(Regions.US_EAST_1)
	                    .build();
	            ObjectListing objectListing = s3Client.listObjects(bucketName);
	            while(true) {
	            Iterator<S3ObjectSummary> objIter = objectListing.getObjectSummaries().iterator();
                while (objIter.hasNext()) {
                    //s3Client.deleteObject(bucketName, objIter.next().getKey());
                	System.out.println(objIter.next());
                }
	            }
    
                // If the bucket contains many objects, the listObjects() call
                // might not return all of the objects in the first listing. Check to
                // see whether the listing was truncated. If so, retrieve the next page of objects 
                // and delete them.
                /*if (objectListing.isTruncated()) {
                    objectListing = s3Client.listNextBatchOfObjects(objectListing);
                } else {
                    break;
                }*/

	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	}
	
	//@Test
	public void namedQuery() {
		/*  AmazonAthenaClientBuilder builder = AmazonAthenaClientBuilder.standard()
		          .withRegion(Regions.US_EAST_1)
		          .withCredentials(InstanceProfileCredentialsProvider.getInstance())
		          .withClientConfiguration(new ClientConfiguration());*/
		//AthenaClientFactory factory = new AthenaClientFactory();
	     // AmazonAthena client = factory.createClient();
		 CreateNamedQueryRequest createNamedQueryRequest = new CreateNamedQueryRequest()
	              .withDatabase("socialanalyticsblog")
	              .withQueryString("select lang, count(*) cnt from socialanalyticsblog.tweets  where event_id='830b9614-fee3-40b3-a25a-cab49066a679' group by lang order by cnt desc ")
	              .withDescription("Sample Description")
	              .withName("SampleQuery");

	      // Call Athena to create the named query. If it fails, an exception is thrown.
	      CreateNamedQueryResult createNamedQueryResult = athenaClient.createNamedQuery(createNamedQueryRequest);
	      System.out.println(createNamedQueryResult);
	}
	
	//@Test
	public void listNamedQueries() {
		// Build the request
        ListNamedQueriesRequest listNamedQueriesRequest = new ListNamedQueriesRequest();

        // Get the list results.
        ListNamedQueriesResult listNamedQueriesResult = athenaClient.listNamedQueries(listNamedQueriesRequest);

        // Process the results.
        boolean hasMoreResults = true;

        while (hasMoreResults) {
            List<String> namedQueryIds = listNamedQueriesResult.getNamedQueryIds();
            // process named query IDs

            // If nextToken is not null,  there are more results. Get the next page of results.
            if (listNamedQueriesResult.getNextToken() != null) {
                listNamedQueriesResult = athenaClient.listNamedQueries(
                        listNamedQueriesRequest.withNextToken(listNamedQueriesResult.getNextToken()));
            }
            else {
                hasMoreResults = false;
            }
            for(String str: namedQueryIds)
            	System.out.println(str);
        }
	}
	
	//@Test
	public  void createFolder() {
	    // create meta-data for your folder and set content-length to 0
	    ObjectMetadata metadata = new ObjectMetadata();
	    metadata.setContentLength(0);
	    this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
        				new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey())))
                .withRegion(Regions.US_EAST_1)
                .build();
	    //bucketName = this.bucketName;
	    String folderName = "raw/Demo1/Demo4";
	    // create empty content
	    InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

	    // create a PutObjectRequest passing the folder name suffixed by /
	    PutObjectRequest putObjectRequest = new PutObjectRequest(this.bucketName,
	                folderName , emptyContent, metadata);

	    // send request to S3 to create folder
	    this.s3Client.putObject(putObjectRequest);
	}
	
	//@Test
	public void deleteFileInFolder()  throws AmazonClientException, AmazonServiceException {
		this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
        				new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey())))
                .withRegion(Regions.US_EAST_1)
                .build();
		this.s3Client.deleteObject(this.bucketName,"raw/Demo1/Demo2");
		
	}
	
	@Test
	public void deleteObjectsInFolder () {
		this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
        				new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey())))
                .withRegion(Regions.US_EAST_1)
                .build();
		   for (S3ObjectSummary file : this.s3Client.listObjects(bucketName, "raw/Demo1").getObjectSummaries()){
			   this.s3Client.deleteObject(bucketName, file.getKey());
		    }
		}
}
