package kr.co.moviepick.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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


import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.PagingVO;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class NoticeCont {

	@Autowired
	NoticeDAO dao;
	

	
	public NoticeCont() {
		System.out.println("---NoticeCont()객체생성됨");
	}
	
	@RequestMapping(value="/notice/list.do")
    public ModelAndView list(NoticeDTO dto,PagingVO vo,
          @RequestParam(value="nowPage", required=false)String nowPage,
          @RequestParam(value="cntPerPage", required=false)String cntPerPage)throws Throwable {
       
       ModelAndView mav = new ModelAndView();
       int total=0;
       total=dao.getArticleCount();

    
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
       System.out.println(cntPerPage);
       System.out.println(nowPage);
       mav.addObject("paging", vo);
       mav.addObject("root", Utility.getRoot());
       mav.addObject("list",dao.list2(dto,vo));
       mav.addObject("nowpage",nowPage);
       mav.addObject("cntPerPage", cntPerPage);
       mav.setViewName("notice/list");
    
       return mav;
    }//list() end
	
	@RequestMapping(value="/admin/notice.do")
    public ModelAndView list1(NoticeDTO dto,PagingVO vo,
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
       mav.setViewName("admin/notice");
    
       return mav;
    }//list() end
	
	
	
	@RequestMapping(value="/admin/noticepage.do", method = RequestMethod.GET)
	public ModelAndView listpage1(NoticeDTO dto,PagingVO vo,
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
		mav.setViewName("admin/notice");
	
		return mav;
	}//list() end
	
	@RequestMapping(value="/notice/listpage.do", method = RequestMethod.GET)
	public ModelAndView listpage(NoticeDTO dto,PagingVO vo,
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
		mav.setViewName("notice/list");
	
		return mav;
	}//list() end
	
	
	
	@RequestMapping(value= "/notice/read.do" , method = RequestMethod.GET)
	public ModelAndView read(int noticeno,PagingVO vo,
			@RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage,
			@RequestParam(value="col", required = false)String col,
			@RequestParam(value="word", required =false)String word,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("notice/read");
		
		System.out.println(vo.getNowPage());
		System.out.println(col);
		NoticeDTO dto = dao.read(noticeno);
		dao.updatecnt(noticeno);
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		mav.addObject("nowPage",vo.getNowPage());
		mav.addObject("cntPerPage",vo.getCntPerPage());
		mav.addObject("col",col);
		mav.addObject("word",word);
		mav.addObject("str",Utility.getConvertCharTextArea(dto.getNoticecon()));
		return mav;
	}//read() end
	
	@RequestMapping(value= "/notice/read2.do" , method = RequestMethod.GET)
	public ModelAndView read2(int noticeno,PagingVO vo,
			@RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage,
			@RequestParam(value="col", required = false)String col,
			@RequestParam(value="word", required =false)String word,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("notice/read2");
		
		System.out.println(vo.getNowPage());
		System.out.println(col);
		NoticeDTO dto = dao.read(noticeno);
		dao.updatecnt(noticeno);
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		mav.addObject("nowPage",vo.getNowPage());
		mav.addObject("cntPerPage",vo.getCntPerPage());
		mav.addObject("col",col);
		mav.addObject("word",word);
		mav.addObject("str",Utility.getConvertCharTextArea(dto.getNoticecon()));
		return mav;
	}//read() end

	
	
	
	//http://localhost:9090/moviepick/bbs/create.do
			@RequestMapping(value="/admin/notice_create.do", method = RequestMethod.GET)
			public String createForm() {
				// /notice/createForm.jsp
				return "admin/notice_createForm";
			}//createForm() end
			
			@RequestMapping(value="/admin/notice_create.do", method = RequestMethod.POST)
			public ModelAndView createProc(NoticeDTO dto,HttpServletRequest req, HttpSession session, HttpServletResponse resp) {
				ModelAndView mav = new ModelAndView();
				mav.setViewName("admin/msgView");
				mav.addObject("root", Utility.getRoot());
				//-> 실제 파일은  /media/storage폴더에 저장
				//-> 저장된 파일관련 정보는 media테이블에 저장
				System.out.println(dto.getNoticecon());
				System.out.println(dto.getNoticedate());
				System.out.println(dto.getNoticesub());
				
				// 실제 물리적인 경로
				String basePath = req.getRealPath("/images/notice");
				
				//1)<input type='file' name = 'posterMF'>
				// -> 파일 가져오기
				MultipartFile posterMF = dto.getPosterMF();
				// -> 파일을 저장하고 리네임된 파일명 변환
				String noticeimg = UploadSaveManager.saveFileSpring30(posterMF, basePath);
				// -> 파일명 dto객체에 담기
				dto.setNoticeimg(noticeimg);
				int cnt = dao.create(dto);
				if (cnt==0) {
					mav.addObject("msg1", "<p>등록 실패</p>");
					mav.addObject("link1", "<input type='button' class=\"bbs_del_back\" value='다시시도' onclick='javascript:history.back()'>");
					mav.addObject("link2", "<input type='button' class=\"bbs_del_back\" value='목록' onclick='location.href=\"notice_list.do\"'>");
				}else {
					mav.addObject("msg1", "<p>등록 성공</p>");
					mav.addObject("link1", "<input type='button' class=\"bbs_del_back\"  value='계속등록' onclick='location.href=\"notice_create.do\"'>");
					mav.addObject("link2", "<input type='button' class=\"bbs_del_back\" value='목록' onclick='location.href=\"notice.do\"'>");
				}//if end
				
				return mav;
			}//createForm() end
			
			@RequestMapping(value= "/admin/notice_update.do", method = RequestMethod.GET)
			public ModelAndView updateForm(NoticeDTO dto) {
				ModelAndView mav= new ModelAndView();
				mav.setViewName("admin/notice_updateForm");
				mav.addObject("root", Utility.getRoot());
				mav.addObject("dto", dao.read(dto.getNoticeno()) );
				
				return mav;
			}//updateForm() end
			
			@RequestMapping(value= "/admin/notice_update.do", method = RequestMethod.POST)
			public ModelAndView updateProc(NoticeDTO dto, HttpServletRequest req) {
				ModelAndView mav = new ModelAndView();
				mav.setViewName("admin/msgView");
				mav.addObject("root", Utility.getRoot());
				
				String basePath = req.getRealPath("/images/notice");
				//기존에 저장된 정보 가져오기
				NoticeDTO oldDTO = dao.read(dto.getNoticeno());
				//  파일을 수정할 것인지?
				MultipartFile posterMF = dto.getPosterMF();
				//System.out.println("지정경로로로"+basePath);
				
				if(posterMF.getSize()>0) {
					//새로운 포스터파일이 전송된경우
				      //기존 파일 삭제				
				      UploadSaveManager.deleteFile(basePath, oldDTO.getNoticeimg());
				      //신규 파일 저장
				      
				      String noticeimg = UploadSaveManager.saveFileSpring30(posterMF, basePath);
				      dto.setNoticeimg(noticeimg);      
				    }else {
				      //포스터 파일을 수정하지 않는 경우
				      dto.setNoticeimg(oldDTO.getNoticeimg());
				    }//if end
				
				
				int cnt = dao.update(dto);
				if(cnt==0) {
					mav.addObject("msg1", "<p>수정 실패</p>");
					
					mav.addObject("link1", "<input type='button' class=\"bbs_del_back\" value='다시시도' onclick='javascript:history.back()'>");
					mav.addObject("link2", "<input type='button' class=\"bbs_del_back\" value='목록' onclick='location.href=\"notice.do\"'>");
				}else {
					mav.addObject("msg1", "<p>수정 성공</p>");
					
					mav.addObject("link2", "<input type='button'  class=\"bbs_del_back\" value='목록' onclick='location.href=\"notice.do\"'>");
				}//if end
				
				return mav;
			}//updateProc() end

			@RequestMapping(value="/admin/notice_delete.do", method = RequestMethod.GET)
			public ModelAndView deleteForm(NoticeDTO dto) {
				ModelAndView mav = new ModelAndView();
				mav.setViewName("admin/notice_deleteForm");
				mav.addObject("root", Utility.getRoot());
				
				
				//삭제관련정보 가져오기
				mav.addObject("dto",dao.read(dto.getNoticeno()));
				
				return mav;
			}//deleteForm() end
			
			
			
			@RequestMapping(value="/admin/notice_delete.do", method = RequestMethod.POST)
			public ModelAndView deleteProc(NoticeDTO dto, HttpServletRequest req) {
				ModelAndView mav = new ModelAndView();
				mav.setViewName("admin/notice_msgView");
				mav.addObject("root", Utility.getRoot());
				
				//삭제하고자 하는 정보 가져오기
				NoticeDTO oldDTO = dao.read(dto.getNoticeno());
				int cnt = dao.delete(dto.getNoticeno());
				if(cnt ==0) {
					mav.addObject("msg1", "<p>삭제 실패</p>");
					mav.addObject("link1", "<input type='button' class=\"bbs_del_back\" value='다시시도' onclick='javascript:history.back()'>");
					mav.addObject("link2", "<input type='button' class=\"bbs_del_back\" value='목록' onclick='location.href=\"notice.do\"'>");
				}else {
					String basepath = req.getRealPath("/images/notice");
					
					UploadSaveManager.deleteFile(basepath, oldDTO.getNoticeimg());
					mav.addObject("msg1", "<p>삭제 성공</p>");
					
					mav.addObject("link2", "<input type='button' value='목록' class=\"bbs_del_back\"  onclick='location.href=\"notice.do\"'>");
				}//if end
				return mav;
				
			}//deleteProc() end
			
}
