package com.nopCommerce.TestCases;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.nopCommerce.PageObjects.LoginPage;
import com.nopCommerce.PageObjects.SearchCustomerPage;


public class TC_SearchCustomer_005 extends BaseClass {

	@Test
	public void serachCustomerByEmailTest() throws Throwable {
		LoginPage lp = new LoginPage(driver);
		SearchCustomerPage scp = new SearchCustomerPage(driver);
		try {
			logger.info("********************TC_SearchCustomer_005: By Email******************");
			lp.setUsername(config.getUsername());
			WebDriverWait wait = new WebDriverWait(driver, 10);
			lp.setPassword(config.getPassword());
			lp.clickLogin();
			logger.info("Login successful");

			scp.clickCustomerTab();
			scp.clickCustomerSubTab();
			scp.setEmail("arthur_holmes@nopCommerce.com");
			logger.info("Email entered");
			scp.clickSearch();
			logger.info("Clicked on Search");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='customers-grid']//tr/td")));

			if (scp.searchCustomerByEmail("arthur_holmes@nopCommerce.com")) {
				logger.info("Search by Email working successfully");
				Assert.assertTrue(true);
			} else {
					captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
					logger.info("Failed: Search functionality failed");
					Assert.assertTrue(false);
			}

		} catch (Throwable e) {
			captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
			logger.info("Failed: Exception Occured");
			logger.error(e.getMessage());
			Assert.assertTrue(false);
		} finally {
			lp.clickLogout();
		}

	}

	@Test
	public void serachCustomerByNameTest() throws Throwable {
		LoginPage lp = new LoginPage(driver);
		SearchCustomerPage scp = new SearchCustomerPage(driver);

		try {
			logger.info("********************TC_SearchCustomer_005: By Name******************");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Email")));
			lp.setUsername(config.getUsername());
			lp.setPassword(config.getPassword());
			lp.clickLogin();
			logger.info("Login successful");

			scp.clickCustomerTab();
			scp.clickCustomerSubTab();
			scp.setFirstName("Victoria");
			logger.info("First Name entered");
			scp.setLastName("Terces");
			logger.info("Last Name entered");
			scp.clickSearch();
			logger.info("Clicked on Search");
			Thread.sleep(2000);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='customers-grid']//tr/td")));

			if (scp.searchCustomerByName("Victoria" + " " + "Terces")) {
				logger.info("Search by Name working successfully");
				Assert.assertTrue(true);
			} else {
					captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
					logger.info("Failed: Search functionality failed");
					Assert.assertTrue(false);
			}

		} catch (Throwable e) {
			captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
			logger.info("Failed: Exception Occured");
			logger.error(e.getMessage());
			Assert.assertTrue(false);
		} 
	}

}