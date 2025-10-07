package pracice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DemoWebshop 
{
     @Test
     public void  vote()
     {
    	 WebDriver driver=new ChromeDriver();
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	 driver.get("https://demowebshop.tricentis.com/");
    	 driver.findElement(By.id("pollanswers-1")).click();
    	 driver.findElement(By.xpath("//input[@value='Vote']")).click();
    	  }
}
