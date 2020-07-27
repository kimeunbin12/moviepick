package kr.co.moviepick.booking;

public class BookingDTO {
	private int bno;
	private String bdate;
	private String bloc;
	private String btype;
	private int scrno;
	private String uid;
	private int mno;
	private int hno;
	private String scrdate;
	private String scrstart;
	private String msub;
	
	public BookingDTO() {}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getBloc() {
		return bloc;
	}

	public void setBloc(String bloc) {
		this.bloc = bloc;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public int getScrno() {
		return scrno;
	}

	public void setScrno(int scrno) {
		this.scrno = scrno;
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

	public int getHno() {
		return hno;
	}

	public void setHno(int hno) {
		this.hno = hno;
	}

	public String getScrdate() {
		return scrdate;
	}

	public void setScrdate(String scrdate) {
		this.scrdate = scrdate;
	}

	public String getScrstart() {
		return scrstart;
	}

	public void setScrstart(String scrstart) {
		this.scrstart = scrstart;
	}

	public String getMsub() {
		return msub;
	}

	public void setMsub(String msub) {
		this.msub = msub;
	}

	
	@Override
	public String toString() {
		return "BookingDTO [bno=" + bno + ", bdate=" + bdate + ", bloc=" + bloc + ", btype=" + btype + ", scrno="
				+ scrno + ", uid=" + uid + ", mno=" + mno + ", hno=" + hno + ", scrdate=" + scrdate + ", scrstart="
				+ scrstart + ", msub=" + msub + "]";
	}
	
	
}
