 package com.nopCommerce.TestCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.nopCommerce.PageObjects.AddCustomerPage;
import com.nopCommerce.PageObjects.LoginPage;

public class TC_AddCustomer_002 extends BaseClass{
	
	@Test
	public void addNewCustomerTest() throws Throwable {
		logger.info("*************TC_AddCustomer_002*************");
		LoginPage lp= new LoginPage(driver);
		lp.setUsername(config.getUsername());
		lp.setPassword(config.getPassword());
		lp.clickLogin();
		logger.info("loggin successfull");
		
		AddCustomerPage acp= new AddCustomerPage(driver);
		acp.clickCustomerTab();
		acp.clickCustomerSubTab();
		acp.clickAddNew();
		acp.setEmail("msdhoni@gmail.com");
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
		acp.setNewsletter("Test store 2");
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
		}
		else
		{	captureScreen(driver,"addNewCustomerTest");
			Assert.assertTrue(false);
			logger.info("Failed");
		}
		
		
	}

}
