<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Popolo la pagina -->
<s:action name="listDistributoriDipendente" namespace="/dipendente"/>

<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
			<h1>DASHBOARD DIPENDENTE</h1>
			<s:a action="listaInterventi" var="listaInterventiAction">listaInterventi</s:a>
			
			<div class="row">
				<div class="col-md-12">
					<h2>GESTIONE DIPENDENTI</h2>
					
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
									<th>Stato</th>
									<th>Prodotti da rifornire</th>
									<th>Indirizzo</th>
									<th>Posizione nell'edificio</th>
									<th>Categorie fornite dal distributore</th>
<!-- 									<th>AZIONI</th> -->
								</tr>
							</thead>
							<tbody>
<%-- 							<s:if test="listDistributoreModel.size() != 0"> Size != 0</s:if> --%>
								<s:iterator value="#attr.listDistributoreModel">
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
										<td><s:property value="id" /></td>
										<td><s:property value="stato" /></td>
										<td><s:property value="prodottiForniti" /></td>
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