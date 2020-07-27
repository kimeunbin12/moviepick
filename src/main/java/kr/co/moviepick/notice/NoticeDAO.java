package kr.co.moviepick.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.moviepick.notice.NoticeDTO;
import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.PagingVO;
@Component
public class NoticeDAO {

	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbClose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;
	ArrayList<NoticeDTO> list = null;
	private int noticeno;
	
	public NoticeDAO() {
		System.out.println("---NoticeDAO()객체 생성됨");
	}
	
	public ArrayList<NoticeDTO> list(){
		
		NoticeDTO dto=null;

		try{
		      con = dbopen.getConnection();
		      sql = new StringBuilder();
		      sql.append(" SELECT noticeno, noticesub, noticecnt, noticedate, noticecon, noticeimg ");
		      sql.append(" FROM notice");
		      sql.append(" ORDER BY noticeno DESC ");
		      pstmt = con.prepareStatement(sql.toString());
		      rs = pstmt.executeQuery();
		      if(rs.next()){
		        list = new ArrayList<NoticeDTO>();
		        do {
		          dto = new NoticeDTO();
		          dto.setNoticeno(rs.getInt("noticeno"));
		          dto.setNoticesub(rs.getString("noticesub"));
		          dto.setNoticecnt(rs.getInt("noticecnt"));
		          dto.setNoticedate(rs.getString("noticedate"));
		          dto.setNoticecon(rs.getString("noticecon"));
		          dto.setNoticeimg(rs.getString("noticeimg"));
		          list.add(dto);
		        } while(rs.next());
		      }else{
		        list = null;
		      }//if end

		    }catch(Exception e){
		      System.out.println("공지사항 목록 실패:"+e);
		    }finally{
		      dbClose.close(con, pstmt, rs);
		    }//try end
		    return list;
	}
	
public ArrayList<NoticeDTO> list1(){
		
		NoticeDTO dto=null;

		try{
		      con = dbopen.getConnection();
		      sql = new StringBuilder();
		      sql.append(" SELECT noticeno, noticesub, noticedate ");
		      sql.append(" FROM notice");
		      sql.append(" order by noticeno desc limit 6 ");
		      pstmt = con.prepareStatement(sql.toString());
		      rs = pstmt.executeQuery();
		      if(rs.next()){
		        list = new ArrayList<NoticeDTO>();
		        do {
		          dto = new NoticeDTO();
		          dto.setNoticeno(rs.getInt("noticeno"));
		          dto.setNoticesub(rs.getString("noticesub"));
		          dto.setNoticedate(rs.getString("noticedate"));
		          list.add(dto);
		        } while(rs.next());
		      }else{
		        list = null;
		      }//if end

		    }catch(Exception e){
		      System.out.println("공지사항 목록1 실패:"+e);
		    }finally{
		      dbClose.close(con, pstmt, rs);
		    }//try end
		    return list;
	}
	
public int getArticleCount() throws Exception{
    
    int x=0;
    try {
       con=dbopen.getConnection();
       pstmt=con.prepareStatement("SELECT count(*) FROM notice");
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

public ArrayList<NoticeDTO> list2(NoticeDTO dto,PagingVO vo){
	

	int nowPage=vo.getNowPage();
	int cntPerPage=vo.getCntPerPage();
  int startRow = ((nowPage-1) * cntPerPage) ;
  
		try{
			con=dbopen.getConnection();
			sql=new StringBuilder();
		  sql.append(" SELECT noticeno, noticesub, noticecnt, noticedate, noticecon, noticeimg ");
	      sql.append(" FROM notice");
	      sql.append(" ORDER BY noticeno DESC ");
	      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
	      pstmt = con.prepareStatement(sql.toString());
	      rs = pstmt.executeQuery();
	      if(rs.next()){
	        list = new ArrayList<NoticeDTO>();
	        do {
	         dto = new NoticeDTO();
	          dto.setNoticeno(rs.getInt("noticeno"));
	          dto.setNoticesub(rs.getString("noticesub"));
	          dto.setNoticecnt(rs.getInt("noticecnt"));
	          dto.setNoticedate(rs.getString("noticedate"));
	          dto.setNoticecon(rs.getString("noticecon"));
	          dto.setNoticeimg(rs.getString("noticeimg"));
	          list.add(dto);
	        } while(rs.next());
	      }else{
	        list = null;
	      }//if end

	    }catch(Exception e){
	      System.out.println("�공지사항 목록2 실패:"+e);
	    }finally{
	      dbClose.close(con, pstmt, rs);
	    }//try end
		return list;
	}//list() end

public ArrayList<NoticeDTO> list3(String col, String word,NoticeDTO dto,PagingVO vo){
	

	int nowPage=vo.getNowPage();
	int cntPerPage=vo.getCntPerPage();
  int startRow = ((nowPage-1) * cntPerPage);
  
		try{
			con=dbopen.getConnection();
			sql=new StringBuilder();
			word = word.trim();
		if(word.length()==0) {	//寃��깋 x
		  sql.append(" SELECT noticeno, noticesub, noticecnt, noticedate, noticecon, noticeimg ");
	      sql.append(" FROM notice");
	      sql.append(" ORDER BY noticeno DESC ");
	      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
	      
		}else {
			//寃��깋o
			 sql.append(" SELECT noticeno, noticesub, noticecnt, noticedate, noticecon, noticeimg ");
			 sql.append(" FROM notice");
		      
		      
		      String search="";
		      if(col.equals("noticesub")) {
		          search += " WHERE noticesub LIKE '%" + word + "%'";
		        } else if(col.equals("ncoticeon")) {
		          search += " WHERE  noticecon LIKE '%" + word + "%'";
		        } else if(col.equals("noticesub_noticecon")) {
		          search += " WHERE noticesub LIKE '%" + word + "%'";
		          search += " OR ncon LIKE '%" + word + "%'";
		        }
		      
		      sql.append(search);
		      sql.append(" ORDER BY noticeno DESC");
		       
		      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
			
		}
	      pstmt = con.prepareStatement(sql.toString());
	      rs = pstmt.executeQuery();
	      if(rs.next()){
	        list = new ArrayList<NoticeDTO>();
	        do {
	          dto = new NoticeDTO();
	          dto.setNoticeno(rs.getInt("noticeno"));
	          dto.setNoticesub(rs.getString("noticesub"));
	          dto.setNoticecnt(rs.getInt("noticecnt"));
	          dto.setNoticedate(rs.getString("noticedate"));
	          dto.setNoticecon(rs.getString("noticecon"));
	          dto.setNoticeimg(rs.getString("noticeimg"));
	          list.add(dto);
	        } while(rs.next());
	      }else{
	        list = null;
	      }//if end

	    }catch(Exception e){
	      System.out.println("관리자 공지사항 리스트 실패:"+e);
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
		
		sql.append(" SELECT COUNT(*) as noticecnt ");
		sql.append(" FROM notice ");
		
		  if(word.trim().length()>=1) {//寃��깋�뼱媛��엳�떎硫�
			  String search="";
		      if(col.equals("uid")) {
		          search += " WHERE uid LIKE '%" + word + "%'";
		        } else if(col.equals("noticesub")) {
		          search += " WHERE noticesub LIKE '%" + word + "%'";
		        } else if(col.equals("noticecon")) {
		          search += " WHERE  noticecon LIKE '%" + word + "%'";
		        } else if(col.equals("noticesub_noticecon")) {
		          search += " WHERE noticesub LIKE '%" + word + "%'";
		          search += " OR noticecon LIKE '%" + word + "%'";
		        }
		      
			  sql.append(search);
		  }//if end
		  
		  pstmt=con.prepareStatement(sql.toString());
		  rs=pstmt.executeQuery();
		  if(rs.next()) {
			  cnt=rs.getInt("noticecnt");
		  }//if end
		 
	} catch (Exception e) {
		System.out.println("관리자 공지사항 검색 리스트 실패:" + e);
	}finally {
		dbClose.close(con, pstmt, rs);
	}//try end
	
	
	return cnt;
}//count() end

	public NoticeDTO read(int noticeno) {
	    NoticeDTO dto = null;
	    try {
	      con = dbopen.getConnection();
	      sql = new StringBuilder();
	      sql.append(" SELECT noticeno, noticesub, noticecnt, noticedate, noticecon, noticeimg");
	      sql.append(" FROM notice");
	      sql.append(" WHERE noticeno = ?"); 
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setInt(1, noticeno);
	      rs = pstmt.executeQuery();
	      if(rs.next()) {
	       dto = new NoticeDTO();
			          dto.setNoticeno(rs.getInt("noticeno"));
			          dto.setNoticesub(rs.getString("noticesub"));
			          dto.setNoticecnt(rs.getInt("noticecnt"));
			          dto.setNoticedate(rs.getString("noticedate"));
			          dto.setNoticecon(rs.getString("noticecon"));
			          dto.setNoticeimg(rs.getString("noticeimg"));
	      }//if end

	    } catch (Exception e) {
	        System.out.println("상세보기실패"+e);
	    } finally {
	        dbClose.close(con, pstmt, rs);
	    }//end
	    return dto;
	  }//read() end
	

	public int create(NoticeDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" INSERT INTO notice( noticesub, noticecon, noticedate,  noticeimg ) ");
			sql.append(" VALUES( ?, ?, now(),  ?) ");
			pstmt = con.prepareStatement(sql.toString());			
			pstmt.setString(1, dto.getNoticesub());
			pstmt.setString(2, dto.getNoticecon());
			
			
			pstmt.setString(3, dto.getNoticeimg());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("글 생성 실패:" +e);
		} finally {
			dbClose.close(con, pstmt);
		}//try end
		return cnt;
	}//create() end
	
	public void updatecnt(int noticeno) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE notice ");
			sql.append(" SET noticecnt = noticecnt + 1 ");
			sql.append(" WHERE noticeno = ? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, noticeno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("실패:"+e);
		} finally {
			dbClose.close(con, pstmt, rs);
		}//try end
	}//updatecnt end
	

	public int update(NoticeDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE notice ");
			sql.append(" SET noticesub=?, noticecon=?, noticeimg=? ");
			sql.append(" WHERE noticeno=? ");
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, dto.getNoticesub());
			pstmt.setString(2, dto.getNoticecon());
			pstmt.setString(3, dto.getNoticeimg());
			pstmt.setInt(4, dto.getNoticeno());
			cnt = pstmt.executeUpdate();			
		} catch (Exception e) {
			System.out.println("수정실패:"+e);
		} finally {
			dbClose.close(con, pstmt);
		}//try end
		return cnt;
	}//update() end
	
	
	public int delete(int noticeno) {
		int cnt= 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" DELETE FROM notice ");
			sql.append(" WHERE noticeno = ? ");
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setInt(1, noticeno);			
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제실패"+ e);
		} finally {
			dbClose.close(con, pstmt);
		}//try end
		
		return cnt;
	}//delete() end
	
}//class end
