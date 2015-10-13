package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainFxController {
	
	



    @FXML
    private Button startt;

    
    @FXML
    void Startt(ActionEvent event) throws IOException {

		Stage stage = null; 
		Parent root = null;
		if(event.getSource()==startt){   	                 
			stage=(Stage) startt.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/viewFX/CRUDView.fxml"));

		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("CRUD");

		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2); 
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 4);  

		stage.setResizable(true);
		stage.show();
    	
    }

    @FXML
    void initialize() {
        assert startt != null : "fx:id=\"start\" was not injected: check your FXML file 'Welcome.fxml'.";

    }

}
