package kr.co.moviepick.screening;

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
public class ScreeningDAO {
  @Autowired
  private DBOpen dbopen;
  @Autowired
  private DBClose dbclose;
  
  Connection con = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  StringBuffer sql = null;
  ArrayList<ScreeningDTO> list = null;
  ArrayList<MovieDTO> mlist = null;
  public ScreeningDAO() {
    System.out.println("---ScreeningDAO()객체생성됨");
  }
  
  public ArrayList<ScreeningDTO> list(ScreeningDTO dto,PagingVO vo){
    
	  int nowPage=vo.getNowPage();
	  int cntPerPage=vo.getCntPerPage();
	  int startRow = ((nowPage-1) * cntPerPage) ;
	    
	  try {
    	
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" SELECT *");
      sql.append(" FROM screening");
      sql.append(" ORDER BY scrno DESC");
      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
      
      pstmt=con.prepareStatement(sql.toString());
      rs=pstmt.executeQuery();
      if(rs.next()) {
        list=new ArrayList<ScreeningDTO>();
        do {
           dto=new ScreeningDTO();
          dto.setScrno(rs.getInt("scrno"));
          dto.setScrdate(rs.getString("scrdate"));
          dto.setScrstart(rs.getString("scrstart"));
          dto.setHno(rs.getInt("hno"));
          dto.setMno(rs.getInt("mno"));
          list.add(dto);
        }while(rs.next());
      }else {
        list=null;
      }
    }catch (Exception e) {
      System.out.println("상영목록 실패:"+e);
    }finally {
      dbclose.close(con, pstmt, rs);
    }
    return list;
    }//list() end
  
  public ArrayList<ScreeningDTO> screenlistcol(String col, String word,ScreeningDTO dto,PagingVO vo){
	    
	  int nowPage=vo.getNowPage();
	  int cntPerPage=vo.getCntPerPage();
	  int startRow = ((nowPage-1) * cntPerPage) ;
	    
	  try {
    	
      con = dbopen.getConnection();
      sql = new StringBuffer();
      word = word.trim();
      
      if(word.length()==0) {
    	  sql.append(" SELECT *");
          sql.append(" FROM screening");
          sql.append(" ORDER BY scrno DESC");
          sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
      }else {
    	  sql.append(" SELECT *");
          sql.append(" FROM screening");
          
          String search="";
	      if(col.equals("scrdate")) {
	          search += " WHERE scrdate LIKE '%" + word + "%'";
	        	} else if(col.equals("hno")) {
	          search += " WHERE hno LIKE '%" + word + "%'";
	        	} else if(col.equals("mno")){
	        		 search += " WHERE mno LIKE '%" + word + "%'";
				} 
	      	sql.append(search);
	      	sql.append(" ORDER BY scrno DESC");
	          sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
      }
     
      
      pstmt=con.prepareStatement(sql.toString());
      rs=pstmt.executeQuery();
      if(rs.next()) {
        list=new ArrayList<ScreeningDTO>();
        do {
           dto=new ScreeningDTO();
          dto.setScrno(rs.getInt("scrno"));
          dto.setScrdate(rs.getString("scrdate"));
          dto.setScrstart(rs.getString("scrstart"));
          dto.setHno(rs.getInt("hno"));
          dto.setMno(rs.getInt("mno"));
          list.add(dto);
        }while(rs.next());
      }else {
        list=null;
      }
    }catch (Exception e) {
      System.out.println("상영목록 실패:"+e);
    }finally {
      dbclose.close(con, pstmt, rs);
    }
    return list;
    }//list() end
  
  public int create(ScreeningDTO dto) {
    int cnt=0;
    try {
      con=dbopen.getConnection();
      sql=new StringBuffer();
      sql.append("INSERT INTO screening( scrdate, scrstart, hno, mno)");
      sql.append("VALUES(?,?,?,?)");
      pstmt=con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getScrdate());
      pstmt.setString(2, dto.getScrstart());
      pstmt.setInt(3, dto.getHno());
      pstmt.setInt(4, dto.getMno());
      cnt=pstmt.executeUpdate();
    }catch (Exception e) {
      System.out.println("상영정보 등록 실패"+e);
    }finally {
      dbclose.close(con,pstmt);
    }
    return cnt;
  }//create() end

