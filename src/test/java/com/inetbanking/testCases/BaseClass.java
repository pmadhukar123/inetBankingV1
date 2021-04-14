package com.inetbanking.testCases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig=new ReadConfig(); //create an object for ReadConfig class to call methods from that class

	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@Parameters("browser")//we create @Parameters annotation because it will take parameter from xml file and we mention "browser" as variable and we will use the same variable name in the xml file
	@BeforeClass
	public void setup(String br)//set up method will take the parameters
	{
		logger =Logger.getLogger("ebanking");//we initiate the Logger class object
	    PropertyConfigurator.configure("log4j.properties");
	    
	    if(br.equals("chrome"))//if br value will equal to chrome then chrome browser will be launched
	    {
	    	System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		    driver=new ChromeDriver();	
	    }
	    else if(br.equals("firefox"))
	    {
	    	System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
		    driver=new FirefoxDriver();	
	    }
	    else if(br.equals("ie"))
	    {
	    	System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
		    driver=new InternetExplorerDriver();	
	    }
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    driver.get(baseURL);
	    
	 }
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken");
	}
	
	
	public String randomstring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}

	public static String randomnum()
	{
		String generatedstring2=RandomStringUtils.randomNumeric(4);
		return(generatedstring2);
	}
	
}