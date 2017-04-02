package bean;

import java.util.HashMap;
import java.util.Map;

public class UserBean {

	private int userID;
	private String uName;
	private String pWord;
	private String lastlogin;
	
	protected String getLastlogin() {
		return lastlogin;
	}
	protected void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	protected int getUserID() {
		return userID;
	}
	protected void setUserID(int userID) {
		this.userID = userID;
	}
	protected String getuName() {
		return uName;
	}
	protected void setuName(String uName) {
		this.uName = uName;
	}
	protected String getpWord() {
		return pWord;
	}
	protected void setpWord(String pWord) {
		this.pWord = pWord;
	}

	
}
