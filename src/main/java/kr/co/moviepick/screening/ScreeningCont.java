package kr.co.moviepick.screening;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ScreeningCont {
  @Autowired
  ScreeningDAO dao;
  
  public ScreeningCont() {
    System.out.println("---ScreeningCont()객체 생성됨...");
  }
  
  @RequestMapping(value="/admin/screen.do")
  public ModelAndView list(ScreeningDTO dto, 
		  PagingVO vo,
			 @RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage)throws Exception {
    ModelAndView mav=new ModelAndView();
    
    int total=0;
    total=dao.getArticleCount2();
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
    mav.setViewName("admin/screen");
    mav.addObject("paging",vo);
    mav.addObject("root", Utility.getRoot());
    mav.addObject("list", dao.list(dto,vo));
    mav.addObject("scrno", dto.getScrno());
    return mav;
  }//list_movie() end
  
  @RequestMapping(value="/admin/screenpage.do")
  public ModelAndView listpage(ScreeningDTO dto,
		  PagingVO vo,
			 @RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage,
			@RequestParam(value="col", required = false)String col,
			@RequestParam(value="word", required =false)String word) throws Exception{
   
	  ModelAndView mav=new ModelAndView();
	  
	  int total=0;
	    total=dao.screeningcount(col, word);
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
   
	mav.setViewName("admin/screen");
	mav.addObject("paging",vo);
    mav.addObject("root", Utility.getRoot());
    mav.addObject("list", dao.screenlistcol(col,word,dto,vo));
    mav.addObject("scrno", dto.getScrno());
    
    
    return mav;
  }//list_movie() end
  
  @RequestMapping(value="/admin/screen_create.do", method=RequestMethod.GET)
  public String createForm() {
    return("admin/screen_createForm");
  }//creatForm() end
  
  @RequestMapping(value="/admin/screen_create.do", method=RequestMethod.POST)
  public ModelAndView createProc(ScreeningDTO dto, HttpServletRequest req, HttpServletResponse resp) {
    ModelAndView mav=new ModelAndView();
    mav.setViewName("admin/msgView");
    mav.addObject("root",Utility.getRoot());
    int cnt=dao.create(dto);
    if(cnt==0) {
      mav.addObject("msg1","<p>상영정보 등록 실패</p>");
      mav.addObject("link1","<input type='button' value='다시시도' class=\"bbs_del_back\" onclick='javascript:history.back()'>");
      mav.addObject("link2","<input type='button' value='그룹목록' class=\"bbs_del_back\" onclick='location.href=\"./screen.do\"'>");
    }else {
      mav.addObject("msg1","<p>상영정보 등록 성공</p>");
      mav.addObject("link2","<input type='button' value='그룹목록' class=\"bbs_del_back\" onclick='location.href=\"./screen.do\"'>");
    }//if end
    return mav;
  }//createProc() end
  
  @RequestMapping(value= "/admin/screen_update.do", method = RequestMethod.GET)
  public ModelAndView updateForm(ScreeningDTO dto) {
    ModelAndView mav= new ModelAndView();
    mav.setViewName("admin/screen_updateForm");
    mav.addObject("root", Utility.getRoot());
    mav.addObject("dto",dao.read(dto.getScrno()));
    return mav;
  }//updateForm() end
  
  @RequestMapping(value= "/admin/screen_update.do", method = RequestMethod.POST)
  public ModelAndView updateProc(ScreeningDTO dto, HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/msgView");
    mav.addObject("root", Utility.getRoot());
    int cnt = dao.update(dto);
    if(cnt==0) {
      mav.addObject("msg1", "<p>수정 실패</p>");
      mav.addObject("link1", "<input type='button' value='다시시도' class=\"bbs_del_back\" onclick='javascript:history.back()'>");
      mav.addObject("link2", "<input type='button' value='목록' class=\"bbs_del_back\" onclick='location.href=\"./screen.do\"'>");
    }else {
      mav.addObject("msg1", "<p>수정 성공</p>");
      mav.addObject("link2", "<input type='button' value='목록' class=\"bbs_del_back\"  onclick='location.href=\"./screen.do\"'>");
    }//if end
    return mav;
  }//updateProc() end
  
  @RequestMapping(value= "/admin/screen_delete.do", method=RequestMethod.GET)
  public ModelAndView delete(ScreeningDTO dto) {
    ModelAndView mav= new ModelAndView();
    mav.setViewName("admin/screen_deleteForm");
    mav.addObject("root",Utility.getRoot());
    //삭제 관련 정보 가져오기
    mav.addObject("dto",dao.read(dto.getScrno()));
    return mav;
  }//delete() end
  
  @RequestMapping(value="/admin/screen_delete.do", method = RequestMethod.POST)
  public ModelAndView deleteProc(ScreeningDTO dto, HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/msgView");
    mav.addObject("root", Utility.getRoot());
    //삭제하고자 하는 정보 가져오기
    ScreeningDTO oldDTO = dao.read(dto.getScrno());
    int cnt = dao.delete(dto.getScrno());
    if(cnt ==0) {
      mav.addObject("msg1", "<p>삭제 실패</p>");
      mav.addObject("link1", "<input type='button' value='다시시도' class=\"bbs_del_back\" onclick='javascript:history.back()'>");
      mav.addObject("link2", "<input type='button' value='목록' class=\"bbs_del_back\" onclick='location.href=\"./screen.do\"'>");
    }else {
      mav.addObject("msg1", "<p>삭제 성공</p>");
      mav.addObject("link2", "<input type='button' value='목록' class=\"bbs_del_back\" onclick='location.href=\"./screen.do\"'>");
    }//if end
    return mav;
  }//deleteProc() end
  
  @RequestMapping(value="/admin/screen-movie.do")
  public ModelAndView moviepeople(MovieDTO dto, PagingVO vo,
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
     
     System.out.println("총 영화 수: " + total );
     System.out.println(cntPerPage);
     System.out.println(nowPage);
     mav.addObject("paging", vo);
     mav.addObject("root", Utility.getRoot());
     mav.addObject("list",dao.list2(dto,vo));
     mav.addObject("nowpage",nowPage);
     mav.addObject("cntPerPage", cntPerPage);
     mav.setViewName("admin/screen-movie");
  
     return mav;
  }//list() end

@RequestMapping(value="/admin/paging.do", method = RequestMethod.GET)
public ModelAndView listpage(MovieDTO dto,PagingVO vo,
  @RequestParam(value="nowPage", required=false)String nowPage,
  @RequestParam(value="cntPerPage", required=false)String cntPerPage,
  @RequestParam(value="col", required = false)String col,
  @RequestParam(value="word", required =false)String word,
  HttpServletRequest req)throws Throwable {

ModelAndView mav = new ModelAndView();
int total=0;
total=dao.count(col, word);
if (nowPage == null && cntPerPage == null) {
  nowPage = "1";
  cntPerPage = "5";
} else if (nowPage == null) {
  nowPage = "1";
} else if (cntPerPage == null) { 
  cntPerPage = "5";
}
vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage)); 

System.out.println("총 영화 수: " + total );
System.out.println("페이지 당 "+cntPerPage+"편");
System.out.println("현재페이지"+nowPage);
System.out.println("col"+ col);

mav.addObject("paging", vo);
mav.addObject("root", Utility.getRoot());
mav.addObject("list",dao.list3(col,word,dto,vo));
mav.addObject("nowpage",nowPage);
mav.addObject("cntPerPage", cntPerPage);
mav.addObject("col",col);
mav.addObject("word",word);
mav.setViewName("admin/screen-movie");

return mav;
}//list() end

  
}
