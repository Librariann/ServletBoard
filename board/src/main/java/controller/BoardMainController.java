package controller;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import controller.HttpUtil;
import service.BoardService;
import vo.BoardVO;

public class BoardMainController implements Controller{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int pagingStart = 0; //페이징스타
		int pagingList = 3;	//한페이지에 나오는 게시물갯
		int pageNum = 0; //현재 페이지 넘버
		/*
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			if(pageNum == 1) {
				pagingStart = 0;
			} else if(pageNum > 1) {
				pagingStart = (pageNum - 1) * pagingList;
			}
		} catch (Exception ex) {
			System.out.println("에러 : "+ex);
		} 
		*/
		
		//Service 객체 메소드 호출
		BoardService service = BoardService.getInstance();
		List<BoardVO> list = service.boardMain();
		//int pageCount = service.boardMainPageCount(pagingList);
		
		request.setAttribute("list", list);
		//request.setAttribute("pageCount", pageCount);
		//request.setAttribute("pageNum", pageNum);
		HttpUtil.forward(request, response, "boardList.jsp");
	}
}
