package kr.co.moviepick.bbs;

import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviepick.bbs.BbsDTO;
import kr.co.moviepick.movie.MovieDTO;
import kr.co.moviepick.review.ReviewDTO;
import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.PagingVO;
import net.utility.PagingVO2;
import net.utility.Utility;
@Component
public class BbsDAO {
	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbClose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;
	ArrayList<BbsDTO> list =null;
	
	
	public BbsDAO() {
		System.out.println("---BbsDAO()객체생성됨...");
	}
	
public ArrayList<BbsDTO> list(BbsDTO dto){
		
		try{
			con=dbopen.getConnection();
			sql=new StringBuilder();
		  sql.append(" SELECT nno, ntitle, nsub, ncon, ncnt, ndate, nimg, uid");
	      sql.append(" FROM bbs");
	      sql.append(" ORDER BY nno DESC");
	      pstmt = con.prepareStatement(sql.toString());
	      rs = pstmt.executeQuery();
	      if(rs.next()){
	        list = new ArrayList<BbsDTO>();
	        do {
	          dto = new BbsDTO();
	          dto.setNno(rs.getInt("nno"));
	          dto.setNtitle(rs.getString("ntitle"));
	          dto.setNsub(rs.getString("nsub"));
	          dto.setNcon(rs.getString("ncon"));
	          dto.setNcnt(rs.getInt("ncnt"));
	          dto.setNdate(rs.getString("ndate"));
	          dto.setNimg(rs.getString("nimg"));
	     
	          dto.setUid(rs.getString("uid"));
	          list.add(dto);
	        } while(rs.next());
	      }else{
	        list = null;
	      }//if end

	    }catch(Exception e){
	      System.out.println("유저게시판목록 실패:"+e);
	    }finally{
	      dbClose.close(con, pstmt, rs);
	    }//try end
		return list;
	}//list() end
	
