package com.mini.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mini.base.BaseTest;
import com.mini.pages.LoginPage;

public class LoginTest extends BaseTest{
	//private WebDriver driver;
	
	@DataProvider(name="logindata")
	public Object[][] logindata(){
		return new Object[][] {
				{"standard_user","secret_sauce",true},
				{"locked_out_user","secret_sauce",false},
				{"standard_user","secret_sauc",false}
		};
	}
	
	@Test(dataProvider="logindata")
	public void verifyLogin(String userName, String password,boolean shouldSucceed) {
		LoginPage lp=new LoginPage(getDriver());
		lp.login(userName, password);
		if(shouldSucceed) { 
			Assert.assertTrue(lp.isOnProductPage(),"Expected Login to succeed");
		}else {
			Assert.assertTrue(lp.isErrorDisplayed(),"Expected error msg for invalid login");
		}
		}
	}


