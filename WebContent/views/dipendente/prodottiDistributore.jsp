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
					<s:form name="update" namespace="/dipendente" action="UpdateQuantitaProdottiByDistributore">
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
											<s:url var="dettaglioProdotto" namespace="/dipendente" action="ProdottoDetail">
														<s:param name="idProdotto">
															<s:property value="%{idProdotto}" />
														</s:param>
													</s:url>
												<td><s:if test="nomeProdottoErogato != 'vuoto'"><a href="${dettaglioProdotto}"> <s:property value="nomeProdottoErogato" /></a></s:if><s:else> <s:property value="nomeProdottoErogato" /></s:else> 
												<s:hidden name="ids" value="%{idProdottoErogato}" />
													<div class="input-group">
														<span class="input-group-btn">
															<button type="button" class="btn btn-default" data-value="-1" data-target='#spinner<s:property value="#rowStatus.count"/>_<s:property value="#columnStatus.count"/>' data-toggle="spinner">
																<span class="glyphicon glyphicon-minus"></span>
															</button>
														</span> <input type="text" name="quantita" data-ride="spinner" id='spinner<s:property value="#rowStatus.count"/>_<s:property value="#columnStatus.count"/>' class="form-control input-number" value='<s:property value="#listDettaglioDistributoreModel.quantita" />' data-min="0" data-max="1000"> <span class="input-group-btn">
															<button type="button" class="btn btn-default" data-value="1" data-target='#spinner<s:property value="#rowStatus.count"/>_<s:property value="#columnStatus.count"/>' data-toggle="spinner" data-on="mousehold">
																<span class="glyphicon glyphicon-plus"></span>
															</button>
														</span>
													</div></td>
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