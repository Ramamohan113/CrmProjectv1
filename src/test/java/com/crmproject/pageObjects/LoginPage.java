package com.crmproject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUser;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement loginBtn;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassWord;
	
	public void setUserName(String username)
	{
		txtUser.sendKeys(username);
	}
	
	public void setPassWord(String password)
	{
		txtPassWord.sendKeys(password);
	}
	public void clickLoginBtn()
	{
		loginBtn.click();
	}
	
}
