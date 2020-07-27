package kr.co.moviepick.movie;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviepick.booking.BookingDTO;
import kr.co.moviepick.screening.ScreeningDTO;
import net.utility.PagingVO;
import net.utility.Utility;

@Controller
public class SeatCont {
	
	@Autowired
	SeatDAO sdao;
	
	@Autowired
	MovieDAO mdao;
	
	public SeatCont() {}
	
	@RequestMapping(value="/movie/choice.do")
	public ModelAndView list(MovieDTO mdto, ScreeningDTO sdto, PagingVO vo) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());
		mav.addObject("mdto", mdao.list5());		
		mav.addObject("sdto", sdao.list(sdto));

		//System.out.println("m"+mdao.list2());		
		//System.out.println("s"+sdao.list(sdto));
		return mav; 
	}//list() end
	
	@RequestMapping(value="/movie/choicetiket.do")
	public ModelAndView listtiket(MovieDTO mdto, ScreeningDTO sdto, PagingVO vo) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());
		mav.addObject("mdto", mdao.list5());		
		mav.addObject("sdto", sdao.list(sdto));

		//System.out.println("m"+mdao.list2());		
		//System.out.println("s"+sdao.list(sdto));
		return mav; 
	}//list() end
	
	@RequestMapping(value="/movie/book.do", method = RequestMethod.POST)
	public ModelAndView seat(MovieDTO mdto, ScreeningDTO sdto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/mypage");
		return mav;
	}//seat() end
	
	
	
	
	@RequestMapping(value="/movie/choice2.do", method = RequestMethod.GET)
	public void choice2(int mno,MovieDTO mdto, ScreeningDTO sdto1, HttpServletRequest req,HttpServletResponse resp) {
		
		
		
		ArrayList<ScreeningDTO> list= sdao.choice2(mno);
		
		String sdto=""; //사용자에게 응답해 주는 메세지
		/*
		if(list!=null) {		
			sdto += "<input type='hidden' value='"+mno+"'>";
			for(int i=0; i<list.size(); i++) {
				ScreeningDTO dto=new ScreeningDTO();
				dto=list.get(i);
				sdto += "<li>";
				sdto += "<a class='bb' href='seat.do?scrno="+ dto.getScrno()+"'>";
				sdto += dto.getScrdate()+ " 상영시작 시간: ";
				sdto += dto.getScrstart() + " 제 "+ dto.getHno()+"관";
				sdto += "</a>";
				sdto += "</li>";
				sdto += "<br>";
			}//for end
		}//if end
		*/
		//System.out.println(sdto);
		String now=Utility.getDate();
		System.out.println(mno);
		if(list!=null) {		
			sdto += "<input type='hidden' id='ee' value='"+mno+"'>";
			sdto +="<dl class='xx'>";
			for(int i=0; i<list.size(); i++) {
				
				ScreeningDTO dto=new ScreeningDTO();
				dto=list.get(i);
				System.out.println("날짜날짜"+dto.getScrdate().compareTo(now));
				if(dto.getScrdate().compareTo(now)>=1) {
					sdto += "<dt class='bb' id='"+dto.getScrdate()+"' value='"+dto.getScrno()+"'> ";	
					sdto +="<div class=\"movie_select_jr\">";
					sdto += "<a class='dd' href='#' onclick='return false'>";
					sdto += ""+dto.getScrdate()+" ";
					sdto += "</a>";
					sdto += "</div>";
					sdto += "</dt>";
				}else {
					
				}


			}//for end
			sdto +="</dl>";
			sdto += "<br>";
		}//if end
		
		try {
			resp.setContentType("text/plain; charset=UTF-8");
			PrintWriter out=resp.getWriter();
			out.println(sdto);
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println(" 실패"+e);
		}

		
	}//choice2() end
	
	@RequestMapping(value="/movie/choice3.do", method = RequestMethod.GET)
	public void  choice3(String scrdate,MovieDTO mdto, ScreeningDTO sdto1, HttpServletResponse resp,HttpServletRequest req, String[] main) {

		int mno=Integer.parseInt(main[0]);
		scrdate= main[1];
			
		System.out.println(main[0]);
		System.out.println(main[1]);
		ArrayList<ScreeningDTO> list= sdao.choice3(scrdate,mno);	
		String sdto2=""; //사용자에게 응답해 주는 메세지
		/*
		if(list!=null) {		
			sdto += "<input type='hidden' value='"+mno+"'>";
			for(int i=0; i<list.size(); i++) {
				ScreeningDTO dto=new ScreeningDTO();
				dto=list.get(i);
				sdto += "<li>";
				sdto += "<a class='bb' href='seat.do?scrno="+ dto.getScrno()+"'>";
				sdto += dto.getScrdate()+ " 상영시작 시간: ";
				sdto += dto.getScrstart() + " 제 "+ dto.getHno()+"관";
				sdto += "</a>";
				sdto += "</li>";
				sdto += "<br>";
			}//for end
		}//if end
		*/
		//System.out.println(sdto);
		
		System.out.println("번호:"+mno);
		System.out.println("영화날짜:"+scrdate);
		String scrstart= "";
		if(list!=null) {
			sdto2 += "<input type='hidden'  value='"+mno+"'>";
			sdto2 +="<div class='zza' >";
			sdto2 +="<ul>";
			for(int i=0; i<list.size(); i++) {
				ScreeningDTO dto=new ScreeningDTO();
				dto=list.get(i);
				sdto2 += " <li id='cc' class='cc' value='"+dto.getScrno()+"'> ";
				sdto2 += "<div class='movie_select_jr' style='	text-align: center;'>";
				System.out.println(dto.getScrno());
				scrstart= dto.getScrstart();
				sdto2 += "<a href='seat.do?scrno="+dto.getScrno()+"' id='goseat' >";
				sdto2 += ""+scrstart.substring(0,5)+" ";
				sdto2 += "제"+dto.getHno()+"관 ";
				sdto2 += "</a>";
				sdto2 +="</div>";
				sdto2 += " </li> ";
			}//for end
			sdto2 +="</ul>";
			sdto2 +="</div>";
			sdto2 += "<br>";
		}//if end
		
		try {
			resp.setContentType("text/plain; charset=UTF-8");
			PrintWriter out=resp.getWriter();
			out.println(sdto2);
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println(" 실패"+e);
		}

	}//choice2() end
	
	@RequestMapping(value= "/movie/seat.do", method = RequestMethod.GET)
	public ModelAndView seatsel(int scrno, BookingDTO bdto) {
		ModelAndView mav =  new ModelAndView();
		mav.setViewName("movie/seat");
		//mav.addObject("list2",sdao.list2(scrno));
		//mav.addObject("seat", sdao.checkseat(scrno));
		//System.out.println("좌석:"+sdao.checkseat(scrno));
		//System.out.println(sdao.list2(scrno));
		
		ArrayList<BookingDTO> list=sdao.list2(scrno);
		String bloc="";
		if(list==null) {
			for(char i='A'; i<='J'; i++) {
				for(int j=1; j<=10; j++){				
					
					String str=i+""+j;      //준비된 좌석 전체 갯수 
					//System.out.println(str +  "  "  +  test[n]);
					bloc+="<input type='checkbox' id='" + i + "" + j +"' class='bloc' name='bloc' value='" + i+""+j+"'/>";
					bloc+="<label for='" + i + "" + j +"'></label>";
				}
				bloc+="<br>";
			}
			mav.addObject("bloc",bloc);
		}else {		
			int count=0;
			Vector<String> seatNUM=new Vector<String>();
			for(int i=0; i<list.size(); i++) {
				BookingDTO bVO=list.get(i);
				String line=bVO.getBloc();
				String word[]=line.split(",");
				for(int j=0; j<word.length; j++) {
					//System.out.print(word[j]+ " ");
					count++;					
					seatNUM.add(word[j]);
				}				
			}//for end		
			//System.out.println();
			//System.out.println(count);
			//System.out.println();
			for(int i=0; i<seatNUM.size(); i++) {
				//System.out.print(seatNUM.get(i) +" ");
			}
			//System.out.println();
			String[] test=new String[seatNUM.size()];
			for(int i=0; i<seatNUM.size(); i++) {
				test[i]=seatNUM.get(i);
				System.out.print(test[i] +" ");				
			}
			
			System.out.println("--------------------");
			
			//예약 좌석 체크하기
						
			for(char i='A'; i<='J'; i++) {
				for(int j=1; j<=10; j++){				
					
					String str=i+""+j;      //준비된 좌석 전체 갯수 
					//System.out.println(str +  "  "  +  test[n]);
					
					bloc+="<input type='checkbox' id='" + i + "" + j +"' class='bloc' name='bloc' value='" + i+""+j+"'";
					
					for(int n=0; n<test.length; n++) {
						if(str.equals(test[n])) {
							bloc+=" disabled = true";
							
						}
					}
					
					bloc+="  />";
					bloc+="<label for='" + i + "" + j +"'></label>";
				}
				bloc+="<br>";
			}
			mav.addObject("bloc",bloc);
			mav.addObject("list3",test); //예약된 좌석번호
			System.out.println(bloc);
		}
		mav.addObject("list2",list);
		return mav;
	}//seatsel() end
	
	@RequestMapping(value= "/movie/seat2.do", method = RequestMethod.GET)
	public ModelAndView seatsel2(int scrno, BookingDTO bdto) {
		ModelAndView mav =  new ModelAndView();
		mav.setViewName("movie/seat2");
		//mav.addObject("list2",sdao.list2(scrno));
		//mav.addObject("seat", sdao.checkseat(scrno));
		//System.out.println("좌석:"+sdao.checkseat(scrno));
		//System.out.println(sdao.list2(scrno));
		
		ArrayList<BookingDTO> list=sdao.list2(scrno);
		String bloc="";
		if(list==null) {
			for(char i='A'; i<='J'; i++) {
				for(int j=1; j<=10; j++){				
					
					String str=i+""+j;      //준비된 좌석 전체 갯수 
					//System.out.println(str +  "  "  +  test[n]);
					bloc+="<input type='checkbox' id='" + i + "" + j +"' class='bloc' name='bloc' value='" + i+""+j+"'/>";
					bloc+="<label for='" + i + "" + j +"'></label>";
				}
				bloc+="<br>";
			}
			mav.addObject("bloc",bloc);
		}else {		
			int count=0;
			Vector<String> seatNUM=new Vector<String>();
			for(int i=0; i<list.size(); i++) {
				BookingDTO bVO=list.get(i);
				String line=bVO.getBloc();
				String word[]=line.split(",");
				for(int j=0; j<word.length; j++) {
					//System.out.print(word[j]+ " ");
					count++;					
					seatNUM.add(word[j]);
				}				
			}//for end		
			//System.out.println();
			//System.out.println(count);
			//System.out.println();
			for(int i=0; i<seatNUM.size(); i++) {
				//System.out.print(seatNUM.get(i) +" ");
			}
			//System.out.println();
			String[] test=new String[seatNUM.size()];
			for(int i=0; i<seatNUM.size(); i++) {
				test[i]=seatNUM.get(i);
				System.out.print(test[i] +" ");				
			}
			
			System.out.println("--------------------");
			
			//예약 좌석 체크하기
						
			for(char i='A'; i<='J'; i++) {
				for(int j=1; j<=10; j++){				
					
					String str=i+""+j;      //준비된 좌석 전체 갯수 
					//System.out.println(str +  "  "  +  test[n]);
					
					bloc+="<input type='checkbox' id='" + i + "" + j +"' class='bloc' name='bloc' value='" + i+""+j+"'";
					for(int n=0; n<test.length; n++) {
						if(str.equals(test[n])) {
							bloc+=" disabled = true";
						}
					}
					bloc+="  />";
					bloc+="<label for='" + i + "" + j +"'></label>";
					
				}
		
				bloc+="<br>";
			}
			mav.addObject("bloc",bloc);
			mav.addObject("list3",test); //예약된 좌석번호
			System.out.println(bloc);
		}
		mav.addObject("list2",list);
		return mav;
	}//seatsel() end
	
	@RequestMapping(value="/movie/seat.do", method = RequestMethod.POST)
	public ModelAndView seatproc(HttpServletRequest req,HttpSession session, BookingDTO bdto, int scrno) {
		String uid=(String)session.getAttribute("uid");
		
		scrno = Integer.parseInt(req.getParameter("scrno"));
		//System.out.println(scrno);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bbs/msgView");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("bdto", bdto);
		mav.addObject("list2",sdao.list2(scrno));
		
		int cnt = sdao.booking(bdto);
		if(cnt==0) {
		mav.addObject("msg1","<script>\r\n" + 
	            " alert(\"실패\");\r\n" + 
	            " location.href=\"javascript:history.back()\";\r\n" + 
	            "</script>"); 
		} else {
			mav.addObject("msg1", "<script>\r\n" + 
		            " alert(\"예매되었습니다.\");\r\n" + 
		            " location.href=\"../mypage/mypage.do?\";\r\n" + 
		            "</script>"); 
		}
		
		return mav;
	}//seatproc() end
	
	
	
	
	
	
	
	
	
	
	
	
}//class end
