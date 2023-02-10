<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
<title>header.jsp</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
.w3-bar,h1,button {font-family: "Montserrat", sans-serif}
.fa-anchor,.fa-coffee {font-size:200px}
</style>
<div class="toptop" id="header">
<br><br><br><br>
</div>
<!-- Navbar -->
<div class="w3-top">
  <div class="w3-bar w3-blue w3-card w3-left-align w3-large">
    <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-red" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    <a href="couflix?command=home" class="w3-bar-item w3-button w3-padding-large w3-white"> <img src="images/couflix-logos.jpeg" onerror="this.src='images/nopic.jpg'" alt="Cinque Terre" width="50" height="50" padding="0"></a>
    <a href="couflix?command=movieAll" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">영화</a>
    <a href="couflix?command=tvAll" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">TV</a>
    
    <a href="search.jsp" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">검색</a>
    <a href="insertUserForm.jsp" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">회원가입</a>
    
	   <div class="w3-dropdown-hover w3-right-align">
	    <button class="w3-button w3-padding-15">
	      관리 <i class="fa fa-caret-down"></i>
	    </button>
	    <div class="w3-dropdown-content w3-card-4 w3-bar-block">
	      <a href="couflix?command=movieDB" class="w3-bar-item w3-button">영화관리</a>
	      <a href="couflix?command=tvDB" class="w3-bar-item w3-button">Tv관리</a>
	      <a href="couflix?command=userDB" class="w3-bar-item w3-button">회원관리</a>
	    </div>
	  </div>
  
  </div>





  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium w3-large">
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Home로고들어감</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">TV</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">영화</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">검색</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">스토어??</a>
  </div>
</div>

</head>
<body>



<body>



</body>
</html>