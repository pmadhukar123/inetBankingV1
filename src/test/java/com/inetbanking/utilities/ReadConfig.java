package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro; //we create an object "pro" for Properties class
	
	public ReadConfig()
	{
		File src = new File("./Configuration/config.properties");
		
		try{
			FileInputStream fis = new FileInputStream(src); //we need to import the config.properties file using FileInputStream
			pro = new Properties();
			pro.load(fis);//load is a method which will load the complete file
		   } catch (Exception e) { //exception is put just in case the file is not present at the location 
			   System.out.println("Exception is " + e.getMessage());
		   }
		
		}
	
	public String getApplicationURL() //once the file is loaded next we add methods for each variable
	{
	String url=pro.getProperty("baseURL");
	return url;
	}
	
	public String getUsername() 
	{
	String username=pro.getProperty("username");
	return username;
	}
	
	public String getPassword() 
	{
	String password=pro.getProperty("password");
	return password;
	}
	
	public String getChromePath() 
	{
	String chromepath=pro.getProperty("chromepath");
	return chromepath;
	}
	
	public String getIEPath() 
	{
	String iepath=pro.getProperty("iepath");
	return iepath;
	}
	
	public String getFirefoxPath() 
	{
	String firefoxpath=pro.getProperty("firefoxpath");
	return firefoxpath;
	}
	
	
}
