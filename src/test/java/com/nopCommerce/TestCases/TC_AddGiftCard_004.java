package com.nopCommerce.TestCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.nopCommerce.PageObjects.AddGiftCardPage;
import com.nopCommerce.PageObjects.LoginPage;

public class TC_AddGiftCard_004 extends BaseClass {

	@Test
	public void addGiftCardTest() throws Throwable {
		logger.info("*************TC_AddGiftCard_004*************");
		try {
			LoginPage lp = new LoginPage(driver);
			lp.setUsername(config.getUsername());
			lp.setPassword(config.getPassword());
			lp.clickLogin();
			logger.info("loggin successfull");

			AddGiftCardPage agc = new AddGiftCardPage(driver);
			agc.clickSalesTab();
			Thread.sleep(2000);
			logger.info("Clicked on Sales Tab");
			agc.clickGiftcardSubTab();
			logger.info("Clicked on Gift Card Sub Tab");
			agc.clickAddNew();
			Thread.sleep(2000);
			logger.info("Clicked on Add New");
			agc.selectOrdertype("Physical");
			logger.info("Selected Order Type");
			// agc.setAmount("250");
			// logger.info("Amount Entered");
			agc.setRecipientName("Raman");
			logger.info("Recipient Added");
			agc.setSenderName("Deepak");
			logger.info("Sender Added");
			agc.clickSave();
			logger.info("Clicked on Save");
			Thread.sleep(2000);

			if (driver.getPageSource().contains("The new gift card has been added successfully.")) {
				logger.info("Gift Card added successfully");
				logger.info("Passed");
				Assert.assertTrue(true);
			} else {
				captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
				logger.info("Failed");
				Assert.assertTrue(false);
			}

		}

		catch (Exception e) {
			captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
			System.out.println(e.getMessage());
			logger.info(e.getMessage());
			Assert.assertTrue(false);
		}
	}

}
