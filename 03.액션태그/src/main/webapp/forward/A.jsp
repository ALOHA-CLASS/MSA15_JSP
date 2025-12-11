<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>액션 태그 - forward - A 화면</title>
</head>
<body>
	<h1>forward A화면</h1>
	<jsp:forward page="B.jsp">
		<jsp:param name="name" value="김조은"/>
		<jsp:param name="age" value="20"/>
	</jsp:forward>
	<p>-----------------</p>
</body>
</html>




