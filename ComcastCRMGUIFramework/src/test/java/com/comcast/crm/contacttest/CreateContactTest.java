package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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
import com.comcast.crm.objectrepositoryUtility.LoginPage;

public class CreateContactTest {

	public static void main(String[] args) throws IOException 
	{
     
		//FileInputStream fis=new FileInputStream("./configAppData/commondata.properties");
       // Properties pobj=new Properties();
      // pobj.load(fis);
		
		//Create Object
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
      Sheet sh = wb.getSheet("contact");
      Row row = sh.getRow(1);
       String lastName = row.getCell(2).toString()+randomInt;
       wb.close();*/
      String lastName = eLib.getDataFromExcel("contact", 1, 2) +jLib.getRandomNumber();
       
      
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
      driver.findElement(By.name("lastname")).sendKeys(lastName);
      driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
      //verify Header msg ExpectedResult
     String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
     
     //LoginPage lp=new LoginPage(driver);
      if(actLastName.contains(lastName))
      {
    	  System.out.println(lastName+"header verified==PASSED");
      }
      else
      {
    	  System.out.println(lastName+"header is verified==FAIL");  
      }
      
      
      driver.quit(); 
	}

}
