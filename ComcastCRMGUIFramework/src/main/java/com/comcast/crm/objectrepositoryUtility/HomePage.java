package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
    WebDriver driver;
	public HomePage(WebDriver driver)     
	{
		this.driver=driver;
	PageFactory.initElements(driver, this);	
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement OrgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement ContactsLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement CampaignsLink;
	
	@FindBy(linkText="More")
	private WebElement MoreLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signoutLink;
	
	
	
	
	public WebElement getOrgLink() {
		return OrgLink;
	}

	public WebElement getContactsLink() {
		return ContactsLink;
	}

	public WebElement getCampaignsLink() {
		return CampaignsLink;
	}

	public WebElement getMoreLink() {
		return MoreLink;
	}

	//Business Libraries
	
	public void navigteToCampaignPage()
	{
		Actions act=new Actions(driver);
		act.moveToElement(MoreLink).perform();
		CampaignsLink.click();
	}
	
	public void logout()
	{
		Actions act=new Actions(driver);
		act.moveToElement(adminImg).perform();
		signoutLink.click();
	}
}
