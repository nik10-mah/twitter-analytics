/**
 * 
 */
package com.ml.epic.ta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.athena.AmazonAthena;
import com.amazonaws.services.athena.AmazonAthenaClientBuilder;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import com.ml.epic.ta.utils.IConstants.AwsAthena;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;

/**
 * The Class AwsConfig: All the AWs Configuration Resides here
 *
 * @since Nov 27, 2018
 */
@Configuration
@EnableConfigurationProperties(AwsProperties.class)
@EnableDynamoDBRepositories(basePackages = "com.ml.epic.ta.repository")
public class AwsConfig {

	@Autowired
	AwsProperties awsProperties;

	public AWSStaticCredentialsProvider credentialsProvider() {

		AWSStaticCredentialsProvider credsProvider = new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey()));

		return credsProvider;
	}

	/**
	 * Amazon Athena : creating instance to be used in aplication
	 *
	 * @return the amazon athena
	 */
	@Bean
	public AmazonAthena athenaClient() {

		AmazonAthenaClientBuilder builder = AmazonAthenaClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(credentialsProvider()).withClientConfiguration(
						new ClientConfiguration().withClientExecutionTimeout(AwsAthena.CLIENT_EXECUTION_TIMEOUT));
		return builder.build();
	}

	/**
	 * Authentication client for using AWS Cognito User and Identity pools.
	 *
	 * @return the amazon cognito identity
	 */
	@Bean
	public AWSCognitoIdentityProvider authClient() {
		AWSCognitoIdentityProviderClientBuilder builder = AWSCognitoIdentityProviderClientBuilder.standard()
				.withRegion(Regions.US_EAST_1).withCredentials(credentialsProvider());
		return builder.build();

	}

	/**
	 * Aws translate: Bean for AWS Transalte Service to be used in application
	 *
	 * @return the amazon translate
	 */
	@Bean
	public AmazonTranslate awsTranslate() {
		AmazonTranslateClientBuilder builder = AmazonTranslateClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(credentialsProvider());
		return builder.build();

	}
	
	/**
	 * Amazon dynamo DB : Bean for AWS Amazon dynamo DB Service to be used in application
	 *
	 * @return the amazon dynamo DB
	 */
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		//Connect AWS
				AmazonDynamoDB amazonDynamoDB =  AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
		                .withCredentials(credentialsProvider()).build();
				return amazonDynamoDB;
	}
}
