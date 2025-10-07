package com.comcast.crm.listnerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListnerImpleClass implements ITestListener,ISuiteListener
{
	//public static ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test ;
public void onStart(ISuite suite) 
{
	System.out.println("Report Configuration");
	String time = new Date().toString().replace(" ", "_").replace(":", " _");

	
	//Spark Report config
	ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/reporter1_ "+time+".html");
	spark.config().setDocumentTitle("CRM Test Suite Results");
	spark.config().setReportName("CRM Report");
	spark.config().setTheme(Theme.DARK);
	
	//add Environment Info and Create Test
	 report=new ExtentReports();
	report.attachReporter(spark);
	report.setSystemInfo("OS","Windows-11");
	report.setSystemInfo("BROWSER", "CHROME-100");
	
}
public void onFinish(ISuite suite) 
{
	System.out.println("Report backUp");
	report.flush();
}
public void onTestStart(ITestResult result) 
{
	System.out.println("=======>"+result.getMethod().getMethodName()+">===START====");
	test = report.createTest(result.getMethod().getMethodName());
	UtilityClassObject.setTest(test);
	test.log(Status.INFO, result.getMethod().getMethodName()+">===START<====");
	
	
}
public void onTestSuccess(ITestResult result) 
{
	System.out.println("=======>"+result.getMethod().getMethodName()+">===END====");
	test.log(Status.PASS, result.getMethod().getMethodName()+">===COMPLETED<====");

}
public void onTestFailure(ITestResult result) 
{
	String testName = result.getMethod().getMethodName();
	
	 TakesScreenshot eDriver = (TakesScreenshot ) BaseClass.sdriver ;
     String filepath = eDriver.getScreenshotAs(OutputType.BASE64);
    
	String time = new Date().toString().replace(" ", "_").replace(":", " _");
	test.addScreenCaptureFromBase64String(filepath ,testName+"_"+time);
	
	test.log(Status.FAIL, result.getMethod().getMethodName()+">===FAILED<====");

	}
public void onTestSkipped(ITestResult result)
{
	//test.log(Status.SKIP, result.getMethod().getMethodName()+">===SKIPPED<====");

}
}

