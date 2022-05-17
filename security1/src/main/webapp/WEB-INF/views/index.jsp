<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메인 페이지</h1>
	<%-- isAuthenticated() : 인증된 사용자의 경우 true --%>
	<sec:authorize access="!isAuthenticated()"><!-- 인증이 되지 않은 경우 보여주기 -->
		<p>
		 	<a href="/login">로그인</a>
		</p>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()"><!-- 인증이 된 경우 보여주기 -->
		<p>
			 <a href="/user-page">유저 페이지</a>
		</p>
	</sec:authorize>
	<!-- isAuthenticated() : 인증이 된 경우 보여주기 -->
	<sec:authorize access="isAuthenticated()">
		<!-- 접근권한을 부여하고, 접근 권한이 없는 경우 버튼 안보이게 처리하기 -->
		<sec:authentication property="principal" var="info"/>
		<c:if test="${info.username=='admin'}">
			<p>
				 <a href="/admin-page">관리자 페이지</a>
			</p>
		</c:if>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()"><!-- 인증이 된 경우 보여주기 -->
		<div>
			<form action="/logout" method="post">
				<button>로그아웃</button>
				    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
	</sec:authorize>
</body>
</html>








