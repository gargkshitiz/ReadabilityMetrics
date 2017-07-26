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
		<form:form method="POST" modelAttribute="apiInput" action="${contextPath}/demo/readability">
			<h2 class="form-heading">Enter Text</h2>
			<spring:bind path="text">
				<textarea name="text" cols="150" rows="10">${apiInput.text}</textarea>
			</spring:bind>
			<br /> 
			<button class="btn btn-lg btn-primary" type="submit">Analyze text</button>
		</form:form>
		<h2>Readability metrics</h2>
		<textarea name="text" cols="50" rows="10">${scores}</textarea>
		<c:if test="${not empty scores.complexWords}">
			<h3>Complex words</h3>
			<textarea name="text" cols="150" rows="2">${scores.complexWords}</textarea>
		</c:if>
		<button class="btn btn-lg btn-primary" onclick="learnWord()">Teach me not to interpret a word as complex</button>
		<input type="text" id="complexWord" placeholder="Enter word here" />
		<span id="successMsg" hidden="true">Success. Please analyze the text again. This word won't be considered complex from now on</span>
	</div>
	<script	src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function learnWord(){
		var url = "${pageContext.request.getScheme()}://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.getContextPath()}/learncomplexword/";
		var complexWord = $('#complexWord').val();
		$.ajax({
			url : url+complexWord,
			type : "POST",
			success : function() {
				$('#complexWord').val("");
				$('#successMsg').show();
			}
		})
		return false;
	}
	</script>
</body>
</html>