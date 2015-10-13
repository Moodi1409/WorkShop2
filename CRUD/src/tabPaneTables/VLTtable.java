package tabPaneTables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class VLTtable {

	public SimpleStringProperty VLTname;
	public SimpleLongProperty VLTpin;
	public SimpleIntegerProperty VLTmemberID;
	public SimpleStringProperty VLTboatType;
	public SimpleIntegerProperty VLTboatLength;


//	}

	public  VLTtable (){
		this.VLTname = new SimpleStringProperty();
		this.VLTpin = new SimpleLongProperty();
		this.VLTmemberID = new SimpleIntegerProperty();
		this.VLTboatType = new SimpleStringProperty();
		this.VLTboatLength = new SimpleIntegerProperty();
	}

	public String getVLTname() {
		return VLTname.get();
	}

	public void setVLTname(String vLTname) {
		VLTname.set(vLTname);
	}

	public Long getVLTpin() {
		return VLTpin.get();
	}

	public void setVLTpin(Long vLTpin) {
		VLTpin.set(vLTpin);
	}

	public Integer getVLTmemberID() {
		return VLTmemberID.get();
	}

	public void setVLTmemberID(Integer vLTmemberID) {
		VLTmemberID.set(vLTmemberID);
	}

	public String getVLTboatType() {
		return VLTboatType.get();
	}

	public void setVLTboatType(String vLTboatType) {
		VLTboatType.set(vLTboatType);
	}

	public Integer getVLTboatLength() {
		return VLTboatLength.get();
	}

	public void setVLTboatLength(Integer vLTboatLength) {
		VLTboatLength.set(vLTboatLength);
	}



}
