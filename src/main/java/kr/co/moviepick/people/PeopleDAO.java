package kr.co.moviepick.people;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.PagingVO;
@Component
public class PeopleDAO {
	
	
	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbClose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;
	ArrayList<PeopleDTO> list= null;
	
	public PeopleDAO() {
		System.out.println("---PeopleDAO() 객체생성됨 ");
	}
	
public PeopleDTO read(int pno) {
		
		PeopleDTO dto= null;
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" SELECT pno,pname,country,pbirth,pgender,ppic ");
			sql.append(" FROM people ");
			sql.append(" WHERE pno=? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, pno);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
					dto=new PeopleDTO();
					dto.setPno(rs.getInt("pno"));
					dto.setPname(rs.getString("pname"));
					dto.setCountry(rs.getString("country"));
					dto.setPbirth(rs.getString("pbirth"));
					dto.setPgender(rs.getString("pgender"));
					dto.setPpic(rs.getString("ppic"));
					
			}else{
				dto=null;
			}//if end
		} catch (Exception e) {
			System.out.println("상세보기 실패"+e);
		}finally {
			dbClose.close(con,pstmt,rs);
		}
		
		return dto;
	}//read() end

public ArrayList<PeopleDTO> list(){
	
	try {
		con=dbopen.getConnection();
		sql=new StringBuilder();
		sql.append(" SELECT pno,pname,country,pbirth,pgender,ppic ");
		sql.append(" FROM people ");
		sql.append(" ORDER BY pno DESC");
		
		pstmt=con.prepareStatement(sql.toString());
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			list=new ArrayList<PeopleDTO>();
			do {
				PeopleDTO dto= new PeopleDTO();
				dto.setPno(rs.getInt("pno"));
				dto.setPname(rs.getString("pname"));
				dto.setCountry(rs.getString("country"));
				dto.setPbirth(rs.getString("pbirth"));
				dto.setPgender(rs.getString("pgender"));
				dto.setPpic(rs.getString("ppic"));
				list.add(dto);
			}while(rs.next());
		}else{
			list=null;//if end
		}
		
	} catch (Exception e) {
		System.out.println("사람 목록 조회 실패"+e);
	}finally {
		dbClose.close(con,pstmt,rs);
	}
	return list;
	
}//list() end

public int create(PeopleDTO dto) {
	int cnt=0;
	try {
		con=dbopen.getConnection();
		sql=new StringBuilder();
		sql.append("INSERT INTO people(pname,country,pbirth,pgender,ppic)");
		sql.append("VALUES(?,?,?,?,?)");
		pstmt=con.prepareStatement(sql.toString());
		pstmt.setString(1, dto.getPname());
		pstmt.setString(2, dto.getCountry());
		pstmt.setString(3, dto.getPbirth());
		pstmt.setString(4, dto.getPgender());
		pstmt.setString(5, dto.getPpic());
		cnt=pstmt.executeUpdate();
	}catch (Exception e) {
		System.out.println("사람 등록 실패"+e);
	}finally {
		dbClose.close(con,pstmt,rs);
	}
	
	return cnt;
}//create() end

public int update(PeopleDTO dto) {
	int cnt=0;
	try {
		con=dbopen.getConnection();
		sql=new StringBuilder();
		sql.append(" UPDATE people ");
		sql.append(" SET pname=?, country=?, pbirth=?, pgender=?, ppic=? ");
		sql.append(" WHERE pno=? ");
		pstmt=con.prepareStatement(sql.toString());
		pstmt.setString(1, dto.getPname());
		pstmt.setString(2, dto.getCountry());
		pstmt.setString(3, dto.getPbirth());
		pstmt.setString(4, dto.getPgender());
		pstmt.setString(5, dto.getPpic());
		pstmt.setInt(6, dto.getPno());
		cnt= pstmt.executeUpdate();
		
	}catch (Exception e) {
		System.out.println("수정 실패"+e);
	}finally {
		dbClose.close(con,pstmt);
	}
	return cnt;
}//update() end

public int delete(int pno) {
	int cnt=0;
	try {
		con=dbopen.getConnection();
		sql=new StringBuilder();
		sql.append("DELETE FROM people ");
		sql.append(" WHERE pno=? ");
		pstmt=con.prepareStatement(sql.toString());
		pstmt.setInt(1, pno);
		cnt=pstmt.executeUpdate();
		
	}catch (Exception e) {
		System.out.println("삭제 실패"+e);
	}finally {
		dbClose.close(con,pstmt);
	}
	return cnt;
}

