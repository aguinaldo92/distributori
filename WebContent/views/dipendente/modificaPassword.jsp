<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h2>AGGIORNAMENTO DIPENDENTE</h2>
					<h5>
						<s:if test="hasActionErrors()">
							<div class="form-group" style="color: red;">
								<label><s:actionerror /></label>
							</div>
						</s:if>
					</h5>
				</div>
			</div>
			<!-- /. ROW  -->
			<hr />
			<s:form name="update" action="UpdatePassword">
				<div class="form-group">
					<label>Modifica Password</label>
				
				
				<div class='form-group input-group input-group-lg <s:if test="hasActionErrors()">has-error</s:if>'>
					<span class="input-group-addon">Nuova Password</span>
					<s:textfield  cssClass="form-control" cssStyle="width: 60%;" type="password" name="newPassword" />
				</div>
				<s:if test="hasFieldErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="newPassword" /></label>
					</div>
				</s:if>
				</div>
				<div class="form-group">
				<div class='form-group input-group input-group-lg <s:if test="hasActionErrors()">has-error</s:if>'>
					<span class="input-group-addon">Ripeti Password</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" type="password" name="confirmPassword" />
				</div>
				<s:if test="hasFieldErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="confirmPassword" /></label>
					</div>
				</s:if>
				<br>
				<br>
				<p align="center">
					<s:a action="ListDipendenti" class="btn btn-danger btn-lg">Annulla</s:a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:submit cssClass="btn btn-success btn-lg" name="edit_button" value="Modifica" />
				</p>
				</div>
			</s:form>


		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->