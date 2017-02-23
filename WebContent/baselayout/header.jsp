<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

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
				
				<s:a action="index" class="navbar-brand" cssStyle="font-size: 26px;">WiFi Drink&Snacks</s:a> 
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