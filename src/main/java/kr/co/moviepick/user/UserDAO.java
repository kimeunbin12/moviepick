package kr.co.moviepick.user;

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
public class UserDAO {

	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbClose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;
	ArrayList<UserDTO> list = null;
	
	public UserDAO() {
		System.out.println("---UserDAO()객체 생성됨..");
		dbopen=new DBOpen();
	}
	
	public int create(UserDTO dto){//admin회원관리에 필요할 것
		int cnt =0;
		try{
		      con = dbopen.getConnection();
		      sql = new StringBuilder();
		      sql.append(" INSERT INTO user(uid, ups, uname, ubirth, uemail, ugender, uphone) ");
		      sql.append(" VALUES(?,?,?,?,?,?,?)");
		      pstmt = con.prepareStatement(sql.toString());
		      pstmt.setString(1, dto.getUid());
		      pstmt.setString(2, dto.getUps());
		      pstmt.setString(3, dto.getUname());
		      pstmt.setString(4, dto.getUbirth());
		      pstmt.setString(5, dto.getUemail());
		      pstmt.setString(6, dto.getUgender());
		      pstmt.setString(7, dto.getUphone());
		      
		      cnt = pstmt.executeUpdate();
		    }catch(Exception e){
		      System.out.println("회원 등록 실패:"+e);
		    }finally{
		      dbClose.close(con, pstmt);
		    }//try end
		    return cnt;
	}//create end()
	
	
	
	public int idcheck(String uid) {
    int cnt=0;
    try {
      con=dbopen.getConnection();          
      sql=new StringBuilder();
      sql.append(" SELECT COUNT(uid) as cnt");
      sql.append(" FROM user");
      sql.append(" WHERE uid=?");
      pstmt=con.prepareStatement(sql.toString());
      pstmt.setString(1, uid);
      rs=pstmt.executeQuery();
      if(rs.next()) {
        cnt=rs.getInt("cnt");
      }
    }catch (Exception e) {
      System.out.println("아이디 중복 확인 실패 : " + e);
    }finally {
      dbClose.close(con, pstmt, rs);
    }//try end
    return cnt;
  }//idcheck() end
	
	
	public int emailCheck(String uemail) {
    int cnt=0;
    try {
      con=dbopen.getConnection();          
      sql=new StringBuilder();
      sql.append(" SELECT COUNT(uemail) as cnt");
      sql.append(" FROM user");
      sql.append(" WHERE uemail=?");
      pstmt=con.prepareStatement(sql.toString());
      pstmt.setString(1, uemail);
      rs=pstmt.executeQuery();
      if(rs.next()) {
        cnt=rs.getInt("cnt");
      }
    }catch (Exception e) {
      System.out.println("메일주소 중복 확인 실패 : " + e);
    }finally {
      dbClose.close(con, pstmt, rs);
    }//try end
    return cnt;
  }//duplecateEmail() end
	
	public String loginCheck(UserDTO dto) {
		
		String uname=null;
		try {
			con=dbopen.getConnection();          
		      sql=new StringBuilder();
		      sql.append(" SELECT uname ");
		      sql.append(" FROM user ");
		      sql.append(" WHERE uid=? and ups=? ");
		      pstmt=con.prepareStatement(sql.toString());
		      pstmt.setString(1,dto.getUid());
		      pstmt.setString(2, dto.getUps());
			
		      rs =pstmt.executeQuery();
		      if(rs.next()) {
		    	  uname= rs.getString("uname");
		      }
		}catch (Exception e) {
		      System.out.println("로그인 실패 : " + e);
	    }finally {
	      dbClose.close(con, pstmt, rs);
	    }//try end
		
		return uname;
		
	}//loginCheck end
	
	public String idsearch(String uname, String uemail) {
		String uid= null;
		try {
			con=dbopen.getConnection();          
		      sql=new StringBuilder();
		      sql.append(" SELECT uid ");
		      sql.append(" FROM user ");
		      sql.append(" WHERE uname=? and uemail=? ");
		      pstmt=con.prepareStatement(sql.toString());
		      pstmt.setString(1,uname);
		      pstmt.setString(2, uemail);
			
		      rs =pstmt.executeQuery();
		      if(rs.next()) {
		    	  uid= rs.getString("uid");
		      }
		}catch (Exception e) {
		      System.out.println("아이디 찾기 실패 : " + e);
	    }finally {
	      dbClose.close(con, pstmt, rs);
	    }//try end
		
		return uid;
		
	}//idsearch() end
	
