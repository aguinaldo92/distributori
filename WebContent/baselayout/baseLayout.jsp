<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

	<link href="http://localhost:8080/distributori/vendor/css/bootstrap.css"  type="text/css" rel="stylesheet" />
	<link href="http://localhost:8080/distributori/vendor/css/font-awesome.css" type="text/css" rel="stylesheet" />
	<link href="http://localhost:8080/distributori/vendor/css/custom.css" type="text/css" rel="stylesheet" />
	<!-- GOOGLE FONTS -->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	
</head>

<body>

	<div id="layout">
	
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		
		<div id="menu">
			<tiles:insertAttribute name="menu" />
		</div>
		
		<div id="body">
			<tiles:insertAttribute name="body" />
		</div>
		
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
		
	</div>
	
</body>

</html>