package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SetInfo {
	
	private final int ref;
	private final StringProperty code;
	private final StringProperty prodBrancName;
	private final StringProperty jaffBranchName;
//	private final StringProperty testBranchName;
//	private final StringProperty productVersion;
	
	public SetInfo() {
        this(0, null, null, null);
    }

	public SetInfo(int ref, String code, String prodBrancName, String jaffBranchName) {
        this.ref = ref;
		this.code = new SimpleStringProperty(code);
        this.prodBrancName = new SimpleStringProperty(prodBrancName);
        this.jaffBranchName = new SimpleStringProperty(jaffBranchName);
        
    }

	  public String code() {
	        return code.get();
	    }

	    public void setCode(String code) {
	        this.code.set(code);
	    }

	    public StringProperty codeProperty() {
	        return code;
	    }
	    
	    /////////////////////////////////////////
	    
	    public String prodBrancName() {
	        return code.get();
	    }

	    public void setProdBrancName(String prodBrancName) {
	        this.prodBrancName.set(prodBrancName);
	    }

	    public StringProperty ProdBrancNameProperty() {
	        return prodBrancName;
	    }

	    /////////////////////////////////////////
	
	    public String jaffBranchName() {
	        return jaffBranchName.get();
	    }

	    public void setJaffBranchName(String jaffBranchName) {
	        this.jaffBranchName.set(jaffBranchName);
	    }

	    public StringProperty JaffBranchNameProperty() {
	        return jaffBranchName;
	    }

		public int getRef() {
			return ref;
		}
	
	
	
	
	
	
}