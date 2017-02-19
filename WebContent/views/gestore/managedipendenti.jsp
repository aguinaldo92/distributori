<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:action name="ListDipendenti"/>

<div id="wrapper">  
           
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>GESTIONE DIPENDENTI</h2>   
                        <h5></h5>
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
                 
                 <div class="panel panel-default">

                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr align="center">
                                        	<th>Badge</th>
                                            <th>Nome</th>
                                            <th>Cognome</th>
                                            <th>Email</th>
                                            <th>Telefono</th>
                                            <th>AZIONI</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                    <s:iterator value="dipendenti">
                                    
                                    <s:url var="EditDip" action="EditDipendente">
										<s:param name="idDip"><s:property value="personaId"/></s:param>
									</s:url>
									<s:url var="DelDip" action="DeleteDipendente">
										<s:param name="idDip"><s:property value="personaId"/></s:param>
									</s:url>
                                    
                                        <tr class="odd gradeA">
                                        	<td><s:property value="personaId"/> </td>
                                            <td><s:property value="persona.nome"/> </td>
                                            <td><s:property value="persona.cognome"/> </td>
                                            <td><s:property value="persona.email"/> </td>
                                            <td class="center"><s:property value="telefono"/> </td>
                                            <td class="center">
											<a href="${EditDip}" class="compare-in" ><button class="btn btn-primary"><i class="fa fa-edit "></i> Edit</button></a>
                                       		
                                       		<button class="btn btn-danger" data-toggle="modal" data-target="#${personaId}_delete"> <i class="fa fa-pencil"></i> Delete</button>
							<!-- INIZIO FINESTRA MODALE DI CONFERMA ELIMINAZIONE -->
				                            <div class="modal fade" id="${personaId}_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				                                <div class="modal-dialog">
				                                    <div class="modal-content">
				                                        <div class="modal-header">
				                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				                                            <h4 class="modal-title" id="myModalLabel">ELIMINAZIONE DIPENDENTE</h4>
				                                        </div>
				                                        <div class="modal-body">
				                                            Attenzione. Questa operazione è irreversibile. Le informazioni eliminate non saranno recuperabili in alcun modo.
				                                            <br>Si vuole, pertanto, confermare l'eliminazione del dipendente ${persona.nome} ${persona.cognome}?
				                                        </div>
				                                        <div class="modal-footer">
				                                            <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
				                                            <a href="${DelDip}" class="compare-in" ><button type="button" class="btn btn-primary">Conferma</button></a>
				                                        </div>
				                                    </div>
				                                </div>
				                            </div>
							<!-- FINE FINESTRA MODALE DI CONFERMA ELIMINAZIONE -->
                                            
                                            </td>
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