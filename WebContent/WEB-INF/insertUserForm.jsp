<%@ page info="게시판 수정하기" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertUserForm.jsp</title>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>

<BODY>





	<form class="form-compact" method="post" action="insert">
		<div class="row paddingBottom20">
			<div class="container col-4">
				<div class="row">
					<h6 class="text-center col-12 mb-0">회원가입</h6>
					<sub class="text-right text-muted col-12"><a href="#"
						tabindex="-1"><i class="far fa-edit"></i></a></sub>
				</div>
				<div class="dropdown-divider mb-3"></div>
				<div class="form-group row">
					<label for="firstName" class="col-4 col-form-label-sm text-right">아이디:</label>
					<div class="col-8">
						<div class="input-group">
							<input id="firstName" name="uId" type="text"
								class="form-control form-control-sm">
						</div>
					</div>
				</div>
				<div class="form-group row align-items-center">
					<label for="lastName" class="col-4 col-form-label-sm text-right">비밀번호</label>
					<div class="col-8">
						<div class="input-group">
							<input id="lastName" name="uPw" type="password"
								class="form-control form-control-sm">
						</div>
					</div>
				</div>

				<div class="form-group row align-items-center">
					<label for="emailAddress"
						class="col-4 col-form-label-sm text-right">이메일:</label>
					<div class="col-8">
						<div class="input-group">
							<input id="emailAddress" name="uMail" type="email"
								class="form-control form-control-sm extendable">
						</div>
					</div>
				</div>

				<div class="form-group row align-items-center">
					<label for="emailAddress"
						class="col-4 col-form-label-sm text-right">이름:</label>
					<div class="col-8">
						<div class="input-group">
							<input id="office" name="uName" type="text"
								class="form-control form-control-sm">
						</div>
					</div>
				</div>
				<div class="form-group row align-items-center">
					<label for="emailAddress"
						class="col-4 col-form-label-sm text-right">전화번호:</label>
					<div class="col-8">
						<div class="input-group">
							<input id="cell" name="uPhone" type="text"
								class="form-control form-control-sm">
						</div>
					</div>
				</div>
				<div align="center">
					<b><span style="font-size: 9pt;"> <input type="submit"
							value="가입하기"> <input type="reset" value="되돌리기">
					</span></b>
				</div>
			</div>
		</div>
	</form>








<!-- 	<form name="insertUserForm" method="post" action="insert">



		<table align="center" cellpadding="1" cellspacing="1" width="600"
			border="1">
			<tr>
				<td width="1220" height="7" colspan="2" bgcolor="#336699">
					<p align="center">
						<font color="white" size="3"><b> 회원가입</b></font>
					</p>
				</td>
			</tr>
			<tr>
				<td width="150" height="10">
					<p align="right">
						<b><span style="font-size: 9pt;">아이디</span></b>
					</p>
				</td>
				<td width="450" height="10"><input type=text name="uId"
					size="30"></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">비밀번호</span></b>
					</p>
				</td>
				<td width="450" height="20"><input type=password name="uPw"
					size="30"></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">이름</span></b>
					</p>
				</td>
				<td width="450" height="20"><input type=text name="uName"
					size="30"></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">메일</span></b>
					</p>
				</td>
				<td width="450" height="20"><input type=text name="uMail"
					size="30"></td>
			</tr>
			<tr>
				<td width="150" height="20">
					<p align="right">
						<b><span style="font-size: 9pt;">전화번호</span></b>
					</p>
				</td>
				<td width="450" height="20"><input type=text name="uPhone"
					size="30"></td>
			</tr>

			<tr>
				<td width="450" height="20" colspan="2" align="center"><b><span
						style="font-size: 9pt;"> <input type="submit" value="가입하기">
							<input type="reset" value="되돌리기">
					</span></b></td>
			</tr>
		</table>
	</form> -->
	<hr>
	<div align=right>
		<span style="font-size: 9pt;">&lt;<a href="${pageContext.request.contextPath}/index.html">리스트로
				돌아가기</a>&gt;<a href="${pageContext.request.contextPath}/login.html">로그인</a></span>
	</div>
</BODY>
</HTML>