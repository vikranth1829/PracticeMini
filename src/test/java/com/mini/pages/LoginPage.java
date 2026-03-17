package com.mini.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;
	
	private By usernameField = By.id("user-name");
	private By passwordField = By.id("password");
	private By loginButton = By.id("login-button");
	private By errormsg = By.cssSelector("button[class='error-button']");
	private By pageTitle =By.cssSelector("span[class^='title']");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterUserName(String userName) {
		driver.findElement(usernameField).sendKeys(userName);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}
	
	public void clickLogin() {
		driver.findElement(loginButton).click();
	}
	
	public void login(String userName,String password)
	{
		
		driver.findElement(usernameField).sendKeys(userName);
		driver.findElement(passwordField).sendKeys(password);	
		driver.findElement(loginButton).click();
	}

	public boolean isOnProductPage() {
		return driver.findElement(pageTitle).getText().equals("Products");
	}

	public boolean isErrorDisplayed() {
		return driver.findElement(errormsg).isDisplayed();
	}

}