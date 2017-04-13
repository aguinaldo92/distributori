<%@ taglib prefix="s" uri="/struts-tags"%>

<s:action name="DashboardGestore" namespace="/gestore" />

<div id="wrapper">
	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
		<div class="col-md-12">
			<h1>DASHBOARD GESTORE</h1>
			
			<hr>
			<div class="row">
			
				<div class="col-md-5 col-sm-6 col-xs-6">
					<div class="panel panel-back noti-box">
						<span class="icon-box bg-color-blue set-icon"> <i class="fa fa-envelope-o"></i>
						</span>
						<div class="text-box">
							<p class="main-text"><s:a namespace="/gestore" action="ShowFeedbacks"><s:property value="#attr.numMessaggiNonLetti"/> Nuovi</s:a></p>
							<p class="text-muted">messaggi dagli utenti</p>
						</div>
					</div>
				</div>
				<div class="col-md-5 col-sm-6 col-xs-6">
					<div class="panel panel-back noti-box">
						<span class="icon-box bg-color-red set-icon"> <i class="fa fa-warning"></i>
						</span>
						<div class="text-box">
							<p class="main-text"><s:a namespace="/gestore" action="ListDistributoriGestore"><s:property value="#attr.numDistributoriNonOk"/> Distributori </s:a></p>
							<p class="text-muted">richiedono attenzione</p>
						</div>
					</div>
				</div>
				
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">ELENCO RIFORNIMENTI</div>
                    <div class="panel-body">
						<p></p>
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr align="center">
									<th>Operatore</th>
									<th>Distributore</th>
									<th>Data rifornimento</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#attr.rifornimenti" var="listRifornimenti">
									<tr>
										<td>
											<s:property value="dipendente.persona.nome"/> <s:property value="dipendente.persona.cognome"/>
										</td>
										<td>
											<s:property value="distributore.indirizzo"/>
										</td>
										<td>
											<s:property value="data"/>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
                    </div>
               	</div>
               	
               	<div class="panel panel-default">
					<div class="panel-heading">ELENCO MANUTENZIONI</div>
                    <div class="panel-body">
						<p></p>
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr align="center">
									<th>Operatore</th>
									<th>Distributore</th>
									<th>Data inizio</th>
									<th>Stato manutenzione</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#attr.manutenzioni" var="listManutenzioni">
									<tr>
										<td>
											<s:property value="dipendente.persona.nome"/> <s:property value="dipendente.persona.cognome"/>
										</td>
										<td>
											<s:property value="distributore.indirizzo"/>
										</td>
										<td>
											<s:property value="dataInizio"/>
										</td>
										<s:if test="dataFine != null">
										<td>
											Terminata in data <s:property value="dataFine"/>
										</td>
										</s:if>
										<s:else>
										<td>
											In corso
										</td>
										</s:else>
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