package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

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
import com.comcast.crm.objectrepositoryUtility.ContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;

public class CreateContactTestWithSupportDateTest2 extends BaseClass{

@Test
public void createContactWithDate() throws IOException {
	
       
      
      String lastName = eLib.getDataFromExcel("contact", 4, 2) +jLib.getRandomNumber();       
      
      //Step-2:Navigate To Contacts Module
      HomePage hp=new HomePage(driver);
      hp.getContactsLink().click();
      
     
      //Step-3 Click on Create Organization Button
      ContactPage cp=new ContactPage(driver);
      cp.getCrateNewContactLink().click();
      
      
      String startDate = jLib.getSystemDateYYYYDDMM();
      String endDate = jLib.getRequiredDateYYYYDDMM(30);
      
      
      CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
      cncp.createContact1(lastName);
      
      driver.findElement(By.name("support_start_date")).clear();
      driver.findElement(By.name("support_start_date")).sendKeys(startDate);
      driver.findElement(By.name("support_end_date")).clear();
      driver.findElement(By.name("support_end_date")).sendKeys(endDate);
      
      cncp.getSaveBtn().click();
      //verify Header msg ExpectedResult
      String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
      if(actLastName.contains(lastName))
      {
    	  System.out.println(lastName+"header verified==PASSED");
      }
      else
      {
    	  System.out.println(lastName+"header is verified==FAIL");
      }
      
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
