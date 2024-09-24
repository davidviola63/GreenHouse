package com.unisa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.unisa.dbconnection.DatabaseUtil;

public class GetPictureDao {
	
	private static Connection con=null;
	private static PreparedStatement ps=null;
	
	public synchronized static byte[] loadImmagineArticolo (int id) throws SQLException {

		ResultSet rs = null;
		byte[] bt = null;
		
		String selectSQL = "SELECT Immagine FROM Articolo WHERE ID = ?";
		try {
			con= DatabaseUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bt = rs.getBytes("Immagine");
			}
			
		}finally {
			con.close();
		}
		return bt;
	}
	
	public synchronized static byte[] loadImmagineMobileRiciclato (int id) throws SQLException {

		ResultSet rs = null;
		byte[] bt = null;
		
		String selectSQL = "SELECT Immagine FROM mobile_riciclato WHERE ID = ?";
		try {
			con= DatabaseUtil.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bt = rs.getBytes("Immagine");
			}
			
		}finally {
			con.close();
		}
		return bt;
	}
}
