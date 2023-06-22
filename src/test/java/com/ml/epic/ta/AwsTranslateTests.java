/**
 * 
 */
package com.ml.epic.ta;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ml.epic.ta.service.AwsTranslateService;

/**
 * Tes cases for Aws Transales Tests
 * 
 * @since Nov 5, 2018
 */
public class AwsTranslateTests extends BaseTest {


	@Autowired
	AwsTranslateService awsTranslateService;

	@Test
	public void translateText() {

		String transaltedText = awsTranslateService.findTranslation(null, "es", "Hello, World");
		System.out.println("Translated Text " + transaltedText);
		assertEquals("Hola, Mundo", transaltedText);
	}

	@Test
	public void multipleLangs() {
		List<String> langCodes = new ArrayList<String>(Arrays.asList("es", "ar", "fr", "cs", "tr"));

		Map<String, String> transalations = awsTranslateService.findTranslation(null, langCodes, "Hello World");

		for (Map.Entry<String, String> entry : transalations.entrySet()) {

			System.out.println(entry);
			
		}
	}

}