	public String pssearch(String uid, String uemail) {
		
		String ups= null;
		
		try {
			con=dbopen.getConnection();          
		      sql=new StringBuilder();
		      sql.append(" SELECT ups ");
		      sql.append(" FROM user ");
		      sql.append(" WHERE uid=? and uemail=? ");
		      pstmt=con.prepareStatement(sql.toString());
		      pstmt.setString(1,uid);
		      pstmt.setString(2, uemail);
			
		      rs =pstmt.executeQuery();
		      if(rs.next()) {
		    	  ups= rs.getString("ups");
		      }
		}catch (Exception e) {
		      System.out.println("비밀번호ㄴ 찾기 실패 : " + e);
	    }finally {
	    	dbClose.close(con, pstmt, rs);
	    }//try end
		
		return ups;
		
	}//idsearch() end
	
	 public UserDTO updateform(String uid){
			
		    UserDTO dto=null;
		    
		   
		    try {
			  con=dbopen.getConnection();
		      sql= new StringBuilder();
		      sql.append(" SELECT uid, ups, uname, ubirth, uemail, ugender, uphone ");
		      sql.append(" FROM user ");
		      sql.append(" WHERE uid=? ");
		      
		      pstmt=con.prepareStatement(sql.toString());
		      pstmt.setString(1, uid);
		      rs=pstmt.executeQuery();
		      
		      if(rs.next()){
		    	  
		        dto=new UserDTO();
		        dto.setUid(rs.getString("uid"));
		        dto.setUps(rs.getString("ups"));
		        dto.setUname(rs.getString("uname"));
		        dto.setUbirth(rs.getString("ubirth"));
		        dto.setUemail(rs.getString("uemail"));
		        dto.setUgender(rs.getString("ugender"));
		        dto.setUphone(rs.getString("uphone"));
		        
		      } else {
		    	  dto=null;
		      }

		    }catch(Exception e){
		    	System.out.println("수정 조회 실패:" + e);
		    }finally{
		    	dbClose.close(con,pstmt,rs);
		    }   
		    
		  
		    return dto;     
		  }//updateform() end
	 
	 public int pscheck(String uid,String ups){
			
		   
		    try {
			  con=dbopen.getConnection();
		      sql= new StringBuilder();
		      sql.append(" SELECT ups ");
		      sql.append(" FROM user ");
		      sql.append(" WHERE uid=? ");
		      
		      pstmt=con.prepareStatement(sql.toString());
		      pstmt.setString(1, uid);
		      rs=pstmt.executeQuery();
		      
		      if(rs.next()){

		        if(rs.getString(1).equals(ups)){
		        	return 1;
		        }else {
		        	return 0;
		        }
		        	
		      } 
		      return -1;
		    }catch(Exception e){
		    	System.out.println("비밀번호 조회 실패:" + e);
		    }finally{
		    	dbClose.close(con,pstmt,rs);
		    }   
		    
		  
		    return -2;     
		  }//updateform() end
		
	 public int update(UserDTO dto) {
			int cnt=0;
			try {
				
				con=dbopen.getConnection();
				sql= new StringBuilder();
				sql.append("UPDATE user ");
				sql.append("SET ups=?, uname=?, ubirth=?, uemail=?, ugender=?, uphone=? ");
				sql.append("WHERE uid=? ");
				pstmt= con.prepareStatement(sql.toString());
				pstmt.setString(1, dto.getUps());
				pstmt.setString(2, dto.getUname());
				pstmt.setString(3, dto.getUbirth());
				pstmt.setString(4, dto.getUemail());
				pstmt.setString(5, dto.getUgender());
				pstmt.setString(6, dto.getUphone());
				pstmt.setString(7, dto.getUid());
				cnt= pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("수정 실패:"+ e);
			}finally {
				dbClose.close(con,pstmt);
			}
			
			return cnt;
		}//update() end
	 public boolean delete(String uid) {
		 
		 boolean b = false;

			try {
				
				con=dbopen.getConnection();

				  sql=new StringBuilder();
				  sql.append(" DELETE FROM user ");
				  sql.append(" WHERE uid=? ");
				  
				  pstmt=con.prepareStatement(sql.toString());
				  pstmt.setString(1, uid);
				  
				  int re = pstmt.executeUpdate();
				  if(re>0) b = true;

			} catch (Exception e) {
				 System.out.println("탈퇴 실패:" + e);
			}finally {
				dbClose.close(con,pstmt);
			}//end

			return b;
			
		}//del() end
	 
