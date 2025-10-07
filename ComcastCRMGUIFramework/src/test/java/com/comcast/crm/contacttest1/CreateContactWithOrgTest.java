package com.comcast.crm.contacttest1;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtillity;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.ContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.OrganizationsPage;

public class CreateContactWithOrgTest extends BaseClass {

	@Test
	public void createContactWithOrg() throws EncryptedDocumentException, IOException {

		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

		// Step-2:Navigate To Organization Module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgLink().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// verify Header phonenumber Info ExpectedResult
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerinfo.contains(orgName)) {
			System.out.println(orgName + "header verified==PASSED");
		} else {
			System.out.println(orgName + "header is verified==FAIL");
		}

		hp.getContactsLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCrateNewContactLink().click();

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact1(contactLastName);

		cncp.getAddOrgLink().click();

		// Switch to Child window
		wLib.switchToTabOnURL(driver, "module=Accounts");

		cncp.getSearchField().sendKeys(orgName);
		cncp.getSaerchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// Switch to Parent Window
		wLib.switchToTabOnURL(driver, "Contacts@action");

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerinfo.contains(contactLastName)) {
			System.out.println(contactLastName + "header verified==PASSED");
		} else {
			System.out.println(contactLastName + "header is verified==FAIL");
		}

		// verify Header OrgName Info ExpectedResult
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgName);
		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + "information is created==PASSED");
		} else {
			System.out.println(orgName + "information is not created==FAIL");
		}
	}

}
