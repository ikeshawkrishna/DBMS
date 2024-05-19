package com.keshaw.DBMS.DBMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException 
    {
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("enter the id :: ");
    	int id = sc.nextInt();
    	
    	List<List<Object>> totalData = new ArrayList<>(); 
    	
        String sql = "select name,id,points from restapidemo where id = ?";
        
        String url = "jdbc:postgresql://localhost:5432/RestAPI";
        String username = "postgres";
        String password = "0000";
        
        Connection con = null;
        try {
         con = DriverManager.getConnection(url, username, password);
        	
        	//Statement st = con.createStatement();
        	
         	PreparedStatement prepSt = con.prepareStatement(sql);
        	prepSt.setInt(1, id);
        	
        	ResultSet rs = prepSt.executeQuery();
        	
        	if (rs.next()) {
        	    //String name = rs.getString(1);
        	    System.out.println("Inside if ");
        	    List<Object> firstRowdata = new ArrayList<>();
        	    firstRowdata.add(rs.getString("name"));
        	    firstRowdata.add(rs.getString("id"));
        	    firstRowdata.add(rs.getString("points"));
    			totalData.add(firstRowdata);
    			
    			if(rs.next()) {
    				while(rs.next()) {
    					List<Object> data = new ArrayList<>();
            			System.out.println("Inside while ");
            			data.add(rs.getString("name"));
            			data.add(rs.getString("id"));
            			data.add(rs.getString("points"));
            			totalData.add(data);
            		}
    			}
        	} else {
        	    System.out.println("No data found for id: " + id);
        	}
        	
        	System.out.println(totalData);
        	
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	con.close();
		}
        
    }
}
