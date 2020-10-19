package com.nopCommerce.TestCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopCommerce.PageObjects.AddCampaignsPage;
import com.nopCommerce.PageObjects.LoginPage;
import com.nopCommerce.Utilities.XLUtils;

public class TC_AddCampaignDDT_003 extends BaseClass {

	@Test(dataProvider = "CampaingnsData")
	public void addCampaignTest(String name, String subject, String body, String store, String role) throws Throwable {
		logger.info("*************TC_AddCampaign_003****************");
		try {
			LoginPage lp = new LoginPage(driver);
			lp.setUsername(config.getUsername());
			lp.setPassword(config.getPassword());
			lp.clickLogin();
			logger.info("Login Successfull");

			AddCampaignsPage acp = new AddCampaignsPage(driver);
			acp.clickPromotionTab();
			logger.info("Promotion Tab clicked");
			acp.clickCampaignsSubTab();
			logger.info("Campaign Sub Tab clicked");
			Thread.sleep(2000);
			acp.clickAddNew();

			acp.setCampaignsName(name);
			logger.info("Campains Name added");
			acp.setSubject(subject);
			logger.info("Subject added");
			acp.setBody(body);
			logger.info("Body added");
			acp.selectStore(store);
			logger.info("Store selected");
			acp.selectCustomerRole(role);
			logger.info("Role selected");
			acp.clickSave();
			Thread.sleep(2000);

			if (driver.getPageSource().contains("The new campaign has been added successfully.")) {
				logger.info("Campaign added successfully");
				logger.info("Passed");
				lp.clickLogout();
				Thread.sleep(3000);
				Assert.assertTrue(true);

			} else {
				captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
				logger.info("Failed");
				lp.clickLogout();
				Thread.sleep(3000);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			captureScreen(driver, new Throwable().getStackTrace()[0].getMethodName());
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			Assert.assertTrue(false);
		}

	}

	@DataProvider(name = "CampaingnsData")
	String[][] getCampaign() throws IOException {
		String path = "./src/test/java/com/nopCommerce/TestData/CampaignsData.xlsx";
		int rowCount = XLUtils.getRowCount(path, "Sheet1");
		int colCount = XLUtils.getCellCount(path, "Sheet1", 1);

		String campainData[][] = new String[rowCount][colCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				campainData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}

		}
		return campainData;
	}

}
