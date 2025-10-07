package pracice.test;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Closed_ShadowRoot {
	

	@Test
	public void closedShadow() throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://demoapps.qspiders.com/ui/shadow/closed?sublist=1");
		driver.findElement(By.xpath("//h1[text()='Login']")).click();
		Actions action=new Actions(driver);
		action.sendKeys(Keys.TAB).perform();
		Thread.sleep(2000);
		action.sendKeys("GirishNaikL").perform();
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB).perform();
		Thread.sleep(2000);
		action.sendKeys("Awesome5").perform();
		driver.quit();
	}
	

}
