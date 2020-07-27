
package kr.co.moviepick.movie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviepick.bbs.BbsDTO;
import kr.co.moviepick.download.DownloadDAO;
import kr.co.moviepick.download.DownloadDTO;
import kr.co.moviepick.people.PeopleDAO;
import kr.co.moviepick.people.PeopleDTO;
import kr.co.moviepick.review.ReviewDAO;
import kr.co.moviepick.review.ReviewDTO;
import net.utility.PagingVO;
import net.utility.PagingVO2;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class MovieCont {
	
	@Autowired
	MovieDAO dao;
	
	@Autowired
	PeopleDAO pedao;
	
	@Autowired
	ReviewDAO redao;
	
	@Autowired
	DownloadDAO ddao;
	
	public MovieCont() {
		System.out.println("---MovieCont()객체 생성됨...");
	}
	//http://localhost:9090/moviepick/movie/list.do
	
	@RequestMapping(value="/movie/list.do", method = RequestMethod.GET)
    public ModelAndView list(MovieDTO dto,PagingVO vo,
          @RequestParam(value="nowPage", required=false)String nowPage,
          @RequestParam(value="cntPerPage", required=false)String cntPerPage,ReviewDTO redto,
          @RequestParam(value="col", required = false)String col,
			@RequestParam(value="word", required =false)String word)throws Throwable {

			
       ModelAndView mav = new ModelAndView();
			
			//평점 평균 구하기

       int total=0;
       total=dao.getArticleCount();

    
       if (nowPage == null && cntPerPage == null) {
          nowPage = "1";
          cntPerPage = "12";
       } else if (nowPage == null) {
          nowPage = "1";
       } else if (cntPerPage == null) { 
          cntPerPage = "12";
       }
       vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
       

       System.out.println("총 게시물 갯수: " + total );
       System.out.println(cntPerPage);
       System.out.println(nowPage);
       mav.addObject("paging", vo);
       mav.addObject("root", Utility.getRoot());
       mav.addObject("list",dao.list2(dto,vo));
       mav.addObject("nowpage",nowPage);
       mav.addObject("cntPerPage", cntPerPage);
		mav.addObject("col",col);
		mav.addObject("word",word);
       mav.setViewName("movie/list");
    
       return mav;
    }//list() end
	
	
	@RequestMapping(value="/movie/listpage.do", method = RequestMethod.GET)
	public ModelAndView listpage(MovieDTO dto,PagingVO vo,
			@RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage,
			@RequestParam(value="col", required = false)String col,
			@RequestParam(value="word", required =false)String word,
			HttpServletRequest req)throws Throwable {
		
		ModelAndView mav = new ModelAndView();

		//총 게시글 수
		int total=0;
		total=dao.count(col, word);
		//현재페이지, 페이지당 보여줄 게시글 수
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "12";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "12";
		}
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage)); 

		
		System.out.println("총 게시물 갯수: " + total );
		System.out.println("페이지당갯수"+cntPerPage);
		System.out.println("현재페이지"+nowPage);
		System.out.println("col"+ col);
		System.out.println("word:"+word);
		mav.addObject("paging", vo);
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list",dao.list3(col,word,dto,vo));
		mav.addObject("nowpage",nowPage);
		mav.addObject("cntPerPage", cntPerPage);
		mav.addObject("col",col);
		mav.addObject("word",word);

		mav.setViewName("movie/list");
	
		return mav;
	}//list() end
	
	//http://localhost:9090/moviepick/movie/read.do
	@RequestMapping(value="/movie/read.do", method=RequestMethod.GET)
	public ModelAndView read(int mno, MovieDTO dto,PeopleDTO peodto, ReviewDTO redto, PagingVO vo,PagingVO2 vo2,HttpServletRequest req, HttpSession session, HttpServletResponse resp
			,@RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage,
			@RequestParam(value="nowPage2", required=false)String nowPage2,
			@RequestParam(value="cntPerPage2", required=false)String cntPerPage2,
			@RequestParam(value="col", required = false)String col,
			@RequestParam(value="word", required =false)String word) {
		ModelAndView mav = new ModelAndView();
		
		dto = dao.read(dto.getMno());
		
		int totalrv=0;
	    totalrv=dao.getArticleCount2(mno);

	    
	       if (nowPage2 == null && cntPerPage2 == null) {
	          nowPage2 = "1";
	          cntPerPage2 = "10";
	       } else if (nowPage2 == null) {
	          nowPage = "1";
	       } else if (cntPerPage2 == null) { 
	          cntPerPage = "10";
	       }
		String uid=(String)session.getAttribute("uid");		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		mav.addObject("peolist", dao.peolist(dto, peodto));
		mav.addObject("peolist2", dao.peolist2(dto, peodto));
		mav.addObject("peodto", pedao.list());
		System.out.println("사람디티오"+peodto);
		//System.out.println(dao.peolist(dto, peodto));
		//mdir, mact
		//System.out.println(dto.getMdir());
		//,로 나뉘진거 배열로 저장
		//String[] stringmdir = dto.getMdir().split(",");
		//int[] intmdir = new int[stringmdir.length];
		//for(int i=0;i<stringmdir.length; i++){ intmdir[i] = Integer.parseInt(stringmdir[i]); }
		//System.out.println(intmdir[0]+1);
		//System.out.println(intmdir[1]+1);
		
		
	    vo2 = new PagingVO2(totalrv, Integer.parseInt(nowPage2), Integer.parseInt(cntPerPage2));
	       
		
		
		//mav.addObject("aver", dao.aver());
		//System.out.println(dao.aver());
		//System.out.println(Float.class.isInstance(dao.aver(redto)));
		//부모글번호(mno)
	    mav.addObject("paging",vo);
	    mav.addObject("paging2", vo2);
	    mav.addObject("nowpage",vo.getNowPage());
		mav.addObject("cntPerPage", vo.getCntPerPage());
		mav.addObject("col",col);
		mav.addObject("word",word);
		mav.addObject("review", dao.review(redto,vo2));
		mav.addObject("mno", dto.getMno());
		System.out.println("리뷰갯수"+totalrv);
		//평점 평균 구하기
		float cnt = dao.aver(mno);
		mav.addObject("aver",Math.round(cnt*10)/10.0);
		//System.out.println(Math.round(cnt*10)/10.0);
		return mav;
	}//read() end
	
	@RequestMapping(value="/movie/read2.do",method=RequestMethod.GET )
	public ModelAndView read2(int mno, MovieDTO dto,PeopleDTO peodto, ReviewDTO redto, PagingVO vo,PagingVO2 vo2,HttpServletRequest req, HttpSession session, HttpServletResponse resp
			,@RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage,
			@RequestParam(value="nowPage2", required=false)String nowPage2,
			@RequestParam(value="cntPerPage2", required=false)String cntPerPage2,
			@RequestParam(value="col", required = false)String col,
			@RequestParam(value="word", required =false)String word) {
		ModelAndView mav = new ModelAndView();
		
		dto = dao.read(dto.getMno());
		
		int totalrv=0;
	    totalrv=dao.getArticleCount2(mno);

	    
	       if (nowPage2 == null && cntPerPage2 == null) {
	          nowPage2 = "1";
	          cntPerPage2 = "10";
	       } else if (nowPage2 == null) {
	          nowPage = "1";
	       } else if (cntPerPage2 == null) { 
	          cntPerPage = "10";
	       }
		String uid=(String)session.getAttribute("uid");		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		mav.addObject("peolist", dao.peolist(dto, peodto));
		mav.addObject("peolist2", dao.peolist2(dto, peodto));
		mav.addObject("peodto", pedao.list());
		System.out.println("사람디티오"+peodto);
		//System.out.println(dao.peolist(dto, peodto));
		//mdir, mact
		//System.out.println(dto.getMdir());
		//,로 나뉘진거 배열로 저장
		//String[] stringmdir = dto.getMdir().split(",");
		//int[] intmdir = new int[stringmdir.length];
		//for(int i=0;i<stringmdir.length; i++){ intmdir[i] = Integer.parseInt(stringmdir[i]); }
		//System.out.println(intmdir[0]+1);
		//System.out.println(intmdir[1]+1);
		
		
	    vo2 = new PagingVO2(totalrv, Integer.parseInt(nowPage2), Integer.parseInt(cntPerPage2));
	       
		
		
		//mav.addObject("aver", dao.aver());
		//System.out.println(dao.aver());
		//System.out.println(Float.class.isInstance(dao.aver(redto)));
		//부모글번호(mno)
	    mav.addObject("paging",vo);
	    mav.addObject("paging2", vo2);
	    mav.addObject("nowpage",vo.getNowPage());
		mav.addObject("cntPerPage", vo.getCntPerPage());
		mav.addObject("col",col);
		mav.addObject("word",word);
		mav.addObject("review", dao.review(redto,vo2));
		mav.addObject("mno", dto.getMno());
		System.out.println("리뷰갯수"+totalrv);
		//평점 평균 구하기
		float cnt = dao.aver(mno);
		mav.addObject("aver",Math.round(cnt*10)/10.0);
		//System.out.println(Math.round(cnt*10)/10.0);
		mav.setViewName("movie/read2");
		return mav;
	}//read() end
	
	//댓글 입력 
	@RequestMapping(value="/movie/reCreate.do", method = RequestMethod.GET)
	public ModelAndView reCreatePost(ReviewDTO dto ,HttpServletRequest req, HttpSession session, HttpServletResponse resq) {
		String uid=(String)session.getAttribute("uid");
		String b = req.getParameter("mno");
		int mno = Integer.parseInt(b);
		
		//System.out.println(uid);
		//System.out.println(dto.getRstar());
		String f = req.getParameter("rstar");
		float rstar = Float.parseFloat(f);
		//String rstar= req.getParameter("rstar");
		//System.out.println(Float.class.isInstance(rstar));
		//System.out.println(dto.getRstar());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/msgView");
		mav.addObject("root",Utility.getRoot());
		
		int cnt = dao.recreate(dto);
		//if (rstar==null) {
			//mav.addObject("alert('평점을 입력해 주세요')");
		//}else 
		if (cnt==0) {
		mav.addObject("msg1","<script>\r\n" + 
	            " alert(\"실패\");\r\n" + 
	            " location.href=\"javascript:history.back()\";\r\n" + 
	            "</script>"); 
		} else {
			mav.addObject("msg1", "<script>\r\n" + 
		            " alert(\"등록되었습니다.\");\r\n" + 
		            " location.href=\"../movie/read.do?mno="+mno+"\";\r\n" + 
		            "</script>"); 
		}//<script>alert("등록되었습니다."); onclick="location.href='../movie/read.do?mno="+${dto.mno}+"';</script>"
		return mav;
	}
	//--------------------------------------------
	//admin 영역
	
	@RequestMapping(value="/admin/movie.do")
	  public ModelAndView list1(MovieDTO dto,PagingVO vo,
	          @RequestParam(value="nowPage", required=false)String nowPage,
	          @RequestParam(value="cntPerPage", required=false)String cntPerPage,ReviewDTO redto)throws Throwable {

				
	       ModelAndView mav = new ModelAndView();
				
				//평점 평균 구하기

	       int total=0;
	       total=dao.getArticleCount();

	    
	       if (nowPage == null && cntPerPage == null) {
	          nowPage = "1";
	          cntPerPage = "10";
	       } else if (nowPage == null) {
	          nowPage = "1";
	       } else if (cntPerPage == null) { 
	          cntPerPage = "10";
	       }
	       vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
	       

	       System.out.println("총 게시물 갯수: " + total );
	       System.out.println(cntPerPage);
	       System.out.println(nowPage);
	       mav.addObject("paging", vo);
	       mav.addObject("root", Utility.getRoot());
	       mav.addObject("list",dao.list2(dto,vo));
	       mav.addObject("nowpage",nowPage);
	       mav.addObject("cntPerPage", cntPerPage);
	       mav.setViewName("admin/movie");
	    
	       return mav;
	    }//list() end
	
	@RequestMapping(value="/admin/moviepage.do", method = RequestMethod.GET)
	public ModelAndView listpage1(MovieDTO dto,PagingVO vo,
			@RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage,
			@RequestParam(value="col", required = false)String col,
			@RequestParam(value="word", required =false)String word,
			HttpServletRequest req)throws Throwable {
		
		ModelAndView mav = new ModelAndView();

		//총 게시글 수
		int total=0;
		total=dao.count(col, word);
		//현재페이지, 페이지당 보여줄 게시글 수
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage)); 

		
		System.out.println("총 게시물 갯수: " + total );
		System.out.println("페이지당갯수"+cntPerPage);
		System.out.println("현재페이지"+nowPage);
		System.out.println("col"+ col);
		mav.addObject("paging", vo);
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list",dao.list3(col,word,dto,vo));
		mav.addObject("nowpage",nowPage);
		mav.addObject("cntPerPage", cntPerPage);
		mav.addObject("col",col);
		mav.addObject("word",word);
		mav.setViewName("admin/movie");
	
		return mav;
	}//list() end
		
	
		@RequestMapping(value = "/admin/movie_create.do", method = RequestMethod.GET )
		public String  createForm() {
		  return "admin/movie_createForm";
		}//createForm() end
		
		@RequestMapping(value = "/admin/movie_create.do", method = RequestMethod.POST )
		public ModelAndView cretaeProc(MovieDTO dto, HttpServletRequest req, HttpServletResponse reqs) {
		  ModelAndView mav = new ModelAndView();
		  mav.setViewName("admin/msgView");
		  mav.addObject("root", Utility.getRoot());
		  String b = req.getParameter("msta");
	    int msta = Integer.parseInt(b);
		  // 실제 물리적인 경로
		  String basePath = req.getRealPath("/images/poster");
		//1)<input type='file' name = 'posterMF'>
	    // -> 파일 가져오기
	    MultipartFile posterMF = dto.getPosterMF();
	    // -> 파일을 저장하고 리네임된 파일명 변환
	    String mposter = UploadSaveManager.saveFileSpring30(posterMF, basePath);
	    // -> 파일명 dto객체에 담기
	    dto.setMposter(mposter);
	    
	    String basePath2 = req.getRealPath("/mp4/mfile");
	  //2)<input type='mfile' name='filenameMF'>
	    MultipartFile filenameMF = dto.getFilenameMF();
	    String mfile = UploadSaveManager.saveFileSpring30(filenameMF, basePath2);
	    dto.setMfile(mfile);
	    dto.setMsize(filenameMF.getSize()/1048576);
	    
	    String basePath3 = req.getRealPath("/mp4/trailer");
	  //3)<input type='trail' name='trailMF'>
	    MultipartFile trailMF = dto.getTrailMF();
	    String mtrail = UploadSaveManager.saveFileSpring30(trailMF, basePath3);
	    //dto.setFilenameMF(filenameMF);
	    dto.setMtrail(mtrail);
	    //dto.setFilename(trail);
	    
	    int cnt = dao.create(dto);
	    if(cnt==0) {
	      mav.addObject("msg1", "<p>등록실패</p>");
	      mav.addObject("link1", "<input type='button' class=\"bbs_del_back\" value='다시 시도' onclick='javascript:history.back()'>");
	      mav.addObject("link2", "<input type='button' class=\"bbs_del_back\" value='등록 취소' onclick='location.href=\"../admin/movie.do\"'>");
	    }else {
	      mav.addObject("msg1", "<p>등록성공</p>");
	      mav.addObject("link2", "<input type='button' class=\"bbs_del_back\" value='목록' onclick='location.href=\"../admin/movie.do\"'>");
	    }//if end
	    
		  return mav;
		}
		
		
		 @RequestMapping(value="/admin/movie_update.do",  method = RequestMethod.GET)
		  public ModelAndView update(MovieDTO dto) {
		    ModelAndView mav=new ModelAndView();
		    dto = dao.read(dto.getMno());
		    mav.setViewName("admin/movie_updateForm");
		    mav.addObject("root", Utility.getRoot());
		    mav.addObject("dto", dao.read(dto.getMno())); 
		    return mav;
		  }//movie_update() end
		 
		 @RequestMapping(value = "/admin/movie_update.do", method = RequestMethod.POST )
		  public ModelAndView updateProc(MovieDTO dto, HttpServletRequest req, HttpServletResponse reqs) {
		    ModelAndView mav = new ModelAndView();
		    mav.setViewName("admin/msgView");
		    mav.addObject("root", Utility.getRoot());
		    // 실제 물리적인 경로
		    String basePath = req.getRealPath("/images/poster");
		    //기존에 저장된 정보 가져오기
		    MovieDTO oldDTO =dao.read(dto.getMno());
		  //1)
		    // -> 파일 가져오기
		    MultipartFile posterMF = dto.getPosterMF();
		    if(posterMF.getSize()>0) {
		      //새로운 포스터 파일이 전송된 경우
		      //기존 파일 삭제
		      UploadSaveManager.deleteFile(basePath, oldDTO.getMposter());
		      //신규파일 저장
		      String mposter = UploadSaveManager.saveFileSpring30(posterMF, basePath);
		      dto.setMposter(mposter);
		    } else {
		      //포스터 파일을 수정하지 않는 경우
		      dto.setMposter(oldDTO.getMposter());
		    }//if end
		      
		    String basePath2 = req.getRealPath("/mp4/mfile");
		  //2)
		    MultipartFile filenameMF = dto.getFilenameMF();
		    if(filenameMF.getSize()>0) {	    	    
		    String mfile = UploadSaveManager.saveFileSpring30(filenameMF, basePath2);
		    dto.setMfile(mfile);
		    dto.setMsize(filenameMF.getSize()/1048576);
		    } else {
		      dto.setMfile(oldDTO.getMfile());
		      dto.setMsize(oldDTO.getMsize());
		    }//if end
		    
		    String basePath3 = req.getRealPath("/mp4/trailer");
		  //3)
		    MultipartFile trailMF = dto.getTrailMF();
		    if(trailMF.getSize()>0) {
		    String mtrail = UploadSaveManager.saveFileSpring30(trailMF, basePath3);
		    //dto.setFilenameMF(filenameMF);
		    dto.setMtrail(mtrail);
		    } else {
		      dto.setMtrail(oldDTO.getMtrail());	      
		    }
		    //dto.setFilename(trail);
		    
		    int cnt = dao.update(dto);
		    if(cnt==0) {
		      mav.addObject("msg1", "<p>수정실패</p>");
		      mav.addObject("link1", "<input type='button' class=\"bbs_del_back\" value='다시 시도' onclick='javascript:history.back()'>");
		      mav.addObject("link2", "<input type='button' class=\"bbs_del_back\" value='수정 취소' onclick='location.href=\"../admin/movie.do\"'>");
		    }else {
		      mav.addObject("msg1", "<p>수정성공</p>");
		      mav.addObject("link2", "<input type='button' value='목록' class=\"bbs_del_back\" onclick='location.href=\"../admin/movie.do\"'>");
		    }//if end
		    
		    return mav;
		  }//updateProc() end
		 
		 @RequestMapping(value="/admin/movie_delete.do", method = RequestMethod.GET)
	   public ModelAndView deleteForm(MovieDTO dto) {
	     ModelAndView mav = new ModelAndView();
	     mav.setViewName("admin/movie_deleteForm");
	     mav.addObject("root", Utility.getRoot());
	     mav.addObject("mno", dto.getMno());
	     
	     //삭제관련정보 가져오기
	     mav.addObject("dto",dao.read(dto.getMno()));
	     
	     return mav;
	   }//deleteForm() end
	   
	   
	   @RequestMapping(value="/admin/movie_delete.do", method = RequestMethod.POST)
	   public ModelAndView deleteProc(MovieDTO dto, HttpServletRequest req) {
	     ModelAndView mav = new ModelAndView();
	     mav.setViewName("admin/msgView");
	     mav.addObject("root", Utility.getRoot());
	     
	     //삭제하고자 하는 정보 가져오기
	     MovieDTO oldDTO = dao.read(dto.getMno());
	     int cnt = dao.delete(dto.getMno());
	     if(cnt ==0) {
	       mav.addObject("msg1", "<p>삭제 실패</p>");
	       mav.addObject("link2", "<input type='button' value='삭제취소'class=\"bbs_del_back\" onclick='location.href=\"../admin/movie.do\"'>");
	       mav.addObject("link1","<input type='button' value='다시시도' class=\"bbs_del_back\" onclick='javascript:history.back()'>");
	     }else {
	       String basePath = req.getRealPath("/images/poster");
	       String basePath2 = req.getRealPath("/mp4/mfile");
	       String basePath3 = req.getRealPath("/mp4/trailer");
	       UploadSaveManager.deleteFile(basePath, oldDTO.getMposter());
	       UploadSaveManager.deleteFile(basePath2, oldDTO.getMfile());
	       UploadSaveManager.deleteFile(basePath3, oldDTO.getMtrail());
	       mav.addObject("msg1", "<p>삭제 성공</p>");
	       mav.addObject("link2", "<input type='button' value='목록' class=\"bbs_del_back\" onclick='location.href=\"../admin/movie.do\"'>");
	     }//if end
	     return mav;
	     
	   }//deleteProc() end
	   
		 @RequestMapping(value="/movie/reviewdelete.do")
		   public ModelAndView reviewdeleteForm(ReviewDTO redto) {
		     ModelAndView mav = new ModelAndView();
		     mav.setViewName("movie/reviewdeleteForm");
		     mav.addObject("root", Utility.getRoot());
		     //삭제관련정보 가져오기
		     mav.addObject("dto",dao.reviewread(redto.getRno()));
		     
		     return mav;
		   }//deleteForm() end
	   
	   @RequestMapping(value="/movie/reviewdelete.do", method = RequestMethod.POST)
	   public ModelAndView reviewdeleteForm(MovieDTO dto,ReviewDTO redto) {
	     ModelAndView mav = new ModelAndView();
	     mav.setViewName("movie/msgView");
	     mav.addObject("root", Utility.getRoot());
	     mav.addObject("rno", redto.getRno());
	     mav.addObject("mno",dto.getMno());
	     System.out.println(redto.getMno());
	     //삭제관련정보 가져오기
	     redao.read(redto.getRno());
	     int cnt= dao.deletereview(redto.getRno());
	     if(cnt==0) {
	    	 
	     }else {
	    	 mav.addObject("msg1", "<script>\r\n" + 
			            " alert(\"삭제되었습니다.\");\r\n" + 
			            " location.href=\"../movie/read.do?mno="+redto.getMno()+"\";\r\n" + 
			            "</script>"); 
	     }
	     
	     return mav;
	   }//deleteForm() end
	   /*
	   @RequestMapping(value="/mypage/down.do")
		public ModelAndView createForm(DownloadDTO ddto, MovieDTO dto,HttpSession session,
	            @RequestParam(value="nowPage", required=false)String nowPage,
	            @RequestParam(value="cntPerPage", required=false)String cntPerPage, PagingVO vo) throws Exception{
			String uid=(String)session.getAttribute("uid");
			ModelAndView mav= new ModelAndView();
			
			int total=0;
			total=ddao.getArticleCount(uid);

			 if (nowPage == null && cntPerPage == null) {
		            nowPage = "1";
		            cntPerPage = "10";
		         } else if (nowPage == null) {
		            nowPage = "1";
		         } else if (cntPerPage == null) { 
		            cntPerPage = "10";
		         }
			 
			 System.out.println("파일이름"+dto.getMfile());
			 vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			mav.addObject("list",dao.readdown(ddto, uid,vo));
			mav.addObject("root",Utility.getRoot());
			mav.setViewName("mypage/down");
			return mav;
		}//createForm() end
		*/
	   
	   @RequestMapping(value="/movie/down.do", method = RequestMethod.POST)
		public ModelAndView createProc(DownloadDTO dto,HttpServletRequest req, HttpSession session, HttpServletResponse resp) {
			String uid=(String)session.getAttribute("uid");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("movie/msgView");
			mav.addObject("root", Utility.getRoot());
			//-> 실제 파일은  /media/storage폴더에 저장
			//-> 저장된 파일관련 정보는 media테이블에 저장
			
			int cnt = dao.create(dto);
			if (cnt==0) {
				 mav.addObject("msg1", "<script>\r\n" + 
				            " alert(\"결제실패\");\r\n" + 
				            " location.href='javascript:history.back()' " + 
				            "</script>"); 
			}else {
				 mav.addObject("msg1", "<script>\r\n" + 
				            " alert(\"결제성공\");\r\n" + 
				            " location.href=\"../mypage/down.do\";\r\n" + 
				            "</script>"); 
			}//if end
			
			return mav;
		}//createForm() end
}
