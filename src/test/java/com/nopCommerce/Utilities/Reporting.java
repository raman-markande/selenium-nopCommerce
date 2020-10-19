package com.nopCommerce.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		spark = new ExtentSparkReporter("./test-output/Test-Report" + timeStamp);
		try {
			spark.loadXMLConfig("./extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Hostname", "Localhost");
		extent.setSystemInfo("Envt", "QA");
		extent.setSystemInfo("Tester", "Raman Markande");

		spark.config().setDocumentTitle("nopCommerce");
		spark.config().setReportName("Functional Testing");
		spark.config().setTheme(Theme.STANDARD);

	}

	public void onTestSuccess(ITestResult result) {
		logger = extent.createTest(result.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		logger = extent.createTest(result.getName());

		// logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(),ExtentColor.RED));
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable().toString(), ExtentColor.RED));

		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png";

		File f = new File(screenshotPath);

		if (f.exists()) {
			logger.fail("Screenshot attached above:" + logger.addScreenCaptureFromPath(screenshotPath));
		}
	}

	public void onTestSkipped(ITestResult result) {
		logger = extent.createTest(result.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.YELLOW));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
