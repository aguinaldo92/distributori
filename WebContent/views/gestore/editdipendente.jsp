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
                     <h2>AGGIORNAMENTO DIPENDENTE</h2>   
                        <h5>messaggi di errore</h5>
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
		<s:iterator value="dipendente">
			<s:form name="update" action="UpdateDipendente">
				
				<p class="form-control-static">Badge: <s:label name="personaId"/></p>
				<p class="form-control-static"></p>
				<div class=" form-group input-group input-group-lg">
  				<span class="input-group-addon">Nome</span>
  				<s:textfield cssClass="form-control" name="persona.nome"/>
				</div>
				<div class=" form-group input-group input-group-lg">
  				<span class="input-group-addon">Cognome</span>
  				<s:textfield cssClass="form-control" name="persona.cognome"/>
				</div>
				<div class=" form-group input-group input-group-lg">
  				<span class="input-group-addon">Email</span>
  				<s:textfield cssClass="form-control" name="persona.email"/>
				</div>
				<div class=" form-group input-group input-group-lg">
  				<span class="input-group-addon">Telefono</span>
  				<s:textfield cssClass="form-control" name="telefono"/>
				</div>
				<p align="center">
				<s:reset cssClass="btn btn-default btn-lg" value="Reset"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:submit cssClass="btn btn-primary btn-lg" value="Modifica"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:a href="#" class="btn btn-danger btn-lg">Annulla</s:a>
				</p>
			</s:form>
		</s:iterator>


    		</div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->