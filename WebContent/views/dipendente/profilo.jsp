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
	                  			<label><s:fielderror /></label>
	               				</div>
						</s:if></h5>
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
		<s:iterator value="#attr.dipendente">
			<s:form name="update" action="UpdateDipendente">
				
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
				<br><br>
				<div class="form-group">
                   <label>Contatti</label>
               	</div>
				<div class=' form-group input-group input-group-lg <s:if test="hasActionErrors()">
																		has-error
																	</s:if>'>
  				<span class="input-group-addon">Email</span>
  				<s:textfield cssClass="form-control" cssStyle="width: 60%;" type="email" name="email"/>
  				<s:if test="hasActionErrors()">
	  				<div class="form-group" style="color: red;">
	                   <label><s:actionerror /></label>
	               	</div>
				</s:if>
  				</div>
  				<div class=" form-group input-group input-group-lg">
  				<span class="input-group-addon">Telefono</span>
  				<s:textfield cssClass="form-control" cssStyle="width: 60%;" name="telefono"/>
				</div>
				<br><br>
				<p align="center">
				<s:a namespace="/dipendente" action="dashboard" class="btn btn-danger btn-lg">Annulla</s:a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:submit cssClass="btn btn-success btn-lg" name="edit_button" value="Modifica"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:a action="goToModificaPassword" class="btn btn-primary btn-lg">Modifica Password</s:a>
				</p>
			</s:form>
		</s:iterator>


    		</div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->