package com.comcast.crm.orgtest1;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationsPage;

public class CreateOrgTest extends BaseClass {

	@Test
	public void createOrgTest() throws EncryptedDocumentException, IOException {

		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgLink().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// Verify Header Msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "name is Verified===PASS");
		} else {
			System.out.println(orgName + "name is not Verified===FAIL");
		}

	}

}
