package com.nopCommerce.TestCases;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.nopCommerce.PageObjects.AddCustomerPage;
import com.nopCommerce.PageObjects.LoginPage;

public class TC_AddCustomer_002 extends BaseClass {

	@Test(priority = 1)
	public void addNewCustomerTest() throws Throwable {
		logger.info("*************TC_AddCustomer_002: addNewCustomerTest*************");
		try {
			LoginPage lp = new LoginPage(driver);
			lp.setUsername(config.getUsername());
			lp.setPassword(config.getPassword());
			lp.clickLogin();
			logger.info("loggin successfull");

			AddCustomerPage acp = new AddCustomerPage(driver);
			acp.clickCustomerTab();
			acp.clickCustomerSubTab();
			acp.clickAddNew();
			acp.setEmail(randomAlphabeticString(8) + "@gmail.com");
			logger.info("added email");
			acp.setPassword("msdhoni");
			logger.info("added passsword");
			acp.setFirstName("MS");
			logger.info("added firstname");
			acp.setLastName("Dhoni");
			logger.info("added lastname");
			acp.selectMale();
			logger.info("Gender seleted");
			acp.setDateOfBirth("08/01/1988");
			logger.info("added DOB");
			acp.setCompany("Syntel Systems");
			logger.info("added company");
			try {
				acp.setNewsletter("Test store 2");
			} catch (Throwable e) {
				e.printStackTrace();
			}
			logger.info("selected Newsletetr");
			acp.selectManagerOfVendor("Vendor 2");
			logger.info("selected Manager of Vendor");
			acp.setAreaAdminComment("Verification of Add new customer");
			logger.info("added admin comment");
			acp.clickSave();
			Thread.sleep(2000);

			if (driver.getPageSource().contains("The new customer has been added successfully.")) {
				logger.info("Customer added successfully");
				logger.info("Passed");
				Assert.assertTrue(true);
			} else {
				captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
				logger.info("Failed");
				Assert.assertTrue(false);
			}

			lp.clickLogout();
		}

		catch (Exception e) {
			captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
			System.out.println(e.getMessage());
			logger.info(e.getMessage());
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 2)
	public void CustomerAdditionWithDuplicateEmailCheck() throws Throwable {
		try {
			logger.info("*************TC_AddCustomer_002:  CustomerAdditionWithDuplicateEmailCheck*************");
			LoginPage lp = new LoginPage(driver);
			AddCustomerPage acp = new AddCustomerPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			Thread.sleep(3000);
			lp.setUsername(config.getUsername());
			lp.setPassword(config.getPassword());
			lp.clickLogin();
			logger.info("loggin successfull");

			acp.clickCustomerTab();
			acp.clickCustomerSubTab();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='customers-grid']//tr/td")));

			String email = acp.getEmailFromGrid();
			logger.info("existing email fetched from grid");
			acp.clickAddNew();
			logger.info("Clicked on Add button");
			acp.setEmail(email);
			logger.info("added existing email");
			acp.clickSave();

			if (driver.getPageSource().contains("Email is already registered")) {
				logger.info("Customer addition with duplicate email verified successfully");
				logger.info("Passed");
				Assert.assertTrue(true);
			} else {
				captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
				logger.info("Failed: New customer could be addeded with existing email");
				Assert.assertTrue(false);
			}

			lp.clickLogout();

		} catch (Exception e) {
			captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
			System.out.println(e.getMessage());
			logger.info(e.getMessage());
			Assert.assertTrue(false);

		}

	}

}
