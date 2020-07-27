package kr.co.moviepick;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviepick.bbs.BbsDAO;
import kr.co.moviepick.bbs.BbsDTO;
import kr.co.moviepick.booking.BookingDAO;
import kr.co.moviepick.download.DownloadDAO;
import kr.co.moviepick.movie.MovieDAO;
import kr.co.moviepick.movie.MovieDTO;
import kr.co.moviepick.notice.NoticeDAO;
import kr.co.moviepick.notice.NoticeDTO;
import kr.co.moviepick.screening.ScreeningDTO;
import net.utility.PagingVO;
import net.utility.Utility;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	NoticeDAO dao;
	
	@Autowired
	MovieDAO mdao;
	
	@Autowired
	BookingDAO bdao;
	
	@Autowired
	DownloadDAO ddao;
	
	@Autowired
	BbsDAO bbsdao;
	
	//moviepick프로젝트의 첫 페이지 호출 
	//http://localhost:9090/moviepick/index.do

	@RequestMapping(value="/main/index.do")
    public ModelAndView list(NoticeDTO dto)throws Throwable {
       
       ModelAndView mav = new ModelAndView();
     
    
       mav.addObject("mlist",mdao.mainlist());
       mav.addObject("list",dao.list1());
       System.out.println(dao.list1());
       mav.addObject("root", Utility.getRoot());
       mav.setViewName("main/index");
    
       return mav;
    }//list() end
	
	@RequestMapping(value = "mypage/mypage.do", method = RequestMethod.GET)
	public ModelAndView mypage(HttpSession session,BbsDTO bbsdto,MovieDTO mdto, ScreeningDTO sdto) {
		
		ModelAndView mav = new ModelAndView();
		String uid=(String)session.getAttribute("uid");
		
		//티켓팅
		mav.addObject("blist", bdao.blistmypage(mdto, sdto, uid));
		
		
		//구매내역
		mav.addObject("dlist",ddao.readmypagemain(mdto, uid));
		//게시판
		mav.addObject("root", Utility.getRoot());
		 mav.addObject("bbslist",bbsdao.mypagemainlist(bbsdto, uid));
         mav.addObject("relist",bbsdao.mypagemainreview(mdto, uid));
		mav.setViewName("mypage/mypage");
		System.out.println("##"+uid);
		
		return mav;
	}
	
	@RequestMapping(value = "intro/index.do", method = RequestMethod.GET)
	public String intro(HttpSession session) {
		
		return "intro/index";
	}
	
}
