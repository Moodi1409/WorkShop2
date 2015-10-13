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

public class UpdateMemberFxController implements Initializable{



	@FXML
	private Button Up_Member_ButtonFX;

	@FXML
	private Button Cancel_Up_Member_ButtonFX;

	@FXML
	private TextField Up_Member_Text_NameFX;

	@FXML
	private TextField Up_Member_Text_pinFX;

	@FXML
	private ComboBox<String> Up_Member_ComboFX;

	public static  ArrayList<String> Update_Member_Arr;

	ObservableList<String> Update_Member_DataList =FXCollections.observableArrayList(

			Update_Member_Arr

			);

	public static void showUpdateMember()
	{
		Update_Member_Arr= new ArrayList<String>();

		java.sql.Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT name,PIN FROM Members order by memberid");
			while ( rs.next() ) {

				Update_Member_Arr.add(rs.getString("name")+"-"+rs.getLong("PIN"));

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
	void UpMemberButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) Up_Member_ButtonFX.getScene().getWindow();

		String result = Up_Member_ComboFX.getValue();

		String CutName = result.replaceAll("-.*", "");
		long CutPIN = Long.parseLong(result.replaceAll(".*-", ""));


		java.sql.Connection c = null;

		try {
			//Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			PreparedStatement preparedStatement = c.prepareStatement("UPDATE members SET name=?, PIN=? where PIN=?");
			preparedStatement.setString(1, Up_Member_Text_NameFX.getText());
			preparedStatement.setLong(2, Long.parseLong(Up_Member_Text_pinFX.getText()));
			preparedStatement.setLong(3, CutPIN);

			preparedStatement.executeUpdate();
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

			PreparedStatement preparedStatement = c.prepareStatement("UPDATE Boats SET name=?, PIN=? where PIN=?");
			preparedStatement.setString(1, Up_Member_Text_NameFX.getText());
			preparedStatement.setLong(2, Long.parseLong(Up_Member_Text_pinFX.getText()));
			preparedStatement.setLong(3, CutPIN);

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
	void CancelUpMemberButtonOnAction(ActionEvent event) {

		Stage stage = (Stage) Cancel_Up_Member_ButtonFX.getScene().getWindow();

		stage.close();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Up_Member_ComboFX.setItems(Update_Member_DataList);


	}

}
