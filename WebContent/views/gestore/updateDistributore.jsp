<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<s:action name="ListDipendenti" namespace="/gestore" rethrowException="true" />
<s:action name="ListCategorie" namespace="/" rethrowException="true" />
<s:action name="ListStati" namespace="/" rethrowException="true" />

<div id="wrapper">
	<s:actionmessage />

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h2>MODIFICA DISTRIBUTORE</h2>
					<h5>
						<s:if test="hasActionErrors()">
							<div class="form-group" style="color: red;">
								<label><s:actionerror /></label> <label><s:actionmessage /></label>
							</div>
						</s:if>
					</h5>

				</div>
			</div>
			<!-- /. ROW  -->
			<hr />
			<s:form name="updateDistributoreForm" namespace="/gestore" action="UpdateDistributore">

				<s:hidden name="idDistributore" value="%{idDistributore}" />

				<div class=' form-group input-group input-group-lg'>
					<span class="input-group-addon">Via</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="distributoreModel.via" placeholder="Es: Via Aldo Moro" value="%{#attr.distributoreModel.via}" />

				</div>
				<s:if test="hasActionErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="distributoreModel.via" /></label>
					</div>
				</s:if>
				<div class=' form-group input-group input-group-lg '>
					<span class="input-group-addon">Civico</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="distributoreModel.civico" placeholder="Es: 177" value="%{#attr.distributoreModel.civico}" />

				</div>
				<s:if test="hasActionErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="distributoreModel.civico" /></label>
					</div>
				</s:if>
				<div class=' form-group input-group input-group-lg '>
					<span class="input-group-addon">Città</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="distributoreModel.citta" placeholder="Es: Milano" value="%{#attr.distributoreModel.citta}" />

				</div>
				<s:if test="hasActionErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="distributoreModel.citta" /></label>
					</div>
				</s:if>
				<div class=' form-group input-group input-group-lg '>
					<span class="input-group-addon">Provincia</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="distributoreModel.provincia" placeholder="Es: MI" value="%{#attr.distributoreModel.provincia}" />

				</div>
				<s:if test="hasActionErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="distributoreModel.provincia" /></label>
					</div>
				</s:if>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Posizione nell'Edificio</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="distributoreModel.posizioneEdificio" placeholder="Es: Primo piano, atrio est" value="%{#attr.distributoreModel.posizioneEdificio}" />

				</div>
				<s:if test="hasActionErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="distributoreModel.posizioneEdificio" /></label>
					</div>
				</s:if>
				<div class=" form-group input-group input-group-lg">
					<s:if test="%{lat != null}">
						<div>ciao!</div>
						<iframe width="470" height="310" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?q=<s:property value="lat"/>,<s:property value="lon"/>&mrt=loc&t=m&z=17&hl=it&output=embed"></iframe>
					</s:if>
				</div>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Numero Scaffali</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="distributoreModel.numScaffali" value="%{#attr.distributoreModel.numScaffali}" readonly="true" />

				</div>
				<s:if test="hasActionErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="distributoreModel.numScaffali" /></label>
					</div>
				</s:if>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Numero posti per scaffale</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="distributoreModel.numPosti" value="%{#attr.distributoreModel.numPosti}" readonly="true" />

				</div>
				<s:if test="hasActionErrors()">
					<div class="form-group" style="color: red;">
						<label><s:fielderror fieldName="distributoreModel.numPosti" /></label>
					</div>
				</s:if>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Stato funzionamento</span>
					<s:select cssStyle="width: 60%;" class="form-control" list="#attr.stati" name="distributoreModel.stato" value="%{#attr.distributoreModel.stato}" />
				</div>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Dipendente</span>
					<s:select cssStyle="width: 60%;" class="form-control" list="#attr.dipendenti" name="distributoreModel.idDipendente" listKey="persona.id" listValue="%{persona.nome + ' ' + persona.cognome}" value="%{#attr.distributoreModel.idDipendente}" />
				</div>
				<div class=" form-group ">
					<span class="input-group">Categorie Erogabili</span>
					<s:checkboxlist list="#attr.categorie" name="distributoreModel.categorieFornite" listKey="id" listValue="nome" value="%{#attr.distributoreModel.categorieFornite}" disabled="true" />
				</div>
				<br>
				<br>

				<p align="center">
					<s:submit cssClass="btn btn-success btn-lg" value="Modifica" />
				</p>
			</s:form>


		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->