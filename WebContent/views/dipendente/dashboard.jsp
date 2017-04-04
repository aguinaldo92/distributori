<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Popolo la pagina -->
<s:action name="listDistributoriDipendente" namespace="/dipendente" />

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
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr align="center">
									<th>Azioni</th>
									<th>Stato</th>
									<th>Prodotti in termine (quantit�)</th>
									<th>Indirizzo</th>
									<th>Posizione nell'edificio</th>
									<th>Categorie fornite dal distributore</th>
									<!-- 									<th>AZIONI</th> -->
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#attr.listDistributoreModel"
									var="listDistrModel">
									<s:url var="prodottiDistributore" namespace="/dipendente"
										action="ProdottiDistributore">
										<s:param name="idDistributore">
											<s:property value="id" />
										</s:param>
									</s:url>
									<s:url var="startManutenz" namespace="/dipendente"
										action="StartManutenzione">
										<s:param name="idDistributore">
											<s:property value="id" />
										</s:param>
									</s:url>
									<s:url var="endManutenz" namespace="/dipendente"
										action="EndManutenzione">
										<s:param name="idDistributore">
											<s:property value="id" />
										</s:param>
									</s:url>
									<tr class="odd gradeA">
										<td><a href="${prodottiDistributore}" class="compare-in">
												<button class="btn btn-primary">Prodotti</button>
										</a>
										<s:if test="stato==0">
											<s:if test="!inManutenzione">
												<a href="${startManutenz}" class="compare-in">
													<button class="btn btn-success">Inizia
														manutenzione</button>
												</a>
											</s:if>
											<s:else>
											<a href="${endManutenz}" class="compare-in">
												<button class="btn btn-danger">Fine manutenzione</button>
											</a>
											</s:else>
										</s:if>
										</td>
											
										<td><img src="/distributori/images/stato-${stato}.ico"
											alt="Stato"></td>
										<!--  mostro solo i prodotti non vuoti -->
										<td><ul>
												<s:iterator value="#listDistrModel.prodottiForniti"
													var="prodotti">

													<li><s:property value="prodotti" /></li>
												</s:iterator>
											</ul></td>
										<td><s:property value="indirizzo" /></td>
										<td><s:property value="posizioneEdificio" /></td>
										<td><ul>
												<s:iterator value="#listDistrModel.categorieFornite"
													var="categorieFornite">
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