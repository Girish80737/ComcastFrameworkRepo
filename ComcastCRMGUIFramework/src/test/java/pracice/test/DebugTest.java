package pracice.test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;

public class DebugTest extends WebDriverUtility
{
@Test
public void createOrgTest() {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	//driver.get("http://49.249.28.218:8888/");
	LoginPage lp=new LoginPage(driver);
	lp.loginToapp("http://49.249.28.218:8888/", "admin", "admin");
	
	HomePage hp=new HomePage(driver);
	hp.getOrgLink().click();
	
	
}
}
