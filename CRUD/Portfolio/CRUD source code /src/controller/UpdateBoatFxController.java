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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateBoatFxController implements Initializable{
	
    @FXML
    private TextField Up_Boat_Text_LengthFX;

    @FXML
    private ComboBox<String> Up_Boat_ComboFX;

    @FXML
    private Button Up_Boat_ButtonFX;


    @FXML
    private Button Cancel_Up_Boat_ButtonFX;

    public static  ArrayList<String> Update_Boat_Arr;

	ObservableList<String> Update_Boat_DataList =FXCollections.observableArrayList(

			Update_Boat_Arr

			);
	
	@FXML
    private ComboBox<String> Up_Boat_Combo_TypeFX;
	ObservableList<String> Up_Boat_type =FXCollections.observableArrayList(

			"Sailboat", "Motorsailer", "kayak/Canoe", "Other"

			);
	
	public static void showUpdateBoat()
	{
		Update_Boat_Arr= new ArrayList<String>();

		java.sql.Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT name,PIN, type, length FROM Boats order by memberid");
			while ( rs.next() ) {

				Update_Boat_Arr.add(rs.getString("name")+"-"+rs.getLong("PIN")+"-"+rs.getString("type")+"-"+rs.getInt("length"));
				
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
    void UpBoatButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) Up_Boat_ButtonFX.getScene().getWindow();

		
		String result = Up_Boat_ComboFX.getValue();
//		String TypeResult = Up_Boat_Combo_TypeFX.getValue();
		
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

			PreparedStatement preparedStatement = c.prepareStatement("UPDATE Boats SET type=?, length=? where PIN=? AND type=? and length=?");
			preparedStatement.setString(1, Up_Boat_Combo_TypeFX.getValue());
			preparedStatement.setInt(2, Integer.parseInt(Up_Boat_Text_LengthFX.getText()));
			preparedStatement.setLong(3, CutPIN);
			preparedStatement.setString(4, CutType);
			preparedStatement.setInt(5, CutLength);
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
    void CancelUpBoatButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) Cancel_Up_Boat_ButtonFX.getScene().getWindow();

		stage.close();
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	Up_Boat_ComboFX.setItems(Update_Boat_DataList);
    	Up_Boat_Combo_TypeFX.setItems(Up_Boat_type);
    }

}
