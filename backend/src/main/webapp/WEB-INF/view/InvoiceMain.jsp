<%@page import="org.omg.CORBA.PUBLIC_MEMBER"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Employee List</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <link href="<%=request.getContextPath()%>/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/static/css/templatemo-style.css" rel="stylesheet">
</head>
<body>  

<div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
 
    		 
    		 
    		 <table   class="table table-striped table-bordered templatemo-user-table">		
           		<tr>
              		<td align="left"><a href="InsertInvoice.do" class="templatemo-edit-btn">New Invoice</a><td>
              	</tr>
              </table>
              <table class="table table-striped table-bordered templatemo-user-table">
              	<thead>
	                 <tr>
	                    <td><a href="" class="white-text templatemo-sort-by">Department </a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">EmployeeNO </a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">RealName</a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">Role</a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">Creation Date</a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">Request Quantity</a></td>
	                    <td colspan="2" align = "center"><a href="" class="white-text templatemo-sort-by">Operation</a></td>      
	                 </tr>
               </thead>
					<c:forEach items="${Invoice}" var="invoice">
					
					    <tr>
					                    <td>${invoice.department}</td>
					                    <td>${invoice.employeeNO}</td>
					                    <td>${invoice.realName}</td>
					                    <td>${invoice.role}</td> 
					                    <td>${invoice.creationDate}</td> 
					                    <td>${invoice.amount}</td> 
					                    
					                 
					                    <td>
					                    	
					                    	
					                    	<c:if test="${! empty Edit}">
					                    	<a href="InvoiceEdit${invoice.id}.do" class="templatemo-edit-btn">Edit</a>															                    	
  	                                        </c:if>
					                    </td>	
					     </tr>
					 
					</c:forEach>
					</table>
		          </div>          
		        </div>
		      </div>
		   </div>
    
                                                                                           

  </body>
</html>