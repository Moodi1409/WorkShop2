package controller;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tabPaneTables.VLTtable;

public class DeleteMemberFXController implements Initializable {
	


    @FXML
    private ComboBox<String> Del_memberFX;

    @FXML
    private Button delete_memberFX;
    
    @FXML
    private Button Cancel_delete_memberFX;
    
	public static  ArrayList<String> DeleteMemberArr;

	ObservableList<String> DeleteDataList =FXCollections.observableArrayList(

			DeleteMemberArr

			);
	
	
	public static void showDeleteMembers()
	{
		DeleteMemberArr= new ArrayList<String>();

		java.sql.Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT name,PIN FROM Members");
			while ( rs.next() ) {
				
				DeleteMemberArr.add(rs.getString("name")+"-"+rs.getLong("PIN"));

			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

	}


    @FXML
    void DeleteMemberOnAction(ActionEvent event) {
    	
    	Stage stage = (Stage) delete_memberFX.getScene().getWindow();
		
		
		String result = Del_memberFX.getValue();

		String CutName = result.replaceAll("-.*", "");
		long CutPIN = Long.parseLong(result.replaceAll(".*-", ""));



		java.sql.Connection c = null;
		//		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);
			
			PreparedStatement preparedStatement = c.prepareStatement("DELETE from Members where PIN=?");
			preparedStatement.setLong(1, CutPIN);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			c.commit();			
			c.close();

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		
		try {
			//Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			PreparedStatement preparedStatement = c.prepareStatement("DELETE from Boats where PIN=?");
			preparedStatement.setLong(1, CutPIN);			
			preparedStatement.executeUpdate();
			c.commit();			
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

		stage.close();

    }
    
    @FXML
    void CancelDeleteMemberOnAction(ActionEvent event) {
    	
		Stage stage = (Stage) Cancel_delete_memberFX.getScene().getWindow();

		stage.close();

    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Del_memberFX.setItems(DeleteDataList);
		

	}

}