	 public ArrayList<UserDTO> login_admin(UserDTO dto){
		    try {
		      con = dbopen.getConnection();
		      sql = new StringBuilder();
		      sql.append(" SELECT uid, ups, uname, ubirth, uemail, ugender, udate, uphone");
		      sql.append(" FROM user");
		      sql.append(" ORDER BY uid DESC");
		      
		      pstmt=con.prepareStatement(sql.toString());
		      rs=pstmt.executeQuery();
		      if(rs.next()) {
		        list=new ArrayList<UserDTO>();
		        do {
		           dto=new UserDTO();
		          dto.setUid(rs.getString("uid"));
		          dto.setUps(rs.getString("ups"));
		          dto.setUname(rs.getString("uname"));
		          dto.setUbirth(rs.getString("ubirth"));
		          dto.setUemail(rs.getString("uemail"));
		          dto.setUgender(rs.getString("ugender"));
		          dto.setUdate(rs.getString("udate"));
		          dto.setUphone(rs.getString("uphone"));
		          list.add(dto);
		        }while(rs.next());
		      }else {
		        list=null;
		      }
		      
		    }catch (Exception e) {
		      System.out.println("무비픽 목록실패:"+e);
		    }finally {
		      dbClose.close(con, pstmt, rs);
		    }
		    return list;
		  }//movie_admin() end
			
	 
	 public UserDTO read(String uid) {
		    UserDTO dto = null;
		    try {
		      con = dbopen.getConnection();
		      sql = new StringBuilder();
		      sql.append(" SELECT * ");
		      sql.append(" FROM user");
		      sql.append(" WHERE uid = ?");
		      
		      pstmt=con.prepareStatement(sql.toString());
		      pstmt.setString(1, uid);
		      rs=pstmt.executeQuery();
		      if(rs.next()) {
		        dto=new UserDTO();
		        dto.setUid(rs.getString("uid"));
		        dto.setUps(rs.getString("ups"));
		        dto.setUname(rs.getString("uname"));
		        dto.setUbirth(rs.getString("ubirth"));
		        dto.setUemail(rs.getString("uemail"));
		        dto.setUgender(rs.getString("ugender"));
		        dto.setUdate(rs.getString("udate"));
		        dto.setUphone(rs.getString("uphone"));
		      }
		      else {
		        dto = null;
		      }
		    }catch(Exception e) {
		      System.out.println("회원정보 읽기 실패: "+e);
		    }finally {
		      dbClose.close(con, pstmt, rs);
		    }
		    return dto;
		  }//read() end
			
			public int admin_delete(String uid) {
		    int cnt= 0;
		    try {
		      con = dbopen.getConnection();
		      sql = new StringBuilder();
		      sql.append(" DELETE FROM user ");
		      sql.append(" WHERE uid = ? ");
		      pstmt= con.prepareStatement(sql.toString());
		      pstmt.setString(1, uid);     
		      cnt = pstmt.executeUpdate();
		    } catch (Exception e) {
		      System.out.println("회원 강제 탈퇴시키기 실패"+ e);
		    } finally {
		      dbClose.close(con, pstmt);
		    }//try end
		    
		    return cnt;
		  }//delete() end
			
			public ArrayList<UserDTO> list2(UserDTO dto,PagingVO vo){


				int nowPage=vo.getNowPage();
				int cntPerPage=vo.getCntPerPage();
				int startRow = ((nowPage-1) * cntPerPage) ;

					try{
						con=dbopen.getConnection();
						sql=new StringBuilder();
						sql.append(" SELECT uid, ups, uname, ubirth, uemail, ugender, udate, uphone");
						      sql.append(" FROM user");
						      sql.append(" ORDER BY uid DESC");
				      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
				      pstmt = con.prepareStatement(sql.toString());
				      rs = pstmt.executeQuery();
				      if(rs.next()){
				        list = new ArrayList<UserDTO>();
				        do {
				        	dto=new UserDTO();
							dto.setUid(rs.getString("uid"));
						          dto.setUps(rs.getString("ups"));
						          dto.setUname(rs.getString("uname"));
						          dto.setUbirth(rs.getString("ubirth"));
						          dto.setUemail(rs.getString("uemail"));
						          dto.setUgender(rs.getString("ugender"));
						          dto.setUdate(rs.getString("udate"));
						          dto.setUphone(rs.getString("uphone"));
						          list.add(dto);
				        } while(rs.next());
				      }else{
				        list = null;
				      }//if end

				    }catch(Exception e){
				      System.out.println("�쑀��寃뚯떆�뙋紐⑸줉 �떎�뙣:"+e);
				    }finally{
				      dbClose.close(con, pstmt, rs);
				    }//try end
					return list;
				}//list() end

