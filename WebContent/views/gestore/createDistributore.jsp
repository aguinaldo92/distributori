<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<%-- <s:action name="ListDipendenti" namespace="/gestore" /> --%>
<%-- <s:action name="ListCategorie" namespace="/" /> --%>

<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h2><s:if test="%{iDdistributore != 'null'}"> MODIFICA </s:if><s:else> INSERIMENTO NUOVO </s:else>DISTRIBUTORE</h2>
					<h5>
						<s:if test="hasFieldErrors()">
							<div class="form-group" style="color: red;">
								<label><s:fielderror /></label>
							</div>
						</s:if>
					</h5>

				</div>
			</div>
			<!-- /. ROW  -->
			<hr />
			<s:form name="addDistributore" namespace="/gestore" action="CreateDistributore">
				<div class=' form-group input-group input-group-lg <s:if test="hasActionErrors()">has-error</s:if>'>
					<span class="input-group-addon">Indirizzo</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="indirizzo" placeholder="Es: Via Aldo Moro 10 Milano, (MI) , Italia" value="%{distributore.indirizzo}" />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="indirizzo" /></label>
						</div>
					</s:if>
				</div>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Posizione nell'Edificio</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="posizioneEdificio" placeholder="Es: Primo piano, atrio est" value="%{distributore.posizioneEdificio}"  />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="posizioneEdificio" /></label>
						</div>
					</s:if>
				</div>
				<div class=" form-group input-group input-group-lg">
					<s:if test="%{#distributore.lat != null}">
						<div>ciao!</div>
						<iframe width="470" height="310" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?q=<s:property value="lat"/>,<s:property value="lon"/>&mrt=loc&t=m&z=17&hl=it&output=embed"></iframe>
					</s:if>
				</div>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Numero Scaffali</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="numScaffali" value="%{distributore.numScaffali}"  />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="numScaffali" /></label>
						</div>
					</s:if>
				</div>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Numero posti per scaffale</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="numPosti" value="%{distributore.numPosti}"  />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="numPosti" /></label>
						</div>
					</s:if>
				</div>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Dipendente</span>
					<s:select cssStyle="width: 60%;" class="form-control" 
					 list="dipendenti" listKey="persona.id" listValue="%{persona.nome + ' ' + persona.cognome}"  value="%{distributore.dipendente.personaId}"	 />
					 
<!-- 					 value="%{distributore.dipendente.persona.nome + ' ' + distributore.dipendente.persona.cognome} "  headerKey="-1" headerValue="Scegliere un dipendente"-->
				</div>
				<div class=" form-group ">
					<span class="input-group">Categorie Erogabili</span>
					<s:checkboxlist  list="categorie" name="categorieErogabili" listKey="id" listValue="nome" value="%{categorieFornite}" />
				</div>
				<br>
				<br>

				<p align="center">
					<s:if test="%{iDdistributore != 'null'}"> <s:submit cssClass="btn btn-success btn-lg" value="Modifica" /> </s:if><s:else><s:submit cssClass="btn btn-primary btn-lg" value="Inserisci" /> </s:else>

				</p>
			</s:form>


		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->