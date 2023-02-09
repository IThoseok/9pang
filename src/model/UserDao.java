package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.UserVo;
import util.DBUtil;

@Repository
public class UserDao {
	
	
	//아이디& 비밀번호 확인 메소드 임시로 로그인 인증용으로 쓰고 추후 수정 또는 이관
	public boolean userLogin(String id, String pw) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		try {
			conn = DBUtil.getConnection();
			System.out.println("디비접속");
			pstmt = conn.prepareStatement("select * from user where u_id=? and u_pw=?");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
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
	
	
	//----미완 고려할점 가입을 시킬때 필수정보는 얼마나 입력시킬거고 
	// 정해둔 필수정보 이외의 정보를 입력하는 경우는? 그냥 입력안하면 null로 처리하면 되는거 아님?
	// 최초 가입시 등급은 bronze로 조정/ 가입일은 now로 처리/ 
	
	public boolean insert(UserVo uvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			System.out.println("인서트");
			pstmt = con.prepareStatement("INSERT INTO user VALUES(?,?,?,?,?,'bronze',now(),0)");
			
			pstmt.setString(1, uvo.getUId());
			pstmt.setString(2, uvo.getUPw());
			pstmt.setString(3, uvo.getUName());
			pstmt.setString(4, uvo.getUMail());
			pstmt.setString(5, uvo.getUPhone());
//			pstmt.setString(6, "bronze");
//			pstmt.setString(7, "now()");
//			pstmt.setInt(6, uvo.getUReward());
			
			pstmt.executeUpdate();
			
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	//회원삭제 관리자가 삭제시 해당 메소드로 바로 삭제
	//회원탈퇴시 아이디비번 확인 메소드로 확인후 해당 메소드 호출
	public boolean delete(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from user where u_id=?");
			pstmt.setString(1, id);

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

	// -------미완 고려해야할점 수정의 경우 id를 제외하곤 수정할게 참 많지 수정로직 자체는 고민을 좀 해봐야겠음
	// 구매시 등급 조정이나 적립금부분이 있기때문에 주기적으로 해줘야 하는데 이건 프로시저로 해결하면될듯?
	public void update(UserVo cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement("UPDATE customer SET password = ? , email = ? WHERE id = ?");
			pstmt.setString(1, cvo.getPassword());
			pstmt.setString(2, cvo.getEmail());
			pstmt.setString(3, cvo.getId());

			pstmt.executeUpdate();

		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
	}

	//전체 회원리스트
	public ArrayList<UserVo> getUsers() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<UserVo> allList = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM user");
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<UserVo>();
			
			while (rset.next()) {
				allList.add(new UserVo(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getInt(8)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return allList;
	}
	
	//회원검색 아이디로 리턴값은 일단 불린
	public boolean getUser(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from user where u_id=?");
			pstmt.setString(1, id);
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