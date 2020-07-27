package kr.co.moviepick.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviepick.mail.EmailDTO;
import kr.co.moviepick.mail.EmailSender;
import net.utility.PagingVO;
import net.utility.Utility;

@Controller
public class UserCont {

	@Autowired
	UserDAO dao;

	@Autowired
	private EmailSender emailSender;
	@Autowired
	private EmailDTO email;

	public UserCont() {
		System.out.println("---UserCont()객체생성됨..");
	}
	
	//회원가입폼 
	@RequestMapping(value = "/login/member.do", method = RequestMethod.GET)
	public ModelAndView memberForm(UserDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/memberForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("uid", dto.getUid());
		return mav;
	}// memberForm() end

	
	//회원가입 
	@RequestMapping(value = "/login/member.do", method = RequestMethod.POST)
	public ModelAndView createProc(UserDTO dto, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/msgView");
		
		mav.addObject("root", Utility.getRoot());
		System.out.println(dto.getUbirth());
		String ubirth = req.getParameter(dto.getUbirth());
		System.out.println(String.class.isInstance(ubirth));
		int cnt = dao.create(dto);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>회원 가입 실패</p>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
		} else {
			
			mav.addObject("msg1", "<script>\r\n" + "	alert(\"회원가입되었습니다.로그인해주세요.\");\r\n"
					+ "	location.href=\"../main/index.do\";\r\n" + "</script>");
			session.invalidate();
			
		} // if end

