package kr.co.moviepick.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.moviepick.booking.BookingDTO;
import kr.co.moviepick.screening.ScreeningDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class SeatDAO {
	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbClose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;
	ArrayList<ScreeningDTO> list =null;
	ArrayList<BookingDTO> list2 =null;
	
	public SeatDAO() {
		System.out.println("---SeatDAO()객체생성됨...");
	}
	
	
	public ArrayList<ScreeningDTO> list(ScreeningDTO sdto){
		try {
			con= dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT scrno, scrdate, scrstart, hno, mno ");
			sql.append(" FROM screening ");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<ScreeningDTO>();
				do {
					sdto = new ScreeningDTO();
					sdto.setScrno(rs.getInt("scrno"));
					sdto.setScrdate(rs.getString("scrdate"));
					sdto.setScrstart(rs.getString("scrstart"));
					sdto.setHno(rs.getInt("hno"));
					sdto.setMno(rs.getInt("mno"));
					list.add(sdto);
				}while(rs.next());
			}else {
				list = null;
			}//if end
		} catch (Exception e) {
			System.out.println("slist실패:"+e);
		} finally {
			dbClose.close(con, pstmt, rs);
		}//try end
		return list;
	}//list end

	
	
	public ArrayList<ScreeningDTO> choice2(int mno){
		try {
			con= dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT scrno, scrdate, scrstart, hno, mno ");
			sql.append(" FROM screening ");
			sql.append(" WHERE mno=? ");
			sql.append(" GROUP BY scrdate ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<ScreeningDTO>();
				do {
					ScreeningDTO sdto = new ScreeningDTO();
					sdto.setScrno(rs.getInt("scrno"));
					sdto.setScrdate(rs.getString("scrdate"));
					sdto.setScrstart(rs.getString("scrstart"));
					sdto.setHno(rs.getInt("hno"));
					sdto.setMno(rs.getInt("mno"));
					list.add(sdto);
				}while(rs.next());
			}else {
				list = null;
			}//if end
		} catch (Exception e) {
			System.out.println("choice2실패:"+e);
		} finally {
			dbClose.close(con, pstmt, rs);
		}//try end
		return list;
	}//choic2() end
	
	public ArrayList<ScreeningDTO> choice3(String scrdate, int mno){
		try {
			con= dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT scrno, scrdate, scrstart, hno, mno ");
			sql.append(" FROM screening ");
			sql.append(" WHERE scrdate=? and mno=?");
			sql.append(" ORDER BY scrstart asc ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, scrdate);
			pstmt.setInt(2, mno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<ScreeningDTO>();
				do {
					ScreeningDTO sdto = new ScreeningDTO();
					sdto.setScrno(rs.getInt("scrno"));
					sdto.setScrdate(rs.getString("scrdate"));
					sdto.setScrstart(rs.getString("scrstart"));
					sdto.setHno(rs.getInt("hno"));
					sdto.setMno(rs.getInt("mno"));
					list.add(sdto);
				}while(rs.next());
			}else {
				list = null;
			}//if end
		} catch (Exception e) {
			System.out.println("choice3실패:"+e);
		} finally {
			dbClose.close(con, pstmt, rs);
		}//try end
		return list;
	}//choic3() end
	
	public int booking(BookingDTO bdto) {
		int cnt = 0;
		try {
			con =dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" INSERT INTO booking(bdate, bloc, btype, scrno, uid ) ");
			sql.append(" VALUES( now(), ?, ?, ?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, bdto.getBloc());
			pstmt.setString(2, bdto.getBtype());
			pstmt.setInt(3, bdto.getScrno());
			pstmt.setString(4, bdto.getUid());
			cnt = pstmt.executeUpdate();
			System.out.println(bdto.getScrno());
		}catch (Exception e) {
			System.out.println("예매 실패:"+e);
		} finally {
			dbClose.close(con, pstmt);
		}//try end
		return cnt;
	}//booking() end
	
	public ArrayList<BookingDTO> list2(int scrno){
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT bno, bdate, bloc, btype, scrno, uid ");
			sql.append(" FROM booking WHERE scrno = ? ");			
			sql.append(" ORDER BY bloc ");			
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, scrno);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list2=new ArrayList<BookingDTO>();
				do {
					BookingDTO bdto=new BookingDTO();
					bdto.setBno(rs.getInt("bno"));
					bdto.setBdate(rs.getString("bdate"));
					bdto.setBloc(rs.getString("bloc"));
					bdto.setBtype(rs.getString("btype"));
					bdto.setScrno(rs.getInt("scrno"));
					bdto.setUid(rs.getString("uid"));
					list2.add(bdto);
					System.out.println("list값:"+list2);
					System.out.println("scrno값:"+scrno);
				}while(rs.next());
			}else {
				list2=null;
			
			}//if end
			
		}catch (Exception e) {
			System.out.println("무비픽 목록실패:"+e);
		}finally {
			dbClose.close(con, pstmt, rs);
		}//try end
		return list2;
	}//list2() end
	
	
	public String checkseat(int scrno, BookingDTO bdto){
		String dto=null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT bloc ");
			sql.append(" FROM booking GROUP BY scrno = ? ");			
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, scrno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto = new String();
				dto.concat(dto);//(rs.getString("bloc"));
			}else {
				dto = null;
			
			}//if end
		} catch (Exception e) {
			System.out.println("중복환인 실패"+e);
		} finally {
			dbClose.close(con, pstmt, rs);
		}//try end
		return dto;
	}//checkseat() end
}//class end
