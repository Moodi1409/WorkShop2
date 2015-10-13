package DAO;

import java.sql.DriverManager;

public class ConnectionDB {
	
	public static java.sql.Connection connection( )
	{
		try {
			Class.forName("org.sqlite.JDBC");
			DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			System.out.println("DB is connected");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

		return null;
	}

}
