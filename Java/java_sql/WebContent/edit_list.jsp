<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="info.searchman.lesson.java_mysql.ShainBeans"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>


<html>
<head>
<title>Edit_list</title>
</head>
<body>
	<%
		ShainBeans shain = (ShainBeans) request.getAttribute("shain");
		String todo_id = request.getParameter("todoid");
		String todo_text = request.getParameter("data");
		String str = new String(request.getParameter("data").getBytes("iso-8859-1"), "utf-8"); 
	%>
	<%
	String userid = shain.getId();
	String userpw = shain.getPw();
	userid = userid.replaceAll("(?i)(&)", "&amp;");
	userid = userid.replaceAll("(?i)(')", "&#039;");
	userid = userid.replaceAll("(?i)(\")", "&quot;");
	userpw = userpw.replaceAll("(?i)(&)", "&amp;");
	userpw = userpw.replaceAll("(?i)(')", "&#039;");
	userpw = userpw.replaceAll("(?i)(\")", "&quot;");
	userid = userid.replaceAll("(?i)(<)", "&lt;");
	userid = userid.replaceAll("(?i)(>)", "&gt;");
	userpw = userpw.replaceAll("(?i)(<)", "&lt;");
	userpw = userpw.replaceAll("(?i)(>)", "&gt;");
	%>
	<%
	todo_text = todo_text.replaceAll("(?i)(&)", "&amp;");
	todo_text = todo_text.replaceAll("(?i)(')", "&#039;");
	todo_text = todo_text.replaceAll("(?i)(\")", "&quot;");
	todo_text = todo_text.replaceAll("(?i)(<)", "&lt;");
	todo_text = todo_text.replaceAll("(?i)(>)", "&gt;");
	%>
	<h1>編集画面</h1>
	<hr><br> 
	<form action="Edit" method="POST">
		<div align="center">
			<input type="hidden" name="id" value="<%=userid%>">
			
			<input type="hidden" name="pw" value="<%=userpw%>"> 
			
			<input type="hidden" name="todoid" value="<%=todo_id%>"> 
			
			<input type="hidden" name="mode" value="editlist_database">
			
			<input required type="text" name="data" value="<%=todo_text%>">
			 
			<br><br> 
			
			<input type="submit" value="保存">
		</div>
	</form>
	<form action="Edit" method="POST">
		<div align="center">
		
			<input type="hidden" name="id" value="<%=userid%>"> 
			
			<input type="hidden" name="pw" value="<%=userpw%>"> 
			
			<input type="hidden" name="mode" value="verification"> 
			
			<input type="submit" value="キャンセル">
			
		</div>
	</form>
</body>
</html>