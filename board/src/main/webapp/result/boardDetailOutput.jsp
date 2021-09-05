<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.BoardVO" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>게시글 작성완료</title>
</head>
<body>
<%
	String result = (String)request.getAttribute("result");
	if(result != null){
		out.print(result);
	} else {
%>
	<h3>${ board.board_contents }</h3>
<%
	}
%>
	
	<a href="/board/index.jsp">첫화면으로 이동</a>
</body>
</html>