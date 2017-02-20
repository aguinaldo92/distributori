<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Free Bootstrap Admin Template : Binary Admin</title>
<!-- BOOTSTRAP STYLES-->
<link href="../vendor/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="../vendor/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="../vendor/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Binary admin</a>
			</div>
			<s:if test="#session.persona != null">
				<div
					style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;">
					<s:property value="#session.persona.nome" /> <s:property value="#session.persona.cognome" />
					<s:a action="Logout" class="btn btn-danger square-btn-adjust">Logout</s:a>
				</div>
			</s:if>
		</nav>
	</div>