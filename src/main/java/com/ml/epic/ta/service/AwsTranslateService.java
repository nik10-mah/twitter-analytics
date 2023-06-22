/**
 * 
 */
package com.ml.epic.ta.service;

import java.util.List;
import java.util.Map;

/**
 * AWS Translate Service for Transalting text inot different languages AWS
 * Suports Few langueages Ref:-
 * https://docs.aws.amazon.com/translate/latest/dg/API_TranslateText.html
 * Aws Java Code Sample Ref:- https://docs.aws.amazon.com/translate/latest/dg/examples-java.html
 * @since Nov 5, 2018
 */
public interface AwsTranslateService {

	/**
	 * Gets the list supported languages. by AWS Transalte
	 *
	 * @return the supported languages
	 */
	List<String> getSupportedLanguages();

	/**
	 * Find translation of give text inot target langauges
	 *
	 * @param sourceLanguage the source language. Can be null. Wil use en in case of null
	 * @param targetLanguage the target language
	 * @param text the text to be translated and Length should not more than 5000
	 * @return the translated text
	 */
	String findTranslation(String sourceLanguage, String targetLanguage, String text);

	/**
	 * Find translation.
	 *
	 * @param sourceLanguage the source language. Can be null. Wil use en in case of null
	 * @param targetLangs the target langs: List target langs in which text is to be converted. Must have one valid lang code in list
	 * @param text the text to be translated in various langs. Length should not more than 5000
	 * @return the map<string,string> key =lang code, value =transalted text in that language code 
	 */
	Map<String, String> findTranslation(String sourceLanguage, List<String> targetLangs, String text);

}
