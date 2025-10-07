package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility
{
	WebDriver driver;                      //Rule-1: create a seperate java class
	public LoginPage(WebDriver driver)     //Rule-2: Object Creation
	{
		this.driver=driver;
	PageFactory.initElements(driver, this);	
	}
	
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	//Rule-3:Object Intialization
	//Rule-4: Object Encapsulation

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Rule-5: Provide Action
	
	public void loginToapp(String url,String username,String password)
	{
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdit.sendKeys("admin");
		passwordEdit.sendKeys("admin");
		loginBtn.click();
	}
}
