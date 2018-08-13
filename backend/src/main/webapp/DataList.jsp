<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>InvoiceList</title>
<link href="static/css/font-awesome.min.css" rel="stylesheet">
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/templatemo-style.css" rel="stylesheet">
<script src="static/js/jQuery/jquery-2.1.4.min.js"></script>
<script src="static/js/bootstrap/js/bootstrap.min.js"></script>
<script src="static/js/bootstrap/js/bootstrap-paginator.min.js"></script>
<style type="text/css">
#queryDiv {
 margin-right: auto;
 margin-left: auto;
 width:600px;
}
#textInput {
 margin-top: 10px;
}

</style>
</head>
<body>




    
	<div id = "queryDiv">
	    <div id="welcomeMsg" ></div>
		<input id = "textInput" type="text" placeholder="please input employee NO" >
		<button id = "queryButton" class="btn btn-primary" type="button">查询</button>
	 </div>
	
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
	                    <td><a href="" class="white-text templatemo-sort-by">id </a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">amount </a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">employeeNo</a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">Role</a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">Remark</a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">RealName</a></td>
	                    <td><a href="" class="white-text templatemo-sort-by">CreationDate</a></td>
	                    <td colspan="2" align = "center"><a href="" class="white-text templatemo-sort-by">Operation</a></td>      
	                 </tr>
               </thead>
               <tbody id="tableBody">
			   </tbody>
			
				</table>
		          </div>          
		        </div>
		      </div>
		   </div>
               

	<script type='text/javascript'>    
	var test1;
	 var urlRootContext = (function () {
         var strPath = window.document.location.pathname;
         var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
         return postPath;
     })();
	
	 
	 
	 //get the login use when the page document is ready
	 $(document).ready(function(){
		 $.ajax({
			type : 'POST',
	        url : urlRootContext+ "/GetLoginUser.do",
	        async : false,
	        dataType : 'json',
	        success : function(data) {
             	test1=data;
             	console.log(data.valueOf());
             	document.getElementById('welcomeMsg').innerHTML='Welcome '+data.realName;	        	
	        },
	        error : function() {
	        	alert("error!");
	        }
		 })
	    });
	
	
	
	
	
	 
	
	function buildTable(employeeNO) {
	 
	var url =  urlRootContext + "/InvoiceJSON.do";   
	var datas = {'Id' : null, 'EmployeeNO' : employeeNO};
	//alert("parameters:"+employeeNO);
	 $(function (){
		 $.ajax({
			type : 'POST',
	        /*contentType : 'application/json',*/
	        url : url,
	        async : false,
	        dataType : 'json',
	        data : datas,
	        success : function(data) {
	        	if (data.length > 0 ) {
	        		$("#tableBody").empty();
	                $(data).each(function(){//重新生成	
	                	$("#tableBody").append('<tr>');
	                	$("#tableBody").append('<td>' + this.id + '</td>');
	                    $("#tableBody").append('<td>' + this.amount + '</td>');
	                    $("#tableBody").append('<td>' + this.employeeNO + '</td>');
	                    $("#tableBody").append('<td>' + this.role + '</td>');
	                    $("#tableBody").append('<td>' + this.remark + '</td>');
	                    $("#tableBody").append('<td>' + this.realName + '</td>');
	                    $("#tableBody").append('<td>' + this.creationDate + '</td>');
	                    $("#tableBody").append('</tr>');
	                })
	                }
	        	else {             	            	
       	          $("#tableBody").append('<tr><th colspan ="4"><center>no data found</center></th></tr>');
       	    }
	        	
	        },
	        error : function() {
	        	$("#tableBody").empty();
	        	 $("#tableBody").append('<tr><th colspan ="4"><center>no data found</center></th></tr>');
	        }
	        
		 })
	    });
	 
	}
	 
	 $("#queryButton").bind("click",function(){
     	var employeeNO = $("#textInput").val();	
     	buildTable(employeeNO);
     });
	
	  </script>
	  
	  	
</body>
</html>