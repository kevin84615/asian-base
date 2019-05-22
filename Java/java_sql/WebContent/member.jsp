<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page import="java.util.*"%>
<%@page import="user_todo.UserBeans"%> 
<%@page import="user_todo.TodoBeans"%> 
<%@page import="user_todo.UserServlet"%> 
<html>
<head>
<script type="text/javascript">
	function changeUnderline(todo_id) {
		var btn_obj = document.getElementById("btn_" + todo_id);
		var text_obj = document.getElementById("text_" + todo_id);
		
		if (text_obj.style.textDecoration == "line-through") {
			text_obj.style.textDecoration = "none";
			btn_obj.value = "完了";
		} else {
			text_obj.style.textDecoration = "line-through";
			btn_obj.value = "未完了";
		}
	}
</script>
<title>Member</title>
</head>
<body>
	<%
		String userid = (String) request.getAttribute("id");
		String userpassword = (String) request.getAttribute("password");
		String userid_utf8 = URLEncoder.encode(userid, "UTF-8");
		String userpassword_utf8 = URLEncoder.encode(userpassword, "UTF-8");
		ArrayList<ArrayList<String>> total = (ArrayList<ArrayList<String>>)request.getAttribute("total");
	%>		
	<h1>
		WELCOME!&emsp;
		<%=userid%></h1>
	<hr>
	<div align="right">
		<table border="0">
			<tr>
				<td><form action="Mode" method="POST">
						<input type="hidden" name="mode" value="delete_User"> <input
							type="hidden" name="id" value="<%=userid%>"> <input
							type="hidden" name="password" value="<%=userpassword%>"> <input
							type="submit" value="アカウント削除">
					</form></td>
				<td><form action="Mode" method="POST">
						<input type="hidden" name="mode" value="change_password"> <input
							type="hidden" name="id" value="<%=userid%>"> <input
							type="hidden" name="password" value="<%=userpassword%>"> <input
							type="submit" value="パスワード変更">
					</form></td>
			</tr>
		</table>
	</div>
	<h2>Todo一覧</h2>
	<form action="Mode" method="POST">
		<div align="left">
			<input type="hidden" name="mode" value="delete_Todo">
			<input type="hidden" name="id" value="<%=userid%>">
			<input type="hidden" name="password" value="<%=userpassword%>">	
			<%	
			int counter = 0;
			for(String abc :total.get(0)) {		
			%>
			<table border="0">
				<tr>
					<td>
					
						<p id="text_<%=total.get(2).get(counter)%>">
							<input type="checkbox" name="todoid"
								value="<%=total.get(2).get(counter)%>&split;<%=total.get(0).get(counter)%>">
							<%
								String source = total.get(0).get(counter);
								String result = URLEncoder.encode(source, "UTF-8");
							%>
							<a
								href="Mode?&mode=edit_Todo&todoid=<%=total.get(2).get(counter)%>&data=<%=result%>&id=<%=userid_utf8%>&password=<%=userpassword_utf8%>">
						
 								<%=total.get(0).get(counter)%>

							</a>
							&emsp;&nbsp;<%=total.get(1).get(counter)%>&emsp;&nbsp;
							 <input type="button" id="btn_<%=total.get(2).get(counter)%>" value="完了"
								onclick="changeUnderline('<%=total.get(2).get(counter)%>');" />
						</p>
					</td>
				</tr>
			</table>
			<%
			counter++;}
			%>
		</div>
		<div align="right">
			<input type="submit" value="削除">
		</div>
	</form>
	<form action="Mode" method="POST">
		<div align="right">
			<input type="hidden" name="mode" value="add_Todo"> <input
				type="hidden" name="id" value="<%=userid%>"> <input
				type="hidden" name="password" value="<%=userpassword%>"> <input
				type="submit" value="追加">
		</div>
	</form>
	<br>
	<br>
	<form action="Mode" method="POST">
		<input type="hidden" name="mode" value="logout"> <input
			type="submit" value="ログアウト">
	</form>
</body>
</html>
