package com.nopCommerce.TestCases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.nopCommerce.Utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	ReadConfig config= new ReadConfig();
	public String baseURL= config.getbaseURL();
	public String username=config.getUsername();
	public String password=config.getPassword();
	public WebDriver driver;
	public Logger logger;
	
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", config.getChromePath());
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();			
		}
		else if (br.equals("edge")) {
			//System.setProperty("webdriver.edge.driver", config.getEdgePath());
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();			
		}
		else
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();	
		}
		
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	
	public String randomAlphabeticString(int c) {
		return RandomStringUtils.randomAlphabetic(c);
	}
	

}
