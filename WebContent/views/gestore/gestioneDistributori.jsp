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
						<p align="right">
							<s:a namespace="/gestore" action="goToCreateDistributore" class="btn btn-success">Inserisci nuovo distributore</s:a>
						</p>
						<p></p>
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
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#attr.listDistributoreModel" var="listDistrModel">
									<s:url var="dettaglioDistributore" namespace="/gestore" action="DistributoreDetail">
										<s:param name="idDistributore">
											<s:property value="id" />
										</s:param>
									</s:url>
									
									<s:url var="prodottiDistributore" namespace="/gestore" action="ProdottiDistributore">
										<s:param name="idDistributore">
											<s:property value="id" />
										</s:param>
									</s:url>

									<tr class="odd gradeA">
										<td>
										
										<a href="${dettaglioDistributore}" class="compare-in">
												<button class="btn btn-primary">Dettaglio</button>
										</a>
										<a href="${prodottiDistributore}" class="compare-in">
												<button class="btn btn-success">Prodotti</button>
										</a>
										
										<s:if test="stato != 0 && stato != 3">
										<button class="btn btn-danger" data-toggle="modal" data-target="#${id}_guasto">Segnala guasto</button>
								<!-- INIZIO FINESTRA MODALE DI CONFERMA GUASTO -->
					                            <div class="modal fade" id="${id}_guasto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					                                <div class="modal-dialog">
					                                    <div class="modal-content">
					                                        <div class="modal-header">
					                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					                                            <h4 class="modal-title" id="myModalLabel">SEGNALAZIONE DI GUASTO</h4>
					                                        </div>
					                                        <s:form name="distributore_guasto" namespace="/gestore" action="SetDistributoreGuasto">
					                                        	<div class="modal-body">
					                                            	Attenzione. Questa operazione è irreversibile.
					                                            	<p align="center">
					                                            		<b>Indirizzo distributore: <s:property value="indirizzo"/></b>
					                                            	</p>
					                                            	<br>Si vuole, pertanto, confermare la segnalazione di guasto?
					                                        		<s:hidden name="idDistributore" value="%{id}"/>
					                                        	</div>
					                                        	<div class="modal-footer">
						                                        	<button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
						                                    		<s:submit id="guasto_submit" onClick="this.form.submit(); this.disabled=true; this.value='Attendi...';" cssClass="btn btn-primary" name="confirm_guasto_button" value="Conferma"/>
						                                    	</div>
						                                    </s:form>
					                                    </div>
					                                </div>
					                            </div>
								<!-- FINE FINESTRA MODALE DI CONFERMA GUASTO -->
											</s:if>								
										
										</td>
										<td><img src="/distributori/images/stato-${stato}.ico" alt="Stato"></td>
										<s:url var="editDip" action="DipendenteDetail">
											<s:param name="idDip">
												<s:property value="dipendente.id" />
											</s:param>
										</s:url>
										<td><a href="${editDip}" class="compare-in">${dipendente.nome}</a></td>
										<!--  mostro solo i prodotti non vuoti -->
										<td><ul>
												<s:iterator value="#listDistrModel.prodottiForniti" var="prodotti">
													<li><s:property value="prodotti" /></li>
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