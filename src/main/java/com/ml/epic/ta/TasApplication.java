package com.ml.epic.ta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.athena.AmazonAthena;
import com.amazonaws.services.athena.AmazonAthenaClientBuilder;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import com.ml.epic.ta.config.AwsProperties;
import com.ml.epic.ta.utils.IConstants.AwsAthena;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * The Main Configuration Class of AthenaPocApplication.
 * 
 */
@SpringBootApplication
@EnableConfigurationProperties(AwsProperties.class)
public class TasApplication {

	/** The aws properties. */
	@Autowired
	AwsProperties awsProperties;

	/**
	 * The main method: The entry point of the application
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(TasApplication.class, args);
	}

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
	 * Layout dialect config for Thymeleaf
	 *
	 * @return the layout dialect
	 */
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

}
