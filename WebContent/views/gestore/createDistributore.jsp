<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<s:action name="ListDipendenti" namespace="/gestore" />
<s:action name="ListCategorie" namespace="/" />

<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h2>INSERISCI NUOVO DISTRIBUTORE</h2>
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
			<s:form name="addDistributore" namespace="/gestore" action="CreateDistributore">
				<div class=' form-group input-group input-group-lg <s:if test="hasActionErrors()">has-error</s:if>'>
					<span class="input-group-addon">Via</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="via" placeholder="Es: Via Aldo Moro" />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="via" /></label>
						</div>
					</s:if>
				</div>
				<div class=' form-group input-group input-group-lg <s:if test="hasActionErrors()">has-error</s:if>'>
					<span class="input-group-addon">Civico</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="via" placeholder="Es: 177"  />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="civico" /></label>
						</div>
					</s:if>
				</div>
				<div class=' form-group input-group input-group-lg <s:if test="hasActionErrors()">has-error</s:if>'>
					<span class="input-group-addon">Città</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="citta" placeholder="Es: Milano" />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="citta" /></label>
						</div>
					</s:if>
				</div>
				<div class=' form-group input-group input-group-lg <s:if test="hasActionErrors()">has-error</s:if>'>
					<span class="input-group-addon">Provincia</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="via" placeholder="Es: MI"  />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="provincia" /></label>
						</div>
					</s:if>
				</div>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Posizione nell'Edificio</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="posizioneEdificio" placeholder="Es: Primo piano, atrio est" />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="posizioneEdificio" /></label>
						</div>
					</s:if>
				</div>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Numero Scaffali</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="numScaffali"   />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="numScaffali" /></label>
						</div>
					</s:if>
				</div>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Numero posti per scaffale</span>
					<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="numPosti"  />
					<s:if test="hasActionErrors()">
						<div class="form-group" style="color: red;">
							<label><s:fielderror fieldName="numPosti" /></label>
						</div>
					</s:if>
				</div>
				<div class=" form-group input-group input-group-lg">
					<span class="input-group-addon">Dipendente</span>
					<s:select cssStyle="width: 60%;" class="form-control" 
					 list="dipendenti" listKey="persona.id" listValue="%{persona.nome + ' ' + persona.cognome}"  />
					 
				</div>
				<div class=" form-group ">
					<span class="input-group">Categorie Erogabili</span>
					<s:checkboxlist  list="categorie" name="categorieErogabili" listKey="id" listValue="nome" />
				</div>
				<br>
				<br>

				<p align="center">
					<s:submit cssClass="btn btn-primary btn-lg" value="Inserisci" /> 

				</p>
			</s:form>


		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->