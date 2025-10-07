package com.comcast.crm.ContactsTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtillity;
import com.comcast.crm.objectrepositoryUtility.ContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationsPage;

public class CreateContactTest extends BaseClass {

	@Test(groups={"SmokeTest"})
	public void createContactTest() throws IOException {

		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCrateNewContactLink().click();

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(lastName);
		
		//String actHeader=driver.findElement(By.className("dvHeaderText")).getText();
		/*if(actHeader.equals(lastName)) {
			System.out.println(lastName+"Header is Verified===PASS");
		}else {
			System.out.println(lastName+"Header is  not Verified===FAIL");
		}*/
		String actHeader =cncp.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);
		
		//boolean status1 = actHeader.contains(actLastName);
		//String actLastName =cncp.getHeaderMsg().getText();
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert sopObj=new SoftAssert();
		sopObj.assertEquals(actLastName, lastName);
        /*if (actLastName.contains(lastName)) {
			System.out.println(lastName + "header verified==PASSED");
		} else {
			System.out.println(lastName + "header is verified==FAIL");
		}*/

	}

	@Test(groups= {"RegressionTest"})
	public void createContactWithDate() throws EncryptedDocumentException, IOException {

		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCrateNewContactLink().click();

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact1(lastName);

		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);

		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);

		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		cncp.getSaveBtn().click();

		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + "information is verified===PASS");
		} else {
			System.out.println(startDate + "information is not verified===FAIL");
		}

	}

	@Test(groups= {"RegressionTest"})
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
