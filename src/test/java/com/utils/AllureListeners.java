package com.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.web73strings.test.Web73StringsBaseAutomationTest;

import io.qameta.allure.Attachment;

public class AllureListeners implements ITestListener {

	public static Logger logger = Logger.getLogger(AllureListeners.class.getName());

	/*
	 * public static String getTestMethod(ITestResult result) { return
	 * result.getMethod().getConstructorOrMethod().getName();
	 * 
	 * }
	 * 
	 * @Attachment(value = "{0}", type = "text/plain") public static String
	 * gettextlogs(String message) { return message;
	 * 
	 * }
	 * 
	 * @Attachment(value = "screenshot failed", type = "image/png") public static
	 * byte[] takesScreenshot(WebDriver driver) { return ((TakesScreenshot)
	 * driver).getScreenshotAs(OutputType.BYTES);
	 * 
	 * }
	 * 
	 * @Override public void onTestFailure(ITestResult result) {
	 * 
	 * Object dr = result.getInstance(); WebDriver driver =
	 * ((Web73StringsBaseAutomationTest) dr).getWebdriver(); try {
	 * logger.info("takes screen shot method ***************** " +
	 * getTestMethod(result));
	 * 
	 * logger.info("takes screen shot method ***************** " +
	 * takesScreenshot(driver));
	 * logger.info("takes screen shot method ***************** " +
	 * getTestMethod(result));
	 * 
	 * } catch (Exception e) {
	 * 
	 * logger.info(e.getMessage());
	 * 
	 * e.printStackTrace(); }
	 */

	public static String getTestMethod(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}

	// ✅ Attach plain text logs (like failure message)
	@Attachment(value = "{0}", type = "text/plain")
	public static String gettextlogs(String message) {
		return message;
	}

	// ✅ Attach screenshot image (required for Allure)
	@Attachment(value = "Screenshot on failure", type = "image/png")
	public static byte[] takeScreenshot(WebDriver driver) {

		try {
			logger.info("testcase screen short started ");
			logger.info("testcase screen shot ended ");

			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;

	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			logger.info("Test FAILED: " + getTestMethod(result));

			Object testInstance = result.getInstance();
			WebDriver driver = ((Web73StringsBaseAutomationTest) testInstance).getWebdriver();

			// ✅ Properly attach screenshot to Allure
			if (driver != null) {
				logger.debug("testcase failed " + "**********************" + getTestMethod(result));
				logger.info("testcase failed " + "**********************" + getTestMethod(result));

				takeScreenshot(driver);

				logger.info("testcase failed " + "**********************" + gettextlogs(getTestMethod(result)));

			}

			// ✅ Optionally attach the exception as a log
			if (result.getThrowable() != null) {
				gettextlogs(getTestMethod(result));

			}

		} catch (Exception e) {
			logger.error("Error in onTestFailure: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
