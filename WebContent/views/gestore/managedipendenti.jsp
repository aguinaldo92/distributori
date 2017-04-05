<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:action name="ListDipendenti" namespace="/gestore"/>

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
                            <p align="right">
                            <s:a namespace="/gestore" action="CreateDipendente" class="btn btn-success">Inserisci nuovo dipendente</s:a>
                            </p>
                            <p></p>
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
                                    
                                    <s:iterator value="#attr.dipendenti">
                 
                                    <s:url var="DipDet" action="DipendenteDetail">
										<s:param name="idDip"><s:property value="personaId"/></s:param>
									</s:url>
									<s:url var="DelDip" action="DeleteDipendente">
										<s:param name="idDip"><s:property value="personaId"/></s:param>
									</s:url>
                                    
                                        <tr class="odd gradeA">
                                        	<td><s:property value="personaId"/> </td>
                                            <td><s:property value="persona.nome"/></td>
                                            <td><s:property value="persona.cognome"/> </td>
                                            <td><s:property value="persona.email"/> </td>
                                            <td><s:property value="telefono"/></td>
                                            <td class="center">
											<s:a href="%{DipDet}" class="compare-in" name="edit_button" value="Edit"><button class="btn btn-primary"><i class="fa fa-edit "></i> Edit</button></s:a>

                                       		<button class="btn btn-danger" data-toggle="modal" data-target="#${personaId}_delete"> <i class="fa fa-pencil"></i> Delete</button>
							<!-- INIZIO FINESTRA MODALE DI CONFERMA ELIMINAZIONE -->
				                            <div class="modal fade" id="${personaId}_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				                                <div class="modal-dialog">
				                                    <div class="modal-content">
				                                        <div class="modal-header">
				                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				                                            <h4 class="modal-title" id="myModalLabel">ELIMINAZIONE DIPENDENTE</h4>
				                                        </div>
				                                        <s:form name="del_dipendente" namespace="/gestore" action="DeleteDipendente">
				                                        	<div class="modal-body">
				                                            	Attenzione. Questa operazione è irreversibile. Le informazioni eliminate non saranno recuperabili in alcun modo.
				                                            	<br>Si vuole, pertanto, confermare l'eliminazione del dipendente<b> ${persona.nome} ${persona.cognome}</b>?
				                                        		<s:hidden name="idDip" value="%{personaId}"/>
				                                        	</div>
				                                        	<div class="modal-footer">
					                                        	<button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
					                                    		<s:submit id="delete_dip_submit" onClick="this.form.submit(); this.disabled=true; this.value='Attendi...';" cssClass="btn btn-primary" name="confirm_del_button" value="Conferma"/>
					                                    	</div>
					                                    </s:form>
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