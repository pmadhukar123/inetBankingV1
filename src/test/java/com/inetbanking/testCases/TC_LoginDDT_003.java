package com.inetbanking.testCases;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.ExcelUtility;

public class TC_LoginDDT_003 extends BaseClass{
	
	@Test
	public void test1() {
		try {
			List<Map<String,String>> loginData=ExcelUtility.getTestDataInMap("LoginData"); //just like everytime to access "ExcelUtility" class from diff package we create an object loginData and pass sheetname LoginData as parameter
			LoginPage lp = new LoginPage(driver);
			driver.manage().window().maximize();
			lp.setUserName(loginData.get(0).get("Username")); //this takes the data from 1st row after header where column name is Username
			lp.setPassword(loginData.get(0).get("Password"));  //this takes the data from 1st row after header where column name is Password
			lp.clickSubmit();
			
			Thread.sleep(3000);
			List<Map<String,String>> addCustData=ExcelUtility.getTestDataInMap("AddCustData");
			AddCustomerPage addcust=new AddCustomerPage(driver);
			addcust.clickAddNewCustomer();
			addcust.custName(addCustData.get(0).get("C_Name"));
			driver.findElement(By.xpath("//tbody/tr[5]/td[2]/input[1]")).click();
			addcust.custdob("10", "15", "1985");
			
			Thread.sleep(3000);
			
			addcust.custaddress(addCustData.get(0).get("C_Address"));
			addcust.custcity(addCustData.get(0).get("C_City"));
			addcust.custstate(addCustData.get(0).get("C_State"));
			addcust.custpinno(addCustData.get(0).get("C_Pin"));
			addcust.custtelephoneno(addCustData.get(0).get("C_Telephone"));
			
			String email=randomstring()+"@gmail.com"; //this will call randomstring method in BaseClass and take random email value from that method
			addcust.custemailid(email);
			addcust.custpassword("abcdef");
			addcust.custsubmit();
			
			Thread.sleep(3000);
			
boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
			
			if (res==true)
			{
				Assert.assertTrue(true);
				logger.info("test case passed...");
			}
			else
			{
				logger.info("test case failed...");
				captureScreen(driver,"addNewCustomer");
				Assert.assertTrue(false);
			}
	
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	@Test
	public void test2() {
		try {
			driver.get(baseURL);
			List<Map<String,String>> loginDataInMap=ExcelUtility.getTestDataInMap("LoginData");
			LoginPage lp = new LoginPage(driver);
			lp.setUserName(loginDataInMap.get(1).get("Username"));  //this takes the data from 2nd row after header where column name is Username
			lp.setPassword(loginDataInMap.get(1).get("Password"));  //this takes the data from 2nd row after header where column name is Password
			lp.clickSubmit();
			
			Thread.sleep(3000);
			
			List<Map<String,String>> addCustDataInMap = ExcelUtility.getTestDataInMap("AddCustData");
			AddCustomerPage addcust=new AddCustomerPage(driver);
			
			addcust.clickAddNewCustomer();
			addcust.custName(addCustDataInMap.get(1).get("C_Name"));
			driver.findElement(By.xpath("//tbody/tr[5]/td[2]/input[2]")).click();
			addcust.custdob("10", "15", "1985");
			
			Thread.sleep(3000);
			
			addcust.custaddress(addCustDataInMap.get(1).get("C_Address"));
			addcust.custcity(addCustDataInMap.get(1).get("C_City"));
			addcust.custstate(addCustDataInMap.get(1).get("C_State"));
			addcust.custpinno(addCustDataInMap.get(1).get("C_Pin"));
			addcust.custtelephoneno(addCustDataInMap.get(1).get("C_Telephone"));
			
			String email=randomstring()+"@gmail.com"; //this will call randomstring method in BaseClass and take random email value from that method
			addcust.custemailid(email);
			addcust.custpassword("abcdef");
			addcust.custsubmit();
			
			Thread.sleep(3000);
			
boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
			
			if (res==true)
			{
				Assert.assertTrue(true);
				logger.info("test case passed...");
			}
			else
			{
				logger.info("test case failed...");
				captureScreen(driver,"addNewCustomer");
				Assert.assertTrue(false);
			}
		
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
