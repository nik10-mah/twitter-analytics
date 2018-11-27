/**
 * 
 */
package com.ml.epic.ta;

import static org.assertj.core.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;

/**
 * The Class AwsCognitoTests. Ref:-
 * https://gorillalogic.com/blog/java-integration-with-amazon-cognito/
 * https://docs.aws.amazon.com/cognito/latest/developerguide/amazon-cognito-user-pools-using-tokens-verifying-a-jwt.html
 * @since Nov 23, 2018
 */
public class AwsCognitoTests extends BaseTest {

	@Autowired
	AWSCognitoIdentityProvider authClient;

	@Test
	public void authTest() {
		try {

			final Map<String, String> authParams = new HashMap<>();
			authParams.put("USERNAME", "nik10.mah@gmail.com");
			authParams.put("PASSWORD", "1qaz!QAZ");
			
//			authParams.put("USERNAME", "abby@vradars.com");
//			authParams.put("PASSWORD", "Abby@2018");
			

			final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
			authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withClientId("3045rrdm5kjp5dh3qt85m9tkuu")
					.withUserPoolId("us-east-1_tZBScdml6").withAuthParameters(authParams);

			AdminInitiateAuthResult result = authClient.adminInitiateAuth(authRequest);
			System.out.println(" Challenge " + result.getChallengeName());
			System.out.println(" Result " + result.getAuthenticationResult());

		} catch (Exception e) {
			System.out.println("============" + e.getMessage());
			fail(e.getMessage());
			e.printStackTrace();
		}

	}

}
