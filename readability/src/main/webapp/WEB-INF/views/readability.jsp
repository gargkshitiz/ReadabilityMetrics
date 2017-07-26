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
		<p><a role="button" href="https://en.wikipedia.org/wiki/Gunning-Fog_Index" target="_blank">Gunning Fog Index score interpretation</a></p>
		<p><a role="button" href="https://en.wikipedia.org/wiki/Flesch-Kincaid_Readability_Test" target="_blank">Flesch Kincaid Readability score interpretation</a></p>
		<form:form method="POST" modelAttribute="apiInput" action="${contextPath}/demo/readability">
			<h4 class="form-heading">Enter Text</h4>
			<spring:bind path="text">
				<textarea id="inputText" name="text" cols="150" rows="10">${apiInput.text}</textarea>
			</spring:bind>
			<br /> 
			<button id="analyzeText" class="btn btn-lg btn-primary" type="submit">Analyze text</button>
		</form:form>
		<c:if test="${not empty scores}">
			<h4>Readability metrics</h4>
			<textarea id="scoresTextArea" name="text" cols="50" rows="8">${scores}</textarea>
			<c:if test="${not empty scores.complexWords}">
				<h4>Complex words</h4>
				<textarea name="text" id="complexWordsTextArea" cols="150" rows="2">${scores.complexWords}</textarea>
				<button class="btn btn-lg btn-primary" onclick="learnWord()">Teach me not to interpret a word as complex</button>
				<input type="text" id="complexWord" placeholder="Enter word here" />
			</c:if>
		</c:if>
	</div>
	<script	src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function learnWord(){
		var url = "${pageContext.request.getScheme()}://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.getContextPath()}/learncomplexword/";
		var complexWord = $('#complexWord').val();
		var inputText = $('#inputText').val();
		$.ajax({
			contentType: 'application/json',
			url : url+complexWord,
			type : "POST",
			data: '{"text":"'+inputText+'"}',
			processData: false,
			success : function(scores) {
				// This is to ensure that the whole HTML is not pulled again
				//$('#complexWordsTextArea').val(scores.complexWords);
				//$('#scoresTextArea').val(scores.gunningFogScore+"\n"+scores.fleschReadingEase);
				
				//This will make a fresh call for the whole HTML
				$('#analyzeText').click();
			}
		})
		return false;
	}
	</script>
</body>
</html>