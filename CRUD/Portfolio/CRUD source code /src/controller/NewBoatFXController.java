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
import tabPaneTables.VLTtable;

public class NewBoatFXController implements Initializable{


	@FXML
	private ComboBox<String> MemberNewBoatNameFX;

	@FXML
	private Button CancelBoatFX;

	@FXML
	private ComboBox<String> BoatTypeFX;

	@FXML
	private TextField BoatLengthFX;

	@FXML
	private Button AddBoatFX;

	public static  ArrayList<String> arr;

	ObservableList<String> list =FXCollections.observableArrayList(

			arr

			);
	ObservableList<String> Boat_type =FXCollections.observableArrayList(

			"Sailboat", "Motorsailer", "kayak/Canoe", "Other"

			);

	public static void showMembers()
	{
		arr= new ArrayList<String>();

		java.sql.Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT name,PIN FROM Members");
			while ( rs.next() ) {
				
				arr.add(rs.getString("name")+"-"+rs.getLong("PIN"));

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
	void AddBoatOnAction(ActionEvent event) {
		Stage stage = (Stage) AddBoatFX.getScene().getWindow();
		VLTtable TableVLTitem = new VLTtable();
		
		String result = MemberNewBoatNameFX.getValue();

		String CutName = result.replaceAll("-.*", "");
		long CutPIN = Long.parseLong(result.replaceAll(".*-", ""));



		java.sql.Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			Statement	stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT memberID FROM Members WHERE PIN="+"'"  +CutPIN +"'");
			int  Member_id = rs.getInt("memberID");
			rs.close();
			stmt.close();
			
			PreparedStatement preparedStatement = c.prepareStatement("insert into Boats(memberID, name, PIN, type, length) values (?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, Member_id);
			preparedStatement.setString(2,CutName);	
			preparedStatement.setLong(3, CutPIN);
			preparedStatement.setString(4,BoatTypeFX.getValue());	
			preparedStatement.setInt(5, Integer.parseInt(BoatLengthFX.getText()));
			TableVLTitem.VLTname.setValue(CutName);
			TableVLTitem.VLTpin.setValue(CutPIN);
			TableVLTitem.VLTmemberID.setValue(Member_id);
			TableVLTitem.VLTboatType.setValue(BoatTypeFX.getValue());
			TableVLTitem.VLTboatLength.setValue(Integer.parseInt(BoatLengthFX.getText()));
			CRUDMainFxController.VLTdata.add(TableVLTitem);
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
	void CancelBoatOnAction(ActionEvent event) {
		Stage stage = (Stage) CancelBoatFX.getScene().getWindow();

		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		MemberNewBoatNameFX.setItems(list);
		BoatTypeFX.setItems(Boat_type);

	}
	
	
}
