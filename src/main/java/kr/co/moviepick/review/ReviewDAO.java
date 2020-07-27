package kr.co.moviepick.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.moviepick.movie.MovieDTO;
import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.PagingVO;
@Component
public class ReviewDAO {
	
	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbclose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;
	ArrayList<ReviewDTO> review = null;
	
	public ReviewDTO read(int rno) {
		ReviewDTO redto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT * ");
			sql.append(" FROM review ");
			sql.append(" WHERE rno = ?");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, rno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				redto=new ReviewDTO();
				redto.setRno(rs.getInt("rno"));
				redto.setRstar(rs.getFloat("rstar"));
				redto.setRcom(rs.getString("rcom"));
				redto.setUid(rs.getString("uid"));
				redto.setMno(rs.getInt("mno"));
			}
			else {
				redto = null;
			}
		}catch(Exception e) {
			System.out.println("무비픽 상세보기실패: "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}
		return redto;
	}
	
	//리뷰에서 가져올 리스트
			public ArrayList<ReviewDTO> review() {
			
			try {
				con = dbopen.getConnection();
				sql = new StringBuffer();
				sql.append("SELECT rno, rstar, rcom, uid, mno ");
				sql.append("FROM review");
				sql.append("WHERE mno = ?");
				sql.append(" ORDER BY rno DESC ");
				pstmt = con.prepareStatement(sql.toString());
				
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
						review.add(redto);
					} while(rs.next());
				} else {
					review= null;
				}//if end
			}catch(Exception e) {
				System.out.println("댓글목록 실패" + e);
			}finally {
				dbclose.close(con, pstmt, rs);
			}//try end
			return review;
		}//review end
			
			public ArrayList<ReviewDTO> review1() {
				
			try {
				con = dbopen.getConnection();
				sql = new StringBuffer();
				sql.append("SELECT rstar, mno ");
				sql.append("FROM review");
				sql.append(" ORDER BY rno DESC ");
				pstmt = con.prepareStatement(sql.toString());
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					review= new ArrayList<ReviewDTO>();
					do {
						ReviewDTO redto = new ReviewDTO();
						redto = new ReviewDTO();
						redto.setRstar(rs.getFloat("rstar"));
						redto.setMno(rs.getInt("mno"));
						review.add(redto);
					} while(rs.next());
				} else {
					review= null;
				}//if end
			}catch(Exception e) {
				System.out.println("댓글목록2 실패" + e);
			}finally {
				dbclose.close(con, pstmt, rs);
			}//try end
			return review;
		}//review end
			
			public ArrayList<ReviewDTO> review_admin(ReviewDTO dto,PagingVO vo){
				
				int nowPage=vo.getNowPage();
				int cntPerPage=vo.getCntPerPage();
			    int startRow = ((nowPage-1) * cntPerPage) ;
				
				try {
		      con = dbopen.getConnection();
		      sql = new StringBuffer();
		      sql.append(" SELECT *");
		      sql.append(" FROM review");
		      sql.append(" ORDER BY rno DESC");
		      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
		      
		      pstmt=con.prepareStatement(sql.toString());
		      rs=pstmt.executeQuery();
		      if(rs.next()) {
		        review=new ArrayList<ReviewDTO>();
		        do {
		           dto=new ReviewDTO();
		          dto.setRno(rs.getInt("rno"));
		          dto.setRstar(rs.getFloat("rstar"));
		          dto.setRcom(rs.getString("rcom"));
		          dto.setUid(rs.getString("uid"));
		          dto.setMno(rs.getInt("mno"));
		          review.add(dto);
		        }while(rs.next());
		      }else {
		        review=null;
		      }
		      
		    }catch (Exception e) {
		      System.out.println("무비픽 목록실패:"+e);
		    }finally {
		      dbclose.close(con, pstmt, rs);
		    }
		    return review;
		    }//review_admin() end
			
			public ArrayList<ReviewDTO> review_adminsearch(String col, String word,ReviewDTO dto,PagingVO vo){
				
				int nowPage=vo.getNowPage();
				int cntPerPage=vo.getCntPerPage();
			    int startRow = ((nowPage-1) * cntPerPage) ;
				
				try {
		      con = dbopen.getConnection();
		      sql = new StringBuffer();
		      word = word.trim();
		      
		      if(word.length()==0) {
		    	  
		    	  sql.append(" SELECT *");
			      sql.append(" FROM review");
			      sql.append(" ORDER BY rno DESC");
			      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
		      }else {
		    	  sql.append(" SELECT *");
			      sql.append(" FROM review");
			      
			      String search="";
			      if(col.equals("uid")) {
			          search += " WHERE uid LIKE '%" + word + "%'";
			        	} else if(col.equals("rcom")) {
			          search += " WHERE rcom LIKE '%" + word + "%'";
			        	} 
			      	sql.append(search);
			      	sql.append(" ORDER BY rno DESC");
				      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
		      }
		      		
		      
		      pstmt=con.prepareStatement(sql.toString());
		      rs=pstmt.executeQuery();
		      if(rs.next()) {
		        review=new ArrayList<ReviewDTO>();
		        do {
		           dto=new ReviewDTO();
		          dto.setRno(rs.getInt("rno"));
		          dto.setRstar(rs.getFloat("rstar"));
		          dto.setRcom(rs.getString("rcom"));
		          dto.setUid(rs.getString("uid"));
		          dto.setMno(rs.getInt("mno"));
		          review.add(dto);
		        }while(rs.next());
		      }else {
		        review=null;
		      }
		      
		    }catch (Exception e) {
		      System.out.println("무비픽 목록실패:"+e);
		    }finally {
		      dbclose.close(con, pstmt, rs);
		    }
		    return review;
		    }//review_admin() end
			

			public int getArticleCount() throws Exception{
			      
			      int x=0;
			      try {
			         con=dbopen.getConnection();
			         pstmt=con.prepareStatement("SELECT count(*) FROM review ");
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
			   }//getArticleCount3
			
			public int count(String col, String word) {
				
				int cnt=0;
				
				try {
					con=dbopen.getConnection();
					sql=new StringBuffer();
					
					sql.append(" SELECT COUNT(*) as cnt ");
					sql.append(" FROM review ");
					
					  if(word.trim().length()>=1) {
						String search="";
					
				      if(col.equals("uid")) {
				          search += " WHERE uid LIKE '%" + word + "%'";
				        } else if(col.equals("rcom")) {
				          search += " WHERE rcom LIKE '%" + word + "%'";
				        } 
						  sql.append(search);
					  }
					  pstmt=con.prepareStatement(sql.toString());
					  rs=pstmt.executeQuery();
					  if(rs.next()) {
						  cnt=rs.getInt("cnt");
					  }//if end
					 
				} catch (Exception e) {
					System.out.println("무비픽 검색 카운트 시ㄹ패:" + e);
				}finally {
					dbclose.close(con, pstmt, rs);
				}//try end
				
				
				return cnt;
			}//count() end
			
			public ReviewDTO read_admin(int rno){
				  ReviewDTO dto = null;
		      try {
		        con = dbopen.getConnection();
		        sql = new StringBuffer();
		        sql.append(" SELECT *");
		        sql.append(" FROM review");
		        sql.append(" ORDER BY rno DESC");
		        
		        pstmt=con.prepareStatement(sql.toString());
		        rs=pstmt.executeQuery();
		        if(rs.next()) {
		          review=new ArrayList<ReviewDTO>();
		          do {
		             dto=new ReviewDTO();
		            dto.setRno(rs.getInt("rno"));
		            dto.setRstar(rs.getFloat("rstar"));
		            dto.setRcom(rs.getString("rcom"));
		            dto.setUid(rs.getString("uid"));
		            dto.setMno(rs.getInt("mno"));
		            review.add(dto);
		          }while(rs.next());
		        }else {
		          dto=null;
		        }
		        
		      }catch (Exception e) {
		        System.out.println("무비픽 목록실패:"+e);
		      }finally {
		        dbclose.close(con, pstmt, rs);
		      }
		      return dto;
		      }//review_admin() end
			
			public int del(int rno) {
			    int cnt= 0;
			    try {
			      con = dbopen.getConnection();
			      sql = new StringBuffer();
			      sql.append(" DELETE FROM review ");
			      sql.append(" WHERE rno = ? ");
			      pstmt= con.prepareStatement(sql.toString());
			      pstmt.setInt(1, rno);     
			      cnt = pstmt.executeUpdate();
			    } catch (Exception e) {
			      System.out.println("댓글 강제 삭제 실패"+ e);
			    } finally {
			      dbclose.close(con, pstmt);
			    }//try end
			    return cnt;
			    }//del() end

			
}//class end
