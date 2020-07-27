package kr.co.moviepick.screening;

public class ScreeningDTO {
	private int scrno;
	private String scrdate;
	private String scrstart;
	private int hno;
	private int mno;
	
	public ScreeningDTO() {}

	public int getScrno() {
		return scrno;
	}

	public void setScrno(int scrno) {
		this.scrno = scrno;
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

	public int getHno() {
		return hno;
	}

	public void setHno(int hno) {
		this.hno = hno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	@Override
	public String toString() {
		return "ScreeningDTO [scrno=" + scrno + ", scrdate=" + scrdate + ", scrstart=" + scrstart + ", hno=" + hno
				+ ", mno=" + mno + "]";
	}
	
	
}
