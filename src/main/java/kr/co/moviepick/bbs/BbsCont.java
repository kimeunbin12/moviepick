package kr.co.moviepick.bbs;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviepick.bbs.BbsDTO;
import kr.co.moviepick.movie.MovieDAO;
import kr.co.moviepick.movie.MovieDTO;
import kr.co.moviepick.review.ReviewDTO;
import net.utility.PagingVO;
import net.utility.PagingVO2;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class BbsCont {

	@Autowired
	BbsDAO dao;
	
	@Autowired
	MovieDAO mdao;
	
	public BbsCont() {
		System.out.println("---BbsCont()객체생성됨");
	}
	
	//http://localhost:9090/moviepick/bbs/create.do
		@RequestMapping(value="/bbs/create.do", method = RequestMethod.GET)
		public String createForm() {
			// /bbs/createForm.jsp
			return "bbs/createForm";
		}//createForm() end
	
		@RequestMapping(value="/bbs/create.do", method = RequestMethod.POST)
		public ModelAndView createProc(BbsDTO dto,MultipartHttpServletRequest mreq,HttpServletRequest req, HttpSession session, HttpServletResponse resp) {
			String uid=(String)session.getAttribute("uid");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("bbs/msgView");
			mav.addObject("root", Utility.getRoot());
			//-> 실제 파일은  /media/storage폴더에 저장
			//-> 저장된 파일관련 정보는 media테이블에 저장
			
			// 실제 물리적인 경로
			String basePath = mreq.getRealPath("/images/netizen");
			
			//1)<input type='file' name = 'posterMF'>
			// -> 파일 가져오기
			MultipartFile posterMF = dto.getPosterMF();
			// -> 파일을 저장하고 리네임된 파일명 변환
			String nimg = UploadSaveManager.saveFileSpring30(posterMF, basePath);
			// -> 파일명 dto객체에 담기
			dto.setNimg(nimg);
			int cnt = dao.create(dto);
			if (cnt==0) {
				mav.addObject("msg1", "<p>등록 실패</p>");
				mav.addObject("link1", "<input type='button' class='bbs_create_rebtn' value='다시시도' onclick='javascript:history.back()'>");
				mav.addObject("link2", "<input type='button' class='bbs_create_libtn' value='목록' onclick='location.href=\"list.do\"'>");
			}else {
				mav.addObject("msg1", "<p>작성 완료</p>");
				mav.addObject("link1", "<input type='button' class='bbs_create_gobtn' value='계속작성' onclick='location.href=\"create.do\"'>");
				mav.addObject("link2", "<input type='button' class='bbs_create_libtn' value='목록' onclick='location.href=\"list.do\"'>");
			}//if end
			
			return mav;
		}//createForm() end
	
	
		@RequestMapping(value="/bbs/list.do")
	      public ModelAndView list(BbsDTO dto,PagingVO vo,
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
	         
	         System.out.println("총 게시물 갯수: " + total );
	         System.out.println(cntPerPage);
	         System.out.println(nowPage);
	         mav.addObject("paging", vo);
	         mav.addObject("root", Utility.getRoot());
	         mav.addObject("list",dao.list2(dto,vo));
	         mav.addObject("nowpage",nowPage);
	         mav.addObject("cntPerPage", cntPerPage);
	         mav.setViewName("bbs/bbsList");
	      
	         return mav;
	      }//list() end
		
		@RequestMapping(value="/bbs/listpage.do", method = RequestMethod.GET)
		public ModelAndView listpage(BbsDTO dto,PagingVO vo,
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
			mav.setViewName("bbs/bbsList");
		
			return mav;
		}//list() end
		
		@RequestMapping(value="/mypage/bbslist.do")
	      public ModelAndView myapgelist(BbsDTO dto,PagingVO vo,PagingVO2 vo2,MovieDTO mdto,
	            @RequestParam(value="nowPage", required=false)String nowPage,
	            @RequestParam(value="cntPerPage", required=false)String cntPerPage,
	            @RequestParam(value="nowPage2", required=false)String nowPage2,
	            @RequestParam(value="cntPerPage2", required=false)String cntPerPage2, HttpSession session
	           )throws Throwable {
	         
			String uid=(String)session.getAttribute("uid");
	         ModelAndView mav = new ModelAndView();
	         int total=0;
	         total=dao.getArticleCountmypage(uid);
	         
	         int total2=0;
	         total2=dao.getArticleCountmypagereview(uid);

	      
	         if (nowPage == null && cntPerPage == null) {
	            nowPage = "1";
	            cntPerPage = "10";
	         } else if (nowPage == null) {
	            nowPage = "1";
	         } else if (cntPerPage == null) { 
	            cntPerPage = "10";
	         }
	         if (nowPage2 == null && cntPerPage2 == null) {
		            nowPage2 = "1";
		            cntPerPage2 = "10";
		         } else if (nowPage2 == null) {
		            nowPage2 = "1";
		         } else if (cntPerPage2 == null) { 
		            cntPerPage2 = "10";
		         }
	         vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
	         vo2 = new PagingVO2(total2, Integer.parseInt(nowPage2), Integer.parseInt(cntPerPage2));
	         
	         System.out.println("총 게시물 갯수: " + total );
	         System.out.println(cntPerPage);
	         System.out.println(nowPage);
	         mav.addObject("paging", vo);
	         mav.addObject("paging2", vo2);
	         mav.addObject("root", Utility.getRoot());
	         mav.addObject("list",dao.mypagelist(dto,vo,uid));
	         mav.addObject("relist",dao.mypagereview(mdto,vo2,uid));
	         mav.addObject("nowpage",nowPage);
	         mav.addObject("cntPerPage", cntPerPage);
	         mav.setViewName("mypage/mypagebbslist");
	      
	         return mav;
	      }//list() end
		
		
		
		@RequestMapping(value= "/bbs/read.do" , method = RequestMethod.GET)
		public ModelAndView read(int nno,PagingVO vo,
				@RequestParam(value="nowPage", required=false)String nowPage,
				@RequestParam(value="cntPerPage", required=false)String cntPerPage,
				@RequestParam(value="col", required = false)String col,
				@RequestParam(value="word", required =false)String word,HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("bbs/bbsRead");
			
			System.out.println(vo.getNowPage());
			System.out.println(col);
			BbsDTO dto = dao.read(nno);
			dao.updatecnt(nno);
			mav.addObject("root", Utility.getRoot());
			mav.addObject("dto", dto);
			mav.addObject("nowPage",vo.getNowPage());
			mav.addObject("cntPerPage",vo.getCntPerPage());
			mav.addObject("col",col);
			mav.addObject("word",word);
			mav.addObject("str",Utility.getConvertCharTextArea(dto.getNcon()));
			return mav;
		}//read() end
		
		@RequestMapping(value= "/bbs/read2.do" , method = RequestMethod.GET)
		public ModelAndView read2(int nno,PagingVO vo,
				@RequestParam(value="nowPage", required=false)String nowPage,
				@RequestParam(value="cntPerPage", required=false)String cntPerPage,
				@RequestParam(value="col", required = false)String col,
				@RequestParam(value="word", required =false)String word,HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("bbs/bbsRead2");
			
			System.out.println(vo.getNowPage());
			System.out.println(col);
			BbsDTO dto = dao.read(nno);
			dao.updatecnt(nno);
			mav.addObject("root", Utility.getRoot());
			mav.addObject("dto", dto);
			mav.addObject("nowPage",vo.getNowPage());
			mav.addObject("cntPerPage",vo.getCntPerPage());
			mav.addObject("col",col);
			mav.addObject("word",word);
			mav.addObject("str",Utility.getConvertCharTextArea(dto.getNcon()));
			return mav;
		}//read() end
		
		@RequestMapping(value= "/bbs/update.do", method = RequestMethod.GET)
		public ModelAndView updateForm(BbsDTO dto) {
			ModelAndView mav= new ModelAndView();
			mav.setViewName("bbs/updateForm");
			mav.addObject("root", Utility.getRoot());
			mav.addObject("dto", dao.read(dto.getNno()) );
			
			return mav;
		}//updateForm() end
		
		@RequestMapping(value= "/bbs/update.do", method = RequestMethod.POST)
		public ModelAndView updateProc(BbsDTO dto, HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("bbs/msgView");
			mav.addObject("root", Utility.getRoot());
			
			String basePath = req.getRealPath("/images/netizen");
			//기존에 저장된 정보 가져오기
			BbsDTO oldDTO = dao.read(dto.getNno());
			//  파일을 수정할 것인지?
			MultipartFile posterMF = dto.getPosterMF();
			//System.out.println("지정경로로로"+basePath);
			
			if(posterMF.getSize()>0) {
				//새로운 포스터파일이 전송된경우
			      //기존 파일 삭제				
			      UploadSaveManager.deleteFile(basePath, oldDTO.getNimg());
			      //신규 파일 저장
			      
			      String nimg = UploadSaveManager.saveFileSpring30(posterMF, basePath);
			      dto.setNimg(nimg);      
			    }else {
			      //포스터 파일을 수정하지 않는 경우
			      dto.setNimg(oldDTO.getNimg());
			    }//if end
			
			
			int cnt = dao.update(dto);
			if(cnt==0) {
				mav.addObject("msg1", "<p>수정 실패</p>");
				mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
				mav.addObject("link2", "<input type='button' class='bbs_update_libtn' value='목록' onclick='location.href=\"list.do\"'>");
			}else {
				mav.addObject("msg1", "<p>수정 성공</p>");
				
				mav.addObject("link2", "<input type='button' class='bbs_update_libtn' value='목록' onclick='location.href=\"list.do\"'>");
			}//if end
			
			return mav;
		}//updateProc() end
		
		@RequestMapping(value="/bbs/delete.do", method = RequestMethod.GET)
		public ModelAndView deleteForm(BbsDTO dto) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("bbs/deleteForm");
			mav.addObject("root", Utility.getRoot());
			
			
			//삭제관련정보 가져오기
			mav.addObject("dto",dao.read(dto.getNno()));
			
			return mav;
		}//deleteForm() end
		
		
		
		@RequestMapping(value="/bbs/delete.do", method = RequestMethod.POST)
		public ModelAndView deleteProc(BbsDTO dto, HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("bbs/msgView");
			mav.addObject("root", Utility.getRoot());
			
			//삭제하고자 하는 정보 가져오기
			BbsDTO oldDTO = dao.read(dto.getNno());
			int cnt = dao.delete(dto.getNno());
			if(cnt ==0) {
				mav.addObject("msg1", "<p>삭제 실패</p>");
				mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
				mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"list.do\"'>");
			}else {
				String basepath = req.getRealPath("/images/netizen");
				
				UploadSaveManager.deleteFile(basepath, oldDTO.getNimg());
				mav.addObject("msg1", "<p>삭제 성공</p>");
				
				mav.addObject("link2", "<input type='button' class='bbs_delete_libtn'  value='목록' onclick='location.href=\"list.do\"'>");
			}//if end
			return mav;
			
		}//deleteProc() end
		//--------------------------------------------------
		//admin 영역
			
			@RequestMapping(value="/admin/bbs_delete.do", method = RequestMethod.GET)
	    public ModelAndView delete(BbsDTO dto) {
	      ModelAndView mav = new ModelAndView();
	      mav.setViewName("admin/bbs_deleteForm");
	      mav.addObject("root", Utility.getRoot());
	      
	      
	      //삭제관련정보 가져오기
	      mav.addObject("dto",dao.read(dto.getNno()));
	      
	      return mav;
	    }//delete() end
	    
	    
	    
	    @RequestMapping(value="/admin/bbs_delete.do", method = RequestMethod.POST)
	    public ModelAndView Proc(BbsDTO dto, HttpServletRequest req) {
	      ModelAndView mav = new ModelAndView();
	      mav.setViewName("admin/msgView");
	      mav.addObject("root", Utility.getRoot());
	      
	      //삭제하고자 하는 정보 가져오기
	      BbsDTO oldDTO = dao.read(dto.getNno());
	      int cnt = dao.bbs_delete(dto.getNno());
	      if(cnt ==0) {
	        mav.addObject("msg1", "<p>삭제 실패</p>");
	        mav.addObject("link1", "<input type='button' class=\"bbs_del_back\" value='다시시도' onclick='javascript:history.back()'>");
	        mav.addObject("link2", "<input type='button'  class=\"bbs_del_back\" value='목록' onclick='location.href=\"../admin/bbslist.do\"'>");
	      }else {
	        String basepath = req.getRealPath("/images/netizen"); 
	        UploadSaveManager.deleteFile(basepath, oldDTO.getNimg());
	        mav.addObject("msg1", "<p>삭제 성공</p>");
	        mav.addObject("link2", "<input type='button'class=\"bbs_del_back\" value='목록' onclick='location.href=\"../admin/bbslist.do\"'>");
	      }//if end
	      return mav;
	      
	    }//deleteProc() end
	    
		@RequestMapping(value="/admin/bbslist.do")
	      public ModelAndView admin_list(BbsDTO dto,PagingVO vo,
	            @RequestParam(value="nowPage", required=false)String nowPage,
	            @RequestParam(value="cntPerPage", required=false)String cntPerPage,ReviewDTO redto)throws Throwable {
	         
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
	         
	         System.out.println("총 게시물 갯수: " + total );
	         System.out.println(cntPerPage);
	         System.out.println(nowPage);
	         mav.addObject("paging", vo);
	         mav.addObject("root", Utility.getRoot());
	         mav.addObject("list",dao.list2(dto,vo));
	         mav.addObject("nowpage",nowPage);
	         mav.addObject("cntPerPage", cntPerPage);
	         mav.setViewName("admin/bbs_adminlist");
	      
	         return mav;
	      }//list() end
		
		@RequestMapping(value="/admin/bbslistpage.do", method = RequestMethod.GET)
		public ModelAndView adminlistpage(BbsDTO dto,PagingVO vo,
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
				cntPerPage = "10";
			} else if (nowPage == null) {
				nowPage = "1";
			} else if (cntPerPage == null) { 
				cntPerPage = "10";
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
			mav.setViewName("admin/bbs_adminlist");
		
			return mav;
		}//list() end
		
		
}//class end