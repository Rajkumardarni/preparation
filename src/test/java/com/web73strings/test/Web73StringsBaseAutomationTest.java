package com.web73strings.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.b2b.common.WebDriversEnum;
import com.b2b.test.base.B2BBaseAutomationTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Web73StringsBaseAutomationTest extends B2BBaseAutomationTest {

	protected static WebDriver driver = null;
	
	protected static String eventName = null;
	private static final Logger log = Logger.getLogger(Web73StringsBaseAutomationTest.class);

	@BeforeSuite
	@Parameters({ "siteURL", "language", "browser", "headless" })
	public void initAutomation(String siteURL, String language, String browser, boolean headless) {
		this.loadProjectConfigFiles();
		this.initTestAutomation(siteURL, language, browser, headless, false);

		log.debug("Site URL :{} " + loginURL);
		
	}

	protected synchronized WebDriver getDriver(String browser, WebDriversEnum webDriver) {
		log.info("Starting of method getDriver");
 
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--incognito");
			System. setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"F:\\ChromeVersions\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			options.addArguments(new String[]{"enable-automation"});
		       // options.addArguments(new String[]{"--headless"});
		        options.addArguments(new String[]{"--no-sandbox"});
		         options.addArguments(new String[]{"--disable-extensions"});
		        options.addArguments(new String[]{"--dns-prefetch-disable"});
		        options.addArguments(new String[]{"--disable-notifications"});
		        options.addArguments(new String[]{"--disable-gpu"});
		        //options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		        options.addArguments("--remote-allow-origins=*");
		        //options.setHeadless(this.isHeadLess);
		       // driver = new ChromeDriver(options);
			
			driver = new ChromeDriver(options);
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		log.info("***************** Driver Successfully Created **************** " + driver.getTitle());
		log.info("End of method getDriver");
		return driver;
	}
	@Parameters({"osPath" ,"browser"})
	protected synchronized WebDriver getWebDrivers(String osPath,String browser, WebDriversEnum webDriver) {
		log.info("Starting of method getWebDriver");
		if (osPath.contains("Linux")) {
			if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				//firefoxOptions.setHeadless(true);
				// chromeOptions.addArguments("--no-sandbox");
				firefoxOptions.addArguments("allow-file-access-from-files");
				firefoxOptions.addArguments("use-fake-device-for-media-stream");
				firefoxOptions.addArguments("use-fake-ui-for-media-stream");
				driver = new FirefoxDriver(firefoxOptions);
			} else if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				//chromeOptions.setHeadless(true);
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("allow-file-access-from-files");
				chromeOptions.addArguments("use-fake-device-for-media-stream");
				chromeOptions.addArguments("use-fake-ui-for-media-stream");
				driver = new ChromeDriver(chromeOptions);
			}
		} else if (osPath.contains("Mac OS X")) {
			browserDriverPath = "/usr/bin/chromedriver";
			if (browserDriverPath.contains("safaridriver")) {
				System.setProperty("webdriver.safari.driver", browserDriverPath);
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("allow-file-access-from-files");
				chromeOptions.addArguments("use-default-device-for-media-stream");
				chromeOptions.addArguments("use-default-ui-for-media-stream");
				driver = new ChromeDriver(chromeOptions);
				log.debug("Safari driver path " + browserDriverPath);
			} else if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
		} else if (osPath.contains("windows")) {
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				//chromeOptions.setHeadless(false);
				chromeOptions.addArguments("allow-file-access-from-files");
				chromeOptions.addArguments("use-fake-device-for-media-stream");
				chromeOptions.addArguments("use-fake-ui-for-media-stream");
				driver = new ChromeDriver(chromeOptions);

			} else if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Chromium")) {
				WebDriverManager.chromiumdriver().setup();
				driver = new EdgeDriver();
			} else if (browser.equalsIgnoreCase("IEDriverServer")) {
				WebDriverManager.iedriver().browserVersion("122").setup();
				driver = new InternetExplorerDriver();
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		log.info("********** Driver Successfully Created ********* " + driver.getTitle());
		log.info("End of method getWebDriver");

		return driver;
	}
	
	
	public WebDriver getWebdriver() {
		return driver;
		
		
		
	}
	
	public static void getTakescreenshot() throws IOException {

		String s = ".//src//main//resources//text.png";
		File f = new File(s);

		TakesScreenshot ts = (TakesScreenshot) driver;
		File fs=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fs, f);
		
		
		

	}
}
