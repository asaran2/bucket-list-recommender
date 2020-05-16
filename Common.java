package bucketList.tests;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * Define Common Webdriver
 * 
 * @author Dhruchita Patel
 */
public class Common {

	Date date = new Date();
	protected Wait<WebDriver> wait;
	protected WebDriver driver;

	public Common(WebDriver driver) {

		this.driver = driver;
	}

	/**
	 * Accept Alert
	 * 
	 */
	public void acceptAlert() {

		pause(4);
		driver.switchTo().alert().accept();

		//Alert alert = driver.switchTo().alert();
		//alert.accept();
	}

	public void dismissAlert() {

		pause(4);
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String acceptAlert1() {

		pause(4);
		Alert alert = driver.switchTo().alert();
		String alerttext = alert.getText();

		alert.accept();
		return alerttext;
	}

	/**
	 * Takes and adds screenshot to TestNG report.
	 * 
	 * @param driver
	 * WebDriver instance.
	 */
	public void makeScreenshot(WebDriver driver, String screenshotName) {

		WebDriver augmentedDriver = new Augmenter().augment(driver);

		/* Take a screenshot */
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		String nameWithExtention = screenshotName + ".png";

		/* Copy screenshot to specific folder */
		try {
			String reportFolder = "target" + File.separator + "failsafe-reports" + File.separator + "firefox"
					+ File.separator;
			String screenshotsFolder = "screenshots";
			File screenshotFolder = new File(reportFolder + screenshotsFolder);
			if (!screenshotFolder.getAbsoluteFile().exists()) {
				screenshotFolder.mkdir();
			}
			FileUtils.copyFile(screenshot,
					new File(screenshotFolder + File.separator + nameWithExtention).getAbsoluteFile());
		} catch (IOException e) {
			this.log("Failed to capture screenshot: " + e.getMessage());
		}
		log(getScreenshotLink(nameWithExtention, nameWithExtention)); // add screenshot link to the report
																		
	}

	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg
	 *            Message/Log to be reported.
	 */
	public void log(String msg) {
		Reporter.log(msg);

	}

	/**
	 * Generates link for TestNG report.
	 * 
	 * @param screenshot_name
	 *            Screenshot name.
	 * @param link_text
	 *            Link text.
	 * @return Formatted link for TestNG report.
	 */
	public String getScreenshotLink(String screenshot_name, String link_text) {

		log("<br><Strong><font color=#FF0000>--Failed</font></strong>");
		return "<a href='../target/failsafe-reports/firefox/screenshots/" + screenshot_name + "'>" + link_text + "</a>";
	}


	/**
	 * Generates random symbols;
	 * 
	 * @param length
	 *            Length of the generated symbols.
	 * 
	 * @return StringBuffer object.
	 */
	public static String generateRandomChars(int length) {
		String total = "iokijfmnbxcvfrpqsdfgvcxzdferiuytjndifur";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			char _char = total.charAt((int) (Math.random() * 100) % total.length());
			buf.append(_char);
		}
		return buf.toString();
	}


	/**
	 * Pauses for given seconds.
	 * 
	 * @param secs
	 */
	public void pause(int secs) {
		try {
			Thread.sleep(secs * 200);
		} catch (InterruptedException interruptedException) {

		}
	}

	/**
	 * Get System OS
	 * 
	 * @return
	 */
	public String getOS() {

		String OS = System.getProperty("os.name").toLowerCase();
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("System OS :: " + OS);
		log("<br></br>-----------------------------------------------------------------------------------");
		log("<br></br>System OS :: " + OS);
		System.out.println("-----------------------------------------------------------------------------------");
		return OS;
	}

	public void jsClick(String string) {
		// TODO Auto-generated method stub

	}

}
