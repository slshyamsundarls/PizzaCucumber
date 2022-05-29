package org.steps;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class BaseClass {

	// webdriver object declaration
	public static WebDriver driver;
	public static WebElement findElement;
	public static WebElement destination;
	public static WebElement source;
	public static Actions act;

	public static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Admin\\eclipse-workspace\\PizzaCucumber\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public static void launchWebSite(String url) {
		driver.get(url);

	}

	public static WebElement switchFrame(String url) {
		WebElement frameElement = driver.findElement(By.xpath(url));
		driver.switchTo().frame(frameElement);
		return frameElement;

	}

	// source for drag and drop function
	public static WebElement dragSource(String element) {
		source = driver.findElement(By.id(element));
		return source;
	}

	// Target for drag and drop function
	public static WebElement dragDestination(String element) {
		destination = driver.findElement(By.id(element));
		return destination;
	}

	public static void dragNDrop(WebElement source, WebElement destination) {
		act = new Actions(driver);
		act.dragAndDrop(source, destination).perform();
	}

	public static WebElement locator(String locatorType, String value) {

		if (locatorType.equals("id")) {
			findElement = driver.findElement(By.id(value));
			return findElement;
		} else if (locatorType.equals("name")) {
			findElement = driver.findElement(By.name(value));
			return findElement;
		} else if (locatorType.equals("xpath")) {
			findElement = driver.findElement(By.xpath(value));
			return findElement;
		} else {
			List<WebElement> findElements = driver.findElements(By.tagName(value));
			return (WebElement) findElements;
		}
	}

	// Mouseover
	public static WebElement mouseOver(String locatorType, String value) throws InterruptedException {
		// act=new Actions(driver);

		findElement = locator(locatorType, value);
		Thread.sleep(1000);
		return findElement;
	}

	// Mouseover click
	public static void mouseOverPerform(WebElement findElement) {
		act = new Actions(driver);
		act.moveToElement(findElement).perform();
	}

	public static void getAttribut(WebElement findElement, String value) {
		String attribute = findElement.getAttribute(value);
		System.out.println(attribute);
	}

	// to get text from selected web element
	public static void textGet(WebElement findElement) {
		String text = findElement.getText();
		System.out.println(text);
	}

	// to send a text to a text box
	public static void textSend(WebElement findElement, String sendValue) {
		findElement.sendKeys(sendValue);

	}

	// to find element by name
	public static WebElement name(String nameValue) {
		findElement = driver.findElement(By.id(nameValue));
		return findElement;
	}

	// to find element by xpath
	public static WebElement xpath(String xPathValue) {
		findElement = driver.findElement(By.xpath(xPathValue));
		return findElement;
	}

	// to click a button
	public static void buttonClick(WebElement findElement) {
		findElement.click();

	}

	// copy text using Robot class
	public static void cutCopy(Robot r) throws InterruptedException {
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);

		Thread.sleep(500);

		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_A);

		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_X);
		Thread.sleep(500);

		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_X);
	}

	public static void selectAllText(Robot r) throws InterruptedException {
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);

		Thread.sleep(500);

		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_A);
	}

	public static void justCut(Robot r) throws InterruptedException {
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_X);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_X);
	}

	public static void justCopy(Robot r) throws InterruptedException {
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_C);
	}

	public static void justPaste(Robot r) throws InterruptedException {
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
	}

	public static void screenShot(String name) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;

		File screenshotAs = tk.getScreenshotAs(OutputType.FILE);
		System.out.println(screenshotAs);

		File dest = new File("G:\\BOOKS\\WORK\\Greens institute\\Practice\\Screenshots\\" + name + ".png");

		FileUtils.copyFile(screenshotAs, dest);
	}

	public static void switchWindow() {
		String parentWindowId = driver.getWindowHandle();

		Set<String> allWindowsId = driver.getWindowHandles();
		System.out.println(allWindowsId);
		for (String x : allWindowsId) {
			if (!parentWindowId.equals(x)) {
				driver.switchTo().window(x);
			}
		}
	}

	public static String getDataFromDB(String query, String columnLabel, String anObject) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "Password");

		String s = query;

		PreparedStatement prepareStatement = connection.prepareStatement(s);
		ResultSet executeQuery = prepareStatement.executeQuery();
		String value = null;

		while (executeQuery.next()) {
			String string = executeQuery.getString(columnLabel);
			System.out.println(string);

			if (string.equalsIgnoreCase(anObject)) {
				value = string;
				System.out.println(string);
			} else {
				throw new Exception("User not found");
			}
		}
		connection.close();
		return value;
	}

	public static void brokenLinks() throws IOException {
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		int size = allLinks.size();
		int count = 0;
		System.out.println("Total number of links : " + size);
		for (int i = 0; i < size; i++) {
			String attribute = allLinks.get(i).getAttribute("href");

			URL url = new URL(attribute);

			URLConnection openConnection = url.openConnection();

			HttpsURLConnection connection = (HttpsURLConnection) openConnection;

			int responseCode = connection.getResponseCode();

			if (responseCode != 200) {
				System.out.println("Broken Link :" + attribute);
				count++;
			}
		}
		System.out.println("Broken Link count :" + count);

	}

	public static void getConfig(String fileLocation, String browser, String url, String username, String password)
			throws IOException {
		File file = new File(fileLocation);

		FileReader fileReader = new FileReader(file);

		Properties properties = new Properties();

		properties.load(fileReader);

		String string = properties.get(browser).toString();
		System.out.println(string);
		String string1 = properties.get(url).toString();
		System.out.println(string1);
		String string2 = properties.get(username).toString();
		System.out.println(string2);
		String string3 = properties.get(password).toString();
		System.out.println(string3);

	}
}