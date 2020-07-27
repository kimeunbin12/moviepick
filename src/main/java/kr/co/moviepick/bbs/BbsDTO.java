package kr.co.moviepick.bbs;

import org.springframework.web.multipart.MultipartFile;

public class BbsDTO {
	private int nno;
	private float nstar;
	private String ntitle;
	private String nsub;
	private String ncon;
	private int ncnt;
	private String ndate;
	private String nimg;
	private String nspo;
	private String uid;
	//-----------------------------------------
	//1) 스프링 파일 객체 맴버 변수 선언
		//<input type='file' name='posterMF'>
		private MultipartFile posterMF;	//실제 파일을 받기위한 변수 생성
		//<input type='file' name='filenameMF'>
		private MultipartFile filenameMF;
		
		//2)getter와 setter작성
		public MultipartFile getPosterMF() {
			return posterMF;
		}

		public void setPosterMF(MultipartFile posterMF) {
			this.posterMF = posterMF;
		}

		public MultipartFile getFilenameMF() {
			return filenameMF;
		}

		public void setFilenameMF(MultipartFile filenameMF) {
			this.filenameMF = filenameMF;
		}

	public BbsDTO() {}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public float getNstar() {
		return nstar;
	}

	public void setNstar(float nstar) {
		this.nstar = nstar;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNsub() {
		return nsub;
	}

	public void setNsub(String nsub) {
		this.nsub = nsub;
	}

	public String getNcon() {
		return ncon;
	}

	public void setNcon(String ncon) {
		this.ncon = ncon;
	}

	public int getNcnt() {
		return ncnt;
	}

	public void setNcnt(int ncnt) {
		this.ncnt = ncnt;
	}

	public String getNdate() {
		return ndate;
	}

	public void setNdate(String ndate) {
		this.ndate = ndate;
	}

	public String getNimg() {
		return nimg;
	}

	public void setNimg(String nimg) {
		this.nimg = nimg;
	}

	public String getNspo() {
		return nspo;
	}

	public void setNspo(String nspo) {
		this.nspo = nspo;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "BbsDTO [nno=" + nno + ", nstar=" + nstar + ", ntitle=" + ntitle + ", nsub=" + nsub + ", ncon=" + ncon
				+ ", ncnt=" + ncnt + ", ndate=" + ndate + ", nimg=" + nimg + ", nspo=" + nspo + ", uid=" + uid + "]";
	}
	
	
}
