<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="user_todo.UserBeans"%>

<html>
<body>
	<%
		UserBeans user = (UserBeans) request.getAttribute("user");
		String userid = user.getId();
		String userpassword = user.getPassword();
	%>
	<br>
	<br>
	<br>
	<form action="Mode" method="POST">
		<div align="center">
		
			ID:<%=userid%>
			<input required type="hidden" name="id" value="<%=userid%>">
		
			<br> <br>本当に削除しますか？
			<br>削除すると、ユーザーアカウントとtodoをすべて削除します。<br> <br> 
			
			<input type="hidden" name="mode" value="delete_User_confirm"> 
			
			<input type="submit" value="削除">
		
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