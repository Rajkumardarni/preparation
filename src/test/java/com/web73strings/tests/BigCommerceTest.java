package com.web73strings.tests;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.b2b.common.WebDriversEnum;
import com.b2b.utils.B2BBaseTestListener;
import com.utils.AllureListeners;
import com.web73strings.page.BigCommercePage;
import com.web73strings.test.Web73StringsBaseAutomationTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({AllureListeners.class})
public class BigCommerceTest extends Web73StringsBaseAutomationTest {

	private BigCommercePage bigCommercePage = null;
	private WebDriver driver = null;

	private static final Logger logger = Logger.getLogger(BigCommerceTest.class.getName());

	@BeforeClass
	@Parameters({ "siteURL", "browser" })
	public void initClass(String siteURL, String browser) throws Exception {
		logger.info("Starting of initClass method");

		this.driver = this.getWebDrivers("windows", "chrome", WebDriversEnum.BIG_COMMERCE_TEST);
		this.bigCommercePage = new BigCommercePage(this.driver);

		driver.get(siteURL);

		logger.info("Starting of initClass method");

	}

	@Test(priority=1)
	@Description("Test Case #1,Verify UserName")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Test Case #1,Verify UserName")
	public void testLoginWihValidEmailAndPassword() {
		logger.info("Starting of testLoginWihValidEmailAndPassword Method");

		try {
			Thread.sleep(5000);
			logger.debug("");
			bigCommercePage.setEnterUserName(testDataProp.getProperty("text.user.name"));
			bigCommercePage.setEnterPassword(testDataProp.getProperty("text.password"));

		} catch (Exception e) {
			Assert.fail();
			logger.error(e.getMessage() + e.getStackTrace());
		}

		logger.info("Ending of testLoginWihValidEmailAndPassword Method");
	}
	@Test(priority=2)
	@Description("Test Case #2,Verify Login")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Test Case #2,Verify Login")
	public void testLogin() {
		logger.info("Starting of testLoginWihValidEmailAndPassword Method");

		try {
			Thread.sleep(5000);
			logger.debug("Starting of testLogin Method");
			bigCommercePage.clickOnLoginButton();
			Thread.sleep(3000);
			//bigCommercePage.FetchAllApi();
			//Web73StringsBaseAutomationTest.getTakescreenshot();
			Thread.sleep(3000);

			String dashboard = this.bigCommercePage.getDashboardLabel();
			assertEquals(dashboard, expectedAssertionsProp.getProperty("label.dashboard"));

			// bigCommercePage.CaptureDevToolsScreenshot();
			// assertTrue(loginPage.isExcelliLogo());

		} catch (Exception e) {
			Assert.fail();
			logger.error(e.getMessage() + e.getStackTrace());
		}

		logger.info("Ending of testLogin Method");
	}

	@AfterClass
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		driver.quit();

		logger.info("Ending of quitDriver method");
	}
}
