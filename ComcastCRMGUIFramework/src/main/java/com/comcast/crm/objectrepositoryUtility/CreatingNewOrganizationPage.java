package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage 
{
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver)     
	{
		this.driver=driver;
	PageFactory.initElements(driver, this);	
	}
	
	@FindBy(name="accountname")
	private WebElement OrgnameEdit;
	
	@FindBy(id="phone")
	private WebElement phoneEditField;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement SaveBtn; 
	
	@FindBy(name="industry")
	private WebElement industryDB;
	
	@FindBy(name="accounttype")
	private WebElement typeDB;
	
	
	
	
	public WebElement getOrgnameEdit() 
	{
		return OrgnameEdit;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	public WebElement getPhoneEditField() {
		return phoneEditField;
	}

	public void createOrg(String orgName)  //Orgname-We are getting this data from Excel sheet
	{
		OrgnameEdit.sendKeys(orgName);
		
		SaveBtn.click();
	}
	
	public void createOrg(String orgName,String industry)  //Orgname-We are getting this data from Excel sheet
	{
		OrgnameEdit.sendKeys(orgName);
		Select sel=new Select(industryDB);
		sel.selectByVisibleText(industry);
		SaveBtn.click();
	}
	public void createOrg1(String type)
	{
		Select sel1=new Select(typeDB);
		sel1.selectByVisibleText(type);
	}
	
	public void createOrgWithPhone(String orgName,String phone ) 
	{
		OrgnameEdit.sendKeys(orgName);
		phoneEditField.sendKeys(phone);
		SaveBtn.click();
		}
	}
	
