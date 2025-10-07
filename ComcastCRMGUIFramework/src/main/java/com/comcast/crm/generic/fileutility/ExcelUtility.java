package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
  public String getDataFromExcel(String sheetName,int rownum,int celnum) throws EncryptedDocumentException, IOException
  {
	  FileInputStream fis=new FileInputStream("./testdata/Book112.xlsx");
	  Workbook wb=WorkbookFactory.create(fis);
	  String data= wb.getSheet(sheetName).getRow(rownum).getCell(celnum).getStringCellValue();
	  
	  return data;
  }
  public int getRowcount(String sheetName) throws EncryptedDocumentException, IOException
  {
	  FileInputStream fis=new FileInputStream("./testdata/Book112.xlsx");
	  Workbook wb=WorkbookFactory.create(fis); 
	  int rowCount=wb.getSheet(sheetName).getLastRowNum();
	  return rowCount;
  }
  public String setDataIntoExcel(String sheetName,int rownum,int celNum,String data) throws Throwable, IOException
  {
	  FileInputStream fis=new FileInputStream("./testdata/Book112.xlsx");
	  Workbook wb=WorkbookFactory.create(fis);
	   wb.getSheet(sheetName).getRow(rownum).createCell(celNum) ;
	   
	   FileOutputStream fos=new FileOutputStream("./testdata/Book112.xlsx");
	   wb.write(fos);
	   wb.close();
	return data;
  }
	
}
