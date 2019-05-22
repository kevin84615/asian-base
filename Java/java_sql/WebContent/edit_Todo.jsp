<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="user_todo.UserBeans"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>


<html>
<head>
<title>Edit_list</title>
</head>
<body>
	<%
		UserBeans user = (UserBeans) request.getAttribute("user");
		String todo_id = request.getParameter("todoid");
		String str = new String(request.getParameter("data").getBytes("iso-8859-1"), "utf-8");
		String userid = user.getId_edit();
		String userpassword = user.getPassword_edit();
		String data = user.getData();
	%>
	<h1>編集画面</h1>
	<hr><br> 
	<form action="Mode" method="POST">
		<div align="center">
			<input type="hidden" name="id" value="<%=userid%>">
			
			<input type="hidden" name="password" value="<%=userpassword%>"> 
			
			<input type="hidden" name="todoid" value="<%=todo_id%>"> 
			
			<input type="hidden" name="mode" value="edit_Todo_database">
			
			<input required type="text" name="data" value="<%=data%>">
			 
			<br><br> 
			
			<input type="submit" value="保存">
		</div>
	</form>

	<form action="Mode" method="POST">
		<div align="center">
		
			<input type="hidden" name="id" value="<%=userid%>"> 
			
			<input type="hidden" name="password" value="<%=userpassword%>"> 
			
			<input type="hidden" name="mode" value="user_verification"> 
			
			<input type="submit" value="キャンセル">
			
		</div>
	</form>
</body>
</html>