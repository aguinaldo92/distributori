<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:action name="ListProdotti" namespace="/gestore" />

<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h2>CATALOGO DEI PRODOTTI</h2>
					<h5></h5>

				</div>
			</div>
			<!-- /. ROW  -->
			<hr />

			<div class="panel panel-default">

				<div class="panel-body">
					<div class="table-responsive">
						<p align="right">
							<s:a action="CreateProdotto" class="btn btn-success">Inserisci nuovo prodotto</s:a>
						</p>
						<div class="panel panel-info">
	                        <div class="panel-heading">
	                            Criteri di filtraggio del catalogo
	                        </div>
	                        <div class="panel-body">
	                        <s:form name="filtraggio_catalogo" namespace="/gestore" action="showCatalogo">
		                    	<div class="form-group">
									<p>
										<label>Per categoria:</label>
										<s:checkboxlist listCssClass="checkbox-inline" list="#attr.categorie" listKey="id" listValue="nome" value="#attr.categorie_scelte" name="list_categorie_scelte"/>
									<br>
										<label>Per famiglia:</label>
										<s:checkboxlist listCssClass="checkbox-inline" list="#attr.famiglie" listKey="id" listValue="nome" value="#attr.famiglie_scelte" name="list_famiglie_scelte"/>
									</p>
								</div>
		                        <div class="panel-footer" style="text-align: right;">
		                            <s:submit class="btn btn-info btn-xs" value="Applica filtri"/>
		                        </div>
	                        </s:form>
	                    	</div>	
						</div>
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr align="center">
									<th>Nome</th>
									<th>Produttore</th>
									<th>Stabilimento</th>
									<th>Categoria</th>
									<th>Famiglie</th>
									<th>Prezzo</th>
									<th>AZIONI</th>
								</tr>
							</thead>
							<tbody>

								<s:iterator value="#attr.prodotti">

									<s:url var="ProdDet" action="ProdottoDetail">
										<s:param name="idProdotto">
											<s:property value="id" />
										</s:param>
									</s:url>

									<tr class="odd gradeA">
										<td><b><s:property value="nome" /></b></td>
										<td><s:property value="stabilimento.produttore.nome" /></td>
										<td><s:property value="stabilimento.citta" /> (<s:property
												value="stabilimento.provincia" />)</td>
										<td><s:property value="categoria.nome" /></td>
										<td>
										<ul><s:iterator value="famiglieProdottos">
												<li><s:property value="famiglia.nome" /></li>
											</s:iterator>
										</ul>
										</td>
										<td><s:property value="prezzo" /></td>
										<td class="center"><s:a href="%{ProdDet}"
												class="compare-in" name="edit_button" value="Edit">
												<button class="btn btn-primary">
													<i class="fa fa-edit "></i> Edit
												</button>
											</s:a></td>
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
	<!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->