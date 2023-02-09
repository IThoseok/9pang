package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.CategoryVo;
import util.DBUtil;

@Repository
public class CategoryDao {
	
	
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
	
	
	// 카테고리 등록
	public boolean insert(CategoryVo ctgvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO category VALUES(null,?,?)");
			
			pstmt.setString(1, ctgvo.getCtgName());
			pstmt.setInt(2, ctgvo.getCtgLv());
			
			pstmt.executeUpdate();
			
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	//카테고리 삭제  ------파라미터 나중에 확인필
	public boolean delete(int num) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from category where ctg_num=?");
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

	// 카테고리 수정
	public void update(CategoryVo ctgvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement("UPDATE category SET ctg_name=?, ctg_lv=? where ctg_num=?");
			pstmt.setString(1, ctgvo.getCtgName());
			pstmt.setInt(2, ctgvo.getCtgLv());
			pstmt.setInt(3, ctgvo.getCtgNum());

			pstmt.executeUpdate();

		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil.close(con, pstmt);
		}
	}

	// 전체 카테고리 근데 이거 아예 전체를 다 출력할거여 아니면 대분류 소분류 나눠서 할거? 분류별로 나누면 부모 카테고리가 기준이되야함
	// 일단 전체출력부터 완성
	public ArrayList<CategoryVo> getCategorys() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<CategoryVo> allList = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM category");
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<CategoryVo>();
			
			while (rset.next()) {
				allList.add(new CategoryVo(rset.getInt(1), rset.getString(2), rset.getInt(3)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return allList;
	}
	
	// 일단 카테고리 검색인데..개별 카테고리를 검색할일이 있나?
	public boolean getCategory(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from category where ctg_name=?");
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