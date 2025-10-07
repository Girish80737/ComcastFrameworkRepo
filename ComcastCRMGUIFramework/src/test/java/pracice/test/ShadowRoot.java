package pracice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ShadowRoot {
	@Test

	public void shadowRoot() throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demoapps.qspiders.com/ui?scenario=1");
		// (//section[@class='poppins text-[15px]'])[10]
		driver.findElement(By.xpath("//section[contains(text(),'Shadow')]")).click();
		driver.findElement(By.xpath("(//section[contains(text(),'Shadow')])[2]")).click();
		Thread.sleep(3000);
		SearchContext shadow_Host = driver.findElement(By.xpath("//form/div[1]")).getShadowRoot();
		Thread.sleep(3000);
		shadow_Host.findElement(By.cssSelector("input[type='text']")).sendKeys("Girish");
		SearchContext shadow_Host1 = driver.findElement(By.xpath("//form/div[2]")).getShadowRoot();
		Thread.sleep(3000);
		shadow_Host1.findElement(By.cssSelector("input[type='text']")).sendKeys("Awesome5");
		driver.quit();
	}
}
