package pracice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InvoiceTest1 
{
	@Test(retryAnalyzer = com.comcast.crm.listnerutility.RetryListnerImp.class)
public void createInvoiceTest()
{
	System.out.println("execute createInvoiceTest");
	
	//Assert.assertEquals("", "Login");
	System.out.println("Step-1");
	System.out.println("Step-2");
	System.out.println("Step-3");
	System.out.println("Step-4");
	
}
}
