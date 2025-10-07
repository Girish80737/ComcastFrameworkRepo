package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtillity 
{
public int getRandomNumber()
{
	Random ranDom=new Random();
	int ranDomNumber=ranDom.nextInt(5000);
	return ranDomNumber;
}

public String getSystemDateYYYYDDMM()
{
	Date dateobj=new Date();
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String date=sdf.format(dateobj);
	return date;
	
}
public String getRequiredDateYYYYDDMM(int days)
{
Date dateobj=new Date();
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String date=sdf.format(dateobj);
	
	Calendar cal = sdf.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH,days);
	String reqDate = sdf.format(cal.getTime());
	
	return reqDate;
}
}
