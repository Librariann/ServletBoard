package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.BoardVO;

import dao.BoardDAO;

public class BoardDAO {
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() {};
	public static BoardDAO getInstance() {
		return dao;
	}
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession(); // 마이바티스 셋팅값 불러오기

	private SqlSession sqlSession; // mapper에 접근하기 위한 SqlSession

	public List<BoardVO> boardMain(int pagingStart, int pagingList) {
	    try {
	    	sqlSession = factory.openSession();  // 세션 열기
	        // selectList(): SELECT
	        // 파라미터: SQLMapper의 네임 스페이스(dev) + SQL문 ID(selectList)
	    	Map<String, Integer> map = new HashMap<String, Integer>();
	    	map.put("pagingStart", pagingStart);
	    	map.put("pagingList", pagingList);
	    	
	        return sqlSession.selectList("dev.selectDataAll", map);
	        
	    } finally {
	        sqlSession.close();
	    }
	}
	
	public BoardVO boardDetail(String idx) {
		try {
	    	sqlSession = factory.openSession();  // 세션 열기
	        return sqlSession.selectOne("dev.selectOne", idx);
	    } finally {
	        sqlSession.close();
	    }
	}
	
	public void boardDetailHitsUpdate(String idx) {
		try {
	    	sqlSession = factory.openSession();  // 세션 열기
	    	int suc = sqlSession.update("dev.detailHitsUpdate", idx);
	    	if(suc > 0) {
	    		sqlSession.commit();
    		}else {
    			sqlSession.rollback();
    		}
	    } finally {
	        sqlSession.close();
	    }
	}
	
	public int boardMainPageCount() {
		try {
	    	sqlSession = factory.openSession();  // 세션 열기
	        return sqlSession.selectOne("dev.selectCount");
	    } finally {
	        sqlSession.close();
	    }
	}
	
	public void boardWrite(BoardVO board) {
		try {
	    	sqlSession = factory.openSession();  // 세션 열기
	    	int insertSuc = sqlSession.insert("dev.boardInsert", board);
	    	if(insertSuc > 0) {
	    		sqlSession.commit();
    		}else {
    			sqlSession.rollback();
    		}
	    } finally {
	        sqlSession.close();
	    }
	}
}
