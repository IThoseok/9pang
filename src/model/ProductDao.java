package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.ProductVo;
import util.DBUtil;

@Repository
public class ProductDao {
	
	
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
	
	
	// ----미완
	// 상품등록 
	// 이거 기존 게스트북할때처럼 상품소개같은 경우 /n이런거 손봐줘야 할텐데
	public boolean insert(ProductVo pvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO Product VALUES(null,?,?,?,?,?,now(),?,?)");
			
			pstmt.setString(1, pvo.getPCate());
			pstmt.setString(2, pvo.getPName());
			pstmt.setInt(3, pvo.getPSellprc());
			pstmt.setInt(4, pvo.getPBuyprc());
			pstmt.setInt(5, pvo.getPStock());
			pstmt.setInt(6, pvo.getPSell());
			pstmt.setString(7, pvo.getPDetail());
			
			pstmt.executeUpdate();
			
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	//상품삭제
	public boolean delete(int num) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from Product where p_num=?");
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

	// -------미완 그냥 기존 수정처럼하면 안될거같고
	// null들어가는거 확인했으니까 테스트한번 ㄱㄱ
	public void update(ProductVo pvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement("UPDATE customer SET password = ? , email = ? WHERE id = ?");
			pstmt.setString(1, pvo.getPassword());
			pstmt.setString(2, pvo.getEmail());
			pstmt.setString(3, pvo.getId());
			pstmt.setString(3, null);

			pstmt.executeUpdate();

		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
	}

	//전체 상품
	// 잠깐 비상비상 상품정렬 어떻게 할려?? 생각나는건 db쿼리로 정렬바꿔가며 부르기/ 리스트 뽑고 수정하기 리스트 수정이낫겠다
	// 가격별 또는 이름순 https://hianna.tistory.com/569 참고
	public ArrayList<ProductVo> getProducts() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<ProductVo> allList = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM Product");
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<ProductVo>();
			
			while (rset.next()) {
				allList.add(new ProductVo(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getInt(5), rset.getInt(6), rset.getString(7), rset.getInt(8), rset.getString(9)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return allList;
	}
	
	// 상품검색 이름검색
	// 이거 근데 번호검색도 있어야할거같은데.. 필요한경우 오버로딩으로 처리
	public boolean getProduct(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from product where p_name=?");
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