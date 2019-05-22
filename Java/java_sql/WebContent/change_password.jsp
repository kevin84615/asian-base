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
		
			 ID:<%=userid%>（変更不可） <input required type="hidden" name="id"
				value="<%=userid%>"> &emsp;&emsp; 
				
				PASSWORD: <input required type="password" name="password" maxlength='20' id="password"
				pattern="^([0-9A-Za-z]|[\\\\\\(\\)/\\*&\$,\\.@=%\\+;:' \\->]|)+$"
				value="<%=userpassword%>">
		
		</div>
		
		<br>
		
		<div align="center">
		
			PASSWORD確認:<input required type="password" name="password_two" maxlength='20' id="password_confirm" oninput="check(this)"
				pattern="^([0-9A-Za-z]|[\\\\\\(\\)/\\*&\$,\\.@=%\\+;:' \\->]|)+$">
		
		</div>
		
		<script language='javascript' type='text/javascript'>
    		function check(input) {
        		if (input.value != document.getElementById('password').value) {
            		input.setCustomValidity('パスワードの確認が正しくありません');
        		} else {
            		input.setCustomValidity('');
        		}
    		}
		</script>
		
		<br> <br>
		<div align="center">
		
			<input type="hidden" name="mode" value="change_password_save"> 
			
			<input type="submit" value="更新">
		
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