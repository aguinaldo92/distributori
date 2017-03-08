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
                        <h5><s:if test="hasFieldErrors()">
	  							<div class="form-group" style="color: red;">
	                  			<label>Sono presenti errori all'interno del form</label>
	               				</div>
						</s:if></h5>
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
		<s:iterator value="#attr.dipendente">
			<s:form name="update" namespace="/gestore" action="UpdateDipendente">
				
				<div class=" form-group input-group input-group-lg">
  				<span class="input-group-addon">Badge</span>
  				<s:textfield cssClass="form-control" cssStyle="width: 10%; background-color: #eee;" readonly="true" name="id"/>
  				</div>
  				<br><br><br><br>
				<div class=" form-group input-group input-group-lg">
  				<span class="input-group-addon">Nome</span>
  				<s:textfield cssClass="form-control" name="nome"/>
  				<span class="input-group-addon">Cognome</span>
  				<s:textfield cssClass="form-control" name="cognome"/>
				</div>
						<h5><s:if test="hasFieldErrors()">
	  							<div class="form-group" style="color: red;">
	                  			<label><s:fielderror fieldName="nome"/></label>
	                  			<label><s:fielderror fieldName="cognome"/></label>
	               				</div>
						</s:if></h5>
				<br><br>
				<div class="form-group">
                   <label>Contatti</label>
               	</div>
				<div class=' form-group input-group input-group-lg <s:if test="hasActionErrors()">
																		has-error
																	</s:if>'>
  				<span class="input-group-addon">Email</span>
  				<s:textfield cssClass="form-control" cssStyle="width: 60%;" type="email" name="email"/>
  				</div>
  					<h5><s:if test="hasFieldErrors()">
		  							<div class="form-group" style="color: red;">
		                  			<label><s:fielderror fieldName="email_esistente"/></label>
		                  			<label><s:fielderror fieldName="email"/></label>
		               				</div>
					</s:if></h5>
  				<div class=" form-group input-group input-group-lg">
  				<span class="input-group-addon">Telefono</span>
  				<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="telefono"/>
				</div>
					<h5><s:if test="hasFieldErrors()">
		  							<div class="form-group" style="color: red;">
		                  			<label><s:fielderror fieldName="telefono"/></label>
		               				</div>
					</s:if></h5>
				<br><br>
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