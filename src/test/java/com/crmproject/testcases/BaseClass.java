package com.crmproject.testcases;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.crmproject.utilities.ReadPropertyData;

public class BaseClass {
	 ReadPropertyData r = new ReadPropertyData();
	public String baseUrl = r.getUrl();
	public String username = r.getUaserName();
	public String password = r.getPassWord();

	public static WebDriver driver;
	public static Logger log;

	@BeforeClass
	public void setUp() throws Throwable {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the browser");
		String browser = sc.nextLine();
		log = Logger.getLogger("CrmProject");
		PropertyConfigurator.configure("Log4j.properties");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");

			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");

			driver = new FirefoxDriver();
		}
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();

	}

	public void captureScreenShot(WebDriver driver, String testname) throws Exception {
		TakesScreenshot t = (TakesScreenshot) driver;
		File src = t.getScreenshotAs(OutputType.FILE);
		File dec = new File("./Screenshot/"+ testname +".png");
		FileUtils.copyFile(src, dec);
		System.out.println("ScreenShot taken");
	}

}
