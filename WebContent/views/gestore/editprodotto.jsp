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
                     <h2>DETTAGLI PRODOTTO</h2>   
                        <h5><s:if test="hasFieldErrors()">
	  							<div class="form-group" style="color: red;">
	                  			<label><s:fielderror /></label>
	               				</div>
						</s:if></h5>
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
		<s:iterator value="prodotto" var="prod">
			<s:form name="update" action="UpdateProdotto">
			
				<div class="panel panel-default">
                        <div class="panel-heading">
                            <h4><b><s:property value="nome"/></b></h4>
                        </div>
                        <div class="panel-body">
                            <ul class="nav nav-pills">
                                <li class=""><a href="#caratt_prodotto" data-toggle="tab">Caratteristiche prodotto</a>
                                </li>
                                <li class=""><a href="#caratt_produttore" data-toggle="tab">Caratteristiche produttore</a>
                                </li>
                                <li class=""><a href="#ingredienti_preparaz" data-toggle="tab">Ingredienti e preparazione</a>
                            </ul>
						
                            <div class="tab-content">
                            
                            <!-- TAB CARATTERISTICHE PRODOTTO -->
                            
                                <div class="tab-pane fade" id="caratt_prodotto">
                                    <h4></h4>
						  				<img src="/distributori/${foto}" class="img-thumbnail" alt="Foto_${nome}" width="304" height="236">
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
						  					<s:select class="form-control" list="all_categ" name="categoria" value="#prod.categoria.id" listKey="id" listValue="nome"/>
                                            </div>
                                        </div>
	                                    <label><h4>Famiglie:</h4></label><br>
										<s:iterator value="all_fam" var="fam">
											<label class="checkbox-inline">
											
											<input name="famiglie_scelte" type="checkbox" 
												<s:iterator value="#prod.famiglieProdottos" var="prod_fam">
												<s:if test="#fam.id==#prod_fam.id">
												checked="checked"
												</s:if>
												</s:iterator>
											/>
											<s:property value="nome" />
											</label>
										</s:iterator>
										<p></p>
                                        <div class="form-group input-group input-group-lg">
                                            <span class="input-group-addon"><i class="fa fa-eur"></i>
                                            </span>
                                            <s:textfield cssClass="form-control" name="prezzo"/>
                                        </div>
                                        <div class=" form-group input-group">
						  					<span class="input-group-addon">Sconto per utenti registati</span>
						  					<s:textfield cssClass="form-control" name="scontoUtenti"/>
										</div>
                                </div>
                                <!-- FINE TAB CARATTERISTICHE PRODOTTO -->
                                
                                <!-- TAB CARATTERISTICHE PRODUTTORE -->
                                <div class="tab-pane fade" id="caratt_produttore">
                                    <h4></h4>
                                    
									<div class="form-group">
	                                    <div class=" form-group input-group input-group-lg">
										<span class="input-group-addon">Nome Azienda</span>
	                                    <select class="form-control">
	                                        <s:iterator value="all_produttori" var="produttore">
	                                        <option 
	                                        <s:if test="#produttore.id==#prod.stabilimento.produttore.id">
	                                        		selected="selected"
	                                        </s:if>>
	                                        <s:property value="nome"/>
	                                        </option>
	                                        </s:iterator>
	                                    </select>
	                                    </div>
                                    </div>
                                    
                                    <div class="form-group">
	                                    <div class=" form-group input-group input-group-lg">
										<span class="input-group-addon">Stabilimento di produzione</span>
	                                    <s:select class="form-control" list="stabilimentiByProduttore" name="stabilimento" value="#prod.stabilimento.id" listKey="id" listValue="citta +' (' + provincia + ')'"/>
	                                    </div>
                                    </div>

                                </div>
                                <!-- FINE TAB CARATTERISTICHE PRODOTTO -->
                                
                                <!-- TAB INGREDIENTI E PREPARAZIONE -->
                                <div class="tab-pane fade" id="ingredienti_preparaz">
                                    <h4></h4>
                                   	<div class=" form-group input-group input-group-lg">
					  					<span class="input-group-addon">Ingredienti</span>
                                           <textarea class="form-control"><s:property value="ingredienti"/></textarea>
                                    </div>
                                    <div class=" form-group input-group input-group-lg">
					  					<span class="input-group-addon">Preparazione</span>
                                           <textarea class="form-control"><s:property value="preparazione"/></textarea>
                                    </div>
                                </div>
                                 <!-- FINE TAB INGREDIENTI E PREPARAZIONE -->
                            </div>
                        </div>
                    </div>
                <p align="center">    		
                <s:a action="ListDipendenti" class="btn btn-danger btn-lg">Annulla</s:a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:submit cssClass="btn btn-primary btn-lg" name="edit_button" value="Modifica"/>
				</p>
			</s:form>
		</s:iterator>
		


    		</div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->