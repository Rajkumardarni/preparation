package com.b2b.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.b2b.test.base.B2BBaseAutomationTest;

public class AutomationUtil extends B2BBaseAutomationTest {
	private static final Logger logger = Logger.getLogger(AutomationUtil.class.getName());

	public static void main(String[] args) {

		String TEST_DATA_PROPERTIES = "testdata_en.properties";
		String EXPECTED_ASS_PROPERTIES = "expectedassertions_en.properties";
		String LANG_ASS_PROPERTIES = "xpaths_en.properties";

		logger.debug("French " + getLanguageFilePath(TEST_DATA_PROPERTIES, "fr"));
		logger.debug("French " + getLanguageFilePath(EXPECTED_ASS_PROPERTIES, "fr"));

		TEST_DATA_PROPERTIES = "testdata.properties";
		EXPECTED_ASS_PROPERTIES = "expectedassertions.properties";

		logger.debug("Default " + getLanguageFilePath(TEST_DATA_PROPERTIES, "en"));
		logger.debug("French " + getLanguageFilePath(EXPECTED_ASS_PROPERTIES, "en"));

		String langxPathFile = getLanguageFilePath(LANG_ASS_PROPERTIES, "en");
		FileReader langxPathReader;

		try {

			langxPathReader = new FileReader(langxPathFile);
			Properties langXPathsProp = new Properties();
			langXPathsProp.load(langxPathReader);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getLanguageFilePath(String fileName, String lang) {

		if (lang == null) {
			lang = "en";
		}

		if (fileName.contains("_")) {
			fileName = fileName.split("_")[0] + "_" + lang + ".properties";
		}

		logger.debug(
				"+++++++++++++++++++++++++++++" + BASE_DIR + "/" + "src/main/resources" + "/" + lang + "/" + fileName);

		return BASE_DIR + "/" + B2BConstants.BASE_PATH + lang + "/" + fileName;
	}
}
