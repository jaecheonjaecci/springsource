<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>doB</h3>
	<h4>age : <%=request.getParameter("age") %></h4>
	<h4>name : ${name} <%--request.getParameter or session.setAttribute를 사용해야 이렇게 가져올 수 있음 --%> </h4>
	<h4><a href="/doC">이동</a></h4> <%--로컬호스트는 고정이고 /doC에 가는 것 --%>
	
</body>
</html>