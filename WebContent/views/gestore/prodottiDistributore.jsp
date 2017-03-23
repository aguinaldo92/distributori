<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h2>Distributore</h2>
				</div>
			</div>
			<!-- /. ROW  -->
			<div class="row">
				<div class="col-md-12">
					<h4>Indirizzo : ${indirizzoDistributore}</h4>
					<h4>Posizione all'interno dell'edificio: ${posizioneEdificioDistributore}</h4>
				</div>
			</div>
			<!-- /. ROW  -->
			<hr />

			<!-- La costruzione della tabella funziona -->
			<div class="panel panel-default">
				<div class="panel-body">
					<s:form name="update" namespace="/gestore" action="UpdateProdottiErogati" >
						<s:hidden name="idDistributore" value="%{idDistributore}"/>	
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover" id="dataTables-example">
								<thead>
									<tr align="center">
										<th>Scaffale / Posto</th>
										<s:iterator begin="1" end="posti" status="headStatus">
											<th><s:property value="#headStatus.count" />° Posto</th>
										</s:iterator>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="listProdottiErogatixScaffale" var="listProdottiErogatixScaffale" status="rowStatus">
										<tr class="odd gradeA">
											<th><s:property value="#rowStatus.count" />° Scaffale</th>
											<!-- SI USA TOP PER INDICARE L'ELEMENTO PIù IN CIMA NELLO STACK, OSSIA L'ELEMENTO DELLA LISTA CORRENTE ALLA TALE RIGA -->
											<!-- DA USARE PER iterare su liste di liste -->
											<s:iterator value="#listProdottiErogatixScaffale" var="listDettaglioDistributoreModel" status="columnStatus">
												<s:url var="dettaglioProdotto" namespace="/gestore" action="ProdottoDetail">
													<s:param name="idProdotto">
														<s:property value="%{#listDettaglioDistributoreModel.idProdotto}" />
													</s:param>
												</s:url>
												<td>
												<s:hidden name="listIdsProdottiErogati" value="%{#listDettaglioDistributoreModel.idProdottoErogato}" />
												<s:select class="form-control" list="prodottiCompatibili" listKey="id" listValue="nome" name="listIdsProdotti" value="%{#listDettaglioDistributoreModel.idProdotto}" />
												<a href="${dettaglioProdotto}" class="btn btn-primary btn-xs">Dettaglio</a> 
												</td>
											</s:iterator>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<s:submit class="btn btn-success" value="Salva Modifiche" />
					</s:form>
				</div>
			</div>

		</div>
		<!-- /. PAGE INNER  -->


	</div>
</div>