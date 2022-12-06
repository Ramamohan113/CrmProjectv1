package com.crmproject.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crmproject.pageObjects.LoginPage;
//@Listeners(com.crmproject.utilities.ExtentReport.class)
public class Tc_LoginPage_001 extends BaseClass{
	@Test
	public void loginTest() throws Exception {
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		log.info("enterd username");
		lp.setPassWord(password);
		log.info("passwrod enterd");
		lp.clickLoginBtn();
		String welcome = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
		if(welcome.equals("Welcome To Manager's Page of Guru99 Bank"))
		{
			Assert.assertTrue(false);
			log.info("Logintest is passed");
		}
		else
		{
			captureScreenShot(driver,"loginTest");
			Assert.assertTrue(false);
			log.warning("logintest is failed");
		}
	}

	
}
