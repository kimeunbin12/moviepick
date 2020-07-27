package kr.co.moviepick.booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.moviepick.movie.MovieDTO;
import kr.co.moviepick.screening.ScreeningDTO;
import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.PagingVO;

@Component
public class BookingDAO {
	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbClose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;
	
	public BookingDAO() {
		System.out.println("---BookingDAO()객체생성됨...");
	}
	
		public ArrayList<BookingDTO> blist(MovieDTO mdto,ScreeningDTO sdto,PagingVO vo,String uid){
		
		ArrayList<BookingDTO> blist=null;
		
		int nowPage=vo.getNowPage();
		int cntPerPage=vo.getCntPerPage();
	    int startRow = ((nowPage-1) * cntPerPage) ;
	    
	    try {
	    	con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT AA.*, MV.msub ");
			sql.append(" FROM ");
			sql.append(" ( ");
			sql.append(" SELECT BK.bno, BK.bdate, BK.bloc, BK.btype, BK.uid, SC.scrno, SC.scrdate, SC.hno, SC.mno, SC.scrstart ");
			sql.append(" FROM booking BK left join screening SC ");
			sql.append(" ON BK.scrno=SC.scrno ");
			sql.append(" ORDER BY BK.bno DESC ");
			sql.append(" )AA left join movie MV ");
			sql.append(" ON AA.mno=MV.mno ");
			sql.append(" WHERE AA.uid=? ");
			sql.append("LIMIT "+ startRow + ","+ cntPerPage );
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				blist=new ArrayList<BookingDTO>();
				do {
					BookingDTO dto=new BookingDTO();
					dto = new BookingDTO();
					dto.setBno(rs.getInt("bno"));
					dto.setMsub(rs.getString("msub"));
					dto.setScrdate(rs.getString("scrdate"));
					dto.setScrstart(rs.getString("scrstart"));
					dto.setBloc(rs.getString("bloc"));
					dto.setHno(rs.getInt("hno"));
					dto.setBdate(rs.getString("bdate"));
					dto.setBtype(rs.getString("btype"));
					dto.setUid(rs.getString("uid"));
					dto.setScrno(rs.getInt("scrno"));
					dto.setMno(rs.getInt("mno"));
					blist.add(dto);
				}while(rs.next());
			}else {
				blist=null;
			}
		} catch (Exception e) {
			System.out.println("예매목록 실패" + e);
		}finally {
			dbClose.close(con, pstmt, rs);
		}
		return blist;
	}
		public int getArticleCount(String uid) throws Exception{
		      
		      int x=0;
		      try {
		         con=dbopen.getConnection();
		         pstmt=con.prepareStatement(
		        		 " SELECT COUNT(*) " + 
		        		 " FROM( " + 
		        		 " SELECT AA.*, MV.msub " + 
		        		 " FROM(" + 
		        		 " SELECT BK.bno,BK.bdate,BK.bloc,BK.btype,BK.uid, SC.scrno, SC.scrdate, SC.hno, SC.mno " + 
		        		 " FROM booking BK left join screening SC " + 
		        		 " ON BK.scrno=SC.scrno " + 
		        		 " ORDER BY BK.bno DESC " + 
		        		 " )AA left join movie MV " + 
		        		 " ON AA.mno=MV.mno " + 
		        		 " WHERE AA.uid=? " + 
		        		 " )BB " );
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
		
		public ArrayList<BookingDTO> blistmypage(MovieDTO mdto,ScreeningDTO sdto,String uid){
			
			ArrayList<BookingDTO> blist=null;

		    try {
		    	con = dbopen.getConnection();
				sql = new StringBuilder();
				sql.append(" SELECT AA.*, MV.msub ");
				sql.append(" FROM ");
				sql.append(" ( ");
				sql.append(" SELECT BK.bno, BK.bdate, BK.bloc, BK.btype, BK.uid, SC.scrno, SC.scrdate, SC.hno, SC.mno, SC.scrstart ");
				sql.append(" FROM booking BK left join screening SC ");
				sql.append(" ON BK.scrno=SC.scrno ");
				sql.append(" ORDER BY BK.bno DESC ");
				sql.append(" )AA left join movie MV ");
				sql.append(" ON AA.mno=MV.mno ");
				sql.append(" WHERE AA.uid=? ");
				sql.append(" LIMIT 5 ");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, uid);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					
					blist=new ArrayList<BookingDTO>();
					do {
						BookingDTO dto=new BookingDTO();
						dto = new BookingDTO();
						dto.setBno(rs.getInt("bno"));
						dto.setMsub(rs.getString("msub"));
						dto.setScrdate(rs.getString("scrdate"));
						dto.setScrstart(rs.getString("scrstart"));
						dto.setBloc(rs.getString("bloc"));
						dto.setHno(rs.getInt("hno"));
						dto.setBdate(rs.getString("bdate"));
						dto.setBtype(rs.getString("btype"));
						dto.setUid(rs.getString("uid"));
						dto.setScrno(rs.getInt("scrno"));
						dto.setMno(rs.getInt("mno"));
						blist.add(dto);
					}while(rs.next());
				}else {
					blist=null;
				}
			} catch (Exception e) {
				System.out.println("예매목록 실패" + e);
			}finally {
				dbClose.close(con, pstmt, rs);
			}
			return blist;
		}
}
