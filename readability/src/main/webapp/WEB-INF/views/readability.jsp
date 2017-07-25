<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Readability score - Demo</title>

<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">

</head>

<body>

	<div class="container">

		<form method="POST" action="${contextPath}/demo/readability">
			<h2 class="form-heading">Enter Text</h2>
			<div class="form-group">
				<input name="text" type="text" class="form-control" placeholder="text" autofocus="true" /> 
				<button class="btn btn-lg btn-primary btn-block" type="submit">Analyze text</button>
			</div>
		</form>

	</div>
	<!-- /container -->
	<script	src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>