<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="${contextPath}/resources/css/reset.css" rel="stylesheet"
	type="text/css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<%-- <link href="${contextPath}/resources/css/style.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${contextPath}/resources/css/sanghee.css" rel="stylesheet"
	type="text/css">


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<title><tiles:insertAttribute name="title" /></title>
</head>
<body class="bg-light">
	<tiles:insertAttribute name="header" />

	<div class="wrap">
		<section id="contents"
			class="container bg-white p-5 rounded shadow-sm">
			<tiles:insertAttribute name="body" />
		</section>
	</div>

	<tiles:insertAttribute name="footer" />


</body>
</html>