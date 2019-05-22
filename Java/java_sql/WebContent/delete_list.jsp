<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="info.searchman.lesson.java_mysql.ShainBeans"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<html>
<head>
<title>Delete_list</title>
</head>
<body>
	<%
		ShainBeans shain = (ShainBeans) request.getAttribute("shain");
		String[] todo = request.getParameterValues("todoid");
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
	<h1>削除画面</h1>
	<hr>
	<form action="Edit" method="POST">
		<div align="left">
			<%
				for (int counter = 0; counter < todo.length; counter++) {
			%>
			<table border="0">
				<tr>
					<td>
						<%
						String text = todo[counter];
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
			
				for (int counter = 0; counter < todo.length; counter++) {
			
				String text = todo[counter];
			
				String[] split_text = text.split("&split;");
			
				String todo_id = split_text[0];
			
				delete_todoid = delete_todoid + "," + todo_id;
			
				}
			%>
			
			<input type="hidden" name="todoid" value="<%=delete_todoid%>"> 
			
			<br> <br> <br> 
			
			<input type="hidden" name="id" value="<%=userid%>"> 
			
			<input type="hidden" name="pw" value="<%=userpw%>">
			
			<input type="hidden" name="mode" value="deletelist_database"> 
			
			<input type="submit" value="削除">
		
		</div>
	</form>
	
	<form action="Edit" method="POST">
		<div align="left">
		
			<input type="hidden" name="id" value="<%=userid%>"> 
			
			<input type="hidden" name="pw" value="<%=userpw%>"> 
			
			<input type="hidden" name="mode" value="verification"> 
			
			<input type="submit" value="キャンセル">
		
		</div>
	</form>
</body>
</html>