package tabPaneTables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CLTtable {
	
	public SimpleStringProperty CLTname;
	public SimpleIntegerProperty CLTmemberID;
	public SimpleIntegerProperty CLTboats;
	
	public CLTtable (){		
		this.CLTname = new SimpleStringProperty();
		this.CLTmemberID = new SimpleIntegerProperty();
		this.CLTboats = new SimpleIntegerProperty();
	}

	public String getCLTname() {
		return CLTname.get();
	}

	public void setCLTname(String cLTname) {
		CLTname.set(cLTname);
	}

	public Integer getCLTmemberID() {
		return CLTmemberID.get();
	}

	public void setCLTmemberID(Integer cLTmemberID) {
		CLTmemberID.set(cLTmemberID);;
	}

	public Integer getCLTboats() {
		return CLTboats.get();
	}

	public void setCLTboats(Integer cLTboats) {
		CLTboats.set(cLTboats);;
	}
	
	

}
