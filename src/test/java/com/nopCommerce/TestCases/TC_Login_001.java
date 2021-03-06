package com.nopCommerce.TestCases;


import org.junit.Assert;
import org.testng.annotations.Test;

import com.nopCommerce.PageObjects.LoginPage;

public class TC_Login_001 extends BaseClass{
	
	@Test
	public void loginTest() throws Throwable {
		logger.info("*************TC_Login_001*************");
		LoginPage lp= new LoginPage(driver);
		lp.setUsername(username);
		logger.info("Entered Username");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.clickLogin();
		
		
		if(driver.getTitle().equals("Dashboard / nopCommerce administration")) {
			logger.info("Titile matched successfully");
			Assert.assertTrue(true);
		}
		else {
			captureScreen(driver,new Throwable().getStackTrace()[0].getMethodName());
			logger.info("Failed");
			Assert.assertTrue(false);
			
		}
		
	}
	
}
