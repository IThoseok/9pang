package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.OrderVo;
import util.DBUtil;

@Repository
public class OrderDao {
	
	
//	//아이디& 비밀번호 확인 메소드 임시로 로그인 인증용으로 쓰고 추후 수정 또는 이관
//	public boolean orderLogin(String id, String pw) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement("select * from order where u_id=? and u_pw=?");
//			pstmt.setString(1, id);
//			pstmt.setString(2, pw);
//			rset = pstmt.executeQuery();
//			
//			if (rset.next()) {
//				return true;
//			}
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//			throw sqle;
//		} finally {
//			DBUtil.close(conn, pstmt, rset);
//		}
//		
//		return false;
//	}
	
	
	// 일단..주문번호 생성인데 아직 손볼곳이 많음------------------------------------
	public boolean insert(OrderVo ovo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO order VALUES(null,?,now(),?,?,?,?)");
			
			pstmt.setString(1, ovo.getUId());
			pstmt.setString(2, ovo.getReceiverName());
			pstmt.setString(3, ovo.getReceiverPhone());
			pstmt.setString(4, ovo.getReceiverAddr());
			pstmt.setInt(5, ovo.getTrackingNum());
			
			pstmt.executeUpdate();
			
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	//주문취소한 경우도 기록은 남을텐데 삭제는 일단 보류============================
	public boolean delete(int num) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from order where p_num=?");
			pstmt.setInt(1, num);

			int result = pstmt.executeUpdate();
			if(result != 0) {
				return true;
			}

		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
		
		return false;
	}

	// 주문번호 수정 - 송장번호 업데이트
	public void update(OrderVo ovo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement("UPDATE order SET tracking_num = ? WHERE order_num = ?");
			pstmt.setInt(1, ovo.getTrackingNum());
			pstmt.setInt(2, ovo.getOrderNum());

			pstmt.executeUpdate();

		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
	}

	// 전체주문리스트를 조회하는 경우 2가지가 있지? 고객이 자기 주문목록 조회할때/ 관리자가 전체 주문목록 관리할때 
	// 오버로딩으로 처리 합시다
	public ArrayList<OrderVo> getorders() throws SQLException {//관리자용 전체주문리스트
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<OrderVo> allList = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM order");
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<OrderVo>();
			
			while (rset.next()) {
				allList.add(new OrderVo(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getInt(7)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return allList;
	}
	
	public ArrayList<OrderVo> getorders(OrderVo ovo) throws SQLException {//회원 주문리스트
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<OrderVo> allList = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM order where u_id=?");
			pstmt.setString(1, ovo.getUId());
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<OrderVo>();
			
			while (rset.next()) {
				allList.add(new OrderVo(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getInt(7)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return allList;
	}
	
	// 주문번호하나만 필요한 경우가 있나?? 보류------------------------------------------
	public boolean getorder(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from order where p_name=?");
			pstmt.setString(1, name);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return false;
	}
	
	

}