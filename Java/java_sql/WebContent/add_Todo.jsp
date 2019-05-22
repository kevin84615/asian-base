<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="user_todo.UserBeans"%>

<html>
<head>
<title>Add_list</title>
</head>
<body>
	<%
		UserBeans user = (UserBeans) request.getAttribute("user");
		String userid = user.getId();
		String userpassword = user.getPassword();
	%>
	<h1>追加画面</h1>
	<hr>
	<form action="Mode" method="POST">
		<div align="center">
			<input type="hidden" name="id" value="<%=userid%>"> 
			
			<input type="hidden" name="password" value="<%=userpassword%>">
			
			<input type="hidden" name="mode" value="add_Todo_database"> 
			
			<input required type="text" name="data" placeholder="記入してください。"
			pattern="^([0-9A-Za-z]|[\\\\\\(\\)/\\*&\$,\\.@=%\\+;:' \\->]|)+$" />
			
			<br> <br> 
			
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