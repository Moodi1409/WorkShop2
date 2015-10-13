package DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import controller.CRUDMainFxController;
import tabPaneTables.CLTtable;
import tabPaneTables.VLTtable;

public class TablesDAO {
	
	public static void showdataVLTtable(){
		
		java.sql.Connection c = null;
	
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			Statement	stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT name, PIN, memberID, type, length FROM Boats");
			while ( rs.next() ) {
				VLTtable TableVLTitem = new VLTtable();
				
				TableVLTitem.VLTname.setValue(rs.getString("name"));
				TableVLTitem.VLTpin.setValue(rs.getLong("PIN"));
				TableVLTitem.VLTmemberID.setValue(rs.getInt("memberID"));
				TableVLTitem.VLTboatType.setValue(rs.getString("type"));
				TableVLTitem.VLTboatLength.setValue(rs.getInt("length"));
				
				CRUDMainFxController.VLTdata.add(TableVLTitem);
			
				
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		
	}
	
	
	
public static void showdataCLTtable(){
		
		java.sql.Connection c = null;
	
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			Statement	stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select name , memberid, COUNT(name) as NBoates from boats GROUP BY memberid");
			while ( rs.next() ) {
				CLTtable CLTtableitem = new CLTtable();
				
				CLTtableitem.CLTname.setValue(rs.getString("name"));
				
				CLTtableitem.CLTmemberID.setValue(rs.getInt("memberid"));
				
				CLTtableitem.CLTboats.setValue(rs.getInt("NBoates"));
				
				CRUDMainFxController.CLTdata.add(CLTtableitem);
			
				
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		
	}
	
	

}
