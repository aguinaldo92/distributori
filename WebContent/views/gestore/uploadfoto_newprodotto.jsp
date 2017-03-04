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
                     <h2><b>Prodotto registrato con successo</b></h2>   
                        <h5><s:if test="hasFieldErrors()">
	  							<div class="form-group" style="color: red;">
	                  			<label><s:fielderror /></label>
	               				</div>
						</s:if></h5>
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
                 
			<div class="panel panel-default">                        	
				<div class="panel-heading">
                	<i>Aggiungi un'immagine al prodotto</i>
                </div>
				<div class="panel-body">
				   	<img id="prod_img" src="/distributori/images/no_image.jpg" class="img-thumbnail" alt="Nessuna foto inserita" width="304" height="236">
				   	<s:form name="file_upload" namespace="/gestore" action="Fileupload_newProdotto" enctype="multipart/form-data">
					<div class="form-group"> 
						<br>
						<label>Inserisci foto</label>
						<table><tr>
						<td><s:hidden name="idProdotto" value="%{#attr.idNewProdotto}"/>
						<s:file onchange='document.file_upload.upload_submit.disabled = false' name="foto" accept="image/jpeg,image/png"/></td>
						<td>
						<i>Tipi di file accettati: *.jpeg, *.png</i>
						</td>
						</tr></table>
						<p align="center">   		
			                <s:a action="showCatalogo" class="btn btn-danger btn-lg">Nessuna immagine</s:a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<s:submit id="upload_submit" disabled="true" onclick='document.getElementById("waiting_ico").style.visibility = "visible"' cssClass="btn btn-primary btn-lg" name="load_img_button" value="Inserisci immagine"/>
			               	<img id="waiting_ico" style="visibility: hidden;" src="/distributori/images/upload_wait.gif" alt="Waiting ... " width="50" height="50">
						</p>
			           </div>
			           </s:form>
	           	</div>
            </div>
		


    		</div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->