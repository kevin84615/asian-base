<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="user_todo.UserBeans"%>
<%@page import="user_todo.TodoBeans"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<html>
<head>
<title>Delete_list</title>
</head>
<body>
	<%
		UserBeans user = (UserBeans) request.getAttribute("user");
		TodoBeans todo = (TodoBeans) request.getAttribute("todo");
		String[] todoid = request.getParameterValues("todoid");
		String userid = user.getId();
		String userpassword = user.getPassword();
	%>
	<h1>削除画面</h1>
	<hr>
	<form action="Mode" method="POST">
		<div align="left">
			<%
				for (String todo_array : todoid) {
			%>
			<table border="0">
				<tr>
					<td>
						<%
						String text = todo_array;
						String[] split_text = text.split("&split;");
						String todo_text = split_text[1];
						%> 
						<%=todo_text%>
					</td>
				</tr>
			</table>
			<%
				}
			%>
			<%			
				String delete_todoid = "0";
			
				for (String todo_array : todoid) {
					
				String text = todo_array;
			
				String[] split_text = text.split("&split;");
			
				String todo_id = split_text[0];
			
				delete_todoid = delete_todoid + "," + todo_id;
			
				}
			%>
			
			<input type="hidden" name="todoid" value="<%=delete_todoid%>"> 
			
			<br> <br> <br> 
			
			<input type="hidden" name="id" value="<%=userid%>"> 
			
			<input type="hidden" name="password" value="<%=userpassword%>">
			
			<input type="hidden" name="mode" value="delete_Todo_database"> 
			
			<input type="submit" value="削除">
		
		</div>
	</form>
	
	<form action="Mode" method="POST">
		<div align="left">
		
			<input type="hidden" name="id" value="<%=userid%>"> 
			
			<input type="hidden" name="password" value="<%=userpassword%>"> 
			
			<input type="hidden" name="mode" value="user_verification"> 
			
			<input type="submit" value="キャンセル">
		
		</div>
	</form>
</body>
</html>