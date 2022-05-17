<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">code</th>
      <th scope="col">title</th>
      <th scope="col">writer</th>
      <th scope="col">price</th>
    </tr>
  </thead>
  <tbody>
  <!-- 반복 시킬 부분을 forEach안에 넣고 dto를 불러와서 값들을 넣어줌 -->
  	<c:forEach var="dto" items="${list}">  	
	    <tr>
	      <th scope="row">${dto.code}</th>
	      <td><a href="/book/read?code=${dto.code}">${dto.title}</a></td>
	      <td>${dto.writer}</td>
	      <td>${dto.price}</td>
	    </tr>
  	</c:forEach>
  </tbody>
</table>

<%@ include file="../includes/footer.jsp"%>
