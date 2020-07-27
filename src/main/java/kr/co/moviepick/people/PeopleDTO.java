package kr.co.moviepick.people;

import org.springframework.web.multipart.MultipartFile;

public class PeopleDTO {
	private int pno;
	private String pname;
	private String country;
	private String pbirth;
	private String pgender;
	private String ppic;
	
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
	
	public PeopleDTO() {}
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPbirth() {
		return pbirth;
	}
	public void setPbirth(String pbirth) {
		this.pbirth = pbirth;
	}
	public String getPgender() {
		return pgender;
	}
	public void setPgender(String pgender) {
		this.pgender = pgender;
	}
	public String getPpic() {
		return ppic;
	}
	public void setPpic(String ppic) {
		this.ppic = ppic;
	}
	@Override
	public String toString() {
		return "PeopleDTO [pno=" + pno + ", pname=" + pname + ", country=" + country + ", pbirth=" + pbirth
				+ ", pgender=" + pgender + ", ppic=" + ppic + "]";
	}
	
	
}
