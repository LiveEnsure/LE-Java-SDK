<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<title>Covid-19® Authentication</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="<c:url value="/resources/theme/images/icons/corona19.png" />"/>
<!--===============================================================================================-->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/fonts/Linearicons-Free-v1.0.0/icon-font.min.css" />">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/vendor/animate/animate.css" />">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/vendor/css-hamburgers/hamburgers.min.css" />">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/vendor/select2/select2.min.css" />">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/css/util.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/css/main.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/css/mystyle.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/css/min/bootstrap-datetimepicker.css" />">
<!--===============================================================================================-->
</head>
<body>


	<div class="container-contact100" id="content" style="background-image: url('/liveensure/resources/theme/images/bg-01.jpg');">
		<div class="wrap-contact100 contact100-form" id="login form-group">
				<span class="contact100-form-title">
					Login User
				</span>
				
				<div class="wrap-input100">
					<span class="label-input100">User ID *</span>
					<input class="input100" type="text" name="email" placeholder="Enter your user id" id="email" />
				</div>

				<div class="container-contact100-form-btn">
					<div class="wrap-contact100-form-btn">
						<div class="contact100-form-bgbtn"></div>
						<button class="contact100-form-btn" id="submit">
							Login
						</button>
					</div>
				</div>
				<span class="contact100-form-title" id="hostchallenge" style="display: none;">
					Host Challenge
				</span>
				<div class="container-contact100-form-btn" id="challenge" style="display: none;">
				</div>
				<div class="result-show">
					<div id="show">
					</div>
				</div>
		</div>

		<span class="contact100-more">
				© 2019-2020 Covid-19 Inc. Patented CN.
		</span>
	</div>

</body>
<!--===============================================================================================-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!--===============================================================================================-->
	<script src="<c:url value="/resources/theme/vendor/bootstrap/js/popper.js" />"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="<c:url value="/resources/theme/vendor/select2/select2.min.js" />"></script>
<!--===============================================================================================-->
	<script src="<c:url value="/resources/theme/js/login.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/theme/js/min/moment.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/theme/js/min/bootstrap-datetimepicker.min.js" />"></script>
</html>
