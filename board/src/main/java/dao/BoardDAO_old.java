package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.BoardVO;

import dao.BoardDAO_old;

public class BoardDAO_old {
	private static BoardDAO_old dao = new BoardDAO_old();
	private BoardDAO_old() {};
	public static BoardDAO_old getInstance() {
		return dao;
	}
	
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://139.150.79.5:3306/JSPDB?user=root&password=tjdgus123");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : "+ex);
			}
		}
		
		close(conn,ps);
	}
	
	public void close(Connection conn, PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : "+ex);
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : "+ex);
			}
		}
	}
	
	public ArrayList<BoardVO> boardMain(int pagingStart, int pagingList) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVO board = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("SELECT idx,board_title,board_contents,board_writer,board_hits FROM board limit "+pagingStart+","+pagingList);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardVO();
				board.setBoard_idx(rs.getInt(1));
				board.setBoard_title(rs.getString(2));
				board.setBoard_contents(rs.getString(3));
				board.setBoard_writer(rs.getString(4));
				board.setBoard_hits(rs.getInt(5));
				list.add(board);
			}
		} catch (Exception ex) {
			System.out.println("에러 : "+ex);
		} finally {
			close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public int boardMainPageCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pageCount = 0;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("SELECT count(*) FROM board");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pageCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("에러 : "+ex);
		} finally {
			close(conn, pstmt, rs);
		}
		
		return pageCount;
	}
	
	public void boardWrite(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into board(board_title, board_contents, board_writer, reg_id, mod_id) values(?,?,?,?,?)");
			pstmt.setString(1, board.getBoard_title());
			pstmt.setString(2, board.getBoard_contents());
			pstmt.setString(3, board.getBoard_writer());
			pstmt.setString(4, board.getBoard_writer());
			pstmt.setString(5, board.getBoard_writer());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("에러 : "+ex);
		} finally {
			close(conn, pstmt);
		}
	}
	
	public BoardVO boardDetail(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVO board = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select board_title,board_contents,board_writer,board_hits from board where idx=?");
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardVO();
				board.setBoard_title(rs.getString(1));
				board.setBoard_contents(rs.getString(2));
				board.setBoard_writer(rs.getString(3));
				board.setBoard_hits(rs.getInt(4));
			}
		} catch (Exception ex) {
			System.out.println("에러 : "+ex);
		} finally {
			close(conn, pstmt, rs);
		}
		
		return board;
	}
	
	public void boardDetailHitsUpdate(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("update board set board_hits=board_hits+1 where idx=?");
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("에러 : "+ex);
		} finally {
			close(conn, pstmt);
		}
	}
}
