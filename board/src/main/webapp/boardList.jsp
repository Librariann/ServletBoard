<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.BoardVO" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>게시글 작성 페이지</title>
</head>
<body>
	
	<h1>게시글 리스트</h1>
	<% 
		List<BoardVO> list = (List<BoardVO>) request.getAttribute("list"); 
		//int pageCount = (int) request.getAttribute("pageCount");
		//int pageNum = (int) request.getAttribute("pageNum");
		if(list !=null)
		{
	%>
			<table border="1">
			<tr>
				<th>제목</th>
				<td>작성자</td>
				<td>조회수</td>
			</tr>
			<%
				for(int i=0; i < list.size(); i++){
				BoardVO board = list.get(i);
			%>
				<tr>
					<th><a href="boardDetail.do?idx=<%=board.getIdx()%>"><%=board.getBoard_title() %></a></th>
					<td><%=board.getBoard_writer() %></td>
					<td><%=board.getBoard_hits() %></td>
				</tr>
			<%
				}
			%>
				
			
	<%
		}
	%>
			
	</div>
</body>
</html>