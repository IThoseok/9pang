package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.CartVo;
import util.DBUtil;

@Repository
public class CartDao {
	
	
//	//아이디& 비밀번호 확인 메소드 임시로 로그인 인증용으로 쓰고 추후 수정 또는 이관
//	public boolean CartLogin(String id, String pw) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement("select * from Cart where u_id=? and u_pw=?");
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
	
	
	// 카트에 신규아이템 추가 
	public boolean insert(CartVo cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO Cart VALUES(null,?,?,?)");
			
			pstmt.setString(1, cvo.getUId());
			pstmt.setInt(2, cvo.getPNum());
			pstmt.setInt(3, cvo.getPCount());
			
			pstmt.executeUpdate();
			
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	//카트내 단일아이템 삭제
	public boolean delete(CartVo cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from Cart where p_num=? and cart_num=?");
			pstmt.setInt(1, cvo.getPNum());
			pstmt.setInt(1, cvo.getCartNum());

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
	
	//카트 비우기
	public boolean deleteAll(CartVo cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from Cart where u_id=?");
			pstmt.setString(1, cvo.getUId());
			
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

	// 카트에서 수정할건 수량말곤 없어 보이는데..일단 수량조절만 하게하고 추후 확인
	public void update(CartVo cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement("UPDATE cart SET p_count=? where cart_num=?");
			pstmt.setInt(1, cvo.getPCount());
			pstmt.setInt(2, cvo.getCartNum());

			pstmt.executeUpdate();

		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
	}

	// 회원아이디로 해당 회원이 담은 카트 아이템들 조회 
	// 생각할점 파라미터 받을때 회원객체로 받을지 그냥 스트링 id만 받을지 아니면 세션에 저장된 id정보로 할지?
	public ArrayList<CartVo> getCarts(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<CartVo> allList = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM cart where u_id=?");
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<CartVo>();
			
			while (rset.next()) {
				allList.add(new CartVo(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getInt(4)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return allList;
	}
	
	// --------------회원 카트는 하나만 뽑는 경우는 없을텐데 일단 여기는 대기
	public boolean getCart(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from Cart where p_name=?");
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