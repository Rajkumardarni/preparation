package com.web73strings.page;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v136.network.Network;
import org.openqa.selenium.devtools.v136.network.model.BlockedReason;
import org.openqa.selenium.devtools.v136.network.model.Request;
import org.openqa.selenium.devtools.v136.network.model.ResourceType;

import com.b2b.support.B2BFindBy;
import com.b2b.support.B2BPageFactory;
import com.web73strings.pages.Web73StringsBaseAutomationPage;

public class BigCommercePage extends Web73StringsBaseAutomationPage {

	@B2BFindBy(xpath = "//input[@name='username']")
	private WebElement txtUserName;

	@B2BFindBy(xpath = "//input[@name='password']")
	private WebElement txtPassword;

	@B2BFindBy(xpath = "//button[@type='submit']")
	private WebElement btnLogin;
	
	@B2BFindBy(xpath = "//p[text()='Invalid credentials']")
	private WebElement lblDashboard;
	
	@B2BFindBy(xpath = "//p[text()='Invalid credentials']")
	private WebElement lblInvalidCredentials;

	@B2BFindBy(xpath = "//input[@name='username']")
	private WebElement txtUserNameB;

	@B2BFindBy(xpath = "//input[@name='password']")
	private WebElement txtPasswordB;

	@B2BFindBy(xpath = "//input[@type='submit']")
	private WebElement btnLoginB;

	private static final Logger logger = Logger.getLogger(BigCommercePage.class.getName());

	public BigCommercePage(WebDriver driver) {
		super(driver);
		B2BPageFactory.initElements(driver, this);
	}

	public void clickOnLoginButton() {
		logger.info(" Starting of clickOnLoginButton");

		this.btnLogin.click();

		logger.info(" Ending of clickOnLoginButton");

	}

	/*
	 * public void FetchFailedAPi() {
	 * 
	 * private static final List<String> failedApis = new ArrayList<>();
	 * 
	 * public static void addFailedApi(String api) { failedApis.add(api); }
	 * 
	 * public static List<String> getFailedApis() { return new
	 * ArrayList<>(failedApis); }
	 * 
	 * public static void clear() { failedApis.clear(); }
	 */

	/*public void FetchAllPassApi() {

		DevTools devTools = ((HasDevTools) driver).getDevTools();

		// Get DevTools session
		devTools.createSession();

		// Enable Network
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// Listen to requestWillBeSent to identify the initiator
		devTools.addListener(Network.requestWillBeSent(), request -> {
			Request req = request.getRequest();
			org.openqa.selenium.devtools.v136.network.model.Initiator initiator = request.getInitiator();

			String initiatorType = initiator.getType().toString();
			String url = req.getUrl();

			System.out.println("➡️ Request URL: " + url);
			System.out.println("   Initiator Type: " + initiatorType);
		});

		// Listen to loadingFailed to capture failed responses
		devTools.addListener(Network.loadingFailed(), failedRequest -> {
			String requestId = failedRequest.getRequestId().toString();
			ResourceType resourceType = failedRequest.getType();
			String errorText = failedRequest.getErrorText();
			Optional<BlockedReason> blockedReason = failedRequest.getBlockedReason();
			// Optional: log only XHR or Fetch types (which are usually marked as 'XHR' in
			// ResourceType)
			if (resourceType.toString().equalsIgnoreCase("XHR") || resourceType.toString().equalsIgnoreCase("Fetch")) {
				System.out.println("❌ Failed API Call Detected:");
				System.out.println("   Resource Type: " + resourceType);
				System.out.println("   Request ID: " + requestId);
				System.out.println("   Error: " + errorText);
				System.out.println("   Blocked Reason: " + blockedReason);
			}
		});

	}*/

	public void setEnterUserName(String strUserName) {
		logger.info(" Starting of setEnterUserName");

		this.txtUserName.sendKeys(strUserName);

		logger.info(" Ending of setEnterUserName");

	}

	public void setEnterPassword(String strPassword) {
		logger.info(" Starting of setEnterPassword");

		this.txtPassword.sendKeys(strPassword);

		logger.info(" Ending of setEnterPassword");

	}

