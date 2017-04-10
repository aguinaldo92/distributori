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
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
                                  
		
			<div class="panel panel-default">                        	
				<div class="panel-heading">
                	<i>Clicca su un pannello per visualizzare le relative informazioni</i>
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
                                   	<img id="prod_img" src="/distributori/${prodotto.foto}" class="img-thumbnail" alt="Foto_${prodotto.nome}" width="304" height="236">
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
                                    <s:form name="prodotto_update" >
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
												  				<s:textfield cssClass="form-control" name="prodotto.nome" readonly="true" />
															</div>
															<div class=" form-group input-group input-group-lg">
											  					<span class="input-group-addon">Descrizione</span>
					                                            <s:textarea cssClass="form-control" name="prodotto.descrizione" readonly="true" />
					                                        </div>
					                                        <p></p>
															<div class="form-group">
					                                            <div class=" form-group input-group input-group-lg">
											  					<span class="input-group-addon">Categoria</span>
											  					<s:select class="form-control" list="#attr.all_categ" name="categoria.id" value="prodotto.categoria.id" listKey="id" listValue="nome" disabled="true" />
					                                            </div>
					                                        </div>
						                                   <h4> Famiglie:</h4>&nbsp;&nbsp;&nbsp;               
						                                    <s:checkboxlist list="#attr.famiglie" name="famiglia_scelta" listKey="id" listValue="nome" value="prodotto.IDsfamiglie" disabled="true"/>
															<p></p>
					                                        <div class="form-group input-group input-group-lg">
					                                            <span class="input-group-addon"><i class="fa fa-eur"></i>
					                                            </span>
					                                            <s:textfield cssClass="form-control" name="prodotto.prezzo" readonly="true" />
					                                        </div>
					                                        <div class=" form-group input-group">
											  					<span class="input-group-addon">Sconto per utenti registati</span>
											  					<s:textfield cssClass="form-control" name="prodotto.sconto" readonly="true" />
											  					<span class="input-group-addon">%</span>
															</div>
					                                </div>
					                                <!-- FINE TAB CARATTERISTICHE PRODOTTO -->
					                                
					                                <!-- TAB CARATTERISTICHE PRODUTTORE -->
					                                <div class="tab-pane fade" id="caratt_produttore">
					                                    <h4></h4>
						 								<div class="form-group">
							                            	<div class=" form-group input-group input-group-lg">
							                            		<span class="input-group-addon">Azienda<br><br>Stabilimento</span>
																<s:doubleselect  
																name="produttore.id" list="#attr.select_mapping.keySet()" listKey="id" listValue="nome + ' - Sede: '+sede" value="prodotto.stabilimento.produttore.id" cssClass="form-control"
						 										doubleName="stabilimento.id" doubleList="#attr.select_mapping.get(top)" doubleListKey="id" doubleListValue="citta +' (' + provincia + ')'" doubleValue="prodotto.stabilimento.it" doubleCssClass="form-control" disabled="true"/>
						 									</div>
														</div>
					                                </div>
					                                <!-- FINE TAB CARATTERISTICHE PRODOTTO -->
					                                
					                                <!-- TAB INGREDIENTI E PREPARAZIONE -->
					                                <div class="tab-pane fade" id="ingredienti_preparaz">
					                                    <h4></h4>
					                                   	<div class=" form-group input-group input-group-lg">
										  					<span class="input-group-addon">Ingredienti</span>
										  						<s:textarea class="form-control" name="prodotto.ingredienti" readonly="true"/>
					                                    </div>
					                                    <div class=" form-group input-group input-group-lg">
										  					<span class="input-group-addon">Preparazione</span>
					                                           <s:textarea class="form-control" name="prodotto.preparazione" readonly="true"/>
					                                    </div>
					                                </div>
					                                 <!-- FINE TAB INGREDIENTI E PREPARAZIONE -->
					                            </div>
					                        </div>
					                    </div>
					                <p align="center">    		
					                <s:a href="javascript:history.back()" class="btn btn-danger btn-lg">Indietro</s:a>
									</p>
								</s:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
		
		


    		</div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->