<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<style>

	body {
		/* 	background-image: url("/resources/image/icon/ground.jpg");
            background-repeat: no-repeat;
            background-size: 100% 100%; */
		/*background: url("/resources/image/icon/backGround.jpg") no-repeat center center fixed;*/
		/*background: url("resources/image/icon/ground.png") no-repeat center center fixed;*/
		/*-webkit-background-size: cover;*/
		/*-moz-background-size: cover;*/
		/*-o-background-size: cover;*/
		/*background-size: cover;*/


		/*background-image: url("resources/image/icon/ground.png");*/
		/*background-size: 100% 100%;*/
		/*width: 500px;*/
		/*height: 300px;*/
		/*padding-top: 10px;*/
		/* margin: 0 50%; */
		/*text-align: center;*/



	}
	input[type=text] {
		background: url("resources/image/icon/loginid.png");
		background-repeat : no-repeat;
		border: 2px solid #cccccc !important;
		border-radius: 5px;
		background-color:rgba(0, 0, 0, 0);
		width : 365px;
		height : 40px;
		padding-left : 35px;
		margin-bottom : 15px;
		color : #676a6c;
		display: inline-block;
	}
	input[type=password] {
		background: url("resources/image/icon/loginpwd.png");
		background-repeat : no-repeat;
		border: 2px solid #cccccc !important;
		border-radius: 5px;
		background-color:rgba(0, 0, 0, 0);
		width : 365px;
		height : 40px;
		padding-left : 35px;
		margin-bottom : 40px;
		color : #676a6c;
		display: inline-block;
	}
	.loginMain{
		padding-top : 150px;
		width : 500px;
		text-align:center;
		margin-right:500px;
	}
	.loginSub{
		background-image: url("resources/image/icon/loginboxground.png");
		background-size: 100% 100%;
		width: 500px;
		height: 300px;
		padding-top: 10px;
		/* margin: 0 50%; */
		text-align: center;
	}
	.loginSub p {
		margin-top: 70px;
	}
	.text-noti{
		color : red;
		margin-top : -30px !important;
		margin-bottom: 20px !important;
	}
</style>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Login</title>
	
	<!-- inspinia css -->
    <link href="/resources/inspinia/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="/resources/inspinia/css/animate.css" rel="stylesheet">
    <link href="/resources/inspinia/css/style.css" rel="stylesheet">   
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/vue/dist\vue.js"></script>


</head>

	<script>

	$( document ).ready(function() {
		alert();
	var app = new Vue({
		  el: '#app',
		  data: {
		    message: '안녕하세요 Vue!'
		  }
		})
	});
	</script>


<body class="white-bg">

			<div id="app">
  {{ message }}
</div>


</body>

</html>

