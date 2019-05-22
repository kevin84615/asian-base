<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="info.searchman.lesson.java_mysql.ShainBeans"%>

<html>
<head>
<title>Add_list</title>
</head>
<body>
	<%
		ShainBeans shain = (ShainBeans) request.getAttribute("shain");
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
	<h1>追加画面</h1>
	<hr>
	<form action="Edit" method="POST">
		<div align="center">
			<input type="hidden" name="id" value="<%=userid%>"> 
			
			<input type="hidden" name="pw" value="<%=userpw%>">
			
			<input type="hidden" name="mode" value="addlist_database"> 
			
			<input required type="text" name="data" placeholder="記入してください。"
			pattern="^([0-9A-Za-z]|[\\\\\\(\\)/\\*&\$,\\.@=%\\+;:' \\->]|)+$" />
			
			<br> <br> 
			
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