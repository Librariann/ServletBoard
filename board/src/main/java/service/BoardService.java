package service;

import java.util.ArrayList;
import java.lang.Math.*;

import dao.BoardDAO;
import service.BoardService;
import vo.BoardVO;

public class BoardService {
	private static BoardService service = new BoardService();
	public BoardDAO dao = BoardDAO.getInstance();
	
	private BoardService() {}
	
	public static BoardService getInstance() {
		return service;
	}
	
	public ArrayList<BoardVO> boardMain(int pagingStart, int pagingList){
		ArrayList<BoardVO> list = dao.boardMain(pagingStart, pagingList);
		return list;
	}
	
	public int boardMainPageCount(int pagingList) {
		int pageCount = dao.boardMainPageCount();
		pageCount = (int) Math.ceil((double)pageCount/pagingList);
		return pageCount;
	}
	
	public void boardWrite(BoardVO board) {
		dao.boardWrite(board);
	}
	
	public BoardVO boardDetail(int idx){
		BoardVO board = dao.boardDetail(idx);
		return board;
	}
	
	public void boardDetailHitsUpdate(int idx){
		dao.boardDetailHitsUpdate(idx);
	}
}