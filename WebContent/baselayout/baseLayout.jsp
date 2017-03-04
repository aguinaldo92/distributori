<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

	<link href="/distributori/vendor/css/bootstrap.css" rel="stylesheet" />
	<link href="/distributori/vendor/css/font-awesome.css" rel="stylesheet" />
	<link href="/distributori/vendor/css/custom.css" rel="stylesheet" />
	<!--  Js per i pulsanti più e meno per le quantità -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.js"></script>
	<script type="text/javascript" src="/distributori/vendor/js/bootstrap.js"></script>
	<script type="text/javascript" src="/distributori/vendor/js/bootstrap-spinner/bootstrap-spinner.js"></script> 
	<script type="text/javascript" src="/distributori/vendor/js/bootstrap-spinner/mousehold.js"></script>
	<!-- GOOGLE FONTS-->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META http-equiv="Pragma" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="-1">
	<meta http-equiv="cache-control" content="no-cache" />
	
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