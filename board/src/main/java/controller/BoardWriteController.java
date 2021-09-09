package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.HttpUtil;
import service.BoardService;
import vo.BoardVO;

public class BoardWriteController implements Controller{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//파라미터 추출
		String board_title = request.getParameter("board_title");
		String board_contents = request.getParameter("board_contents");
		String board_writer = request.getParameter("board_writer");
		
		//validation check
		if(board_title.isEmpty() || board_contents.isEmpty() || board_writer.isEmpty()) {
			request.setAttribute("error", "모든항목을 입력해주세요");
			HttpUtil.forward(request, response, "/boardMain.jsp");
			return;
		}
		
		//VO 객체 바인딩
		BoardVO board = new BoardVO();
		board.setBoard_title(board_title);
		board.setBoard_contents(board_contents);
		board.setBoard_writer(board_writer);
		
		//Service 객체 메소드 호출
		BoardService service = BoardService.getInstance();
		//service.boardWrite(board);
		
		//ouput view 페이지 이동
		request.setAttribute("", board_title);
		HttpUtil.forward(request, response, "result/boardWriteOutput.jsp");
	}
}
