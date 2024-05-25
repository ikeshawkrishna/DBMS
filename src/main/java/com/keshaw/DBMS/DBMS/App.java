package com.keshaw.DBMS.DBMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args ) throws SQLException 
    {
    	
    	String sql = "select name,id,points from restapidemo where id in (' ')";
    	
    	List<List<Object>> list = new ArrayList<List<Object>>();
    	try {
    		System.out.println(getDataFromDB(sql));
		} catch (Exception e) {
			System.out.println("Exception : "+e);
		}
    	
        
    }
    
    public static List<List<Object>> getDataFromDB(String sql) throws Exception{
    	
    	String url = "jdbc:postgresql://localhost:5432/RestAPI";
		String username = "postgres";
		String password = "0000";
		Connection con = null;

		List<List<Object>> total = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(url, username, password);
			
			PreparedStatement st = con.prepareStatement(sql);			
			ResultSet rs = st.executeQuery();
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			int Columncount = rsMeta.getColumnCount();
			
			if(rs.next()) {
				List<Object> firstrow = new ArrayList<Object>();
				for(int i=1; i<=Columncount; i++) {
					firstrow.add(rs.getString(i));
				}
				total.add(firstrow);
				
				while(rs.next()) {
					List<Object> remainingrow = new ArrayList<Object>();
					for(int i=1; i<=Columncount; i++) {
						remainingrow.add(rs.getString(i));
					}
					total.add(remainingrow);
				}
			} 
			
		} catch (Exception e) {
			throw e;
			//return total;
		} finally {
			con.close();
		}
		
    	return total;
    }	
}