	public int create(BbsDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" INSERT INTO bbs( ntitle, nsub, ncon, nimg, ndate, uid)");
			sql.append(" VALUES( ?, ?, ?, ?, now(), ?) ");
			pstmt = con.prepareStatement(sql.toString());			
			pstmt.setString(1, dto.getNtitle());
			pstmt.setString(2, dto.getNsub());
			pstmt.setString(3, dto.getNcon());		
			pstmt.setString(4, dto.getNimg());
			pstmt.setString(5, dto.getUid());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("글 생성 실패:" +e);
		} finally {
			dbClose.close(con, pstmt);
		}//try end
		return cnt;
	}//create() end
	
	public BbsDTO read(int nno) {
		BbsDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT nno, nsub,ntitle, uid, ndate, ncon, nimg, ncnt" );
			sql.append(" FROM bbs ");
			sql.append(" WHERE nno = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, nno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BbsDTO();
				dto.setNno(rs.getInt("nno"));
				dto.setNsub(rs.getString("nsub"));
				dto.setNtitle(rs.getString("ntitle"));
				dto.setUid(rs.getString("uid"));
				dto.setNdate(rs.getString("ndate"));
				dto.setNcon(rs.getString("ncon"));
				dto.setNimg(rs.getString("nimg"));
				dto.setNcnt(rs.getInt("ncnt"));
			} else {
				dto = null;
			}//if end
		} catch (Exception e) {
			System.out.println("상세보기 실패:"+e);
		} finally {
			dbClose.close(con, pstmt, rs);
		}//try end
		return dto;
	}//read() end
	
	public void updatecnt(int nno) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE bbs ");
			sql.append(" SET ncnt = ncnt + 1 ");
			sql.append(" WHERE nno = ? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, nno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("실패:"+e);
		} finally {
			dbClose.close(con, pstmt, rs);
		}//try end
	}
	
	public int update(BbsDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE bbs ");
			sql.append(" SET ntitle =?, nsub=?, ncon=?, nimg=? ");
			sql.append(" WHERE nno=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getNtitle());
			pstmt.setString(2, dto.getNsub());
			pstmt.setString(3, dto.getNcon());
			pstmt.setString(4, dto.getNimg());
			pstmt.setInt(5, dto.getNno());
			cnt = pstmt.executeUpdate();			
		} catch (Exception e) {
			System.out.println("수정실패:"+e);
		} finally {
			dbClose.close(con, pstmt);
		}//try end
		return cnt;
	}//update() end
	
	public int delete(int nno) {
		int cnt= 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" DELETE FROM bbs ");
			sql.append(" WHERE nno = ? ");
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setInt(1, nno);			
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제실패"+ e);
		} finally {
			dbClose.close(con, pstmt);
		}//try end
		
		return cnt;
	}//delete() end
	public int getArticleCount() throws Exception{
	      
	      int x=0;
	      try {
	         con=dbopen.getConnection();
	         pstmt=con.prepareStatement("SELECT count(*) FROM bbs");
	         rs=pstmt.executeQuery();
	         
	         if(rs.next()) {
	            x=rs.getInt(1);
	         }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         
	         if(rs!=null) {
	            try {
	               rs.close();
	            }catch (SQLException e) {
	               
	            }
	         }//if END
	         if(pstmt!=null) {
	            try {
	               pstmt.close();
	            }catch (SQLException e) {
	               
	            }
	         }//if END
	         
	         if(con!=null) {
	            try {
	               con.close();
	            }catch (SQLException e) {
	               
	            }
	         }//if END
	      }
	      
	      return x;
	   }//getArticleCount
	
	public int getArticleCountmypage(String uid) throws Exception{
	      
	      int x=0;
	      try {
	         con=dbopen.getConnection();
	         pstmt=con.prepareStatement("SELECT count(*) FROM bbs where uid=?");
	         pstmt.setString(1, uid);
	         rs=pstmt.executeQuery();
	         
	         if(rs.next()) {
	            x=rs.getInt(1);
	         }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         
	         if(rs!=null) {
	            try {
	               rs.close();
	            }catch (SQLException e) {
	               
	            }
	         }//if END
	         if(pstmt!=null) {
	            try {
	               pstmt.close();
	            }catch (SQLException e) {
	               
	            }
	         }//if END
	         
	         if(con!=null) {
	            try {
	               con.close();
	            }catch (SQLException e) {
	               
	            }
	         }//if END
	      }
	      
	      return x;
	   }//getArticleCount
	
	public int getArticleCountmypagereview(String uid) throws Exception{
	      
	      int x=0;
	      try {
	         con=dbopen.getConnection();
	         pstmt=con.prepareStatement("SELECT count(*) FROM review WHERE uid=?");
	         pstmt.setString(1, uid);
	         rs=pstmt.executeQuery();
	         
	         if(rs.next()) {
	            x=rs.getInt(1);
	         }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         
	         if(rs!=null) {
	            try {
	               rs.close();
	            }catch (SQLException e) {
	               
	            }
	         }//if END
	         if(pstmt!=null) {
	            try {
	               pstmt.close();
	            }catch (SQLException e) {
	               
	            }
	         }//if END
	         
	         if(con!=null) {
	            try {
	               con.close();
	            }catch (SQLException e) {
	               
	            }
	         }//if END
	      }
	      
	      return x;
	   }//getArticleCount
	
	public ArrayList<BbsDTO> list2(BbsDTO dto,PagingVO vo){
		

		int nowPage=vo.getNowPage();
		int cntPerPage=vo.getCntPerPage();
	    int startRow = ((nowPage-1) * cntPerPage) ;
	    
			try{
				con=dbopen.getConnection();
				sql=new StringBuilder();
			  sql.append(" SELECT nno, ntitle, nsub, ncon, ncnt, ndate, nimg, uid");
		      sql.append(" FROM bbs");
		      sql.append(" ORDER BY nno DESC");
		      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
		      pstmt = con.prepareStatement(sql.toString());
		      rs = pstmt.executeQuery();
		      if(rs.next()){
		        list = new ArrayList<BbsDTO>();
		        do {
		          dto = new BbsDTO();
		          dto.setNno(rs.getInt("nno"));
		          dto.setNtitle(rs.getString("ntitle"));
		          dto.setNsub(rs.getString("nsub"));
		          dto.setNcon(rs.getString("ncon"));
		          dto.setNcnt(rs.getInt("ncnt"));
		          dto.setNdate(rs.getString("ndate"));
		          dto.setNimg(rs.getString("nimg"));
		          dto.setUid(rs.getString("uid"));
		          list.add(dto);
		        } while(rs.next());
		      }else{
		        list = null;
		      }//if end

		    }catch(Exception e){
		      System.out.println("유저게시판목록 실패:"+e);
		    }finally{
		      dbClose.close(con, pstmt, rs);
		    }//try end
			return list;
		}//list() end
	
	
	
	public ArrayList<BbsDTO> list3(String col, String word,BbsDTO dto,PagingVO vo){
		

		int nowPage=vo.getNowPage();
		int cntPerPage=vo.getCntPerPage();
	    int startRow = ((nowPage-1) * cntPerPage);
	    
			try{
				con=dbopen.getConnection();
				sql=new StringBuilder();
				word = word.trim();
			if(word.length()==0) {	//검색 x
			  sql.append(" SELECT nno, ntitle, nsub, ncon, ncnt, ndate, nimg, uid");
		      sql.append(" FROM bbs");
		      sql.append(" ORDER BY nno DESC");
		      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
		      
			}else {
				//검색o
				  sql.append("  SELECT nno, ntitle, nsub, ncon, ncnt, ndate, nimg, uid");
			      sql.append(" FROM bbs ");
			      
			      
			      String search="";
			      if(col.equals("uid")) {
			          search += " WHERE uid LIKE '%" + word + "%'";
			        } else if(col.equals("nsub")) {
			          search += " WHERE nsub LIKE '%" + word + "%'";
			        } else if(col.equals("ncon")) {
			          search += " WHERE  ncon LIKE '%" + word + "%'";
			        } else if(col.equals("nsub_ncon")) {
			          search += " WHERE nsub LIKE '%" + word + "%'";
			          search += " OR ncon LIKE '%" + word + "%'";
			        }
			      
			      sql.append(search);
			      sql.append(" ORDER BY nno DESC");
			       
			      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
				
			}
		      pstmt = con.prepareStatement(sql.toString());
		      rs = pstmt.executeQuery();
		      if(rs.next()){
		        list = new ArrayList<BbsDTO>();
		        do {
		          dto = new BbsDTO();
		          dto.setNno(rs.getInt("nno"));
		          dto.setNtitle(rs.getString("ntitle"));
		          dto.setNsub(rs.getString("nsub"));
		          dto.setNcon(rs.getString("ncon"));
		          dto.setNcnt(rs.getInt("ncnt"));
		          dto.setNdate(rs.getString("ndate"));
		          dto.setNimg(rs.getString("nimg"));
		          dto.setUid(rs.getString("uid"));
		          list.add(dto);
		        } while(rs.next());
		      }else{
		        list = null;
		      }//if end

		    }catch(Exception e){
		      System.out.println("유저게시판목록 실패:"+e);
		    }finally{
		      dbClose.close(con, pstmt, rs);
		    }//try end
			return list;
		}//list() end
	
