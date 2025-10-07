package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility 
{

Connection con;

 public void getDbconnection(String Url,String username,String password)
 {
	 try
	 {
		 Driver driver=new Driver();
		 DriverManager.registerDriver(driver);
		 
		  con = DriverManager.getConnection(Url, username, password);
	 }
	 catch(Exception e)
	 {
		 
	 }
 }
 
 public void getDbconnection()
 {
	 try
	 {
		 Driver driver=new Driver();
		 DriverManager.registerDriver(driver);
		 
		  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	 }
	 catch(Exception e)
	 {
	 }
 }
 
 public void closeDbconnection() throws SQLException
 {
	 try {
	 con.close();
 }catch (Exception e) 
	 {
}
 }
 
 public ResultSet executeConSelectQuery(String Query)
 {
	 ResultSet result=null;
	 try{
		 Statement stat=con.createStatement();
		 result=stat.executeQuery(Query);
	 }catch(Exception e) {
		
	 }
	return result;
 }
 
 public int executeNonSelectQuery(String Query)
 {
	 int result=0;
	 try{
		 Statement stat=con.createStatement();
		 result=stat.executeUpdate(Query);
	 }catch(Exception e) {
		
	 }
	 return result;
}
}

