<%@ page info="게시판 수정하기" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertUserForm.jsp</title>
</head>

<BODY>


<form name="insertUserForm" method="post" action="insert">


    
	<table align="center" cellpadding="1" cellspacing="1" width="600" border="1">
    <tr>
        <td width="1220" height="7" colspan="2" bgcolor="#336699">
            <p align="center"><font color="white" size="3"><b>
            회원가입</b></font></p>
        </td>
    </tr>
    <tr>
        <td width="150" height="10">
            <p align="right"><b><span style="font-size:9pt;">아이디</span></b></p>
        </td>
        <td width="450" height="10">
		<input type=text name="uId" size="30"></td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">비밀번호</span></b></p>
        </td>
        <td width="450" height="20" >
		<input type=password name="uPw" size="30"></td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">이름</span></b></p>
        </td>
        <td width="450" height="20" >
		<input type=text name="uName" size="30"></td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">메일</span></b></p>
        </td>
        <td width="450" height="20" >
		<input type=text name="uMail" size="30"></td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">전화번호</span></b></p>
        </td>
        <td width="450" height="20" >
		<input type=text name="uPhone" size="30"></td>
    </tr>
    
    <tr>
        <td width="450" height="20" colspan="2" align="center">
        	<b><span style="font-size:9pt;">
				<input type="submit" value="가입하기"> 
				<input type="reset" value="되돌리기">
			</span></b>
		</td>
    </tr>
</table>
</form>
<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="index.html">리스트로 돌아가기</a>&gt;<a href="${pageContext.request.contextPath}/login.html">로그인</a></span></div>
</BODY>
</HTML>