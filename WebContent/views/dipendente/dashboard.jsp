<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Popolo la pagina -->
<s:action name="listDistributoriDipendente" namespace="/dipendente"/>

<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
		<div class="row">
		<div class="col-md-12">
			<h1>DASHBOARD DIPENDENTE</h1>
			
			<s:debug />
			
			
					
				</div>
			</div>
			<!-- /. ROW  -->
			<hr />

			<div class="panel panel-default">
				<div class="panel-body">
					<div class="table-responsive">
<!-- 						<p align="right"> -->
<%-- 							<s:a action="CreateDipendente" class="btn btn-success">Inserisci nuovo dipendente</s:a> --%>
<!-- 						</p> -->
<!-- 						<p></p> -->
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr align="center">
									<th>ID</th>
									<th>Prodotti da rifornire</th>
									<th>Indirizzo</th>
									<th>Posizione nell'edificio</th>
									<th>Categorie fornite dal distributore</th>
<!-- 									<th>AZIONI</th> -->
								</tr>
							</thead>
							<tbody>
<%-- 							<s:if test="listDistributoreModel.size() != 0"> Size != 0</s:if> --%>
								<s:iterator value="#attr.listDistributoreModel" var="listDistrModel" >
								<s:url var="dettaglioDistributore" action="DettaglioDistributore">
										<s:param name="idDistributore"><s:property value="id"/></s:param>
									</s:url>
<%-- 									<s:url var="EditDip" action="EditDipendente"> --%>
<%-- 										<s:param name="idDip"> --%>
<%-- 											<s:property value="personaId" /> --%>
<%-- 										</s:param> --%>
<%-- 									</s:url> --%>
<%-- 									<s:url var="DelDip" action="DeleteDipendente"> --%>
<%-- 										<s:param name="idDip"> --%>
<%-- 											<s:property value="personaId" /> --%>
<%-- 										</s:param> --%>
<%-- 									</s:url> --%>
									<tr class="odd gradeA">
										<td>
									<a href="${dettaglioDistributore}" class="compare-in" ><button class="btn btn-primary"><i class="fa fa-edit "></i>Dettaglio</button></a></td>
										<td><s:property value="stato" /></td>
										<td><ul><s:iterator value="#listDistrModel.prodottiForniti" var="prodotti"><li><s:property  value="prodotti"/></li></s:iterator></ul></td>
										<td><s:property value="indirizzo" /></td>
										<td><s:property value="posizioneEdificio" /></td>
										<td><s:property value="categorieFornite" /></td>
									</tr>

								</s:iterator>

							</tbody>
						</table>
					</div>

				</div>
			</div>

		</div>
		<!-- /. PAGE INNER  -->


	</div>
</div>