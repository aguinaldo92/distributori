<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="hasActionErrors()">
	<div style="color: red">
		<s:actionerror />
	</div>
</s:if>
<div class="wrapper">
<s:div class="container">
	<s:div class="account">
			<h2 class="account-in">Account</h2>
			<s:form action="Login">
			<s:textfield name="username" label="Username"/>
			<s:password name="password" label="Password"/>
			<s:submit value="Invia" />
			</s:form>
			<s:a action="registrationUser" cssClass="btn btn-primary" type="tiles" >Registrazione</s:a>
	</s:div>
</s:div>

<div class="push"></div>
</div>