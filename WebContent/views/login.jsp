<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>


<div id="wrapper">

	<!-- /. NAV SIDE  -->
	<div id="page-wrapper">
		<div id="page-inner">
			<div class="row">
				<div class="col-md-12">
					<h2>Effettua il LOGIN</h2>
					<h5></h5>

				</div>
			</div>
			<!-- /. ROW  -->
			<hr />

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div class="login-panel panel panel-default">

							<div class="panel-body">
								<s:form action="Login" role="form">
									<fieldset>
										<div
											class='form-group <s:if test="hasActionErrors()">
											has-error
										</s:if>'>
											<s:textfield name="email" label="Email" placeholder="E-mail"
												type="email" class="form-control" />
										</div>
										<div
											class='form-group <s:if test="hasActionErrors()">
											has-error
										</s:if>'>
											<s:password name="password" label="Password"
												class="form-control" placeholder="Password" />
										</div>
										<div class="form-group">
											<s:submit value="login"
												class="btn btn-lg btn-success btn-block" />
										</div>
										<s:if test="hasActionErrors()">
											<div style="color: red">
												<s:actionerror />
											</div>
										</s:if>
									</fieldset>
								</s:form>
							</div>
						</div>
					</div>
				</div>


		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
