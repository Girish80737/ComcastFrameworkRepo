package com.comcast.crm.orgtest;

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
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtillity;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationsPage;


public class CreateOrgTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
     
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtillity jLib=new JavaUtillity();
		
       
      String BROWSER=fLib.getDataFromPropertiesFile("browser");
      String URL=fLib.getDataFromPropertiesFile("url");
      String USERNAME=fLib.getDataFromPropertiesFile("username");
      String PASSWORD=fLib.getDataFromPropertiesFile("password");
      
      //Generate Random Number
      //Random random = new Random();
      //int randomInt = random.nextInt(1000);
      
     /* FileInputStream fis1=new FileInputStream("./testdata/Book112.xlsx");
      Workbook wb = WorkbookFactory.create(fis1);
      Sheet sh = wb.getSheet("org");
      Row row = sh.getRow(1);
       String orgName = row.getCell(2).toString()+randomInt;
       wb.close();*/
      String orgName = eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNumber();
      
       
      
      WebDriver driver=null;
      if(BROWSER.equals("chrome"))
      {
   	   driver=new ChromeDriver();
      }
      else if(BROWSER.equals("firefox"))
      {
   	   driver=new FirefoxDriver();
      }
      else if(BROWSER.equals("edge"))
      {
   	   driver=new EdgeDriver();
      }else
      {
   	   driver=new ChromeDriver();
      }
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      driver.get(URL);
      
     /* driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      driver.findElement(By.name("user_name")).sendKeys(USERNAME);
      driver.findElement(By.name("user_password")).sendKeys(PASSWORD );
      driver.findElement(By.xpath("//input[@type='submit']")).click();
      //Step-2:Navigate To Organization Module
      driver.findElement(By.linkText("Organizations")).click();
      //Step-3 Click on Create Organization Button
      driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
      //Enter The Details and create new Organization 
      driver.findElement(By.name("accountname")).sendKeys(orgName);
      driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
      //verify Header msg ExpectedResult
     String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
      if(headerinfo.contains(orgName))
      {
    	  System.out.println(orgName+"header verified==PASSED");
      }
      else
      {
    	  System.out.println(orgName+"header is verified==FAIL");  
      }
      
      //verify Header OrgName Info ExpectedResult
      String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
      if(actOrgName.equals(orgName))
      {
    	  System.out.println(orgName+"is created==PASSED");
      }
      else
      {
    	  System.out.println(orgName+"is created==FAIL");  
      }*/
      //LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
      //lp.getUsernameEdit().sendKeys("admin");
      //lp.getPasswordEdit().sendKeys("admin");
      //lp.getLoginBtn().click();
     // lp.loginToapp("admin", "admin");
      LoginPage lp=new LoginPage(driver);
   //   lp.loginToapp("admin","admin"); 
      //lp.getLoginBtn().click();
      
      HomePage hp=new HomePage(driver);
      hp.getOrgLink().click();
      
      OrganizationsPage cnp=new OrganizationsPage(driver);
      cnp.getCreateNewOrgLink().click();
      
      CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
      cnop.createOrg(orgName);
      
      //Verify Header Msg Expected Result
      OrganizationInfoPage oip=new OrganizationInfoPage(driver);
      String actOrgName = oip.getHeaderMsg().getText();
      if(actOrgName.contains(orgName))
      {
    	  System.out.println(orgName+"name is Verified===PASS");
      }else
      {
    	  System.out.println(orgName+"name is not Verified===FAIL");
      }
      
      
      driver.quit();
      
       
	}
	
	
}

