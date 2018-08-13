<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="description" content="">
<meta name="author" content="templatemo">
<link href="<%=request.getContextPath()%>/static/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/static/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/static/css/templatemo-style.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/static/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/static/js/jQuery/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/bootstrap/js/bootstrap-paginator.min.js"></script>

<style type="text/css">
#register-form {
	border: 1px solid rgb(197, 197, 197);
	width: 800px;
	margin: auto;
	border-image: none;
	padding: 30px;
	border-radius: 3px;
}
</style>
<title>Invoice Modified/New</title>
</head>
<body>
	<div class="templatemo-flex-row">
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget white-bg">
					<fieldset>
						<legend>Invoice Request Information</legend>
						<form id="register-form" action="InvoiceSave.do" method="post" autocomplete="off">
							<input type="hidden" value="${Invoice.id}" class="form-control" name="id" id="id" autocomplete="off"/>
							<input type="hidden" value="${Invoice.employeeID}" class="form-control" name="employeeID" id="employeeID" autocomplete="off"/>
							<div class="row form-group">
								<div class="col-lg-6 col-md-6 form-group">
									<label for="firstname">Issue Date：</label> 
									<input value="${Invoice.creationDate}" class="form-control" id="creationDate" name="creationDate" autocomplete="off"/>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-lg-6 col-md-6 form-group">
									<label for="mobile">Remark：</label> 
									<input value="${Invoice.remark}" class="form-control" name="remark" id="remark" autocomplete="off"/>
									
								</div>
							</div>
							<div class="row form-group">
								<div class="col-lg-6 col-md-6 form-group">
									<label for="email">Request Quantity：</label> 
									<input
										value="${Invoice.amount}" class="form-control" name="amount"
										id="amount" autocomplete="off"/>
									
								</div>
							</div>
					

							<div class="form-group text-right">
								<button type="submit" class="templatemo-blue-button">保存</button>
								<button type="reset" class="templatemo-blue-button">重置</button>
							</div>
						</form>
					</fieldset>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
