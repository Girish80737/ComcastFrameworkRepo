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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtillity;

public class CreateContactTestWithSupportDateTest {

	public static void main(String[] args) throws IOException 
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
     // int randomInt = random.nextInt(1000);
      
      String lastName = eLib.getDataFromExcel("contact", 4, 2) +jLib.getRandomNumber();       
      
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
      driver.get(URL);
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      driver.findElement(By.name("user_name")).sendKeys(USERNAME);
      driver.findElement(By.name("user_password")).sendKeys(PASSWORD );
      driver.findElement(By.xpath("//input[@type='submit']")).click();
      //Step-2:Navigate To Contacts Module
      driver.findElement(By.linkText("Contacts")).click();
      //Step-3 Click on Create Organization Button
      driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
      //Enter The Details and create new Organization
      
        /*Date dateobj=new Date();
		
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String startDate=sim.format(dateobj);
		
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String endDate=sim.format(cal.getTime());*/
      
      String startDate = jLib.getSystemDateYYYYDDMM();
      String endDate = jLib.getRequiredDateYYYYDDMM(30);
      
      driver.findElement(By.name("lastname")).sendKeys(lastName);
      
      driver.findElement(By.name("support_start_date")).clear();
      driver.findElement(By.name("support_start_date")).sendKeys(startDate);
      driver.findElement(By.name("support_end_date")).clear();
      driver.findElement(By.name("support_end_date")).sendKeys(endDate);
      
      driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
      
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
      
     driver.quit(); 
	}

}
