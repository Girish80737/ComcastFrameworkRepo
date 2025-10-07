package pracice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class DragAndDropElement {
	@Test
	
	public void dragAmdDrop()
	{
		 WebDriver driver=new ChromeDriver();
          WebDriverUtility wLib=new WebDriverUtility();
          wLib.maximize(driver);
          wLib.waitForPageToLoad(driver);
          driver.get("https://testautomationpractice.blogspot.com/");
          
         // Actions act=new Actions(driver);
          WebElement drag = driver.findElement(By.id("draggable"));
          WebElement drop = driver.findElement(By.id("droppable"));
          wLib.dragAndDrop(driver, drag, drop);
         
	}
}
