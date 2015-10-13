package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.TablesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tabPaneTables.CLTtable;
import tabPaneTables.VLTtable;

public class CRUDMainFxController implements Initializable {

    @FXML
    private Button add_boat;
    @FXML
    private Button add_member;
    @FXML
    private Button delete_member;
    @FXML
    private Button delete_Boat;
    @FXML
    private Button update_member;
    @FXML
    private Button update_boat;
    
   
    @FXML
    private TableView<VLTtable> VerboseListT;
    
    @FXML
    private TableColumn<VLTtable, String> NameVLT;
    
    @FXML
    private TableColumn<VLTtable, Long> PINvlt;
    
    @FXML
    private TableColumn<VLTtable, Integer> MemberIdVLT;

    @FXML
    private TableColumn<VLTtable, String> BoatTypeVLT;
    
    @FXML
    private TableColumn<VLTtable, Integer> BoatLengthVLT;

    public static ObservableList<VLTtable> VLTdata =FXCollections.observableArrayList();


    @FXML
    private TableView<CLTtable> CompactListT;
      
    @FXML
    private TableColumn<CLTtable, String> NameCLT;

    @FXML
    private TableColumn<CLTtable, Integer> MemberIdCLT;
    
    @FXML
    private TableColumn<CLTtable, Integer> BoatsCLT;

    public static ObservableList<CLTtable> CLTdata =FXCollections.observableArrayList();

    @FXML
    void AddMember(ActionEvent event) throws IOException {
    	
    	Stage stage; 
		Parent root;

		if(event.getSource()==add_member)
		{
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/viewFX/addMemberView.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("New Member");
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(add_member.getScene().getWindow());
			stage.showAndWait();
			
			VerboseListT.getItems().clear();
			TablesDAO.showdataVLTtable();
			
			CompactListT.getItems().clear();
			TablesDAO.showdataCLTtable();
		}

    }
    
    @FXML
    void AddBoat(ActionEvent event) throws IOException {
    	Stage stage; 
		Parent root;
		
		NewBoatFXController.showMembers();
		
		if(event.getSource()==add_boat)
		{
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/viewFX/BoatView.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("New Boat");
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(add_boat.getScene().getWindow());
			stage.showAndWait();
			
			VerboseListT.getItems().clear();
			TablesDAO.showdataVLTtable();
			
			CompactListT.getItems().clear();
			TablesDAO.showdataCLTtable();
			
		}

    }
    
    @FXML
    void DeleteMember(ActionEvent event) throws IOException {
    	
    	Stage stage; 
		Parent root;
		
		DeleteMemberFXController.showDeleteMembers();
		
		if(event.getSource()==delete_member)
		{
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/viewFX/DeleteMemberView.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("delete member");
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(delete_member.getScene().getWindow());
			stage.showAndWait();
			
			VerboseListT.getItems().clear();
			TablesDAO.showdataVLTtable();
			
			CompactListT.getItems().clear();
			TablesDAO.showdataCLTtable();
			
		}

    }
    
    @FXML
    void DeleteBoat(ActionEvent event) throws IOException {
    	
    	Stage stage; 
		Parent root;
		
		DeleteBoatFXController.showDeleteBoat();
		
		if(event.getSource()==delete_Boat)
		{
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/viewFX/DeleteBoatView.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("Delete a member Boat");
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(delete_Boat.getScene().getWindow());
			stage.showAndWait();
			
			VerboseListT.getItems().clear();
			TablesDAO.showdataVLTtable();
			
			CompactListT.getItems().clear();
			TablesDAO.showdataCLTtable();
			
		}

    }
    
    @FXML
    void UpdateMember(ActionEvent event) throws IOException {
    	
    	Stage stage; 
		Parent root;
		
		UpdateMemberFxController.showUpdateMember();
		
		if(event.getSource()==update_member)
		{
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/viewFX/UpdateMemberViwe.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("Update a Member");
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(update_member.getScene().getWindow());
			stage.showAndWait();
			
			VerboseListT.getItems().clear();
			TablesDAO.showdataVLTtable();
			
			CompactListT.getItems().clear();
			TablesDAO.showdataCLTtable();
			
		}

    }

    @FXML
    void UpdateBoat(ActionEvent event) throws IOException {
    	
    	Stage stage; 
		Parent root;
		
		UpdateBoatFxController.showUpdateBoat();
		
		if(event.getSource()==update_boat)
		{
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/viewFX/UpdateBoatViwe.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("Update a member Boat");
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(update_boat.getScene().getWindow());
			stage.showAndWait();
			
			VerboseListT.getItems().clear();
			TablesDAO.showdataVLTtable();
			
			CompactListT.getItems().clear();
			TablesDAO.showdataCLTtable();
		}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		NameVLT.setCellValueFactory(new PropertyValueFactory<VLTtable,String>("VLTname") );
		PINvlt.setCellValueFactory(new PropertyValueFactory<VLTtable,Long>("VLTpin") );
		MemberIdVLT.setCellValueFactory(new PropertyValueFactory<VLTtable,Integer>("VLTmemberID") );
		BoatTypeVLT.setCellValueFactory(new PropertyValueFactory<VLTtable,String>("VLTboatType") );
		BoatLengthVLT.setCellValueFactory(new PropertyValueFactory<VLTtable,Integer>("VLTboatLength") );
		
		VerboseListT.setItems(VLTdata);
		TablesDAO.showdataVLTtable();
		
		NameCLT.setCellValueFactory(new PropertyValueFactory<CLTtable,String>("CLTname") );
		MemberIdCLT.setCellValueFactory(new PropertyValueFactory<CLTtable,Integer>("CLTmemberID") );
		BoatsCLT.setCellValueFactory(new PropertyValueFactory<CLTtable,Integer>("CLTboats") );
		
		CompactListT.setItems(CLTdata);
		TablesDAO.showdataCLTtable();
		
	}

    

}