public int getArticleCount() throws Exception{

int x=0;
try {
   con=dbopen.getConnection();
   pstmt=con.prepareStatement("SELECT count(*) FROM people");
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

public ArrayList<PeopleDTO> list2(PeopleDTO dto,PagingVO vo){


int nowPage=vo.getNowPage();
int cntPerPage=vo.getCntPerPage();
int startRow = ((nowPage-1) * cntPerPage) ;

	try{
		con=dbopen.getConnection();
		sql=new StringBuilder();
		sql.append(" SELECT pno,pname,country,pbirth,pgender,ppic ");
	    sql.append(" FROM people ");
	    sql.append(" ORDER BY pno DESC");
      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
      pstmt = con.prepareStatement(sql.toString());
      rs = pstmt.executeQuery();
      if(rs.next()){
        list = new ArrayList<PeopleDTO>();
        do {
        	PeopleDTO dto1= new PeopleDTO();
			dto1.setPno(rs.getInt("pno"));
			dto1.setPname(rs.getString("pname"));
			dto1.setCountry(rs.getString("country"));
			dto1.setPbirth(rs.getString("pbirth"));
			dto1.setPgender(rs.getString("pgender"));
			dto1.setPpic(rs.getString("ppic"));
			list.add(dto1);
        } while(rs.next());
      }else{
        list = null;
      }//if end

    }catch(Exception e){
      System.out.println("사람 리스트2 실패:"+e);
    }finally{
      dbClose.close(con, pstmt, rs);
    }//try end
	return list;
}//list() end

public ArrayList<PeopleDTO> list3(String col, String word,PeopleDTO dto,PagingVO vo){


int nowPage=vo.getNowPage();
int cntPerPage=vo.getCntPerPage();
int startRow = ((nowPage-1) * cntPerPage);

	try{
		con=dbopen.getConnection();
		sql=new StringBuilder();
		word = word.trim();
	if(word.length()==0) {	//寃��깋 x
		  sql.append(" SELECT pno,pname,country,pbirth,pgender,ppic ");
	      sql.append(" FROM people ");
	      sql.append(" ORDER BY pno DESC");
      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
      
	}else {
		//寃��깋o
		  sql.append(" SELECT pno,pname,country,pbirth,pgender,ppic ");
	      sql.append(" FROM people ");
	      
	      
	      String search="";
	      if(col.equals("uid")) {
	          search += " WHERE uid LIKE '%" + word + "%'";
	        } else if(col.equals("pname")) {
	          search += " WHERE pname LIKE '%" + word + "%'";
	        } else if(col.equals("country")) {
	          search += " WHERE  country LIKE '%" + word + "%'";
	        } else if(col.equals("pname_country")) {
	          search += " WHERE pname LIKE '%" + word + "%'";
	          search += " OR ncon LIKE '%" + word + "%'";
	        }
	      
	      sql.append(search);
	      sql.append(" ORDER BY pno DESC");
	       
	      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
		
	}
      pstmt = con.prepareStatement(sql.toString());
      rs = pstmt.executeQuery();
      if(rs.next()){
        list = new ArrayList<PeopleDTO>();
        do {
        	dto = new PeopleDTO();
          	dto.setPno(rs.getInt("pno"));
			dto.setPname(rs.getString("pname"));
			dto.setCountry(rs.getString("country"));
			dto.setPbirth(rs.getString("pbirth"));
			dto.setPgender(rs.getString("pgender"));
			dto.setPpic(rs.getString("ppic"));
          list.add(dto);
        } while(rs.next());
      }else{
        list = null;
      }//if end

    }catch(Exception e){
      System.out.println("사람 리스트 3실패:"+e);
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
	
	sql.append(" SELECT COUNT(*) as pno ");
	sql.append(" FROM people ");
	
	  if(word.trim().length()>=1) {//寃��깋�뼱媛��엳�떎硫�
		  String search="";
	      if(col.equals("pname")) {
	          search += " WHERE pname LIKE '%" + word + "%'";
	        } else if(col.equals("country")) {
	          search += " WHERE  country LIKE '%" + word + "%'";
	        } else if(col.equals("pname_country")) {
	          search += " WHERE pname LIKE '%" + word + "%'";
	          search += " OR pname LIKE '%" + word + "%'";
	        }
	      
		  sql.append(search);
	  }//if end
	  
	  pstmt=con.prepareStatement(sql.toString());
	  rs=pstmt.executeQuery();
	  if(rs.next()) {
		  cnt=rs.getInt("pno");
	  }//if end
	 
} catch (Exception e) {
	System.out.println("사람 검색어 카운트 실패:" + e);
}finally {
	dbClose.close(con, pstmt, rs);
}//try end


return cnt;
}//count() end
	
}//class end
