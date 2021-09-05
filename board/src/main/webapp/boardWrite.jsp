<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>게시글 작성 페이지</title>
</head>
<body>
	
	<h1>게시글 작성</h1>
	<form action="boardWrite.do" method="post">
	
		<div>
			제목 : <input type="text" name="board_title" ></input>
		</div>
		<div>
			내용 : <textarea name="board_contents"></textarea>
		</div>
	
		<input type="hidden" name="board_writer" value="admin" ></input>
		<input type="submit" value="게시글 작성" ></input>
	</form>
</body>
</html>