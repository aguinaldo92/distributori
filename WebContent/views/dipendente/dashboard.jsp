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
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr align="center">
									<th>ID</th>
									<th>Azioni</th>
									<th>Stato</th>
									<th>Prodotti in termine (quantità)</th>
									<th>Indirizzo</th>
									<th>Posizione nell'edificio</th>
									<th>Categorie fornite dal distributore</th>
									<!-- 									<th>AZIONI</th> -->
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#attr.listDistributoreModel" var="listDistrModel">
									<s:url var="prodottiDistributore" namespace="/dipendente" action="ProdottiDistributore">
										<s:param name="idDistributore">
											<s:property value="id" />
										</s:param>
									</s:url>
									<s:url var="startManutenz" namespace="/dipendente" action="StartManutenzione">
										<s:param name="idDistributore">
											<s:property value="id" />
										</s:param>
									</s:url>
									<s:url var="endManutenz" namespace="/dipendente" action="EndManutenzione">
										<s:param name="idDistributore">
											<s:property value="id" />
										</s:param>
									</s:url>
									<tr class="odd gradeA">
										<td><s:property value="id" /></td>
										<td><s:if test="stato==1 || stato ==2">
												<a href="${prodottiDistributore}" class="compare-in">
													<button class="btn btn-primary">Prodotti</button>
												</a>
											</s:if> <s:if test="stato==0">
												<a href="${startManutenz}" class="compare-in">
													<button class="btn btn-success">Inizia manutenzione</button>
												</a>
											</s:if> <s:else>
												<s:if test="stato==3">
													<a href="${endManutenz}" class="compare-in">
														<button class="btn btn-danger">Fine manutenzione</button>
													</a>
												</s:if>

												<s:else>
													<button class="btn btn-danger" data-toggle="modal" data-target="#${id}_guasto">Segnala guasto</button>
													<!-- INIZIO FINESTRA MODALE DI CONFERMA GUASTO -->
													<div class="modal fade" id="${id}_guasto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
																	<h4 class="modal-title" id="myModalLabel">SEGNALAZIONE DI GUASTO</h4>
																</div>
																<s:form name="distributore_guasto" namespace="/dipendente" action="SetDistributoreGuasto">
																	<div class="modal-body">
																		Attenzione. Questa operazione è irreversibile.
																		<p align="center">
																			<b>Indirizzo distributore: <s:property value="indirizzo" /></b>
																		</p>
																		<br>Si vuole, pertanto, confermare la segnalazione di guasto?
																		<s:hidden name="idDistributore" value="%{id}" />
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
																		<s:submit id="guasto_submit" onClick="this.form.submit(); this.disabled=true; this.value='Attendi...';" cssClass="btn btn-primary" name="confirm_guasto_button" value="Conferma" />
																	</div>
																</s:form>
															</div>
														</div>
													</div>
													<!-- FINE FINESTRA MODALE DI CONFERMA GUASTO -->
												</s:else>

											</s:else></td>

										<td><img src="/distributori/images/stato-${stato}.ico" alt="Stato"></td>
										<!--  mostro solo i prodotti non vuoti -->
										<td><ul class="list-group">
												<s:iterator value="#listDistrModel.prodottiForniti" var="prodotti">

													<li class="list-group-item"><s:property value="prodotti" /></li>
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