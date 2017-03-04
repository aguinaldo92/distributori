<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
		<div class="col-md-12">
			<h1>DASHBOARD GESTORE</h1>
			<s:a namespace="gestore" action="listaInterventi">listaInterventi</s:a>
			<hr>
			<div class="row">
			
				<div class="col-md-5 col-sm-6 col-xs-6">
					<div class="panel panel-back noti-box">
						<span class="icon-box bg-color-blue set-icon"> <i class="fa fa-envelope-o"></i>
						</span>
						<div class="text-box">
							<p class="main-text">120 Nuovi</p>
							<p class="text-muted">Messages</p>
						</div>
					</div>
				</div>
				<div class="col-md-5 col-sm-6 col-xs-6">
					<div class="panel panel-back noti-box">
						<span class="icon-box bg-color-red set-icon"> <i class="fa fa-warning"></i>
						</span>
						<div class="text-box">
							<p class="main-text">120 Distributori</p>
							<p class="text-muted">Richiedono manutenzione</p>
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>