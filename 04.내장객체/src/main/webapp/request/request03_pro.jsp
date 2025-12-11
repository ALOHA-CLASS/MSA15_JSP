<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>모든 파라미터 가져오기</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 모든 파라미터 가져오기
		Enumeration<String> params = request.getParameterNames();
		while( params.hasMoreElements() ) {
			String name = params.nextElement();				// 파라미터 이름
			String value = request.getParameter(name);		// 값
			out.println("<h3>" + name + " : " + value + "</h3>");	
		}
	%>
</body>
</html>










