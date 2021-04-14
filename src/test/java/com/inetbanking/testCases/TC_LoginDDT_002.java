package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{

	@Test(dataProvider="LoginData") //this is the actual test case
	public void loginDDT(String user, String pwd) throws InterruptedException  //for any TestNG Test we need to write a method under which we will write our test steps
	{
 LoginPage lp = new LoginPage(driver);//to access the LoginPage class, we create an object "lp" of LoginPage and pass driver as parameter
    	 
    	 lp.setUserName(user);  //now under LoginPage class we have created setuserName actions method so using the object we simply call that methods and pass parameter "user" for which value is returned using DataProvider method
    	 logger.info("Entered username");
    	 lp.setPassword(pwd);
    	 logger.info("Entered password");
    	 lp.clickSubmit();
    	 logger.info("Submit is clicked");
    	 
    	 Thread.sleep(3000);
    	 
    	 if(isAlertPresent()==true)
    	 {
    		 driver.switchTo().alert().accept();//close alert
    		 driver.switchTo().defaultContent();
    		 Assert.assertTrue(false);
    		 logger.warn("Login failed");
    	 }
    	 else
    	 {
    		 Assert.assertTrue(true);
    		 logger.info("Login passed");
    		 lp.clickLogout();
    		 Thread.sleep(3000);
    		 driver.switchTo().alert().accept();//close the logout alert
    		 driver.switchTo().defaultContent();
    	 }
	}
	
	public boolean isAlertPresent() //user defined method created to check alert is present or not
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException//this is a DataProvider method which will take data from excel, store it into 2-dimension array and then pass it to actual test case
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]=new String[rownum][colcount];//this line will store the read data into 2-dim array
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i,j);//this line will read the data currently 1 0
			}
		}
		return logindata;//this line will return the data
	}
}
