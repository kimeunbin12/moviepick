package kr.co.moviepick.notice;

import org.springframework.web.multipart.MultipartFile;

public class NoticeDTO {
	private int noticeno;
	private String noticesub;
	private int noticecnt;
	private String noticedate;
	private String whether;
	private String noticecon;
	private String noticeimg;
//---------------------------------------
	//1)스프링 파일 객체 멤버 변수 선언
  	//<input type='file' name='posterMF'>
  	private MultipartFile posterMF;
  	
  	//2)getter와 setter작성
  	public MultipartFile getPosterMF() {
  		return posterMF;
  	}

  	public void setPosterMF(MultipartFile posterMF) {
  		this.posterMF = posterMF;
  	}
  	
  	
	public NoticeDTO() {}

	public int getNoticeno() {
		return noticeno;
	}

	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}

	public String getNoticesub() {
		return noticesub;
	}

	public void setNoticesub(String noticesub) {
		this.noticesub = noticesub;
	}

	public int getNoticecnt() {
		return noticecnt;
	}

	public void setNoticecnt(int noticecnt) {
		this.noticecnt = noticecnt;
	}

	public String getNoticedate() {
		return noticedate;
	}

	public void setNoticedate(String noticedate) {
		this.noticedate = noticedate;
	}

	public String getWhether() {
		return whether;
	}

	public void setWhether(String whether) {
		this.whether = whether;
	}

	public String getNoticecon() {
		return noticecon;
	}

	public void setNoticecon(String noticecon) {
		this.noticecon = noticecon;
	}

	public String getNoticeimg() {
		return noticeimg;
	}

	public void setNoticeimg(String noticeimg) {
		this.noticeimg = noticeimg;
	}

	@Override
	public String toString() {
		return "NoticeDTO [noticeno=" + noticeno + ", noticesub=" + noticesub + ", noticecnt=" + noticecnt
				+ ", noticedate=" + noticedate + ", whether=" + whether + ", noticecon=" + noticecon + ", noticeimg="
				+ noticeimg + "]";
	}
	
	
}
