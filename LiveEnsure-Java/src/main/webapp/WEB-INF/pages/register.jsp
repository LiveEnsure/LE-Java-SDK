<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="https://www.flaticon.com/free-icon/login_1000997">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/css/mystyle.css" />">
</head>
<body>
	<div class="content">
		<!-- Page content -->
		<div id="login" class="container">
			<div class="heading">Registration</div>
			<font color="red">${errorMessage}</font>
			First Name : <input type="text" name="First Name" id="firstName" class="input" /><br />
			Last Name : <input type="text" name="Last Name" id="lastName" class="input" /><br />
			Email Id : <input type="text" name="Email Id" id="email" class="input" /> <br />
			<input id="register" type="submit" value="Register" class="input" />
		</div>
		<div class="result-show">
			<div id="show">
			</div>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="<c:url value="/resources/theme/js/register.js" />"></script>
</html>