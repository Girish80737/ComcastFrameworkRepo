package com.comcast.crm.OrganizationTest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listnerutility.ListnerImpleClass;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationsPage;

@Listeners(com.comcast.crm.listnerutility.ListnerImpleClass.class )
public class CreateOrgTest extends BaseClass {

	@Test(groups= {"SmokeTest"})
	public void createOrgTest() throws EncryptedDocumentException, IOException {

		UtilityClassObject.getTest().log(Status.INFO, "Read Data From Excel");
		String orgName = eLib.getDataFromExcel("org", 1, 3) + jLib.getRandomNumber();
      
		UtilityClassObject.getTest().log(Status.INFO, "Navigate To Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		UtilityClassObject.getTest().log(Status.INFO, "Navigate To Create Org Page");
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgLink().click();

		UtilityClassObject.getTest().log(Status.INFO, "Create a New Org");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName+"====> Create a New Org");
		// Verify Header Msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertEquals(true, actOrgName.contains(orgName));
		/*if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "name is Verified===PASS");
		} else {
			System.out.println(orgName + "name is not Verified===FAIL");
		}

	}*/
	}

	// public class CreateOrgTestWithIndustries extends BaseClass {

	@Test(groups= {"RegressionTest"})

	public void createOrgWithIndustries() throws EncryptedDocumentException, IOException {

		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgLink().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry);

		cnop.createOrg1(type);

		// verify the inustries and Type info
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndustries.equals(industry)) {
			System.out.println(industry + "information is veified==PASSED");
		} else {
			System.out.println(orgName + "information is not veified==FAIL");
		}

		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.equals(type)) {
			System.out.println(type + "information is veified==PASSED");
		} else {
			System.out.println(type + "information is not veified==FAIL");
		}

	}

	// public class CreateOrgTestWithPhoneNumber extends BaseClass {

	@Test(groups={"RegressionTest"})
	public void createContactWithPhoneNumber() throws EncryptedDocumentException, IOException {

		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		// Step-2:Navigate To Organization Module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgLink().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgWithPhone(orgName, phoneNumber);

		// verify Header phonenumber Info ExpectedResult
		String actPhoneno = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actPhoneno.equals(phoneNumber)) {
			System.out.println(phoneNumber + " is created==PASSED");
		} else {
			System.out.println(phoneNumber + " is created==FAIL");
		}
	}

}
