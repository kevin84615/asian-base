<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
<title>LOGIN</title>
<script type="text/javascript">
</script>
<style>
h1 {
	color: Blue;
}
</style>
<link rel="stylesheet" type="text/css" href="/java_sql/css/index.css">
</head>
<body>
	<h1>LOGIN</h1>
	<hr>
	<br>
	<form action="Mode" method="POST">
		<div align="center">
			<table border="0">
				<tr>
					<td>ID: <input required type="text" name="id" maxlength='20' placeholder="ID" 
						pattern="^([0-9A-Za-z]|[\\\\\\(\\)/\\*&\$,\\.@=%\\+;:' \\->])+$"/>&emsp;&nbsp;
					</td>
					
					<td>PASSWORD:</td>
					<td>
					<input required type="password" name="password" maxlength='20'  placeholder="PASSWORD"
					pattern="^([0-9A-Za-z]|[\\\\\\(\\)/\\*&\$,\\.@=%\\+;:' \\->])+$" /></td>
				</tr>
			</table>
			<br><br><br>
			
			<input type="button" onclick="location.href='new_ID.jsp'"value="新規追加"> 
			
			&emsp;&emsp;&emsp;&emsp; 
				
			<input type="hidden" name="mode" value="user_verification"> 
			
			<input type="submit" value="ログイン">&nbsp;
			
		</div>
	</form>
	<br>
	<br>


</body>
</html>