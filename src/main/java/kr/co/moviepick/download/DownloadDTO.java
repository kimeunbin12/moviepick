package kr.co.moviepick.download;

public class DownloadDTO {
	private int dno;
	private String uid;
	private int mno;
	private String msub;
	private String ddate;
	private String mfile;
	
	
	public DownloadDTO() {}

	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	
	public String getMsub() {
		return msub;
	}

	public void setMsub(String msub) {
		this.msub = msub;
	}
	
	

	public String getDdate() {
		return ddate;
	}

	public void setDdate(String ddate) {
		this.ddate = ddate;
	}

	
	public String getMfile() {
		return mfile;
	}

	public void setMfile(String mfile) {
		this.mfile = mfile;
	}

	@Override
	public String toString() {
		return "DownloadDTO [dno=" + dno + ", uid=" + uid + ", mno=" + mno + ", msub=" + msub + ", ddate=" + ddate
				+ ", mfile=" + mfile + "]";
	}
	
	
}
