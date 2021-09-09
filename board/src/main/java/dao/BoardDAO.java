package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

	public BoardVO boardMain() {
	    try {
	    	sqlSession = factory.openSession();  // 세션 열기
	        // selectList(): SELECT
	        // 파라미터: SQL 맵퍼의 네임 스페이스(com.atoz_develop.spms.dao.ProjectDao) + SQL문 ID(selectList)
	        return sqlSession.selectOne("dev.selectDataAll");
	    } finally {
	        sqlSession.close();
	    }
	}
}
