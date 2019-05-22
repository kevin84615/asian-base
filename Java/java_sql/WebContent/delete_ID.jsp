<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="info.searchman.lesson.java_mysql.ShainBeans"%>

<html>
<body>
	<%
		ShainBeans shain = (ShainBeans) request.getAttribute("shain");
	%>
	<br>
	<br>
	<br>
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
	<form action="Edit" method="POST">
		<div align="center">
		
			ID:<%=userid%>
			<input required type="hidden" name="id" value="<%=userid%>">
		
			<br> <br>本当に削除しますか？
			<br>削除すると、ユーザーアカウントとtodoをすべて削除します。<br> <br> 
			
			<input type="hidden" name="mode" value="delete_id"> 
			
			<input type="submit" value="削除">
		
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