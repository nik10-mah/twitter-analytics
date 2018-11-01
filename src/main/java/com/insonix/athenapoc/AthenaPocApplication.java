package com.insonix.athenapoc;

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
import com.insonix.athenapoc.config.AwsProperties;
import com.insonix.athenapoc.utils.IConstants.AwsAthena;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * The Main Configuration Class of AthenaPocApplication.
 * 
 */
@SpringBootApplication
@EnableConfigurationProperties(AwsProperties.class)
public class AthenaPocApplication {

	/** The aws properties. */
	@Autowired
	AwsProperties awsProperties;

	/**
	 * The main method: The entry point of the application
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AthenaPocApplication.class, args);
	}

	/**
	 * Amazon Athena : creating instance to be used in aplication
	 *
	 * @return the amazon athena
	 */
	@Bean
	public AmazonAthena athenaClient() {
		AWSStaticCredentialsProvider credsProvider = new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey()));

		AmazonAthenaClientBuilder builder = AmazonAthenaClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(credsProvider).withClientConfiguration(
						new ClientConfiguration().withClientExecutionTimeout(AwsAthena.CLIENT_EXECUTION_TIMEOUT));
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
