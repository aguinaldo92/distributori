<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Errore generico</title>

<link href="/distributori/vendor/css/bootstrap.css" rel="stylesheet" />
<link href="/distributori/vendor/css/font-awesome.css" rel="stylesheet" />
<link href="/distributori/vendor/css/custom.css" rel="stylesheet" />

</head>
<body style="background-color: #c90000;">
	<p align="center">
		<img src="/distributori/images/general_error.png" align="middle"> <br> Ci scusiamo, ma qualcosa non � andato come avrebbe dovuto!
	</p>
	<div class="row">
		<div class="col-md-12">

			<p align="center">
				<s:a href="javascript:history.back()" class="btn btn-danger  square-btn-adjust">Indietro</s:a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="/distributori" class="btn btn-primary square-btn-adjust">Torna alla HOMEPAGE</a>
			</p>
		</div>
	</div>
</body>
</html>