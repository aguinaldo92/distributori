<%@ taglib prefix="s" uri="/struts-tags"%>


<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">

			
			<div class="row">
				<div class="col-md-12">
					<h2>Distributore</h2>
					
					<s:debug />
				</div>
			</div>
			<!-- /. ROW  -->
			<hr />

<!-- La costruzione della tabella funziona -->
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
								<s:iterator begin="1" end="posti" status="headStatus" >
									<th><s:property value="#headStatus.count"/>° Fila</th>
								</s:iterator>
								</tr>
								
							</thead>
							<tbody>
							<s:iterator begin="1" end="2" >
								<tr class="odd gradeA">
								<s:iterator begin="1" end="posti" >
								<td>Posto</td>
								
									
									

								</s:iterator>
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