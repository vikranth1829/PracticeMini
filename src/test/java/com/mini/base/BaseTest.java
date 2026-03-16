package com.mini.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	protected WebDriver driver;
	Properties config;
	ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
	
//	public WebDriver getDriver() {
//		return tldriver.get();
//	}
	
	@BeforeSuite
	public void loadConfig() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\config.properties");
		config.load(fis);
	}
	
	@BeforeMethod
	public void setup() {

		String browserName =config.getProperty("browserName","chrome");
		String baseUrl = config.getProperty("baseUrl");
		
		if(browserName==null) {
			browserName="chrome";
		}
		
		switch(browserName.toLowerCase()) 
		{
		case "chrome" :
			tldriver.set(new ChromeDriver());
			break;
		
		case "edge" :
			tldriver.set(new EdgeDriver());
			break;
			
		case "firefox" :
			tldriver.set(new FirefoxDriver());
			break;
			
		default:
			throw new IllegalArgumentException("Unsupported browser : "+browserName);
		}
		tldriver.get().get(baseUrl);
		
	}
	
	@AfterMethod
	public void tearDown() {
		if(tldriver!=null) {
			tldriver.get().quit();
			tldriver.remove();
		}
	}
}
