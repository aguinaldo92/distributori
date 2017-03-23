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
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<h5>
								<label><s:actionerror /></label>
							</h5>
						</div>
					</s:if>
					<s:if test="hasActionMessages()">
						<label><s:actionmessage /></label>
					</s:if>
				</div>
			</div>
			<!-- /. ROW  -->
			<hr />

			<s:form name="update" namespace="/dipendente" action="UpdateDipendente">
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Badge</span>
					<s:textfield cssClass="form-control" cssStyle="width: 10%; background-color: #eee;" readonly="true" name="id" value="%{#attr.dipendente.id}" />
				</div>
				<br>
				<br>
				<br>
				<br>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Nome</span>
					<s:textfield cssClass="form-control" name="nome" value="%{#attr.dipendente.nome}" />
					<span class="input-group-addon">Cognome</span>
					<s:textfield cssClass="form-control" name="cognome" value="%{#attr.dipendente.cognome}" />
				</div>
				<s:if test="hasFieldErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="nome" /></label>
					</div>
				</s:if>
				<s:if test="hasFieldErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="cognome" /></label>
					</div>
				</s:if>
				<br>
				<br>
				<div class="form-group">
					<label>Contatti</label>
				</div>
				<div class=' form-group input-group input-group-lg'> 
					<span class="input-group-addon">Email</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" type="email" name="email" value="%{#attr.dipendente.email}" />
				</div>
				<s:if test="hasFieldErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="email" /></label>
					</div>
				</s:if>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Telefono</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="telefono" value="%{#attr.dipendente.telefono}" />
				</div>
				<s:if test="hasFieldErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="telefono" /></label>
					</div>
				</s:if>
				<br>
				<br>
				<div class="btn-group">
					<s:a namespace="/dipendente" action="dashboard" class="btn navbar-btn btn-danger btn-lg">Annulla</s:a>
					<s:submit cssClass="btn navbar-btn btn-success btn-lg col-lg-4" value="Modifica" />
					<s:a namespace="/dipendente" action="goToModificaPassword" class="btn navbar-btn btn-primary btn-lg col-lg-4">Modifica Password</s:a>
				</div>
			</s:form>


		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->