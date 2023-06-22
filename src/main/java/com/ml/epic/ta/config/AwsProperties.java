/**
 * 
 */
package com.ml.epic.ta.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * 
 * @since Oct 27, 2018
 */
@ConfigurationProperties(prefix = "aws.config")
public class AwsProperties {

	private String accessKey;
	private String secretKey;

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AwsProperties [accessKey=");
		builder.append(accessKey);
		builder.append(", secretKey=");
		builder.append(secretKey);
		builder.append("]");
		return builder.toString();
	}

	
	
}
