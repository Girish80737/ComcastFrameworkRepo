package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws IOException 
	{

	FileUtility fLib=new FileUtility();
	ExcelUtility eLib=new ExcelUtility();
	JavaUtillity jLib=new JavaUtillity();
	WebDriverUtility wLib=new WebDriverUtility();
	
   
  String BROWSER=fLib.getDataFromPropertiesFile("browser");
  String URL=fLib.getDataFromPropertiesFile("url");
  String USERNAME=fLib.getDataFromPropertiesFile("username");
  String PASSWORD=fLib.getDataFromPropertiesFile("password");
  
      //Generate Random Number
     // Random random = new Random();
      //int randomInt = random.nextInt(1000);
      
      //FileInputStream fis1=new FileInputStream("./testdata/Book112.xlsx");
      //Workbook wb = WorkbookFactory.create(fis1);
      //Sheet sh = wb.getSheet("contact");
      //Row row = sh.getRow(7);
       //String orgName = row.getCell(2).toString()+randomInt;
       //String contactLastName = row.getCell(3).toString();
       //getStringcellValue();
       
       //wb.close();
      String orgName = eLib.getDataFromExcel("contact", 7, 2) +jLib.getRandomNumber();
      String contactLastName = eLib.getDataFromExcel("contact", 7, 3) +jLib.getRandomNumber();
       
      
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
      //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      wLib.waitForPageToLoad(driver);
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
      
      //verify Header phonenumber Info ExpectedResult
      String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
      if(headerinfo.contains(orgName))
      {
    	  System.out.println(orgName+"header verified==PASSED");
      }
      else
      {
    	  System.out.println(orgName+"header is verified==FAIL");  
      }
      
      //Navigate To Contact Module
      driver.findElement(By.linkText("Contacts")).click();
      //Step-3 Click on Create Organization Button
      driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
      //Enter The Details and create new Organization 
      driver.findElement(By.name("lastname")).sendKeys(contactLastName);
      
      driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
      
      //Switch to Child Window
      wLib.switchToTabOnURL(driver, "module=Accounts");
     /* Set<String> set = driver.getWindowHandles();
      Iterator<String> it = set.iterator();
      
      while(it.hasNext())
      {
    	String windowId = it.next(); 
    	driver.switchTo().window(windowId);
    	
    	@Nullable
		String actUrl = driver.getCurrentUrl();
    	if(actUrl.contains("module=Accounts"))
    	{
    		break;
    	}
      }*/
      
      driver.findElement(By.name("search_text")).sendKeys(orgName);
      driver.findElement(By.name("search")).click();
      driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();//dynamic Xpath
      
      //Switch to Parent Window
      wLib.switchToTabOnURL(driver, "Contacts@action");
      
      /*Set<String> set1 = driver.getWindowHandles();
      Iterator<String> it1 = set1.iterator();
      
      while(it1.hasNext())
      {
    	String windowId = it1.next(); 
    	driver.switchTo().window(windowId);
    	
    	@Nullable
		String actUrl = driver.getCurrentUrl();
    	if(actUrl.contains("Contacts@action"))
    	{
    		break;
    	}
      }*/
      
      driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
      
       headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
      if(headerinfo.contains(contactLastName))
      {
    	  System.out.println(contactLastName+"header verified==PASSED");
      }
      else
      {
    	  System.out.println(contactLastName+"header is verified==FAIL");  
      }
      
    //verify Header OrgName Info ExpectedResult
      String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
      System.out.println(actOrgName);
      if(actOrgName.trim().equals(orgName))
      {
    	  System.out.println(orgName+"information is created==PASSED");
      }
      else
      {
    	  System.out.println(orgName+"information is not created==FAIL");  
      }
      
      
      driver.quit(); 
	}

}
