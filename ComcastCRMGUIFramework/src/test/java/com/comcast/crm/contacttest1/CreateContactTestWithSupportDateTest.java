package com.comcast.crm.contacttest1;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtillity;
import com.comcast.crm.objectrepositoryUtility.ContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;

public class CreateContactTestWithSupportDateTest extends BaseClass{

     @Test
      public void createContactWithDate() throws EncryptedDocumentException, IOException
      {
      
      String lastName = eLib.getDataFromExcel("contact", 4, 2) +jLib.getRandomNumber(); 
      
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
      
      
      String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
      if(actStartDate.equals(startDate))
      {
    	  System.out.println(startDate+"information is verified===PASS");
      }else
      {
    	  System.out.println(startDate+"information is not verified===FAIL");
      }
      
}
}