				public ArrayList<UserDTO> list3(String col, String word,UserDTO dto,PagingVO vo){


				int nowPage=vo.getNowPage();
				int cntPerPage=vo.getCntPerPage();
				int startRow = ((nowPage-1) * cntPerPage);

					try{
						con=dbopen.getConnection();
						sql=new StringBuilder();
						word = word.trim();
					if(word.length()==0) {	//寃��깋 x
						  sql.append(" SELECT uid, ups, uname, ubirth, uemail, ugender, udate, uphone");
						      sql.append(" FROM user");
						      sql.append(" ORDER BY uid DESC");
					      sql.append(" ORDER BY pno DESC");
				      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
				      
					}else {
						//寃��깋o
						 sql.append(" SELECT uid, ups, uname, ubirth, uemail, ugender, udate, uphone");
						 sql.append(" FROM user");
					      
					      
					      String search="";
					      if(col.equals("uid")) {
					          search += " WHERE uid LIKE '%" + word + "%'";
					        } else if(col.equals("uname")) {
					          search += " WHERE uname LIKE '%" + word + "%'";
					        } else if(col.equals("uemail")) {
					          search += " WHERE  uemail LIKE '%" + word + "%'";
					        } else if(col.equals("uphone")) {
					          search += " WHERE uphone LIKE '%" + word + "%'";			
					        }
					      
					      sql.append(search);
					      sql.append(" ORDER BY uid DESC");
					       
					      sql.append(" LIMIT "+ startRow + ","+ cntPerPage );
						
					}
				      pstmt = con.prepareStatement(sql.toString());
				      rs = pstmt.executeQuery();
				      if(rs.next()){
				        list = new ArrayList<UserDTO>();
				        do {
				        	dto=new UserDTO();
							dto.setUid(rs.getString("uid"));
						          dto.setUps(rs.getString("ups"));
						          dto.setUname(rs.getString("uname"));
						          dto.setUbirth(rs.getString("ubirth"));
						          dto.setUemail(rs.getString("uemail"));
						          dto.setUgender(rs.getString("ugender"));
						          dto.setUdate(rs.getString("udate"));
						          dto.setUphone(rs.getString("uphone"));
				          list.add(dto);
				        } while(rs.next());
				      }else{
				        list = null;
				      }//if end

				    }catch(Exception e){
				      System.out.println("�쑀��寃뚯떆�뙋紐⑸줉 �떎�뙣:"+e);
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
					
					sql.append(" SELECT COUNT(*) as uid ");
					sql.append(" FROM user ");
					
					  if(word.trim().length()>=1) {//寃��깋�뼱媛��엳�떎硫�
						  String search="";
					      if(col.equals("uid")) {
					          search += " WHERE uid LIKE '%" + word + "%'";
					        } else if(col.equals("uname")) {
					          search += " WHERE uname LIKE '%" + word + "%'";
					        } else if(col.equals("ubirth")) {
					          search += " WHERE  ubirthy LIKE '%" + word + "%'";
					        } else if(col.equals("uname_ubirth")) {
					          search += " WHERE uname LIKE '%" + word + "%'";
					          search += " OR uname LIKE '%" + word + "%'";
					        }
					      
						  sql.append(search);
					  }//if end
					  
					  pstmt=con.prepareStatement(sql.toString());
					  rs=pstmt.executeQuery();
					  if(rs.next()) {
						  cnt=rs.getInt("uid");
					  }//if end
					 
				} catch (Exception e) {
					System.out.println("�뻾 媛��닔 �떎�뙣:" + e);
				}finally {
					dbClose.close(con, pstmt, rs);
				}//try end


				return cnt;
				}//count() end
				
				public int getArticleCount() throws Exception{

					int x=0;
					try {
					   con=dbopen.getConnection();
					   pstmt=con.prepareStatement("SELECT count(*) FROM user");
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
}//class end
