package kr.co.moviepick.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.moviepick.download.DownloadDTO;
import kr.co.moviepick.people.PeopleDTO;
import kr.co.moviepick.review.ReviewDTO;
import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.PagingVO;
import net.utility.PagingVO2;

@Component
public class MovieDAO {
	
	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbclose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;
	ArrayList<MovieDTO> list = null;
	ArrayList<MovieDTO> list5 = null;
	ArrayList<ReviewDTO> review = null;
	ArrayList<PeopleDTO> peolist=null;
	public MovieDAO() {
		System.out.println("---MovieDAO()객체생성됨");
	}
	
	public ArrayList<MovieDTO> list(){
		
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT mno,msub,mgrade,msum,mposter,msta");
			sql.append(" FROM movie");
			sql.append(" ORDER BY mno DESC");
			
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<MovieDTO>();
				do {
					MovieDTO dto=new MovieDTO();
					dto.setMno(rs.getInt("mno"));
					dto.setMsub(rs.getString("msub"));
					dto.setMgrade(rs.getInt("mgrade"));
					dto.setMsum(rs.getString("msum"));
					dto.setMposter(rs.getString("mposter"));
					dto.setMsta(rs.getInt("msta"));
					list.add(dto);
				}while(rs.next());
			}else {
				list=null;
			}
			
		}catch (Exception e) {
			System.out.println("무비픽 목록실패:"+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}
		return list;
	}
	
	
	
	public ArrayList<MovieDTO> mainlist(){
		
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT mno,msub,mgrade,msum,mposter,msta,mopen");
			sql.append(" FROM movie");
			sql.append(" WHERE msta=1 ");
			sql.append(" ORDER BY mno DESC");
			sql.append(" LIMIT 5");
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<MovieDTO>();
				do {
					MovieDTO dto=new MovieDTO();
					dto.setMno(rs.getInt("mno"));
					dto.setMsub(rs.getString("msub"));
					dto.setMgrade(rs.getInt("mgrade"));
					dto.setMsum(rs.getString("msum"));
					dto.setMposter(rs.getString("mposter"));
					dto.setMsta(rs.getInt("msta"));
					dto.setMopen(rs.getString("mopen"));
					list.add(dto);
				}while(rs.next());
			}else {
				list=null;
			}
			
		}catch (Exception e) {
			System.out.println("무비픽 목록실패:"+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}
		return list;
	}
	
	
	public int getArticleCount() throws Exception{
	      
	      int x=0;
	      try {
	         con=dbopen.getConnection();
	         pstmt=con.prepareStatement("SELECT count(*) FROM movie");
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
	
	
	
	
	public int getArticleCount2(int mno) {
	      
	      int x=0;
	      try {
	         con=dbopen.getConnection();
	         pstmt=con.prepareStatement("SELECT count(*) FROM review where mno=? ");
	         pstmt.setInt(1, mno);
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
	
	public ArrayList<MovieDTO> list3(String col, String word,MovieDTO dto,PagingVO vo){
		

		int nowPage=vo.getNowPage();
		int cntPerPage=vo.getCntPerPage();
	    int startRow = ((nowPage-1) * cntPerPage);
	    String search="";
			try{
				con=dbopen.getConnection();
				sql=new StringBuffer();
				word = word.trim();
				
			if(word.length()==0 & col.length()==0) {	
				//단어검색 x, 장르만
				 sql.append(" SELECT mno, msub, mposter,mgrade,mgenre,msta");
				  sql.append(" FROM movie");
				  sql.append(" ORDER BY mno DESC");
			      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
		      
			}else  {
			
			      
				  sql.append(" SELECT mno, msub, mposter,mgrade,mgenre,msta");
				  sql.append(" FROM movie");
			      
			      
			      if(col.equals("msub")) {
			          search += " WHERE msub LIKE '%" + word + "%'";
			        } else if(col.equals("msum")) {
			          search += " WHERE msum LIKE '%" + word + "%'";
			        } else if(col.equals("msub_msum")) {
			          search += " WHERE msub LIKE '%" + word + "%'";
			          search += " OR msum LIKE '%" + word + "%'";
			        }else if(col.equals("fan")){
			        	 search += " WHERE mgenre LIKE '%fan%' ";
			        }else if(col.equals("rom")){
			        	 search += " WHERE mgenre LIKE '%rom%' ";
			        }else if(col.equals("act")){
			        	 search += " WHERE mgenre LIKE '%act%' ";
			        }else if(col.equals("doc")){
			        	 search += " WHERE mgenre LIKE '%doc%' ";
			        }else if(col.equals("com")){
			        	 search += " WHERE mgenre LIKE '%com%' ";
			        }else if(col.equals("ani")){
			        	 search += " WHERE mgenre LIKE '%ani%' ";
			        }else if(col.equals("mus")){
			        	 search += " WHERE mgenre LIKE '%mus%' ";
			        }else if(col.equals("thr")){
			        	 search += " WHERE mgenre LIKE '%thr%' ";
			        }else if(col.equals("cri")) {
			        	 search += " WHERE mgenre LIKE '%cri%' ";
			        }
			      
			      sql.append(search);
			      sql.append(" ORDER BY mno DESC");
			       
			      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
			      
			}
		      pstmt = con.prepareStatement(sql.toString());
		      rs = pstmt.executeQuery();
		      if(rs.next()){
		       list = new ArrayList<MovieDTO>();
		        do {
					 MovieDTO dto1=new MovieDTO();
					dto1.setMno(rs.getInt("mno"));
					dto1.setMsub(rs.getString("msub"));
					dto1.setMposter(rs.getString("mposter"));
					dto1.setMgrade(rs.getInt("mgrade"));
					dto1.setMgenre(rs.getString("mgenre"));
					dto1.setMsta(rs.getInt("msta"));
		          list.add(dto1);  
		        } while(rs.next());
		      }else{
		        list = null;
		      }//if end

		    }catch(Exception e){
		      System.out.println("무비픽리스트3 실패:"+e);
		    }finally{
		      dbclose.close(con, pstmt, rs);
		    }//try end
			return list;
		}//list() end

	public int count(String col, String word) {
		
		int cnt=0;
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuffer();
			
			sql.append(" SELECT COUNT(*) as cnt ");
			sql.append(" FROM movie ");
			
			  if(word.trim().length()>=1 | col.length()>=1) {
				String search="";
			
		      if(col.equals("msub")) {
		          search += " WHERE msub LIKE '%" + word + "%'";
		        } else if(col.equals("msum")) {
		          search += " WHERE msum LIKE '%" + word + "%'";
		        } else if(col.equals("msub_msum")) {
		          search += " WHERE msub LIKE '%" + word + "%'";
		          search += " OR msum LIKE '%" + word + "%'";
		        }else if(col.equals("fan")){
		        	 search += " WHERE mgenre LIKE '%fan%' ";
		        }else if(col.equals("rom")){
		        	 search += " WHERE mgenre LIKE '%rom%' ";
		        }else if(col.equals("act")){
		        	 search += " WHERE mgenre LIKE '%act%' ";
		        }else if(col.equals("doc")){
		        	 search += " WHERE mgenre LIKE '%doc%' ";
		        }else if(col.equals("com")){
		        	 search += " WHERE mgenre LIKE '%com%' ";
		        }else if(col.equals("ani")){
		        	 search += " WHERE mgenre LIKE '%ani%' ";
		        }else if(col.equals("mus")){
		        	 search += " WHERE mgenre LIKE '%mus%' ";
		        }else if(col.equals("thr")){
		        	 search += " WHERE mgenre LIKE '%thr%' ";
		        }else if(col.equals("cri")) {
		        	 search += " WHERE mgenre LIKE '%cri%' ";
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
	
	
	
	
	public MovieDTO read(int mno) {
		MovieDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT mno, msub, mposter, mgenre, mgrade, mrun, mopen, mend, mdir, mact, msum, mtrail, mwhy, mfile, msize, msta");
			sql.append(" FROM movie");
			sql.append(" WHERE mno = ?");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, mno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new MovieDTO();
				dto.setMno(rs.getInt("mno"));
				dto.setMsub(rs.getString("msub"));
				dto.setMposter(rs.getString("mposter"));
				dto.setMgenre(rs.getString("mgenre"));
				dto.setMgrade(rs.getInt("mgrade"));
				dto.setMrun(rs.getInt("mrun"));
				dto.setMopen(rs.getString("mopen"));
				dto.setMend(rs.getString("mend"));
				dto.setMdir(rs.getString("mdir"));
				dto.setMact(rs.getString("mact"));
				dto.setMsum(rs.getString("msum"));
				dto.setMtrail(rs.getString("mtrail"));
				dto.setMwhy(rs.getString("mwhy"));
				dto.setMfile(rs.getString("mfile"));
				dto.setMsize(rs.getInt("msize"));
				dto.setMsta(rs.getInt("msta"));
			}
			else {
				dto = null;
			}
		}catch(Exception e) {
			System.out.println("무비픽 상세보기실패: "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}
	
	//리뷰에서 가져올 리스트
		public ArrayList<ReviewDTO> review(ReviewDTO redto,PagingVO2 vo2) {
			
			int nowPage2=vo2.getNowPage2();
			int cntPerPage2=vo2.getCntPerPage2();
		    int startRow = ((nowPage2-1) * cntPerPage2 ) ;
		    
			ArrayList<ReviewDTO> review =null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT rno, rstar, rcom, uid, mno ");
			sql.append("FROM review ");
			sql.append("WHERE mno = ? ");
			sql.append("ORDER BY rno DESC ");
			sql.append(" LIMIT "+ startRow + ","+ cntPerPage2 );
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, redto.getMno());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				review= new ArrayList<ReviewDTO>();
				do {
					//ReviewDTO redto = new ReviewDTO();
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
			System.out.println("리뷰목록 실패" + e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		return review;
	}//review end
	
		public int recreate(ReviewDTO dto) {
			int cnt = 0;
			try {
				con = dbopen.getConnection();
				sql = new StringBuffer();
				sql.append(" INSERT INTO review(rno, rstar, rcom, uid, mno)");
			    sql.append(" VALUES(?, ?, ?, ?, ?)"); 
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, dto.getRno());
				pstmt.setFloat(2, dto.getRstar());
				pstmt.setString(3, dto.getRcom());
				pstmt.setString(4, dto.getUid());
				pstmt.setInt(5, dto.getMno());
				cnt = pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("리뷰생성 실패"+e);
			} finally {
				dbclose.close(con, pstmt);
			}//try end
			return cnt;
		}//create() end
	
	public float aver(int mno) {
		float cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT AVG(rstar) as cnt FROM review WHERE mno = ? GROUP BY mno ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			/*if(rs.next()) {
				//ReviewDTO dto = new ReviewDTO();
				redto = new ReviewDTO();
				redto.setMno(rs.getInt("mno"));
			} else {
				review= null;
			}//if end
			*/
			if(rs.next()) {
		        cnt=rs.getFloat("cnt");
		      }
		} catch (Exception e) {
			System.out.println("평균 실패:"+e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return cnt;
	}//aver end
	public ArrayList<PeopleDTO> peolist(MovieDTO dto, PeopleDTO peodto) {
		
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT pno, pname FROM people WHERE pno in(" + dto.getMdir() + ")");
			//System.out.println(sql.toString());
			/*
			for(int i=0; i<intmdir.length-1; i++) {
				sql.append(" ?,");
			}
			sql.append(" ?)");
			*/
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				peolist=new ArrayList<PeopleDTO>();
				do {
					peodto = new PeopleDTO();
					peodto.setPno(rs.getInt("pno"));
					peodto.setPname(rs.getString("pname"));
					peolist.add(peodto);
				} while(rs.next());
			}else {
				peolist=null;
			}
			
			//System.out.println("사람리스트"+peolist);
		}catch(Exception e) {
		      System.out.println("사람 상세보기 실패: "+e);
		    }finally {
		    	dbclose.close(con, pstmt, rs);
		    }   
		return peolist;
	}//peolist() end
	
public ArrayList<PeopleDTO> peolist2(MovieDTO dto, PeopleDTO peodto) {
		
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT pno, pname FROM people WHERE pno in(" + dto.getMact() + ")");
			//System.out.println(sql.toString());
			/*
			for(int i=0; i<intmdir.length-1; i++) {
				sql.append(" ?,");
			}
			sql.append(" ?)");
			*/
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				peolist=new ArrayList<PeopleDTO>();
				do {
					peodto = new PeopleDTO();
					peodto.setPno(rs.getInt("pno"));
					peodto.setPname(rs.getString("pname"));
					peolist.add(peodto);
				} while(rs.next());
			}else {
				peolist=null;
			}
			
			//System.out.println("사람리스트"+peolist);
		}catch(Exception e) {
		      System.out.println("사람2 상세보기 실패: "+e);
		    }finally {
		    	dbclose.close(con, pstmt, rs);
		    }   
		return peolist;
	}//peolist2() end
public ArrayList<MovieDTO> list4(MovieDTO dto,PagingVO vo, String mgenre){
	

	int nowPage=vo.getNowPage();
	int cntPerPage=vo.getCntPerPage();
    int startRow = ((nowPage-1) * cntPerPage) ;
    
		try{
			con=dbopen.getConnection();
			sql=new StringBuffer();
		  sql.append(" SELECT *");
		sql.append(" FROM movie");
		sql.append(" WHERE mgenre = ? ");
		sql.append(" ORDER BY mno DESC");
	      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setString(1, mgenre);
	      rs = pstmt.executeQuery();
	      if(rs.next()){
	        list = new ArrayList<MovieDTO>();
	        do {
	        	dto=new MovieDTO();
				 dto.setMno(rs.getInt("mno"));
			      dto.setMsub(rs.getString("msub"));
			      dto.setMposter(rs.getString("mposter"));
			      dto.setMgenre(rs.getString("mgenre"));
			      dto.setMgrade(rs.getInt("mgrade"));
			      dto.setMrun(rs.getInt("mrun"));
			      dto.setMopen(rs.getString("mopen"));
			      dto.setMend(rs.getString("mend"));
			      dto.setMdir(rs.getString("mdir"));
			      dto.setMact(rs.getString("mact"));
			      dto.setMsum(rs.getString("msum"));
			      dto.setMtrail(rs.getString("mtrail"));
			      dto.setMwhy(rs.getString("mwhy"));
			      dto.setMfile(rs.getString("mfile"));
			      dto.setMsize(rs.getInt("msize"));
			      dto.setMsta(rs.getInt("msta"));
	          list.add(dto);
	        } while(rs.next());
	      }else{
	        list = null;
	      }//if end

	    }catch(Exception e){
	      System.out.println("무비픽리스트2 실패:"+e);
	    }finally{
	      dbclose.close(con, pstmt, rs);
	    }//try end
		return list;
	}//list() end
public ArrayList<MovieDTO> list2(MovieDTO dto,PagingVO vo){
	

	int nowPage=vo.getNowPage();
	int cntPerPage=vo.getCntPerPage();
    int startRow = ((nowPage-1) * cntPerPage) ;
    
		try{
			con=dbopen.getConnection();
			sql=new StringBuffer();
		  sql.append(" SELECT *");
		sql.append(" FROM movie");
		sql.append(" ORDER BY mno DESC");
	      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
	      pstmt = con.prepareStatement(sql.toString());
	      rs = pstmt.executeQuery();
	      if(rs.next()){
	        list = new ArrayList<MovieDTO>();
	        do {
	        	dto=new MovieDTO();
				 dto.setMno(rs.getInt("mno"));
			      dto.setMsub(rs.getString("msub"));
			      dto.setMposter(rs.getString("mposter"));
			      dto.setMgenre(rs.getString("mgenre"));
			      dto.setMgrade(rs.getInt("mgrade"));
			      dto.setMrun(rs.getInt("mrun"));
			      dto.setMopen(rs.getString("mopen"));
			      dto.setMend(rs.getString("mend"));
			      dto.setMdir(rs.getString("mdir"));
			      dto.setMact(rs.getString("mact"));
			      dto.setMsum(rs.getString("msum"));
			      dto.setMtrail(rs.getString("mtrail"));
			      dto.setMwhy(rs.getString("mwhy"));
			      dto.setMfile(rs.getString("mfile"));
			      dto.setMsize(rs.getInt("msize"));
			      dto.setMsta(rs.getInt("msta"));
	          list.add(dto);
	        } while(rs.next());
	      }else{
	        list = null;
	      }//if end

	    }catch(Exception e){
	      System.out.println("무비픽리스트2 실패:"+e);
	    }finally{
	      dbclose.close(con, pstmt, rs);
	    }//try end
		return list;
	}//list() end

		public ArrayList<MovieDTO> movie_admin(MovieDTO dto){
		try {
		  con = dbopen.getConnection();
		  sql = new StringBuffer();
		  sql.append(" SELECT *");
		  sql.append(" FROM movie");
		  sql.append(" ORDER BY mno DESC");
		  
		  pstmt=con.prepareStatement(sql.toString());
		  rs=pstmt.executeQuery();
		  if(rs.next()) {
		    list=new ArrayList<MovieDTO>();
		    do {
		       dto=new MovieDTO();
		      dto.setMno(rs.getInt("mno"));
		      dto.setMsub(rs.getString("msub"));
		      dto.setMposter(rs.getString("mposter"));
		      dto.setMgenre(rs.getString("mgenre"));
		      dto.setMgrade(rs.getInt("mgrade"));
		      dto.setMrun(rs.getInt("mrun"));
		      dto.setMopen(rs.getString("mopen"));
		      dto.setMend(rs.getString("mend"));
		      dto.setMdir(rs.getString("mdir"));
		      dto.setMact(rs.getString("mact"));
		      dto.setMsum(rs.getString("msum"));
		      dto.setMtrail(rs.getString("mtrail"));
		      dto.setMwhy(rs.getString("mwhy"));
		      dto.setMfile(rs.getString("mfile"));
		      dto.setMsize(rs.getInt("msize"));
		      dto.setMsta(rs.getInt("msta"));
		      list.add(dto);
		    }while(rs.next());
		  }else {
		    list=null;
		  }
		  
		}catch (Exception e) {
		  System.out.println("무비픽 목록실패:"+e);
		}finally {
		  dbclose.close(con, pstmt, rs);
		}
		return list;
		}//movie_admin() end
		
		public int create(MovieDTO dto) {
		  int cnt = 0;
		  try {
		con = dbopen.getConnection();
		sql = new StringBuffer();
		sql.append(" INSERT INTO movie ( msub, mposter, mdir, mact, mgenre, mrun, mgrade, mopen, mend, msta, mfile, msum, mtrail, mwhy,msize ) ");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? )");
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, dto.getMsub());
		pstmt.setString(2, dto.getMposter());
		pstmt.setString(3, dto.getMdir());
		pstmt.setString(4, dto.getMact());
		pstmt.setString(5, dto.getMgenre());
		pstmt.setInt(6, dto.getMrun());
		pstmt.setInt(7, dto.getMgrade());
		pstmt.setString(8, dto.getMopen());
		pstmt.setString(9, dto.getMend());
		pstmt.setInt(10, dto.getMsta());
		pstmt.setString(11, dto.getMfile());
		pstmt.setString(12, dto.getMsum());
		pstmt.setString(13, dto.getMtrail());
		pstmt.setString(14, dto.getMwhy());
		pstmt.setLong(15, dto.getMsize());
		
		cnt = pstmt.executeUpdate();
		} catch (Exception e) {
		System.out.println("생성 실패:"+e);
		} finally {
		dbclose.close(con, pstmt);
		} //try end
		  
		  return cnt ;
		}//create() end
		
		public int update(MovieDTO dto) {
		int cnt = 0;
		try {
		  con = dbopen.getConnection();
		  sql = new StringBuffer();
		  sql.append(" UPDATE movie ");
		  sql.append(" SET msub =?, mposter=?, mgenre=?, mgrade=?, mrun=?, mopen=?, mend=?, mdir=?, mact=?, msum=?, mtrail=?, mwhy=?, mfile=?,  msta=? ");
		  sql.append(" WHERE mno=? ");
		  pstmt = con.prepareStatement(sql.toString());
		  pstmt.setString(1, dto.getMsub());
		  pstmt.setString(2, dto.getMposter());
		  pstmt.setString(3, dto.getMgenre());
		  pstmt.setInt(4, dto.getMgrade());
		  pstmt.setInt(5, dto.getMrun());
		  pstmt.setString(6, dto.getMopen());
		  pstmt.setString(7, dto.getMend());
		  pstmt.setString(8, dto.getMdir());
		  pstmt.setString(9, dto.getMact());
		  pstmt.setString(10, dto.getMsum());
		  pstmt.setString(11, dto.getMtrail());
		  pstmt.setString(12, dto.getMwhy());
		  pstmt.setString(13, dto.getMfile());
		  pstmt.setInt(14, dto.getMsta());
		  
		  pstmt.setInt(15, dto.getMno());
		  cnt = pstmt.executeUpdate();      
		} catch (Exception e) {
		  System.out.println("무비픽 수정실패:"+e);
		} finally {
		  dbclose.close(con, pstmt);
		}//try end
		return cnt;
		}//update() end
		
		public int delete(int mno) {
		int cnt= 0;
		try {
		  con = dbopen.getConnection();
		  sql = new StringBuffer();
		  sql.append(" DELETE FROM movie ");
		  sql.append(" WHERE mno = ? ");
		  pstmt= con.prepareStatement(sql.toString());
		  pstmt.setInt(1, mno);     
		  cnt = pstmt.executeUpdate();
		} catch (Exception e) {
		  System.out.println("무비픽 삭제 실패"+ e);
		} finally {
		  dbclose.close(con, pstmt);
		}//try end
		
		return cnt;
		}//delete() end
		
		public ArrayList<MovieDTO> list5(){
		      try {
		         con = dbopen.getConnection();
		         sql = new StringBuffer();
		         sql.append(" SELECT mno, msub, mposter,mgrade");
		         sql.append(" FROM movie WHERE msta = 1");
		         sql.append(" ORDER BY mno DESC");
		         
		         pstmt=con.prepareStatement(sql.toString());
		         rs=pstmt.executeQuery();
		         if(rs.next()) {
		            list5=new ArrayList<MovieDTO>();
		            do {
		               MovieDTO dto=new MovieDTO();
		               dto.setMno(rs.getInt("mno"));
		               dto.setMsub(rs.getString("msub"));
		               dto.setMposter(rs.getString("mposter"));
		               dto.setMgrade(rs.getInt("mgrade"));
		               list5.add(dto);
		            }while(rs.next());
		         }else {
		            list5=null;
		         }
		         
		      }catch (Exception e) {
		         System.out.println("무비픽 목록실패:"+e);
		      }finally {
		         dbclose.close(con, pstmt, rs);
		      }
		      return list5;
		   }
		
		public int deletereview(int rno) {
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
				System.out.println("삭제실패"+ e);
			} finally {
				dbclose.close(con, pstmt);
			}//try end
			
			return cnt;
		}//delete() end
		
		public ReviewDTO reviewread(int rno) {
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
		
		
		public int create(DownloadDTO dto) {
			int cnt=0;
			try {
				con = dbopen.getConnection();
				sql = new StringBuffer();
				sql.append("INSERT INTO download(mno,uid)");
				sql.append("VALUES(?,?)");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setInt(1, dto.getMno());
				pstmt.setString(2, dto.getUid());
				cnt=pstmt.executeUpdate();
			}catch (Exception e) {
				System.out.println("다운로드 dao 실패:" +e);
			}finally {
				dbclose.close(con, pstmt);
			}
			return cnt;
		}
		
		public ArrayList<MovieDTO> readdown(DownloadDTO ddto,String uid,PagingVO vo) {
			
			int nowPage=vo.getNowPage();
			int cntPerPage=vo.getCntPerPage();
		    int startRow = ((nowPage-1) * cntPerPage) ;
		    
			ArrayList<MovieDTO> list= null;
			
			try {
				con = dbopen.getConnection();
				sql = new StringBuffer();
				sql.append(" select DL.* , MV.mno, MV.msub, MV.mfile ");
				sql.append(" from download DL left join movie MV  ");
				sql.append(" on DL.mno=MV.mno ");
				sql.append(" WHERE DL.uid = ? ");
				sql.append(" ORDER BY dno DESC ");
				sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
				  pstmt = con.prepareStatement(sql.toString());
			      pstmt.setString(1, uid);
			      rs = pstmt.executeQuery();
			      if(rs.next()){
				        list = new ArrayList<MovieDTO>();
				        do {
				       MovieDTO dto= new MovieDTO();
				          dto=new MovieDTO();
				          dto.setDno(rs.getInt("dno"));
				          dto.setMno(rs.getInt("mno"));
				          dto.setUid(rs.getString("uid"));
				          dto.setMsub(rs.getString("msub"));
				          dto.setDdate(rs.getString("ddate"));
				          dto.setMfile(rs.getString("mfile"));
				          list.add(dto);
				        } while(rs.next());
				      }else{
				        list = null;
				      }//if end
			} catch (Exception e) {
				System.out.println("--다운로드 목록 실패");
			}finally {
				dbclose.close(con, pstmt, rs);
			}
			
			return list;
		}
}//class end
