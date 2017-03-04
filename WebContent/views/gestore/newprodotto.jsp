<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<div id="wrapper">  
           
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>REGISTRAZIONE NUOVO PRODOTTO</h2>   
                        <h5><s:if test="hasFieldErrors()">
	  							<div class="form-group" style="color: red;">
	                  			<label><s:fielderror /></label>
	               				</div>
						</s:if></h5>
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
            
			<s:form name="update" namespace="/gestore" action="AddProdotto">
				<div class="panel panel-default">
                        <div class="panel-heading">
                            <h4></h4>
                        </div>
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
						  				<div class="form-group">
											<br>
											<label>Inserisci la foto del prodotto</label>
		                                    <s:file name="foto" accept="image/*"/>
                                        </div>
										<p></p>
										<div class=" form-group input-group input-group-lg">
							  				<span class="input-group-addon">Nome</span>
							  				<s:textfield cssClass="form-control" name="nome"/>
										</div>
										<div class=" form-group input-group input-group-lg">
						  					<span class="input-group-addon">Descrizione</span>
                                            <s:textarea cssClass="form-control" name="descrizione"/>
                                        </div>
                                        <p></p>
										<div class="form-group">
                                            <div class=" form-group input-group input-group-lg">
						  					<span class="input-group-addon">Categoria</span>
						  					<s:select class="form-control" list="all_categ" name="categoria.id" listKey="id" listValue="nome"/>
                                            </div>
                                        </div>
	                                    <label><h4>Famiglie:</h4></label>&nbsp;&nbsp;&nbsp;               
	                                    <s:checkboxlist list="famiglie" name="famiglia_scelta" listKey="id" listValue="nome"/>
										<p></p>
                                        <div class="form-group input-group input-group-lg">
                                            <span class="input-group-addon"><i class="fa fa-eur"></i>
                                            </span>
                                            <s:textfield cssClass="form-control" name="prezzo"/>
                                        </div>
                                        <div class=" form-group input-group">
						  					<span class="input-group-addon">Sconto per utenti registati</span>
						  					<s:textfield cssClass="form-control" name="sconto_percentuale"/>
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
											name="produttore.id" list="select_mapping.keySet()" listKey="id" listValue="nome" cssClass="form-control"
	 										doubleName="stabilimento.id" doubleList="select_mapping.get(top)" doubleListKey="id" doubleListValue="citta +' (' + provincia + ')'" doubleCssClass="form-control"/>
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
                                    <div class=" form-group input-group input-group-lg">
					  					<span class="input-group-addon">Preparazione</span>
                                           <s:textarea class="form-control" name="preparazione"/>
                                    </div>
                                </div>
                                 <!-- FINE TAB INGREDIENTI E PREPARAZIONE -->
                            </div>
                        </div>
                    </div>
                <p align="center">    		
                <s:a action="ListProdotti" class="btn btn-danger btn-lg">Annulla</s:a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:submit cssClass="btn btn-primary btn-lg" value="Modifica"/>
				</p>
			</s:form>
		


    		</div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->