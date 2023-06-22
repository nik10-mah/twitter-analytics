/**
 * 
 */
package com.ml.epic.ta.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import com.ml.epic.ta.service.AwsTranslateService;
import com.ml.epic.ta.utils.IConstants.AwsTransaltor;

/**
 * The Class AwsTranslateServiceImpl.
 *
 * @since Nov 5, 2018
 */
@Service("awsTranslateService")
public class AwsTranslateServiceImpl implements AwsTranslateService {

	@Autowired
	AmazonTranslate awsTranslate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ml.epic.ta.service.AwsTranslateService#getSupportedLanguages()
	 */
	@Override
	public List<String> getSupportedLanguages() {
		List<String> langCodes = new ArrayList<>(
				Arrays.asList("en","ar", "zh", "zh-TW", "cs", "fr", "de", "it", "ja", "pt", "ru", "es", "tr"));
		return langCodes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ml.epic.ta.service.AwsTranslateService#findTranslation(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@Override
	public String findTranslation(String sourceLanguage, String targetLanguage, String text) {
		return this.translate(sourceLanguage, targetLanguage, text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ml.epic.ta.service.AwsTranslateService#findTranslation(java.lang.
	 * String, java.util.List, java.lang.String)
	 */
	@Override
	public Map<String, String> findTranslation(String sourceLanguage, List<String> targetLangs, String text) {
		Map<String, String> translations = new HashMap<>();

		for (String targetLang : targetLangs) {
			String translatedText = this.translate(sourceLanguage, targetLang, text);
			translations.put(targetLang, translatedText);
		}
		return translations;
	}

	/**
	 * Translate.
	 *
	 * @param sourceLanguage
	 *            the source language
	 * @param targetLanguage
	 *            the target language
	 * @param text
	 *            the text
	 * @return the string
	 */
	private String translate(String sourceLanguage, String targetLanguage, String text) {

		sourceLanguage = sourceLanguage == null ? AwsTransaltor.DEFAULT_SOURCE_LANG : sourceLanguage;
		TranslateTextRequest request = new TranslateTextRequest().withText(text).withSourceLanguageCode(sourceLanguage)
				.withTargetLanguageCode(targetLanguage);
		TranslateTextResult result = awsTranslate.translateText(request);
		String transaltedText = result.getTranslatedText();
		return transaltedText;
	}

}
