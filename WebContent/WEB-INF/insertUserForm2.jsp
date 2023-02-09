<%@ page info="게시판 수정하기" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertUserForm.jsp</title>
</head>

<BODY>

<form action="login" method="post">
		id : <input type="text" name="id" value="1"><br>
		pw : <input type="password" name="pw" value="1"><br>
		<input type="submit" value="로그인">
	</form>


<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="index.html">리스트로 돌아가기</a>&gt;<a href="${pageContext.request.contextPath}/login.html">로그인</a></span></div>
</BODY>
</HTML>