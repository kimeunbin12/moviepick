package kr.co.moviepick.hall;

public class HallDTO {
	private int hno;
	private String hname;
	private int seat;
	private String seatimg;
	
	public HallDTO() {}

	public int getHno() {
		return hno;
	}

	public void setHno(int hno) {
		this.hno = hno;
	}

	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public String getSeatimg() {
		return seatimg;
	}

	public void setSeatimg(String seatimg) {
		this.seatimg = seatimg;
	}

	@Override
	public String toString() {
		return "HallDTO [hno=" + hno + ", hname=" + hname + ", seat=" + seat + ", seatimg=" + seatimg + "]";
	}
	
	
}
