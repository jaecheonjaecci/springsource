<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인</h1>
<form action="" method="post">
	<div>
		<label for="userid">userid</label>
		<input type="text" name="userid" id="userid" />
	</div>
	<div>
	<!-- controller의 있는 변수명과 name이 같아야 함 -->
		<label for="password">password</label>
		<input type="password" name="password" id="password" />
	</div>
	<div>
		<label for="name">name</label>
		<input type="text" name="name" id="name" />
	</div>
	<div>
		<input type="submit"  value="전송"/>
	</div>
</form>
</body>
</html>