package kr.co.moviepick.download;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviepick.movie.MovieDTO;
import net.utility.PagingVO;
import net.utility.Utility;

@Controller
public class DownloadCont {
	
	@Autowired
	DownloadDAO dao;
	
	public  DownloadCont() {
		System.out.println("---DownloadCont()객체 생성됨");
	}

	@RequestMapping(value="/mypage/down.do")
	public ModelAndView createForm(DownloadDTO dto, MovieDTO mdto,HttpSession session,
            @RequestParam(value="nowPage", required=false)String nowPage,
            @RequestParam(value="cntPerPage", required=false)String cntPerPage, PagingVO vo) throws Exception{
		String uid=(String)session.getAttribute("uid");
		ModelAndView mav= new ModelAndView();
		
		int total=0;
		total=dao.getArticleCount(uid);
		System.out.println(total);
		 if (nowPage == null && cntPerPage == null) {
	            nowPage = "1";
	            cntPerPage = "10";
	         } else if (nowPage == null) {
	            nowPage = "1";
	         } else if (cntPerPage == null) { 
	            cntPerPage = "10";
	         }
		 
		 vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		 mav.addObject("paging", vo);
		 mav.addObject("list",dao.read(mdto, uid,vo));
		mav.addObject("root",Utility.getRoot());
		mav.setViewName("mypage/down");
		return mav;
	}//createForm() end
	
	
}

