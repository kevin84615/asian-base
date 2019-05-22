<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
<title></title>
</head>
<body>
	<br>

	<%
		String error_message01 = (String) request.getAttribute("message01");
		String error_message02 = (String) request.getAttribute("message02");
	%>

	<br>
	<br>
	<h1><%=error_message01%></h1>
	<h1><%=error_message02%></h1>
	<br>
	<br>
	<h1><a href="./index.jsp">ログインページに戻る</a></h1>


</body>
</html>
