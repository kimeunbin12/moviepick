package kr.co.moviepick.user;

public class UserDTO {
	private String uid;
	private String ups;
	private String uname;
	private String ubirth;
	private String uemail;
	private String ugender;
	private String udate;
	private String uphone;
	private String ugrade;
	
	public UserDTO() {}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUps() {
		return ups;
	}
	public void setUps(String ups) {
		this.ups = ups;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUbirth() {
		return ubirth;
	}
	public void setUbirth(String ubirth) {
		this.ubirth = ubirth;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUgender() {
		return ugender;
	}
	public void setUgender(String ugender) {
		this.ugender = ugender;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUgrade() {
		return ugrade;
	}
	public void setUgrade(String ugrade) {
		this.ugrade = ugrade;
	}
	
	@Override
	public String toString() {
		return "UserDTO [uid=" + uid + ", ups=" + ups + ", uname=" + uname + ", ubirth=" + ubirth + ", uemail=" + uemail
				+ ", ugender=" + ugender + ", udate=" + udate + ", uphone=" + uphone + ", ugrade=" + ugrade + "]";
	}
	
	
}
