package kr.co.moviepick.review;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.utility.PagingVO;
import net.utility.Utility;

@Controller
public class ReviewCont {

	@Autowired
	ReviewDAO dao;
	
	public ReviewCont(){
		System.out.println("---ReviewCont()객체 생성됨");
	}
	
	@RequestMapping(value="/admin/review.do")
	  public ModelAndView adminreviewlist(ReviewDTO dto, PagingVO vo,
			 @RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception {
			
	    ModelAndView mav=new ModelAndView();
	    int total=0;
	    total=dao.getArticleCount();
	    System.out.println("총 게시글수"+total);
	 
	    if (nowPage == null && cntPerPage == null) {
	       nowPage = "1";
	       cntPerPage = "10";
	    } else if (nowPage == null) {
	       nowPage = "1";
	    } else if (cntPerPage == null) { 
	       cntPerPage = "10";
	    }
	    vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
	    
	    mav.setViewName("admin/review");
	    mav.addObject("paging",vo);
	    mav.addObject("root", Utility.getRoot());
	    mav.addObject("list", dao.review_admin(dto,vo));
	    mav.addObject("mno", dto.getMno());
	    return mav;
	  }//list_review() end
		
		@RequestMapping(value="/admin/reviewpage.do")
		  public ModelAndView adminreviewlistpage(ReviewDTO dto, PagingVO vo,
				 @RequestParam(value="nowPage", required=false)String nowPage,
				@RequestParam(value="cntPerPage", required=false)String cntPerPage,
				@RequestParam(value="col", required = false)String col,
				@RequestParam(value="word", required =false)String word) throws Exception {
				
		    ModelAndView mav=new ModelAndView();
		    int total=0;
		    total=dao.count(col,word);
		    System.out.println("검색 총 게시글수:"+total);
		 
		    if (nowPage == null && cntPerPage == null) {
		       nowPage = "1";
		       cntPerPage = "10";
		    } else if (nowPage == null) {
		       nowPage = "1";
		    } else if (cntPerPage == null) { 
		       cntPerPage = "10";
		    }
		    vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		    
		    mav.setViewName("admin/review");
		    mav.addObject("paging",vo);
		    mav.addObject("root", Utility.getRoot());
		    mav.addObject("list", dao.review_adminsearch(col,word,dto,vo));
			mav.addObject("col",col);
			mav.addObject("word",word);
		    mav.addObject("mno", dto.getMno());
		    return mav;
		  }//list_review() end
		
		@RequestMapping(value="/admin/review_delete.do", method = RequestMethod.GET)
	     public ModelAndView delForm(ReviewDTO dto) {
	       ModelAndView mav = new ModelAndView();
	       mav.setViewName("admin/review_deleteForm");
	       mav.addObject("root", Utility.getRoot());
	       //mav.addObject("rno", dto.getRno());
	       
	       //삭제관련정보 가져오기
	       mav.addObject("dto",dao.read_admin(dto.getRno()));
	       System.out.println(dto);
	       
	       return mav;
	     }//delForm() end
	     
	     
	     @RequestMapping(value="/admin/review_delete.do", method = RequestMethod.POST)
	     public ModelAndView delProc(ReviewDTO dto, HttpServletRequest req) {
	       ModelAndView mav = new ModelAndView();
	       mav.setViewName("admin/msgView");
	       mav.addObject("root", Utility.getRoot());
	       
	       //삭제하고자 하는 정보 가져오기
	       ReviewDTO oldDTO = dao.read_admin(dto.getRno());
	       int cnt = dao.del(dto.getRno());
	       if(cnt ==0) {
	         mav.addObject("msg1", "<p>삭제 실패</p>");
	         mav.addObject("link2", "<input type='button' value='삭제취소' class=\"bbs_del_back\" onclick='location.href=\"../admin/review.do\"'>");
	         mav.addObject("link1","<input type='button' value='다시시도' class=\"bbs_del_back\" onclick='javascript:history.back()'>");
	       }else {
	         mav.addObject("msg1", "<p>삭제 성공</p>");
	         mav.addObject("link2", "<input type='button' value='목록' class=\"bbs_del_back\" onclick='location.href=\"../admin/review.do\"'>");
	       }//if end
	       return mav;
	       
	     }//delProc() end
	     

			
}
