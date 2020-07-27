package kr.co.moviepick.review;

public class ReviewDTO {
	private int rno;
	private float rstar;
	private String rcom;
	private String uid;
	private int mno;
	private String msub;
	
	public ReviewDTO() {}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public float getRstar() {
		return rstar;
	}

	public void setRstar(float rstar) {
		this.rstar = rstar;
	}

	public String getRcom() {
		return rcom;
	}

	public void setRcom(String rcom) {
		this.rcom = rcom;
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

	
	@Override
	public String toString() {
		return "ReviewDTO [rno=" + rno + ", rstar=" + rstar + ", rcom=" + rcom + ", uid=" + uid + ", mno=" + mno
				+ ", msub=" + msub + "]";
	}
	
	
}
