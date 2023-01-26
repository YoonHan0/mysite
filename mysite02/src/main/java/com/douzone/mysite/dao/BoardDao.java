package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class BoardDao {

	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = 
					"SELECT a.no, a.title, b.name, a.hit, a.reg_date, a.user_no"
					+ " FROM board a JOIN user b ON a.user_no = b.no"
					+ " ORDER BY a.g_no DESC, a.o_no ASC";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				String reg_date = rs.getString(5);
				int user_no = rs.getInt(6);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(name);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setUserNo(user_no);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public void insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			
			String sql = "INSERT INTO board VALUES(null, ?, ?, 0, now(), (SELECT IFNULL(MAX(g_no)+1, 1) FROM board b), 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getUserNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public BoardVo findViewPage(BoardVo vo2) {
// 		List<BoardVo> result = new ArrayList<>();
		BoardVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = 
					"SELECT title, contents" +
					" FROM board" +
					" WHERE no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo2.getNo());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = new BoardVo();
				result.setTitle(rs.getString(1));
				result.setContent(rs.getString(2));
				
//				BoardVo vo = new BoardVo();
//				vo.setTitle(title);
//				vo.setContent(contents);
//				System.out.println(vo2.getUserNo() + " : " + vo2.getNo());
				// result.setUserNo(vo2.getUserNo());
				result.setNo(vo2.getNo());

//				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	public BoardVo findByNo(int no) {
		
		BoardVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "SELECT title, contents FROM board WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new BoardVo();

				result.setTitle(rs.getString(1));
				result.setContent(rs.getString(2));
				result.setNo(no);
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void update(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = 
					"UPDATE board" +
					" SET title = ?, contents = ?" +
					" WHERE no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("========== Board UPDATE 완료! ==========");
	}

	public void delete(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = 
					"DELETE" +
					" FROM board" +
					" WHERE no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("========== Board DELETE 완료! ==========");
	}
	public BoardVo findInfoForReply(int no) {

		BoardVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = 
					"SELECT a.g_no, a.o_no, a.depth, b.no"
					+ " FROM board a JOIN user b ON a.user_no = b.no"
					+ " WHERE a.no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new BoardVo();

				result.setG_no(rs.getInt(1));
				result.setO_no(rs.getInt(2));
				result.setDepth(rs.getInt(3));
				result.setUserNo(rs.getInt(4));
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	public void insertReply(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt01 = null;
		PreparedStatement pstmt02 = null;
														// vo (no, title, reply)
		BoardVo vo2 = findInfoForReply(vo.getNo());		// vo2 (g_no, o_no, depth, user_no)
		
		try {
			
			conn = getConnection();
			
			conn.setAutoCommit(false); 
			
			String sql01 = 
					"UPDATE board"
					+ " SET o_no = o_no + 1"
					+ " WHERE g_no = ? AND o_no > ?";
			
			pstmt01 = conn.prepareStatement(sql01);
			pstmt01.setInt(1, vo2.getG_no());
			pstmt01.setInt(2, vo2.getO_no());

			pstmt01.executeUpdate();
			
			/*=============================================================================*/
			
			String sql02 = "INSERT INTO board VALUES"
					+ "(null, ?, ?, 0, now(), ?, ?, ?, ?)";	//제목, 내용, hit, 시간, g_no, o_no, depth, user_no
			pstmt02 = conn.prepareStatement(sql02);
			pstmt02.setString(1, vo.getTitle());
			pstmt02.setString(2, vo.getContent());
			pstmt02.setInt(3, vo2.getG_no());
			pstmt02.setInt(4, vo2.getO_no()+1);
			pstmt02.setInt(5, vo2.getDepth()+1);
			pstmt02.setInt(6, vo2.getUserNo());

			pstmt02.executeUpdate();

			conn.commit();
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt02 != null) {
					pstmt02.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.64.2:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
}
