package bean;

import java.util.HashMap;
import java.util.Map;

public class PersonBean {
	private int pid;
	private String fname;
	private String lname;
	private int address;
	private String memberSince;
	private static Map<String, UserBean> UsersList;
	
	protected String getMemberSince() {
		return memberSince;
	}
	protected void setMemberSince(String memberSince) {
		this.memberSince = memberSince;
	}
	
	protected static void setUserList(Map<String, UserBean> list){
		UsersList = new HashMap<String, UserBean>(list);
	}
	protected static Map<String, UserBean> getUserList(){
		return new HashMap<String, UserBean>(UsersList);
	}
}
