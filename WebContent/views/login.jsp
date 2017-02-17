<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">


<body>
	<s:if test="hasActionErrors()">
		<div style="color: red">
			<s:actionerror />
		</div>
	</s:if>
	
<br></br>
		<s:div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="login-panel panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Please Sign In</h3>
						</div>
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


</body>

</html>