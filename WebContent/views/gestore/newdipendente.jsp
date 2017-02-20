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
                     <h2>REGISTRAZIONE NUOVO DIPENDENTE</h2>   
                        <h5>messaggi di errore</h5>
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
			<s:form name="update" action="AddDipendente">
				
				<div class=" form-group input-group input-group-lg">
  				<span class="input-group-addon">Nome</span>
  				<s:textfield cssClass="form-control" name="nome"/>
  				<span class="input-group-addon">Cognome</span>
  				<s:textfield cssClass="form-control" name="cognome"/>
				</div>
				<br><br>
				<div class=" form-group input-group input-group-lg">
  				<span class="input-group-addon">Email</span>
  				<s:textfield cssClass="form-control" name="email"/>
  				<span class="input-group-addon">Telefono</span>
  				<s:textfield cssClass="form-control" name="telefono"/>
				</div>
				<br><br>
				<div class="form-group">
                    <label>NOTA: La password di accesso verr� generata dal sistema e 
                    inviata automaticamente via email al dipendente.</label>
               	</div>
				<p align="center">
				<s:a action="ListDipendenti" class="btn btn-danger btn-lg">Annulla</s:a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:submit cssClass="btn btn-primary btn-lg" value="Inserisci"/>

				</p>
			</s:form>


    		</div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->