<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<s:action namespace="/" name="PreloadedFieldsProdotto" />

<div id="wrapper">  
           
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2><b><s:property value="prodotto.nome"/></b></h2>   
                        <h5><s:if test="hasFieldErrors()">
	  							<div class="form-group" style="color: red;">
	                  			<label>Sono presenti errori all'interno del form</label>
	               				</div>
						</s:if></h5>
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
                                  
		<s:iterator value="#attr.prodotto" var="prod">
		
			<div class="panel panel-default">                        	
				<div class="panel-heading">
                	<i>Clicca su un pannello per modificare o visualizzare le relative informazioni</i>
                </div>
                <div class="panel-body">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#foto_prodotto">Foto del prodotto</a>
                                </h4>
                            </div>
                            <div id="foto_prodotto" class="panel-collapse in" style="height: 0px;">
                                <div class="panel-body">
                                   	<img id="prod_img" src="/distributori/${foto}" class="img-thumbnail" alt="Foto_${nome}" width="304" height="236">
                                   	<s:form name="file_upload" namespace="/gestore" action="FileUpload_editProdotto" enctype="multipart/form-data">
									<div class="form-group"> 
										<br>
										<label>Modifica foto</label>
										<table><tr>
										<td><s:hidden name="idProdotto"/>
										<s:file onchange='document.file_upload.upload_submit.disabled = false' name="foto" accept="image/jpeg,image/png"/></td>
										<td>
										<i>Tipi di file accettati: *.jpeg, *.png</i>
										</td>
										</tr></table>
										<p align="center">    		
						                <s:submit id="upload_submit" disabled="true" onclick='document.getElementById("waiting_ico").style.visibility = "visible"' cssClass="btn btn-primary btn-lg" name="load_img_button" value="Modifica immagine"/>
						                <img id="waiting_ico" style="visibility: hidden;" src="/distributori/images/upload_wait.gif" alt="Waiting ... " width="50" height="50">
										</p>
						            </div>
						            </s:form>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#info_prodotto" class="collapsed">Informazioni del prodotto</a>
                                </h4>
                            </div>
                            <div id="info_prodotto" class="panel-collapse collapse" style="height: auto;">
                                <div class="panel-body">
                                    <s:form name="prodotto_update" namespace="/gestore" action="UpdateProdotto">
									<s:hidden name="idProdotto"/>
									<div class="panel panel-default">
					
					                        <div class="panel-body">
					                        
					                            <ul class="nav nav-pills">
					                                <li class="active"><a href="#caratt_prodotto" data-toggle="tab">Caratteristiche prodotto</a>
					                                </li>
					                                <li class=""><a href="#caratt_produttore" data-toggle="tab">Caratteristiche produttore</a>
					                                </li>
					                                <li class=""><a href="#ingredienti_preparaz" data-toggle="tab">Ingredienti e preparazione</a>
					                            </ul>
											
					                            <div class="tab-content">
					                            
					                            <!-- TAB CARATTERISTICHE PRODOTTO -->
					                            
					                                <div class="tab-pane fade active in" id="caratt_prodotto">
					                                    <h4></h4>
															<div class=" form-group input-group input-group-lg">
												  				<span class="input-group-addon">Nome</span>
												  				<s:textfield cssClass="form-control" name="nome"/>
															</div>
																	<h5><s:if test="hasFieldErrors()">
												  							<div class="form-group" style="color: red;">
												                  			<label><s:fielderror fieldName="nome"/></label>
												               				</div>
																	</s:if></h5>
															<div class=" form-group input-group input-group-lg">
											  					<span class="input-group-addon">Descrizione</span>
					                                            <s:textarea cssClass="form-control" name="descrizione"/>
					                                        </div>
					                                        		<h5><s:if test="hasFieldErrors()">
												  							<div class="form-group" style="color: red;">
												                  			<label><s:fielderror fieldName="descrizione"/></label>
												               				</div>
																	</s:if></h5>
					                                        <p></p>
															<div class="form-group">
					                                            <div class=" form-group input-group input-group-lg">
											  					<span class="input-group-addon">Categoria</span>
											  					<s:select class="form-control" list="#attr.all_categ" name="categoria.id" value="#prod.categoria.id" listKey="id" listValue="nome"/>
					                                            </div>
					                                        </div>
						                                    <h4>Famiglie:</h4>&nbsp;&nbsp;&nbsp;               
						                                    <s:checkboxlist list="#attr.famiglie" name="famiglia_scelta" listKey="id" listValue="nome" value="#prod.IDsfamiglie"/>
																	<h5><s:if test="hasFieldErrors()">
												  							<div class="form-group" style="color: red;">
												                  			<label><s:fielderror fieldName="nofamilyselected"/></label>
												               				</div>
																	</s:if></h5>
															<p></p>
					                                        <div class="form-group input-group input-group-lg">
					                                            <span class="input-group-addon"><i class="fa fa-eur"></i>
					                                            </span>
					                                            <s:textfield cssClass="form-control" name="prezzo"/>
					                                        </div>
					                                        		<h5><s:if test="hasFieldErrors()">
												  							<div class="form-group" style="color: red;">
												                  			<label><s:fielderror fieldName="prezzo"/></label>
												               				</div>
																	</s:if></h5>
					                                        <div class=" form-group input-group">
											  					<span class="input-group-addon">Sconto per utenti registati</span>
											  					<s:textfield cssClass="form-control" name="sconto"/>
											  					<span class="input-group-addon">%</span>
															</div>
																	<h5><s:if test="hasFieldErrors()">
												  							<div class="form-group" style="color: red;">
												                  			<label><s:fielderror fieldName="sconto"/></label>
												               				</div>
																	</s:if></h5>
					                                </div>
					                                <!-- FINE TAB CARATTERISTICHE PRODOTTO -->
					                                
					                                <!-- TAB CARATTERISTICHE PRODUTTORE -->
					                                <div class="tab-pane fade" id="caratt_produttore">
					                                    <h4></h4>
						 								<div class="form-group">
							                            	<div class=" form-group input-group input-group-lg">
							                            		<span class="input-group-addon">Azienda<br><br>Stabilimento</span>
																<s:doubleselect  
																name="produttore.id" list="#attr.select_mapping.keySet()" listKey="id" listValue="nome + ' - Sede: '+sede" value="#prod.stabilimento.produttore.id" cssClass="form-control"
						 										doubleName="stabilimento.id" doubleList="#attr.select_mapping.get(top)" doubleListKey="id" doubleListValue="citta +' (' + provincia + ')'" doubleValue="#prod.stabilimento.it" doubleCssClass="form-control"/>
						 									</div>
														</div>
					                                </div>
					                                <!-- FINE TAB CARATTERISTICHE PRODOTTO -->
					                                
					                                <!-- TAB INGREDIENTI E PREPARAZIONE -->
					                                <div class="tab-pane fade" id="ingredienti_preparaz">
					                                    <h4></h4>
					                                   	<div class=" form-group input-group input-group-lg">
										  					<span class="input-group-addon">Ingredienti</span>
										  						<s:textarea class="form-control" name="ingredienti"/>
					                                    </div>
					                                    		<h5><s:if test="hasFieldErrors()">
												  							<div class="form-group" style="color: red;">
												                  			<label><s:fielderror fieldName="ingredienti"/></label>
												               				</div>
																	</s:if></h5>
					                                    <div class=" form-group input-group input-group-lg">
										  					<span class="input-group-addon">Preparazione</span>
					                                           <s:textarea class="form-control" name="preparazione"/>
					                                    </div>
					                                    		<h5><s:if test="hasFieldErrors()">
												  							<div class="form-group" style="color: red;">
												                  			<label><s:fielderror fieldName="preparazione"/></label>
												               				</div>
																	</s:if></h5>
					                                </div>
					                                 <!-- FINE TAB INGREDIENTI E PREPARAZIONE -->
					                            </div>
					                        </div>
					                    </div>
					                <p align="center">    		
					                <s:a action="showCatalogo" class="btn btn-danger btn-lg">Annulla</s:a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<s:submit cssClass="btn btn-primary btn-lg" name="edit_button" value="Modifica informazioni"/>
									</p>
								</s:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
		</s:iterator>
		


    		</div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->