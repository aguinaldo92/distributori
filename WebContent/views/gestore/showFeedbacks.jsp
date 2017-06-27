<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<s:action namespace="/gestore" name="ListFeedback"/>

<div id="wrapper">  
           
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2><b>FEEDBACKS DEI CLIENTI</b></h2>   
                        <h5></h5>
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
                 
			
			
			<div class="panel panel-default">
                        <div class="panel-heading">
                            Hai <s:property value="#attr.numFeedbackNonLetti"/> messaggi non letti
                        </div>
                        <div class="panel-body">
                            <div class="panel-group" id="accordion">
                            
		    				<s:iterator value="#attr.feedbacks">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#showFB_${id}" class="collapsed">
                                            <s:if test="letto==0"><b></s:if>
                                            <s:property value="data"/> - da <s:property value="persona.email"/>
                                            <s:if test="letto==0"></b></s:if>
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="showFB_${id}" class="panel-collapse collapse" style="height: 0px;">
                                        <div class="panel-body">
                                        <p align="left">
                                        <s:property value="testo"/>
                                        </p>
                                        <s:form action="UpdateFeedback">
	                                        <p align="right">
                                        	<s:hidden name="id"/>
	                                        <s:if test="letto==0">
	                                        <s:submit cssClass="btn btn-success btn-sm" name="submit_btn" value="Segna come letto"/>
	                                        </s:if>
	                                        <s:else>
	                                        <s:submit cssClass="btn btn-danger btn-sm" name="submit_btn" value="Segna come non letto"/>
	                                        </s:else>
	                                        </p>
                                        </s:form>
                                        </div>
                                    </div>
                                </div>
           	 				</s:iterator>
           	 				
                            </div>
                        </div>
                    </div>
		


    		</div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->