<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="https://www.flaticon.com/free-icon/login_1000997">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/css/mystyle.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/css/min/bootstrap-datetimepicker.css" />">
</head>
<body>
	<div class="content" id="content">
		<!-- Page content -->
		<!-- <input id="delete" type="button" value="Delete" class="delete" /> -->
		<div id="login form-group" class="container">
			<div class="heading">Login</div>
			<font color="red">${errorMessage}</font>
			User Id : <input type="text" name="userId" id="email" class="input" /><br />
			<!-- Agent Id : <input type="text" name="agentId" id="agentId" class="input" /><br />
			API Key : <input type="text" name="apiKey" id="apiKey" class="input" /><br /> -->
			<input id="submit" type="submit" value="Login" class="input" />
		</div>
		<div class="challenge form-group" id="challenge" style="display: none;">
		</div>
		<div class="result-show">
			<div id="show">
			</div>
		</div>
	</div>
</body>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/theme/js/login.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/theme/js/min/moment.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/theme/js/min/bootstrap-datetimepicker.min.js" />"></script>
</html>