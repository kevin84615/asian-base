<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>CreatAccount</title>
<style>
h1 {
	color: Blue;
}
</style>
<link rel="stylesheet" type="text/css" href="/java_sql/css/index.css">
</head>
<body>
	<h1>NewAccount</h1>
	<hr>
	<br>
	<form action="Mode" method="POST" onsubmit="return confirmMessage();">
		<div align="center">
			<table border="0">
				<tr>
					<td>ID: <input required type="text" name="id" maxlength='20'
						placeholder="ID"
						pattern="^([0-9A-Za-z]|[\\\\\\(\\)/\\*&\$,\\.@=%\\+;:' \\->]|)+$" />&emsp;&nbsp;</td>
					<td>PASSWORD:</td>
					<td><input required type="password" name="password" maxlength='20' id="password" placeholder="PASSWORD"
				pattern="^([0-9A-Za-z]|[\\\\\\(\\)/\\*&\$,\\.@=%\\+;:' \\->]|)+$"></td>
				</tr>
				<tr>
					<td>&emsp;&nbsp;</td>
					<td>PASSWORD確認:</td>
					<td><input required type="password" name="password_two" maxlength='20' id="password_confirm" oninput="check(this)" placeholder="Confirm PASSWORD"
				pattern="^([0-9A-Za-z]|[\\\\\\(\\)/\\*&\$,\\.@=%\\+;:' \\->]|)+$"></td>
				</tr>
			</table>
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
		<div align="center">
			<br> <br> <br> <input type="hidden" name="mode"
				value="add_User"> <input type="submit" value="追加">&emsp;&emsp;<input
				type="button" onclick="location.href='index.jsp'" value="キャンセル">
		</div>
	</form>

</body>
</html>