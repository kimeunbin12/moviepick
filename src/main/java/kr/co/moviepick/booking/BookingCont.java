package kr.co.moviepick.booking;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviepick.movie.MovieDTO;
import kr.co.moviepick.screening.ScreeningDTO;
import net.utility.PagingVO;
import net.utility.Utility;

@Controller
public class BookingCont {
	
	@Autowired
	BookingDAO dao;
	
	public BookingCont() {
		System.out.println("---BookingCont()객체생성됨");
	}
	
	@RequestMapping(value="/mypage/bookinglist.do")
	public ModelAndView list(BookingDTO dto, MovieDTO mdto, ScreeningDTO sdto,PagingVO vo,
			@RequestParam(value="nowPage", required=false)String nowPage,
            @RequestParam(value="cntPerPage", required=false)String cntPerPage,HttpSession session)throws Throwable {
		
		String uid=(String)session.getAttribute("uid");
		
		int total=0;
         total=dao.getArticleCount(uid);

      
         if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "10";
         } else if (nowPage == null) {
            nowPage = "1";
         } else if (cntPerPage == null) { 
            cntPerPage = "10";
         }
         vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

  
		 ModelAndView mav = new ModelAndView();
		 mav.addObject("paging", vo);
		 System.out.println(dao.blist(mdto, sdto, vo, uid));
		mav.addObject("list",dao.blist(mdto, sdto, vo, uid));
		mav.setViewName("mypage/bookinglist");		 
				 
		return mav;
		
	}
	
	
}
