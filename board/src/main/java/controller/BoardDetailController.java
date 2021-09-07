package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.HttpUtil;
import service.BoardService;
import vo.BoardVO;

public class BoardDetailController implements Controller{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String idx = request.getParameter("idx");
		int iidx = Integer.parseInt(idx);
		
		BoardService service = BoardService.getInstance();
		BoardVO board = service.boardDetail(iidx);
		service.boardDetailHitsUpdate(iidx);
		System.out.println("TEST!");
		if(board == null) {
			request.setAttribute("result", "검색정보가 없습니다.");
		} else {
			request.setAttribute("board", board);
		}
		
		HttpUtil.forward(request, response, "result/boardDetailOutput.jsp");
	}
}