public ArrayList<BbsDTO> mypagelist(BbsDTO dto,PagingVO vo,String uid){
		

		int nowPage=vo.getNowPage();
		int cntPerPage=vo.getCntPerPage();
	    int startRow = ((nowPage-1) * cntPerPage) ;
	    
			try{
				con=dbopen.getConnection();
				sql=new StringBuilder();
			  sql.append(" SELECT nno,ntitle, nsub, ndate,uid");
		      sql.append(" FROM bbs");
		      sql.append(" WHERE uid=? ");
		      sql.append(" ORDER BY nno DESC");
		      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
		      pstmt = con.prepareStatement(sql.toString());
		      pstmt.setString(1, uid);
		      rs = pstmt.executeQuery();
		      if(rs.next()){
		        list = new ArrayList<BbsDTO>();
		        do {
		          dto = new BbsDTO();
		          dto.setNno(rs.getInt("nno"));
		          dto.setNtitle(rs.getString("ntitle"));
		          dto.setNsub(rs.getString("nsub"));
		          dto.setNdate(rs.getString("ndate"));
		          dto.setUid(rs.getString("uid"));
		          list.add(dto);
		        } while(rs.next());
		      }else{
		        list = null;
		      }//if end

		    }catch(Exception e){
		      System.out.println("마이페이지 유저게시판목록 실패:"+e);
		    }finally{
		      dbClose.close(con, pstmt, rs);
		    }//try end
			return list;
		}//list() end

	public int count(String col, String word) {
		
		int cnt=0;
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT COUNT(*) as cnt ");
			sql.append(" FROM bbs ");
			
			  if(word.trim().length()>=1) {//검색어가있다면
				  String search="";
			      if(col.equals("uid")) {
			          search += " WHERE uid LIKE '%" + word + "%'";
			        } else if(col.equals("nsub")) {
			          search += " WHERE nsub LIKE '%" + word + "%'";
			        } else if(col.equals("ncon")) {
			          search += " WHERE  ncon LIKE '%" + word + "%'";
			        } else if(col.equals("nsub_ncon")) {
			          search += " WHERE nsub LIKE '%" + word + "%'";
			          search += " OR ncon LIKE '%" + word + "%'";
			        }
			      
				  sql.append(search);
			  }//if end
			  
			  pstmt=con.prepareStatement(sql.toString());
			  rs=pstmt.executeQuery();
			  if(rs.next()) {
				  cnt=rs.getInt("cnt");
			  }//if end
			 
		} catch (Exception e) {
			System.out.println("행 갯수 실패:" + e);
		}finally {
			dbClose.close(con, pstmt, rs);
		}//try end
		
		
		return cnt;
	}//count() end
	
	public ArrayList<ReviewDTO> mypagereview(MovieDTO mdto,PagingVO2 vo2, String uid) {
		
		int nowPage2=vo2.getNowPage2();
		int cntPerPage2=vo2.getCntPerPage2();
	    int startRow = ((nowPage2-1) * cntPerPage2) ;
		
		ArrayList<ReviewDTO> review = null;
		
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" select RV.* , MV.mno, .MV.msub ");
			sql.append(" from review RV left join movie MV  ");
			sql.append(" on RV.mno=MV.mno ");
			sql.append(" WHERE RV.uid = ? ");
			sql.append(" ORDER BY rno DESC ");
			sql.append(" LIMIT "+ startRow + ","+ cntPerPage2 );
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				review= new ArrayList<ReviewDTO>();
				do {
					ReviewDTO redto = new ReviewDTO();
					redto = new ReviewDTO();
					redto.setRno(rs.getInt("rno"));
					redto.setRstar(rs.getFloat("rstar"));
					redto.setRcom(rs.getString("rcom"));
					redto.setUid(rs.getString("uid"));
					redto.setMno(rs.getInt("mno"));
					redto.setMsub(rs.getString("msub"));
					review.add(redto);
				} while(rs.next());
			} else {
				review= null;
			}//if end
		}catch(Exception e) {
			System.out.println("댓글목록 실패" + e);
		}finally {
			dbClose.close(con, pstmt, rs);
		}//try end
		return review;
	}//review end

	//--------------------------------------
	//admin 영역
	
	public ArrayList<BbsDTO> bbs_admin(BbsDTO dto){
	    try {
	      con = dbopen.getConnection();
	      sql = new StringBuilder();
	      sql.append(" SELECT *");
	      sql.append(" FROM bbs");
	      sql.append(" ORDER BY nno DESC");
	      
	      pstmt=con.prepareStatement(sql.toString());
	      rs=pstmt.executeQuery();
	      if(rs.next()) {
	        list=new ArrayList<BbsDTO>();
	        do {
	           dto=new BbsDTO();
	          dto.setNno(rs.getInt("nno"));
	          dto.setNtitle(rs.getString("ntitle"));
	          dto.setNsub(rs.getString("nsub"));
	          dto.setUid(rs.getString("uid"));
	          dto.setNcon(rs.getString("ncon"));
	          dto.setNcnt(rs.getInt("ncnt"));
	          dto.setNdate(rs.getString("ndate"));
	          dto.setNimg(rs.getString("nimg"));
	         
	          list.add(dto);
	        }while(rs.next());
	      }else {
	        list=null;
	      }
	      
	    }catch (Exception e) {
	      System.out.println("네티즉픽 게시글 리스팅 실패:"+e);
	    }finally {
	      dbClose.close(con, pstmt, rs);
	    }
	    return list;
	  }//movie_admin() end
		
		public int bbs_delete(int nno) {
	    int cnt= 0;
	    try {
	      con = dbopen.getConnection();
	      sql = new StringBuilder();
	      sql.append(" DELETE FROM bbs ");
	      sql.append(" WHERE nno = ? ");
	      pstmt= con.prepareStatement(sql.toString());
	      pstmt.setInt(1, nno);     
	      cnt = pstmt.executeUpdate();
	    } catch (Exception e) {
	      System.out.println("네티즌픽 게시글 삭제 실패"+ e);
	    } finally {
	      dbClose.close(con, pstmt);
	    }//try end
	    
	    return cnt;
	  }//bbs_delete() end
	
	
		public ArrayList<BbsDTO> mypagemainlist(BbsDTO bbsdto,String uid){
			

		    
				try{
					con=dbopen.getConnection();
					sql=new StringBuilder();
				  sql.append(" SELECT nno,ntitle, nsub, ndate,uid");
			      sql.append(" FROM bbs");
			      sql.append(" WHERE uid=? ");
			      sql.append(" ORDER BY nno DESC");
			      sql.append(" LIMIT 5 " );
			      pstmt = con.prepareStatement(sql.toString());
			      pstmt.setString(1, uid);
			      rs = pstmt.executeQuery();
			      if(rs.next()){
			        list = new ArrayList<BbsDTO>();
			        do {
			          bbsdto = new BbsDTO();
			          bbsdto.setNno(rs.getInt("nno"));
			          bbsdto.setNtitle(rs.getString("ntitle"));
			          bbsdto.setNsub(rs.getString("nsub"));
			          bbsdto.setNdate(rs.getString("ndate"));
			          bbsdto.setUid(rs.getString("uid"));
			          list.add(bbsdto);
			        } while(rs.next());
			      }else{
			        list = null;
			      }//if end

			    }catch(Exception e){
			      System.out.println("마이페이지 유저게시판목록 실패:"+e);
			    }finally{
			      dbClose.close(con, pstmt, rs);
			    }//try end
				return list;
			}//list() end
		
		public ArrayList<ReviewDTO> mypagemainreview(MovieDTO mdto,String uid) {
			
		
			ArrayList<ReviewDTO> review = null;
			
			try {
				con = dbopen.getConnection();
				sql = new StringBuilder();
				sql.append(" select RV.* , MV.mno, .MV.msub ");
				sql.append(" from review RV left join movie MV  ");
				sql.append(" on RV.mno=MV.mno ");
				sql.append(" WHERE RV.uid = ? ");
				sql.append(" ORDER BY rno DESC ");
				sql.append(" LIMIT 5");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, uid);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					review= new ArrayList<ReviewDTO>();
					do {
						ReviewDTO redto = new ReviewDTO();
						redto = new ReviewDTO();
						redto.setRno(rs.getInt("rno"));
						redto.setRstar(rs.getFloat("rstar"));
						redto.setRcom(rs.getString("rcom"));
						redto.setUid(rs.getString("uid"));
						redto.setMno(rs.getInt("mno"));
						redto.setMsub(rs.getString("msub"));
						review.add(redto);
					} while(rs.next());
				} else {
					review= null;
				}//if end
			}catch(Exception e) {
				System.out.println("댓글목록 실패" + e);
			}finally {
				dbClose.close(con, pstmt, rs);
			}//try end
			return review;
		}//review end
}//class end
