package controller;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMemberFxController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField NewMemberFX;
	@FXML
	private TextField NewMemberPINfx;

	public static String add_member_name;
	public static int add_member_PIN;

	@FXML
	private Button AddNewMemberFX;
	@FXML
	private Button CancelNewMemberFX;



	@FXML
	void AddNewMember_onAction(ActionEvent event) {
		Stage stage = (Stage) AddNewMemberFX.getScene().getWindow();
		
		
		java.sql.Connection c = null;

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:CRUD_db");
			c.setAutoCommit(false);

			PreparedStatement preparedStatement = c.prepareStatement("insert into Members(name, PIN) values (?, ?)");
			preparedStatement.setString(1,NewMemberFX.getText());	
			preparedStatement.setInt(2, Integer.parseInt(NewMemberPINfx.getText()));
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
	void CancelNewMember_OnAction(ActionEvent event) {
		Stage stage = (Stage) CancelNewMemberFX.getScene().getWindow();

		stage.close();
	}



}
