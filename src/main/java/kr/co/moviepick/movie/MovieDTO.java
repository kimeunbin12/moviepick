package kr.co.moviepick.movie;

import org.springframework.web.multipart.MultipartFile;

public class MovieDTO {
	private int mno;
	private String msub;
	private String mposter;
	private String mgenre;
	private int mgrade;
	private int mrun;
	private String mopen;
	private String mend;
	private String mdir;
	private String mact;
	private String msum;
	private String mtrail;
	private String mwhy;
	private String mfile;
	private long msize;
	private int msta;
	private int dno;
	private String ddate;
	private String uid;
	//-----------------------
	private MultipartFile posterMF;
	
	private MultipartFile filenameMF;
	
	private MultipartFile  trailMF;
	
	public MultipartFile getTrailMF() {
    return trailMF;
  }

  public void setTrailMF(MultipartFile trailMF) {
    this.trailMF = trailMF;
  }

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
	public MovieDTO() {}

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

	public String getMposter() {
		return mposter;
	}

	public void setMposter(String mposter) {
		this.mposter = mposter;
	}

	public String getMgenre() {
		return mgenre;
	}

	public void setMgenre(String mgenre) {
		this.mgenre = mgenre;
	}

	public int getMgrade() {
		return mgrade;
	}

	public void setMgrade(int mgrade) {
		this.mgrade = mgrade;
	}

	public int getMrun() {
		return mrun;
	}

	public void setMrun(int mrun) {
		this.mrun = mrun;
	}

	public String getMopen() {
		return mopen;
	}

	public void setMopen(String mopen) {
		this.mopen = mopen;
	}

	public String getMend() {
		return mend;
	}

	public void setMend(String mend) {
		this.mend = mend;
	}

	public String getMdir() {
		return mdir;
	}

	public void setMdir(String mdir) {
		this.mdir = mdir;
	}

	public String getMact() {
		return mact;
	}

	public void setMact(String mact) {
		this.mact = mact;
	}

	public String getMsum() {
		return msum;
	}

	public void setMsum(String msum) {
		this.msum = msum;
	}

	public String getMtrail() {
		return mtrail;
	}

	public void setMtrail(String mtrail) {
		this.mtrail = mtrail;
	}

	public String getMwhy() {
		return mwhy;
	}

	public void setMwhy(String mwhy) {
		this.mwhy = mwhy;
	}

	public String getMfile() {
		return mfile;
	}

	public void setMfile(String mfile) {
		this.mfile = mfile;
	}

	public long getMsize() {
		return msize;
	}

	public void setMsize(long msize) {
		this.msize = msize;
	}

	public int getMsta() {
		return msta;
	}

	public void setMsta(int msta) {
		this.msta = msta;
	}

	
	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getDdate() {
		return ddate;
	}

	public void setDdate(String ddate) {
		this.ddate = ddate;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	

	@Override
	public String toString() {
		return "MovieDTO [mno=" + mno + ", msub=" + msub + ", mposter=" + mposter + ", mgenre=" + mgenre + ", mgrade="
				+ mgrade + ", mrun=" + mrun + ", mopen=" + mopen + ", mend=" + mend + ", mdir=" + mdir + ", mact="
				+ mact + ", msum=" + msum + ", mtrail=" + mtrail + ", mwhy=" + mwhy + ", mfile=" + mfile + ", msize="
				+ msize + ", msta=" + msta + ", dno=" + dno + ", ddate=" + ddate + ", uid=" + uid + ", posterMF="
				+ posterMF + ", filenameMF=" + filenameMF + ", trailMF=" + trailMF + "]";
	}
	
	
}
