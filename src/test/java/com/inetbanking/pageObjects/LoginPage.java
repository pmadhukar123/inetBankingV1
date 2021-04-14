package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver; //1st we need to create a driver object
	
	public LoginPage(WebDriver rdriver) //2nd we write a constructor "LoginPage" and it takes "rdriver" as the parameter 
	{
		ldriver=rdriver; //Inside the constructor we initiate the rdriver
		PageFactory.initElements(rdriver, this); //we also need to specify the PageFactory class
	}
	
	
	@FindBy(name="uid") // We find the element locator for User Name field
	@CacheLookup  //this is optional
	WebElement txtUserName; //We declare a WebElement name
	
	@FindBy(name="password") // We find the element locator for Password field
	@CacheLookup   //this is optional
	WebElement txtPassword; //We declare a WebElement name

	@FindBy(name="btnLogin") // We find the element locator for Login button
	@CacheLookup   //this is optional
	WebElement btnLogin; //We declare a WebElement name
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup   
	WebElement lnkLogout;
	
	
	public void setUserName(String uname){  //Next we add Action Methods for above 3 elements
		txtUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd){
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSubmit(){
		btnLogin.click();
	}
	
	public void clickLogout(){
		lnkLogout.click();
	}
}
