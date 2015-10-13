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

public class DeleteBoatFXController implements Initializable{
	
    @FXML
    private Button Cancel_delete_BoatFX;

    @FXML
    private Button delete_BoatFX;

    @FXML
    private ComboBox<String> Del_BoatFX;
    
	public static  ArrayList<String> Delete_Boat_Arr;

	ObservableList<String> Delete_Boat_DataList =FXCollections.observableArrayList(

			Delete_Boat_Arr

			);
	
	public static void showDeleteBoat()
	{
		Delete_Boat_Arr= new ArrayList<String>();

		java.sql.Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT name,PIN, type, length FROM Boats order by memberid");
			while ( rs.next() ) {

				Delete_Boat_Arr.add(rs.getString("name")+"-"+rs.getLong("PIN")+"-"+rs.getString("type")+"-"+rs.getInt("length"));
				
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
    void DeleteBoatOnAction(ActionEvent event) {
    	
    	Stage stage = (Stage) delete_BoatFX.getScene().getWindow();

		
		String result = Del_BoatFX.getValue();


		long CutPIN ;
		String CutType;
		int CutLength;
		String[] temp;
		 temp = result.split("-");

			  CutPIN =  Long.parseLong(temp[1]);
			  CutType= temp[2];
			  CutLength = Integer.parseInt(temp[3]);


		

		java.sql.Connection c = null;
		
		try {
			//Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			PreparedStatement preparedStatement = c.prepareStatement("DELETE from Boats where PIN=? AND type=? and length=?");
			preparedStatement.setLong(1, CutPIN);
			preparedStatement.setString(2, CutType);
			preparedStatement.setInt(3, CutLength);
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
    void CancelDeleteBoatOnAction(ActionEvent event) {
    	
    	Stage stage = (Stage) Cancel_delete_BoatFX.getScene().getWindow();

		stage.close();

    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {

    	Del_BoatFX.setItems(Delete_Boat_DataList);
		

	}

}
