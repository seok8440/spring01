<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>회원목록</h2>
<!-- WEB-INF밑에 view가 있어서 Controller를 경유하지않으면 이동불가능(보안문제때문에) -->
<input type="button" value="회원등록" onclick="location.href='${path}/member/write.do'"> 
<table border="1" width="700px">
	<tr>
		<td>아이디</td>
		<td>네임</td>
		<td>이메일</td>
		<td>가입일자</td>
	</tr>
<c:forEach var="row" items="${list}"> <!-- MemberController에서 Model객체에 넣어줬던 list -->
	<tr>
		<td>${row.userid}</td>
		<td><a href="${path}/member/view.do?userid=${row.userid}">${row.name}</a></td>
		<td>${row.email}</td>
		<!-- value를 pattern안에 처럼 표현하라는 뜻 -->
		<td><fmt:formatDate value="${row.join_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td> 
	</tr>
</c:forEach>
</table>
</body>
</html>