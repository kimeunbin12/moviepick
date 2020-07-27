package kr.co.moviepick.download;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.moviepick.bbs.BbsDTO;
import kr.co.moviepick.movie.MovieDTO;
import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.PagingVO;
@Component
public class DownloadDAO {
	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbClose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;
	
	public DownloadDAO() {
		
		System.out.println("--DownloadDAO() 객체 생성됨");
	}
	
	public ArrayList<DownloadDTO> read(MovieDTO mdto,String uid,PagingVO vo) {
	
		int nowPage=vo.getNowPage();
		int cntPerPage=vo.getCntPerPage();
	    int startRow = ((nowPage-1) * cntPerPage) ;
	    
		ArrayList<DownloadDTO> list= null;
		
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
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
			        list = new ArrayList<DownloadDTO>();
			        do {
			        DownloadDTO dto= new DownloadDTO();
			          dto=new DownloadDTO();
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
			dbClose.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public int getArticleCount(String uid) throws Exception{
	      
	      int x=0;
	      try {
	         con=dbopen.getConnection();
	         pstmt=con.prepareStatement("SELECT count(*) FROM download where uid=?");
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
	
	
	public ArrayList<DownloadDTO> readmypagemain(MovieDTO mdto,String uid) {
		
		
	    
		ArrayList<DownloadDTO> list= null;
		
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" select DL.* , MV.mno, MV.msub, MV.mfile ");
			sql.append(" from download DL left join movie MV  ");
			sql.append(" on DL.mno=MV.mno ");
			sql.append(" WHERE DL.uid = ? ");
			sql.append(" ORDER BY dno DESC ");
			sql.append(" LIMIT 5 " );
			  pstmt = con.prepareStatement(sql.toString());
		      pstmt.setString(1, uid);
		      rs = pstmt.executeQuery();
		      if(rs.next()){
			        list = new ArrayList<DownloadDTO>();
			        do {
			        DownloadDTO dto= new DownloadDTO();
			          dto=new DownloadDTO();
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
			System.out.println("--마이페이지 메인 다운로드 목록 실패");
		}finally {
			dbClose.close(con, pstmt, rs);
		}
		
		return list;
	}
}
