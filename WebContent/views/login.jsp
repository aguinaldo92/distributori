<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<div id="wrapper">  
           
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Effettua il LOGIN</h2>   
                        <h5>messaggi di errore</h5>
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
                 
                 		<s:div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="login-panel panel panel-default">
						
						<div class="panel-body">
							<s:form action="Login" role="form">
								<fieldset>
									<div class="form-group">
										<s:textfield name="email" label="Email" placeholder="E-mail"
											type="email" class="form-control" />
									</div>
									<div class="form-group">
										<s:password name="password" label="Password" class="form-control" placeholder="Password" />
									</div>
									<div class="form-group">
										<s:submit value="login"
											class="btn btn-lg btn-success btn-block" />
									</div>
								</fieldset>
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</s:div>
                 
               
    </div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->

    
   
</body>
</html>