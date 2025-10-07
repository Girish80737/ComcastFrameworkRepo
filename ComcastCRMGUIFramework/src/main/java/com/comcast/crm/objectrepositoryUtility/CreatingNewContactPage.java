package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage 
{
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver)     
	{
		this.driver=driver;
	PageFactory.initElements(driver, this);	
	}
	
	@FindBy(name="lastname")
	public WebElement lastName;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	public WebElement saveBtn;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	public WebElement addOrgLink;
	
	@FindBy(id="search_txt")
	public WebElement searchField;
	
	@FindBy(name="search")
	public WebElement searchBtn;
	
	@FindBy(className="dvHeaderText")
	private WebElement  headerMsg;

	
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getAddOrgLink() {
		return addOrgLink;
	}

	public WebElement getLastName() 
	{
		return lastName;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getSearchField() {
		return searchField;
	}
	
	public WebElement getSaerchBtn() {
		return searchBtn;
	}

	
	public void createContact(String lastname)
	{
		lastName.sendKeys(lastname);
		saveBtn.click();
	}
	
	public void createContact1(String lastname)
	{
		lastName.sendKeys(lastname);
		
	}
	
	
}
