<%@ taglib prefix="s" uri="/struts-tags"%>


<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h1>ELENCO DISTRIBUTORI</h1>
				</div>
			</div>
			<!-- /. ROW  -->
			<hr />

			<div class="panel panel-default">
				<div class="panel-body">
					<s:if test="#updateBoolean"> Aggiornato!</s:if>
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr align="center">
									<th>Azioni</th>
									<th>Stato</th>
									<th>Manutentore</th>
									<th>Prodotti in termine (quantità)</th>
									<th>Indirizzo</th>
									<th>Posizione nell'edificio</th>
									<th>Categorie fornite dal distributore</th>
									<!-- 									<th>AZIONI</th> -->
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#attr.listDistributoreModel" var="listDistrModel">
									<s:url var="dettaglioDistributore" namespace="/gestore" action="DettaglioDistributore">
										<s:param name="idDistributore">
											<s:property value="id" />
										</s:param>
									</s:url>

									<tr class="odd gradeA">
										<td><a href="${dettaglioDistributore}" class="compare-in">
												<button class="btn btn-primary">Dettaglio</button>
										</a></td>
										<td><img src="/distributori/images/stato-${stato}.ico" alt="Stato" ></td>
										<s:url var="editDip" action="EditDipendente">
											<s:param name="idDip">
												<s:property value="dipendente.id" />
											</s:param>
										</s:url>
										<td><a href="${editDip}" class="compare-in">${dipendente.nome}</a></td>
										<!--  mostro solo i prodotti non vuoti -->
										<td><ul>
												<s:iterator value="#listDistrModel.prodottiForniti" var="prodotti">
													<s:if test="%{!#prodotti.contains('vuoto:')}">
														<li><s:property value="prodotti" /></li>
													</s:if>
												</s:iterator>
											</ul></td>
										<td><s:property value="indirizzo" /></td>
										<td><s:property value="posizioneEdificio" /></td>
										<td><ul>
												<s:iterator value="#listDistrModel.categorieFornite" var="categorieFornite">
													<li><s:property /></li>
												</s:iterator>
											</ul></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>