package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.OrderDetailVo;
import model.domain.OrderVo;
import util.DBUtil;

@Repository
public class OrderDetailDao {
	
	
//	//아이디& 비밀번호 확인 메소드 임시로 로그인 인증용으로 쓰고 추후 수정 또는 이관
//	public boolean ProductLogin(String id, String pw) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement("select * from Product where u_id=? and u_pw=?");
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
	
	
	// 주문을하는 순간 주문번호와 주문상품상세는 동시에 생성되고 주문상품상세가 주문번호에 속하게되니까 
	// 일단 주문번호 먼저 만들고 주문상품상세를 만들어야겠지 그럼 주문번호먼저 생성하고 생성확인 로직거쳐서 생성된 경우만 주문상품상세가 만들어지게
	// 그러면 여기 파라미터를 주문번호도 받아서 써야겠네 아니다 여기서 다이렉트로 받지말고 컨트롤러에서 넘겨주고 여기는 odvo만 받아서 하자
	public boolean insert(OrderDetailVo odvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO order_detail VALUES(null,?,?,?,?,?,?)");
			
			pstmt.setInt(1, odvo.getOrderNum());
			pstmt.setInt(2, odvo.getPNum());
			pstmt.setInt(3, odvo.getPCount());
			pstmt.setInt(4, odvo.getPSellprc());
			pstmt.setString(5, odvo.getOrderStat());
			pstmt.setInt(6, odvo.getRefund());
			
			pstmt.executeUpdate();
			
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	//주문상품상세는 지울게 아니라 환불처리를 하던가 그렇게 하는게 맞지?  여기는 보류----------------------------
	public boolean delete(int num) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from order_detail where p_num=?");
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

	// 변경가능한 요소는 주문상태/ 환불여부
	// 주문상태와 환불여부를 구분할 로직은 추후고려
	public void update(OrderDetailVo odvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement("UPDATE order_detail SET order_stat = ? , refund = ? WHERE order_detail_num = ?");
			pstmt.setString(1, odvo.getOrderStat());
			pstmt.setInt(2, odvo.getRefund());
			pstmt.setInt(3, odvo.getOrderDetailNum());

			pstmt.executeUpdate();

		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
	}

	// 주문번호에 속하는 주문상품상세 전체 리스트
	public ArrayList<OrderDetailVo> getOrderDetails(OrderDetailVo odvo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<OrderDetailVo> allList = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM order_detail where order_num=?");
			pstmt.setInt(1, odvo.getOrderNum());
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<OrderDetailVo>();
			
			while (rset.next()) {
				allList.add(new OrderDetailVo(rset.getInt(1), rset.getInt(2), rset.getInt(3), rset.getInt(4), rset.getInt(5), rset.getString(6), rset.getInt(7)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return allList;
	}
	
	// 주문상품상세에서 개별로 처리해야할 경우 주문상세번호로 구분
	public boolean getOrderDetail(OrderDetailVo odvo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from order_detail where order_detail_num=?");
			pstmt.setInt(1, odvo.getOrderDetailNum());
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