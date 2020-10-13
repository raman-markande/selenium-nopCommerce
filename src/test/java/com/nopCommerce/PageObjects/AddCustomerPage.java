package com.nopCommerce.PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	WebDriver ldriver;
	public AddCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	@FindBy(xpath="(//span[contains(text(),'Customers')])[1]")
	WebElement customerTab;
	
	@FindBy(xpath="(//span[contains(text(),'Customers')])[2]")
	WebElement customerSubTab;
	
	@FindBy(linkText="Add new")
	WebElement btnAddNew;
	
	@FindBy(how= How.XPATH, using="//input[@id='Email']")
	WebElement textEmail;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='FirstName']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='LastName']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='Gender_Male']")
	WebElement radioMaleGender;
	
	@FindBy(xpath="//input[@id='DateOfBirth']")
	WebElement txtDateOfBirth;
	
	@FindBy(xpath="//input[@id='Company']")
	WebElement txtCompany;
	
	@FindBy(xpath="//input[@id='IsTaxExempt']")
	WebElement checkBoxIsTaxExempt;
	
	@FindBy(xpath="(//div[@class='k-multiselect-wrap k-floatwrap'])[1]")
	WebElement divNewsletter;
	@FindBy(xpath="//ul[@id='SelectedNewsletterSubscriptionStoreIds_taglist']/following-sibling::input")
	WebElement txtNewsletter;
	
	@FindBy(xpath="//select[@id='VendorId']")
	WebElement selectMOV;
	
	@FindBy(xpath="//textarea[@id='AdminComment']")
	WebElement txtAreaAdminComment;
	
	@FindBy(xpath="//button[@name='save']")
	WebElement btnSave;
	
	
	public void clickCustomerTab() {
		customerTab.click();
	}
	
	public void clickCustomerSubTab() {
		customerSubTab.click();
	}
	
	public void clickAddNew() {
		btnAddNew.click();
	}
	
	public void setEmail(String email) {
		textEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setFirstName(String firstname) {
		txtFirstName.sendKeys(firstname);
	}
	
	public void setLastName(String lastname) {
		txtLastName.sendKeys(lastname);
	}
	
	public void selectMale() {
		radioMaleGender.click();
	}
	
	public void setDateOfBirth(String dob) {
		txtDateOfBirth.sendKeys(dob);
	}
	
	public void setCompany(String company) {
		txtCompany.sendKeys(company);
	}
	
	public void setNewsletter(String newsletter) throws Throwable {
		divNewsletter.click();
		Thread.sleep(1000);
		txtNewsletter.sendKeys(newsletter);
		Thread.sleep(1000);
		txtNewsletter.sendKeys(Keys.ENTER);		
	}
	
	public void selectManagerOfVendor(String mov) {
		Select select = new Select(selectMOV);
		select.selectByVisibleText(mov);
	}
	
	public void setAreaAdminComment(String comment) {
		txtAreaAdminComment.sendKeys(comment);
	}
	
	public void clickSave() {
		btnSave.click();
	}

}
