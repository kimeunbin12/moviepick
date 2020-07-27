package kr.co.moviepick.people;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.utility.PagingVO;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class PeopleCont {
	
	@Autowired
	PeopleDAO dao;
	
	public PeopleCont() {
		System.out.println("---PeopleCont() 객체 생성됨");
	}
	
	//http://localhost:9090/moviepick/people/read.do
	@RequestMapping(value="/people/read.do")
	public ModelAndView read(int pno) {
		ModelAndView mav= new ModelAndView();
		PeopleDTO dto= dao.read(pno);
		if(dto!=null) {
			mav.setViewName("people/read");
		}//if end
		
		mav.addObject("root",Utility.getRoot());
		mav.addObject("dto",dto);
		return mav;
	}

	
	//http://localhost:9090/moviepick/people/list.do
		@RequestMapping("/people/list.do")
		public ModelAndView list(PeopleDTO dto) {
			ModelAndView mav= new ModelAndView();
			mav.setViewName("people/list");
			mav.addObject("root",Utility.getRoot());
			mav.addObject("list",dao.list());

			return mav;
		}//list() emd
		
		
		
		
		@RequestMapping(value="/admin/people.do")
	    public ModelAndView list(PeopleDTO dto,PagingVO vo,
	          @RequestParam(value="nowPage", required=false)String nowPage,
	          @RequestParam(value="cntPerPage", required=false)String cntPerPage)throws Throwable {
	       
	       ModelAndView mav = new ModelAndView();
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
	       
	       System.out.println("어드민토탈: " + total );
	       System.out.println(cntPerPage);
	       System.out.println(nowPage);
	       mav.addObject("paging", vo);
	       mav.addObject("root", Utility.getRoot());
	       mav.addObject("list",dao.list2(dto,vo));
	       mav.addObject("nowpage",nowPage);
	       mav.addObject("cntPerPage", cntPerPage);
	       mav.setViewName("admin/peoplelist_admin");
	    
	       return mav;
	    }//list() end
		
		@RequestMapping(value="/admin/peoplepage.do", method = RequestMethod.GET)
		public ModelAndView listpage1(PeopleDTO dto,PagingVO vo,
				@RequestParam(value="nowPage", required=false)String nowPage,
				@RequestParam(value="cntPerPage", required=false)String cntPerPage,
				@RequestParam(value="col", required = false)String col,
				@RequestParam(value="word", required =false)String word,
				HttpServletRequest req)throws Throwable {
			
			ModelAndView mav = new ModelAndView();

			//珥� 寃뚯떆湲� �닔
			int total=0;
			total=dao.count(col, word);
			//�쁽�옱�럹�씠吏�, �럹�씠吏��떦 蹂댁뿬以� 寃뚯떆湲� �닔
			if (nowPage == null && cntPerPage == null) {
				nowPage = "1";
				cntPerPage = "10";
			} else if (nowPage == null) {
				nowPage = "1";
			} else if (cntPerPage == null) { 
				cntPerPage = "10";
			}
			vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage)); 

			
			System.out.println("어드민검색토탈: " + total );
			System.out.println("어드민검색 페이지"+cntPerPage);
			System.out.println("어드민검색 현재페이지"+nowPage);
			System.out.println("col"+ col);
			
			mav.addObject("paging", vo);
			mav.addObject("root", Utility.getRoot());
			mav.addObject("list",dao.list3(col,word,dto,vo));
			mav.addObject("nowpage",nowPage);
			mav.addObject("cntPerPage", cntPerPage);
			mav.addObject("col",col);
			mav.addObject("word",word);
			mav.setViewName("admin/peoplelist_admin");
		
			return mav;
		}//list() end
	
	@RequestMapping(value="/admin/people_create.do", method=RequestMethod.GET)
	public ModelAndView createForm(PeopleDTO dto) {

		ModelAndView mav=new ModelAndView();
		mav.addObject("root",Utility.getRoot());
		mav.setViewName("admin/people_createForm");
		
		return mav;
	}//creatForm() end
	
	@RequestMapping(value="/admin/people_create.do", method=RequestMethod.POST)
	public ModelAndView createProc(PeopleDTO dto, HttpServletRequest req, HttpServletResponse resp) {
		// /media/createForm.jsp
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/msgView");
		mav.addObject("root",Utility.getRoot());

		//전송된 파일 처리
		//실제 파일은 /media/storage폴더에 저장
		//저장된 파일 관련 정보는 media테이블에 저장
		
		//실제 물리적인 경로
		String basePath= req.getRealPath("/images/people");
		//1)<input type='file' name='posterMF' size='50'></td>    
		//->파일 가져오기
		MultipartFile posterMF = dto.getPosterMF();
		//->파일을 저장하고 리네임된 파일명 반환
		String ppic=UploadSaveManager.saveFileSpring30(posterMF, basePath);
		//->파일명 dto객체에 담기
		dto.setPpic(ppic);
		int cnt=dao.create(dto);
	
		if(cnt==0) {
			mav.addObject("msg1","<p>인물 등록 실패</p>");
			mav.addObject("link1","<input type='button' value='다시시도' class=\"bbs_del_back\" onclick='javascript:history.back()'>");
			mav.addObject("link2","<input type='button' value='그룹목록' class=\"bbs_del_back\" onclick='location.href=\"./people.do\"'>");
			
		}else {
			mav.addObject("msg1","<p>인물 등록 성공</p>");
			mav.addObject("link2","<input type='button' value='그룹목록' class=\"bbs_del_back\" onclick='location.href=\"./people.do\"'>");
			
		}//if end
		
		return mav;
	}//createProc() end
	
	@RequestMapping(value= "/admin/people_update.do", method = RequestMethod.GET)
	public ModelAndView updateForm(PeopleDTO dto) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("admin/people_updateForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto",dao.read(dto.getPno()));
		
		return mav;
	}//updateForm() end
	
	@RequestMapping(value= "/admin/people_update.do", method = RequestMethod.POST)
	public ModelAndView updateProc(PeopleDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/msgView");
		mav.addObject("root", Utility.getRoot());
		
		
		//기존에 저장된 정보 가져오기
		PeopleDTO oldDTO = dao.read(dto.getPno());
		
		String basePath = req.getRealPath("/images/people");
		//  파일을 수정할 것인지?
		MultipartFile posterMF = dto.getPosterMF();
		//System.out.println("지정경로로로"+basePath);
		
		if(posterMF.getSize()>0) {
			//새로운 포스터파일이 전송된경우
		      //기존 파일 삭제				
		      UploadSaveManager.deleteFile(basePath, oldDTO.getPpic());
		      //신규 파일 저장
		      
		      String ppic = UploadSaveManager.saveFileSpring30(posterMF, basePath);
		      dto.setPpic(ppic);     
		    }else {
		      //포스터 파일을 수정하지 않는 경우
		      dto.setPpic(oldDTO.getPpic());
		    }//if end
		
		
		int cnt = dao.update(dto);
		if(cnt==0) {
			mav.addObject("msg1", "<p>수정 실패</p>");
			
			mav.addObject("link1", "<input type='button' class=\"bbs_del_back\" value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' class=\"bbs_del_back\" value='목록' onclick='location.href=\"./people.do\"'>");
		}else {
			mav.addObject("msg1", "<p>수정 성공</p>");
			
			mav.addObject("link2", "<input type='button' class=\"bbs_del_back\" value='목록' onclick='location.href=\"./people.do\"'>");
		}//if end
		
		return mav;
	}//updateProc() end
	
	@RequestMapping(value= "/admin/people_delete.do", method=RequestMethod.GET)
	public ModelAndView delete(PeopleDTO dto) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("admin/people_deleteForm");
		mav.addObject("root",Utility.getRoot());
		//삭제 관련 정보 가져오기
		mav.addObject("dto",dao.read(dto.getPno()));
		return mav;
		
	}//delete() end
	
	@RequestMapping(value="/admin/people_delete.do", method = RequestMethod.POST)
	public ModelAndView deleteProc(PeopleDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/msgView");
		mav.addObject("root", Utility.getRoot());
		
		//삭제하고자 하는 정보 가져오기
		PeopleDTO oldDTO = dao.read(dto.getPno());
		int cnt = dao.delete(dto.getPno());
		if(cnt ==0) {
			mav.addObject("msg1", "<p>삭제 실패</p>");
			mav.addObject("link1", "<input type='button' class=\"bbs_del_back\"  value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' class=\"bbs_del_back\"  value='목록' onclick='location.href=\"./people.do\"'>");
		}else {
			String basepath = req.getRealPath("/images/people");
			
			UploadSaveManager.deleteFile(basepath, oldDTO.getPpic());
			mav.addObject("msg1", "<p>삭제 성공</p>");
			
			mav.addObject("link2", "<input type='button' class=\"bbs_del_back\" value='목록' onclick='location.href=\"./people.do\"'>");
		}//if end
		return mav;
		
	}//deleteProc() end
	
	@RequestMapping(value="/admin/movie-people.do")
	  public ModelAndView moviepeople(PeopleDTO dto, PagingVO vo,
	        @RequestParam(value="nowPage", required=false)String nowPage,
	        @RequestParam(value="cntPerPage", required=false)String cntPerPage)throws Throwable {
	     
	     ModelAndView mav = new ModelAndView();
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
	     
	     System.out.println("총 인물 수: " + total );
	     System.out.println(cntPerPage);
	     System.out.println(nowPage);
	     mav.addObject("paging", vo);
	     mav.addObject("root", Utility.getRoot());
	     mav.addObject("list",dao.list2(dto,vo));
	     mav.addObject("nowpage",nowPage);
	     mav.addObject("cntPerPage", cntPerPage);
	     mav.setViewName("admin/movie-people");
	  
	     return mav;
	  }//list() end

	@RequestMapping(value="/admin/paged.do", method = RequestMethod.GET)
	public ModelAndView listpage(PeopleDTO dto,PagingVO vo,
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


	System.out.println("총 인물 수: " + total );
	System.out.println("페이지 당 "+cntPerPage+"명");
	System.out.println("현재페이지"+nowPage);
	System.out.println("col"+ col);

	mav.addObject("paging", vo);
	mav.addObject("root", Utility.getRoot());
	mav.addObject("list",dao.list3(col,word,dto,vo));
	mav.addObject("nowpage",nowPage);
	mav.addObject("cntPerPage", cntPerPage);
	mav.addObject("col",col);
	mav.addObject("word",word);
	mav.setViewName("admin/movie-people");

	return mav;
	}//list() end
}