public ScreeningDTO read(int scrno) {
    ScreeningDTO dto= null;
    try {
      con=dbopen.getConnection();
      sql=new StringBuffer();
      sql.append(" SELECT scrno, scrdate, scrstart, hno, mno ");
      sql.append(" FROM screening ");
      sql.append(" WHERE scrno=? ");
      pstmt=con.prepareStatement(sql.toString());
      pstmt.setInt(1, scrno);
      rs=pstmt.executeQuery();
      if(rs.next()) {
          dto=new ScreeningDTO();
          dto.setScrno(rs.getInt("scrno"));
          dto.setScrdate(rs.getString("scrdate"));
          dto.setScrstart(rs.getString("scrstart"));
          dto.setHno(rs.getInt("hno"));
          dto.setMno(rs.getInt("mno"));
      }else{
        dto=null;
      }//if end
    } catch (Exception e) {
      System.out.println("상영정보 읽기 실패"+e);
    }finally {
      dbclose.close(con,pstmt,rs);
    }
    return dto;
  }//read() end
  
  public int update(ScreeningDTO dto) {
    int cnt=0;
    try {
      con=dbopen.getConnection();
      sql=new StringBuffer();
      sql.append(" UPDATE screening ");
      sql.append(" SET  scrdate=?, scrstart=?, hno=?, mno=? ");
      sql.append(" WHERE scrno=? ");
      pstmt=con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getScrdate());
      pstmt.setString(2, dto.getScrstart());
      pstmt.setInt(3, dto.getHno());
      pstmt.setInt(4, dto.getMno());
      pstmt.setInt(5,  dto.getScrno());
      cnt= pstmt.executeUpdate();
    }catch (Exception e) {
      System.out.println("수정 실패"+e);
    }finally {
      dbclose.close(con,pstmt);
    }
    return cnt;
  }//update() end

  public int delete(int scrno) {
    int cnt=0;
    try {
      con=dbopen.getConnection();
      sql=new StringBuffer();
      sql.append("DELETE FROM screening ");
      sql.append(" WHERE scrno=? ");
      pstmt=con.prepareStatement(sql.toString());
      pstmt.setInt(1, scrno);
      cnt=pstmt.executeUpdate();
    }catch (Exception e) {
      System.out.println("삭제 실패"+e);
    }finally {
      dbclose.close(con,pstmt);
    }
    return cnt;
  }
  
  public int getArticleCount2() throws Exception{
    
    int x=0;
    try {
       con=dbopen.getConnection();
       pstmt=con.prepareStatement("SELECT count(*) FROM screening");
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

  public ArrayList<MovieDTO> list2(MovieDTO dto, PagingVO vo){
  int nowPage=vo.getNowPage();
  int cntPerPage=vo.getCntPerPage();
  int startRow = ((nowPage-1) * cntPerPage) ;
  try{
    con=dbopen.getConnection();
    sql=new StringBuffer();
    sql.append(" SELECT mno, msub, mgrade, mend");
      sql.append(" FROM movie");
      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
      pstmt = con.prepareStatement(sql.toString());
      rs = pstmt.executeQuery();
      if(rs.next()){
        mlist = new ArrayList<MovieDTO>();
        do {
          dto = new MovieDTO();
          dto.setMno(rs.getInt("mno"));
          dto.setMsub(rs.getString("msub"));
          dto.setMgrade(rs.getInt("mgrade"));
          dto.setMend(rs.getString("mend"));
          mlist.add(dto);
        } while(rs.next());
      }else{
        list = null;
      }//if end
    }catch(Exception e){
      System.out.println("영화정보 목록화 실패:"+e);
    }finally{
      dbclose.close(con, pstmt, rs);
    }//try end
  return mlist;
  }//list2() end

  public ArrayList<MovieDTO> list3(String col, String word, MovieDTO dto, PagingVO vo){
    
  int nowPage=vo.getNowPage();
  int cntPerPage=vo.getCntPerPage();
  int startRow = ((nowPage-1) * cntPerPage);
  try{
    con=dbopen.getConnection();
    sql=new StringBuffer();
    word = word.trim();
  if(word.length()==0) {  //검색 x
    sql.append(" SELECT mno, msub, mgrade, mend");
      sql.append(" FROM movie");
      sql.append(" ORDER BY mno DESC");
      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
      
  }else {
    //검색o
      sql.append("  SELECT mno, msub, mgrade, mend");
        sql.append(" FROM movie ");
        
        
        String search="";
        if(col.equals("msub")) {
            search += " WHERE msub LIKE '%" + word + "%'";
          } else if(col.equals("mgrade")) {
            search += " WHERE mgrade LIKE '%" + word + "%'";
          }
        
        sql.append(search);
        sql.append(" ORDER BY mno DESC");
         
        sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
    
  }
      pstmt = con.prepareStatement(sql.toString());
      rs = pstmt.executeQuery();
      if(rs.next()){
        mlist = new ArrayList<MovieDTO>();
        do {
          dto = new MovieDTO();
          dto.setMno(rs.getInt("mno"));
          dto.setMsub(rs.getString("msub"));
          dto.setMgrade(rs.getInt("mgrade"));
          dto.setMend(rs.getString("mend"));
          mlist.add(dto);
        } while(rs.next());
      }else{
        mlist = null;
      }//if end

    }catch(Exception e){
      System.out.println("영화 목록 실패:"+e);
    }finally{
      dbclose.close(con, pstmt, rs);
    }//try end
  return mlist;
  }//list() end

  public int count(String col, String word) {

  int cnt=0;

  try {
  con=dbopen.getConnection();
  sql=new StringBuffer();

  sql.append(" SELECT COUNT(*) as cnt ");
  sql.append(" FROM movie ");

    if(word.trim().length()>=1) {//검색어가있다면
      String search="";
      if(col.equals("msub")) {
        search += " WHERE msub LIKE '%" + word + "%'";
      } else if(col.equals("mgrade")) {
        search += " WHERE mgrade LIKE '%" + word + "%'";
      }
      sql.append(search);
    }//if end
    pstmt=con.prepareStatement(sql.toString());
    rs=pstmt.executeQuery();
    if(rs.next()) {
      cnt=rs.getInt("cnt");
    }//if end
  } catch (Exception e) {
  System.out.println("행 갯수 세기 실패:" + e);
  }finally {
  dbclose.close(con, pstmt, rs);
  }//try end
  
  return cnt;
  }//count() end
  
  public int screeningcount(String col, String word) {

	  int cnt=0;

	  try {
	  con=dbopen.getConnection();
	  sql=new StringBuffer();

	  sql.append(" SELECT COUNT(*) as cnt ");
	  sql.append(" FROM screening ");

	    if(word.trim().length()>=1) {//검색어가있다면
   
	          String search="";
		      if(col.equals("scrdate")) {
		          search += " WHERE scrdate LIKE '%" + word + "%'";
		        	} else if(col.equals("hno")) {
		          search += " WHERE hno LIKE '%" + word + "%'";
		        	} else if(col.equals("mno")){
		        		 search += " WHERE mno LIKE '%" + word + "%'";
					} 
		      	sql.append(search);
;
	    }//if end
	    pstmt=con.prepareStatement(sql.toString());
	    rs=pstmt.executeQuery();
	    if(rs.next()) {
	      cnt=rs.getInt("cnt");
	    }//if end
	  } catch (Exception e) {
	  System.out.println("행 갯수 세기 실패:" + e);
	  }finally {
	  dbclose.close(con, pstmt, rs);
	  }//try end
	  
	  return cnt;
	  }//count() end

  
}//class end
