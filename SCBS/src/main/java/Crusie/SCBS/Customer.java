package Crusie.SCBS;

public class Customer {
	
	private String CustID,Name,EmailId;

	public Customer(String custID, String name, String emailId) {
		super();
		CustID = custID;
		Name = name;
		EmailId = emailId;
	}

	public String getCustID() {
		return CustID;
	}

	public void setCustID(String custID) {
		CustID = custID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	

}
