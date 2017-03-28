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
							<p class="text-muted">richiedono manutenzione</p>
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>