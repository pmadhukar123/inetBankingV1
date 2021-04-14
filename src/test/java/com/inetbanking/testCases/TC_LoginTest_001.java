package com.inetbanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;



public class TC_LoginTest_001 extends BaseClass
{
     @Test
     public void loginTest()  //for any TestNG Test we need to write a method under which we will write our test steps
     {
    	  	 
    	 logger.info("URL is opened");
    	 
    	 LoginPage lp = new LoginPage(driver); //to access the LoginPage class, we create an object "lp" of LoginPage and pass driver as parameter
    	 
    	 lp.setUserName(username); //now under LoginPage class we have created setuserName actions method so using the object we simply call that methods and pass parameter that can be read from config file
    	 logger.info("Entered username");
    	 
    	 lp.setPassword(password);
    	 logger.info("Entered password");
    	 
    	 lp.clickSubmit();
    	 logger.info("Submit is clicked");
    	 
    	 
    	 if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))//we dynamically capture the title of the page and verify it. We get this title by manually seeing at the top of the tab opened
    	 {
    		 Assert.assertTrue(true);
    		 logger.info("Login test passed");
    	 }
    	 else 
    		 {
    		 Assert.assertTrue(false);
    		 logger.info("Login test failed");
    		 }

    	 
     }
}