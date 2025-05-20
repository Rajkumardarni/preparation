package com.web73strings.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.b2b.base.B2BBaseAutomationPage;
import com.b2b.support.B2BPageFactory;

public class Web73StringsBaseAutomationPage extends B2BBaseAutomationPage {
	private static final Logger log = LogManager.getLogger(Web73StringsBaseAutomationPage.class);

	public static String TEST_FILE_PATH = null;
	private static int currentyear = 0;
	static int Year = 0;

	public Web73StringsBaseAutomationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		log.info("Starting of DUPRBaseAutomationPage");
		if (TEST_FILE_PATH == null) {
			TEST_FILE_PATH = getTestFilePath();

			log.debug("In Constructor " + TEST_FILE_PATH);
		}
		B2BPageFactory.initElements(driver, this);
		log.info("Ending of DUPRBaseAutomationPage");
	}

	public String randomNumber(int digits) {
		log.info("Starting of randomNumber method");
		log.info("Ending of randomNumber method");

		return String.valueOf(RandomStringUtils.randomNumeric(digits));
	}

	public String randomAlphabet(int noOfAlphabets) {
		log.info("Starting of randomAlphabet method");
		log.info("Ending of randomAlphabet method");

		return String.valueOf(RandomStringUtils.randomAlphabetic(noOfAlphabets));
	}

	public static String getRandomString(final int length) {
		final StringBuilder buffer = new StringBuilder();
		final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final int charactersLength = chars.length();

		for (int i = 0; i < length; i++) {
			final double index = Math.random() * charactersLength;
			buffer.append(chars.charAt((int) index));
		}
		return buffer.toString();
	}

	public static double convertDouble(String value) {
		return NumberUtils.toDouble(value);
	}

	public static int convertInteger(String value) {
		return NumberUtils.toInt(value);
	}

	public static boolean convertBoolean(String value) {
		return Boolean.parseBoolean(value);
	}

	public static String getRandomName() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMM");
		return "" + dtf.format(java.time.LocalDateTime.now()) + "_" + getRandomString(8);
	}

	public static String getNumberFromString(String msg) {
		return msg.replaceAll("[^0-9]", "");
	}

	public void clickAndDragSliderNode(WebElement webElement) {
		log.info("starting of clickAndDragSliderNode");

		this.implicitWait();
		Actions action = new Actions(driver);
		action.clickAndHold(webElement);
		action.moveByOffset(100, 0).build().perform();

		log.info("Ending of clickAndDragSliderNode");
	}

	public void elementClick(WebElement webElement) {
		log.info("Starting of elementClick method");

		try {
			clickOnWebElement(webElement);
		} catch (Exception e) {
			try {
				clickOnElement(webElement);
			} catch (Exception e2) {
				webElement.click();
			}
		}

		log.info("Ending of elementClick method");
	}

	public void clickOnElementUsingActionClass(WebElement WebElement) {
		log.info("Starting of clickOnElementUsingActionClass method");

		Actions action = new Actions(driver);
		action.moveToElement(WebElement).click().build().perform();

		log.info("Ending of clickOnElementUsingActionClass method");
	}

	public void mouseHoverAndClick(WebElement webElement) {
		log.info("Starting of mouseHoverAndClick method");

		Actions action = new Actions(driver);
		action.moveToElement(webElement).click().build().perform();

		log.info("Ending of mouseHoverAndClick method");
	}

	public void clickUsingActionsClass(WebElement webElement) {
		log.info("Starting of clickUsingActionsClass method");

		try {
			clickOnElementUsingActionClass(webElement);
		} catch (Exception e) {
			webElement.click();
		}

		log.info("Ending of clickUsingActionsClass method");
	}

	public int getCurrentDate() {
		log.info("Starting of getCurrentDate method");

		LocalDateTime dateTime = LocalDateTime.now();
		int date = dateTime.getDayOfMonth();

		log.info("Ending of getCurrentDate method");

		return date;
	}

	public int getFutureDate(int days) {
		log.info("Starting of getFutureDate method");

		LocalDateTime dateTime = LocalDateTime.now();
		LocalDateTime date = dateTime.plusDays(days);
		int dateValue = date.getDayOfMonth();

		log.info("Ending of getFutureDate method");

		return dateValue;
	}

	public String getCurrentHour() {
		log.info("Starting of getCurrentHour method");

		SimpleDateFormat simpleformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a z");

		simpleformat = new SimpleDateFormat("h");
		String strHour = simpleformat.format(new Date());

		log.info("Ending of getCurrentHour method");
		return strHour;
	}

	public String getCurrentMinute() {
		log.info("Starting of getCurrentMinute method");

		SimpleDateFormat simpleformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a z");

		simpleformat = new SimpleDateFormat("m");
		String strMinutes = simpleformat.format(new Date());

		log.info("Ending of getCurrentMinute method");
		return strMinutes;
	}

	public String getCurrentMeridiem() {
		log.info("Starting of getCurrentMeridiem method");

		SimpleDateFormat simpleformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a z");

		simpleformat = new SimpleDateFormat("a");
		String meridiem = simpleformat.format(new Date());
		String meridiemValue = meridiem.toUpperCase();

		log.info("Ending of getCurrentMeridiem method");
		return meridiemValue;
	}

	public String getCurrentZone() {
		log.info("Starting of getCurrentZone method");

		SimpleDateFormat simpleformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a z");

		simpleformat = new SimpleDateFormat("z");
		String zone = simpleformat.format(new Date());

		log.info("Ending of getCurrentZone method");
		return zone;
	}

	public void clickOnCurrentDate(int date) {
		log.info("Starting of clickOnCurrentDate method");
		hardWait(3);
		try {
			System.out.println("*** Current Date: //button[text()='" + date + "']***");
			clickUsingActionsClass(driver.findElement(By.xpath("//button[text()='" + date + "']")));

		} catch (Exception e) {
			System.out.println("*** Current Date: //button[text()='" + date + "']***");
			clickOnWebElement(driver.findElement(By.xpath("//button[text()='" + date + "']")));
		}

		log.info("Ending of clickOnCurrentDate method");
	}

	public void clickOnCurrentTime(String strHour) {
		log.info("Starting of clickOnCurrentTime method");
		hardWait(2);
		try {
			System.out.println("*** Current Time/Meridiem: //span[contains(text(),'" + strHour + "')]***");
			clickUsingActionsClass(driver.findElement(By.xpath("//span[contains(text(),'" + strHour + "')]")));
		} catch (Exception e) {
			System.out.println("*** Current Time/Meridiem: //span[contains(text(),'" + strHour + "')]***");
			clickOnWebElement(driver.findElement(By.xpath("//span[contains(text(),'" + strHour + "')]")));
		}

		log.info("Ending of clickOnCurrentTime method");
	}

	public void switchToNewTab() {
		log.info("Starting of switchToNewTab method");

		// To Open new window and check Assertions
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		if (tab.size() > 0) {
			driver.switchTo().window(tab.get(1));
		}

		log.info("Ending of switchToNewTab method");
	}

	public void closeTab() {
		log.info("Starting of closeTab method");

		// Recent window will be closed and returns back
		driver.close();
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));

		log.info("Ending of closeTab method");
	}

	public void verticalScroll(WebElement webElement) {
		log.info("Starting of verticalScroll method");

		Actions action = new Actions(driver);
		Actions moveToElement = action.moveToElement(webElement);
		for (int i = 0; i < 5; i++) {

			moveToElement.sendKeys(Keys.DOWN).build().perform();
		}

		log.info("Ending of verticalScroll method");
	}

	public Month getFutureMonth(int days) {
		log.info("Starting of getFutureMonth method");

		LocalDateTime dateTime = LocalDateTime.now();
		LocalDateTime date = dateTime.plusDays(days);
		Month monthValue = date.getMonth();
		System.out.println("Month Value = " + monthValue);

		log.info("Ending of getFutureMonth method");

		return monthValue;
	}

	public Month getPreviousMonth(int days) {
		log.info("Starting of getPreviousMonth method");

		LocalDateTime dateTime = LocalDateTime.now();
		LocalDateTime date = dateTime.minusDays(days);
		Month monthValue = date.getMonth();
		System.out.println("Month Value = " + monthValue);

		log.info("Starting of getPreviousMonth method");

		return monthValue;
	}

	public static WebElement getElementIfVisible(WebDriver driver, WebElement element) {
		log.info("Starting of getElementIfVisible method");

		try {
			element = (new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOf(element)));
		} catch (StaleElementReferenceException se) {
			try {
				element = new WebDriverWait(driver, Duration.ofSeconds(20))
						.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
			} catch (Exception e) {
				log.error("Element unavailable\n" + se.getMessage());

			}
		} catch (Exception e) {
			log.error("Element unavailable\n" + e.getMessage());

		}

		log.info("Ending of getElementIfVisible method");
		return element;
	}

	public Month getCurrentMonth() {
		log.info("Starting of getCurrentMonth method");

		LocalDateTime dateTime = LocalDateTime.now();
		Month monthValue = dateTime.getMonth();
		System.out.println("Month Value = " + monthValue);

		log.info("Starting of getCurrentMonth method");

		return monthValue;
	}

	public int getCurrentYear() {
		log.info("Starting of getCurrentYear method");

		LocalDateTime dateTime = LocalDateTime.now();
		currentyear = dateTime.getYear();

		log.info("Ending of getCurrentYear method");

		return currentyear;
	}

	public int getPreviousYear(int year) {
		log.info("Starting of getPreviousYear method");

		LocalDateTime dateTime = LocalDateTime.now();
		LocalDateTime yr = dateTime.minusYears(year);
		Year = yr.getYear();
		System.out.println("Year Value = " + Year);

		log.info("Ending of getPreviousYear method");

		return Year;
	}

	public void clickOnSelectedYear(int year) {
		log.info("Starting of clickOnSelectedYear method");

		int requiredYearValue = this.getPreviousYear(year);
		System.out.println(requiredYearValue);
		this.hardWait(3);
		try {
			clickUsingActionsClass(
					driver.findElement(By.xpath("//button[contains(text(),'" + requiredYearValue + "')]")));
		} catch (Exception e) {
			clickOnWebElement(driver.findElement(By.xpath("//button[contains(text(),'" + requiredYearValue + "')]")));
		}

		log.info("Ending of clickOnSelectedYear method");
	}

	public void backspace(WebElement ele) {
		ele.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);

	}

	public void clickUsingJavaScript(WebElement Element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Element);

	}

	public void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}

	public void clickOnEnter(WebElement element) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	

}