		return mav;
	}// createProc() end

	
	//로그인폼
	@RequestMapping(value = "/login/login.do", method = RequestMethod.GET)
	public String loginForm() {
		return "login/loginForm";
	}// loginForm() end

	@RequestMapping(value = "/login/agreement.do", method = RequestMethod.GET)
	public String agreement() {
		return "login/agreement";
	}// loginForm() end

	//로그인
	@RequestMapping(value="/login/login.do", method=RequestMethod.POST)
	  public ModelAndView loginProc(UserDTO dto, HttpServletRequest req, HttpSession session, HttpServletResponse resq) {
		
	    String uid= req.getParameter("uid");
	    String ups= req.getParameter("ups");
	    ModelAndView mav=new ModelAndView();
	    mav.setViewName("login/msgView");
	    mav.addObject("root",Utility.getRoot());
	  
	    String uname=dao.loginCheck(dto);
	    if(uname==null) {
	      mav.addObject("msg1",  "<p>* 로그인 실패 *</p>");
	      mav.addObject("msg2",  "<p>아이디/비밀번호를 다시 확인해주세요!</p>");
	      mav.addObject("link1","<input type='button' value='다시시도' onclick='javascript:history.back()'>");
	   }else {
	      session.setAttribute("uid", uid);
	      session.setAttribute("ups", ups);
	      if(uid.equals("admin")) {
	        mav.addObject("msg1", "<script>\r\n" + 
	            " alert(\"관리자님 로그인 되었습니다.\");\r\n" + 
	            " location.href=\"../admin/index.do\";\r\n" + 
	            "</script>"); 
	      }else {
	        mav.addObject("msg2", "<script>\r\n" + 
	            " alert(\""+ uid +"님 로그인 되었습니다.\");\r\n" + 
	            " location.href=\"../main/index.do\";\r\n" + 
	            "</script>"); 
	      }//if end
	          
	   }//if end

	   return mav;
	}//loginProc() end

	//아이디 중복확인
	@RequestMapping(value = "/login/idcheck.do", method = RequestMethod.GET)
	public String idCheckForm() {
		return "login/idCheck";
	}// loginForm() end
	
	
	//아이디 중복확인
	@RequestMapping(value = "/login/idCheck.do", method = RequestMethod.POST)
	public ModelAndView idCheck(UserDTO dto, HttpServletRequest req, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/idCheckProc");
		mav.addObject("root", Utility.getRoot());

		String uid = req.getParameter("uid");
		String ups = req.getParameter("ups");
		System.out.println(uid + "," + ups);

		session.setAttribute("uid", uid);
		session.setAttribute("ups", ups);

		int cnt = dao.idcheck(uid);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>"+uid+"<br>사용가능</p>");
			
			mav.addObject("msg3", "<input type='button' value='적용' onclick='javascript:apply(\"" + uid + "\")'>");

		} else {
			mav.addObject("msg1", "<p>이미 있는 아이디</p>");

		} // if end
		return mav;
	}// loginProc() end
	
	
	//이메일 중복확인
	@RequestMapping(value = "/login/emailCheck.do", method = RequestMethod.GET)
	public String emailCheckForm() {
		return "login/emailCheck";
	}// emailCheckForm() end

	@RequestMapping(value = "/login/emailCheck.do", method = RequestMethod.POST)
	public ModelAndView emailCheck(UserDTO dto, HttpServletRequest req, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/emailCheckProc");
		mav.addObject("root", Utility.getRoot());
		String uemail = req.getParameter("uemail");

		int cnt = dao.emailCheck(uemail);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>"+uemail+"<br>사용가능한 이메일</p>");
			mav.addObject("msg3", "<input type='button' value='적용' onclick='javascript:apply(\"" + uemail + "\")'>");

		} else {
			mav.addObject("msg1", "<p>이미 있는 이메일</p>");

		} // if end
		return mav;
	}// loginProc() end
	
	
	//로그아웃
	@RequestMapping(value = "/login/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session, HttpServletResponse resq) {

		ModelAndView mav = new ModelAndView();
		session.invalidate();

		mav.setViewName("login/logout");

		return mav;

	}
	
	
	
	//아이디 찾기
	@RequestMapping(value = "/login/idsearch.do", method = RequestMethod.GET)
	public String idsearch() {
		return "login/idsearch";
	}// idsearch() end

	@RequestMapping(value = "/login/idsearch.do", method = RequestMethod.POST)
	public ModelAndView idsearchafter(UserDTO dto, HttpServletRequest req,HttpSession session) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/msgView");
		mav.addObject("root", Utility.getRoot());
		String uname = req.getParameter("uname");
		String uemail = req.getParameter("uemail");

		String uid = dao.idsearch(uname, uemail);
		req.setAttribute("uid", uid);

		if (uid == null) {

			mav.addObject("msg1", "<p>정보를 찾을 수 없습니다.</p>");
			mav.addObject("msg2", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");

		} else {

			mav.addObject("msg1", "<script>\r\n" + "	alert(\"당신의 아이디는 " + uid + " 입니다.\");\r\n"
					+ "	location.href=\"../main/index.do\";\r\n" + "</script>");
			session.invalidate();
		}
		return mav;
	}// idsearchafter() end

	//비밀번호찾기
	@RequestMapping(value = "/login/pssearch.do", method = RequestMethod.GET)
	public String pssearch() {
		return "login/pssearch";
	}// psSearch() end

	@RequestMapping(value = "/login/pssearch.do", method = RequestMethod.POST)
	public ModelAndView pssearchafter(UserDTO dto, HttpServletRequest req,HttpSession session) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/msgView");
		mav.addObject("root", Utility.getRoot());
		String uid = req.getParameter("uid");
		String uemail = req.getParameter("uemail");

		String ups = dao.pssearch(uid, uemail);
		req.setAttribute("ups", ups);

		if (ups == null) {

			mav.addObject("msg1", "<p>정보를 찾을 수 없습니다.</p>");

		} else {
			email.setContent("비밀번호는 " + ups + " 입니다.");
			email.setReceiver(uemail);
			email.setSubject(uid + "님 비밀번호 찾기 메일입니다.");
			emailSender.SendEmail(email);
			mav.addObject("msg1", "<script>\r\n" + "	alert(\"" + uid + "님 이메일로 비밀번호가 발송되었습니다.\");\r\n"
					+ "	location.href=\"../main/index.do\";\r\n" + "</script>");
			session.invalidate();
		}
		return mav;
	}// idsearchafter() end
	

	//내정보 수정 전 비밀번호맞는지 확인
	@RequestMapping(value= "/mypage/infchange.do", method = RequestMethod.GET)
	public ModelAndView pscheck(UserDTO dto,HttpServletRequest req,HttpSession session) {
		
		String uid=(String)session.getAttribute("uid");
		System.out.println("##"+uid);
		ModelAndView mav= new ModelAndView();
		mav.setViewName("mypage/pscheck");
		mav.addObject("root", Utility.getRoot());
		
		return mav;
	}//updateForm() end
	
	@RequestMapping(value= "/mypage/infchange.do", method = RequestMethod.POST)
	public ModelAndView infchange(UserDTO dto,HttpServletRequest req, HttpSession session) {
		
		String uid=(String)session.getAttribute("uid");
		String ups= req.getParameter("ups");
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("mypage/msgView");
		mav.addObject("root", Utility.getRoot());
		
		int cnt= dao.pscheck(uid, ups); 
		

		if(cnt==0){
			mav.addObject("msg1",  "<p> 비밀번호가 틀렸습니다. </p>");
			 mav.addObject("link1","<input type='button' value='다시시도' onclick='javascript:history.back()'>");
		}else if(cnt==1) {
			session.setAttribute("uid", uid);
			mav.addObject("msg1",  "<script>\r\n" + 
		            " location.href=\"../mypage/update.do\";\r\n" + 
		            "</script>");
			
		}
		return mav;
	}//updateForm() end
	
	
	//내정보 수정
	@RequestMapping(value = "/mypage/update.do", method = RequestMethod.GET)
	public ModelAndView updateForm(String uid, HttpSession session) {
		
		uid=(String)session.getAttribute("uid");
		
		ModelAndView mav = new ModelAndView();
		
		UserDTO dto= dao.updateform(uid);
		mav.setViewName("mypage/updateForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		mav.addObject("uid", dao.updateform(uid));
		
		
		
		return mav;
	}// memberForm() end
	

	@RequestMapping(value = "/mypage/update.do", method = RequestMethod.POST)
	public ModelAndView updateProc(String uid, UserDTO dto, HttpSession session) {
		
		uid=(String)session.getAttribute("uid");
		
		ModelAndView mav = new ModelAndView();
	
		mav.setViewName("mypage/msgView");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("uid", dao.updateform(uid));
		
		int cnt= dao.update(dto);
		if(cnt==0){
			mav.addObject("msg1", "<p>수정 실패</p>");
			mav.addObject("link1","<input type='button' value='다시시도' onclick='javascript:history.back()'>");
		  }else{
			  mav.addObject("msg1", "<p>수정 성공</p>");
			  mav.addObject("msg1",  "<script>\r\n" + 
			            " location.href=\"../mypage/update.do\";\r\n" + 
			            "</script>");
		  }//if end
		
		return mav;
	}// memberForm() end
	
	
	@RequestMapping(value = "/mypage/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteform(String uid,UserDTO dto,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		uid=(String)session.getAttribute("uid");
		mav.addObject("uid",uid);
		mav.addObject("dto", dto);
		mav.setViewName("mypage/deleteForm");
		
		return mav;
	}// psSearch() end
	
	//탈퇴하기
		@RequestMapping(value = "/mypage/delete.do", method = RequestMethod.POST)
		public ModelAndView deleteproc(String uid,UserDTO dto, HttpSession session) {
			
			uid=(String)session.getAttribute("uid");
			System.out.println(uid);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("mypage/msgView");
			
			boolean cnt= dao.delete(uid);
			if(cnt==false) {
				mav.addObject("msg1", "<p> 탈퇴할 수 없습니다.</p>");
				mav.addObject("link1","<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			}else {
				mav.addObject("msg1", "<script>\r\n" + "alert(\"탈퇴되었습니다.\");\r\n"
						+ "	location.href=\"../main/index.do\";\r\n" + "</script>");
				session.invalidate();
				
			}
			return mav;
		}// psSearch() end
	

		  
		  @RequestMapping(value="/admin/login_delete.do", method = RequestMethod.GET)
		  public ModelAndView deleteForm(UserDTO dto) {
		    ModelAndView mav = new ModelAndView();
		    mav.setViewName("admin/member_deleteForm");
		    mav.addObject("root", Utility.getRoot());
		    mav.addObject("uid", dto.getUid());
		    
		    //삭제관련정보 가져오기
		    mav.addObject("dto",dao.read(dto.getUid()));
		    
		    return mav;
		  }//deleteForm() end
		  
		  
		  
		  @RequestMapping(value="/admin/login_delete.do", method = RequestMethod.POST)
		  public ModelAndView deleteProc(UserDTO dto, HttpServletRequest req) {
		    ModelAndView mav = new ModelAndView();
		    mav.setViewName("admin/msgView");
		    mav.addObject("root", Utility.getRoot());
		    mav.addObject("uid", dto.getUid());
		    //삭제하고자 하는 정보 가져오기
		    UserDTO oldDTO = dao.read(dto.getUid());
		    int cnt = dao.admin_delete(dto.getUid());
		    if(cnt ==0) {
		      mav.addObject("msg1", "<p>삭제 실패</p>");
		      mav.addObject("link2", "<input type='button' value='삭제취소'class=\"bbs_del_back\" onclick='location.href=\"../admin/login.do\"'>");
		      mav.addObject("link1","<input type='button' value='다시시도' class=\"bbs_del_back\" onclick='javascript:history.back()'>");
		    }else {
		      
		      mav.addObject("msg1", "<p>삭제 성공</p>");
		      mav.addObject("link2", "<input type='button' value='목록' class=\"bbs_del_back\" onclick='location.href=\"../admin/login.do\"'>");
		    }//if end
		    return mav;
		    
		  }//deleteProc() end
		  
		  @RequestMapping(value="/admin/login.do")
		    public ModelAndView list(UserDTO dto,PagingVO vo,
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
		       mav.setViewName("admin/member");
		    
		       return mav; 
		    }//list() end
			
			@RequestMapping(value="/admin/loginpage.do", method = RequestMethod.GET)
			public ModelAndView listpage1(UserDTO dto,PagingVO vo,
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
				mav.setViewName("admin/member");
			
				return mav;
			}//list() end
			
			
}// class end