	public void ScreenshotForFailedAPI() throws IOException {

		/*
		 * // Setup WebDriver (ChromeDriver in this case)
		 * System.setProperty("webdriver.chrome.driver",
		 * "F:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); WebDriver
		 * driver = new ChromeDriver();
		 */

		/*
		 * // Navigate to the URL driver.get(
		 * "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		 */

		// Find the web element you want to capture
		WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));
		btnLogin.click();

		// Capture screenshot of the web element
		File screenshot = btnLogin.getScreenshotAs(OutputType.FILE);

		// Save the screenshot to a file
		File destinationFile = new File("networkscreenshot.png");
		FileUtils.copyFile(screenshot, destinationFile);

		// Close the driver
		driver.quit();
	}

	public void CaptureDevToolsScreenshot() throws Exception {

		// Step 1: Set Chrome to launch with DevTools open

		/*
		 * System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
		 * WebDriver driver = new ChromeDriver(options);
		 * 
		 * // Step 2: Navigate to your application driver.get(
		 * "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		 */

		// Step 3: Give time to load DevTools and network data
		Thread.sleep(5000); // Adjust if needed

		// Step 4: Capture full screen (including DevTools)
		Robot robot = new Robot();
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--auto-open-devtools-for-tabs");
		BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		String screenshotPath = System.getProperty("user.dir") + "/devtools_screenshot1.png";
		ImageIO.write(screenFullImage, "png", new File(screenshotPath));
		System.out.println("✅ Full desktop screenshot saved: " + screenshotPath);

		driver.quit();
	}

	public String getDashboardLabel() {
		logger.info("Starting of getDashboardLabel");
		logger.info("Ending of getDashboardLabel");

		return lblDashboard.getText();
	}

	/*
	 * public void FetchAllApi1() throws AWTException, IOException {
	 * 
	 * // Create DevTools session DevTools dev = ((HasDevTools)
	 * driver).getDevTools(); dev.createSession();
	 * 
	 * // Enable Network dev.send(Network.enable(Optional.empty(), Optional.empty(),
	 * Optional.empty()));
	 * 
	 * // Map to store request timestamps Map<RequestId, Double> requestTimeMap =
	 * new HashMap<>();
	 * 
	 * // Listen for request events dev.addListener(Network.requestWillBeSent(),
	 * request -> { String url = request.getRequest().getUrl(); String method =
	 * request.getRequest().getMethod(); RequestId requestId =
	 * request.getRequestId(); double requestTimestamp = request.getTimestamp();
	 * 
	 * // Store the timestamp for the request requestTimeMap.put(requestId,
	 * requestTimestamp);
	 * 
	 * System.out.println("URL: " + url); System.out.println("Method: " + method);
	 * });
	 * 
	 * // Listen for response events dev.addListener(Network.responseReceived(),
	 * response -> { RequestId requestId = response.getRequestId(); Response res =
	 * response.getResponse(); int statusCode = res.getStatus(); String status =
	 * (statusCode >= 200 && statusCode < 309) ? "pass" : "fail"; double
	 * responseTimestamp = response.getTimestamp();
	 * 
	 * // Calculate response time Double requestTimestamp =
	 * requestTimeMap.get(requestId); if (requestTimestamp != null) { double
	 * responseTimeInSeconds = responseTimestamp - requestTimestamp; double
	 * responseTimeInMillis = responseTimeInSeconds * 1000; // Convert to
	 * milliseconds System.out.println("Response time: " + responseTimeInMillis +
	 * " ms"); } else {
	 * System.out.println("No matching request timestamp found for response."); }
	 * 
	 * System.out.println("Response URL: " + res.getUrl());
	 * System.out.println("Status Code: " + statusCode + " (" + status + ")"); }); }
	 */

	public void setEnterUserNameB(String strUserName) {
		logger.info(" Starting of setEnterUserNameB");

		this.txtUserNameB.sendKeys(strUserName);

		logger.info(" Ending of setEnterUserNameB");

	}

	public void setEnterPasswordB(String strPassword) {
		logger.info(" Starting of setEnterPasswordB");

		this.txtPasswordB.sendKeys(strPassword);

		logger.info(" Ending of setEnterPasswordB");

	}

	public void clickOnLoginButtonB() {
		logger.info(" Starting of clickOnLoginButtonB");

		this.btnLoginB.click();

		logger.info(" Ending of clickOnLoginButtonB");

	}
	public void FetchAllApi() {
		DevTools dev = ((HasDevTools) driver).getDevTools();
        dev.createSession();
        dev.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        dev.addListener(Network.requestWillBeSent(), request -> {

            System.out.println("URL***************************************" + request.getRequest().getUrl());

            System.out.println("method type****************************** " + request.getRequest().getMethod());
        });

        // Capture all network responses
        dev.addListener(Network.responseReceived(), response -> {
            System.out.println("AllNetwork response*************"+response.getResponse().getUrl());

            int statusCode = response.getResponse().getStatus();
  		  String status =(statusCode >= 200 && statusCode < 309) ? "pass" : "fail";

            System.out.println("status code ************************************" + statusCode);
        });
}}