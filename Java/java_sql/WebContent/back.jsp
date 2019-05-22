<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="user_todo.UserBeans"%>
<html>
<head>
<title>back</title>
</head>
<body>
	<br>
	<%
		UserBeans user = (UserBeans) request.getAttribute("user");
		String error_message01 = (String) request.getAttribute("message01");
		String error_message02 = (String) request.getAttribute("message02");
		String userid = user.getId();
		String userpassword = user.getPassword();
	%>

	<br>
	<br>
	<h1><%=error_message01%></h1>
	<h1><%=error_message02%></h1>
	<br>
	<br>
	<form action="Mode" method="POST">
		<div align="center">
			<input type="hidden" name="id" value="<%=userid%>"> 
			
			<input type="hidden" name="password" value="<%=userpassword%>"> 
				
			<input type="hidden" name="mode" value="user_verification"> 
				
			<input type="submit" value="前のページに戻る">
		</div>
	</form>



</body>
</html>
