package com.oti.controller;


import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.oti.dao.EmployeeDao;
import com.oti.domain.Employee;
import com.oti.domain.Invoice;
import com.oti.domain.Message;
import com.oti.service.EmployeeService;
import com.oti.service.EmployeeServiceImpl;
import com.oti.service.InvoiceService;
import com.oti.util.JWT;
import com.oti.util.PagedResult;

import java.util.List;
/**
 * 功能概要：UserController
 */

@Controller
public class InvoiceController  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private InvoiceService invoiceService;
	@Resource
	private EmployeeService empService;

	/**
	 * 显示首页
	 */
	@RequestMapping("/InvoiceMain")  
	public String InvoiceMain(HttpServletRequest request, HttpServletResponse response,Model moder){
	try {    

		    if (request.getSession().getAttribute("EmployeeID") ==null)  return "redirect:login.do";	
			int EmployeeID=(Integer) request.getSession().getAttribute("EmployeeID");
			String EmployeeNO=(String) request.getSession().getAttribute("EmployeeNO");
			String RealName=(String) request.getSession().getAttribute("RealName");
			String Role=(String) request.getSession().getAttribute("Role");
			String Department=(String) request.getSession().getAttribute("Department");	
		
			System.out.println("EmployeeID:" + EmployeeID);
			System.out.println("Role:" +Role);
			System.out.println("Department:" +Department);	
			
			List<Invoice> invList = null;

			if (Role.equals("TL")) 
			{
				 invList =invoiceService.selectInvoiceByDepartment(Department);
				 Calendar cal=Calendar.getInstance();  
				 int d=cal.get(Calendar.DATE); 
				 System.out.println("current Day:"+d);
				 if (d<=5) moder.addAttribute("Edit", 1); 
			}
				
			if (Role.equals("TM"))
			{
				 invList =invoiceService.selectInvoiceByEmployeeID(EmployeeID);
			}
			if (Role.equals("ADMIN"))
			{
				 invList =invoiceService.selectInvoiceAll();
			}
			
			
			System.out.println("invoice List:"+invList.toString());

			moder.addAttribute("Invoice", invList);
    	    
    	} catch (Exception e) {
		   e.printStackTrace();
		   moder.addAttribute("error",e.toString());
		   return "error";
		}

		return "InvoiceMain";
	}
	
	
	@RequestMapping("/InvoiceList")
	 public String InvoiceList(Model moder){
		 return "InvoiceList";
	 }
	     
	  
		@RequestMapping(value="/InvoiceJSON", method = {RequestMethod.POST})  
		@ResponseBody
		public String InvoiceJSON(Employee empp){
			
			
			List<Invoice> invList = null;
			//String employeeNo=request.getParameter("employeeNO");
		
		try {    
			System.out.println("Employee NO:"+empp.getEmployeeNO());
			Employee emp=empService.selectUserById(empp.getEmployeeNO());
			invList =invoiceService.selectInvoiceByEmployeeID(emp.getId());
			System.out.println("invoice List:"+JSON.toJSONString(invList));
            
	    	    
	    	} catch (Exception e) {
			   e.printStackTrace();
			   return "error";
			}

			return JSON.toJSONString(invList);
		}
		
		
		@RequestMapping(value="/GetLoginUser")  
		@ResponseBody
		public String GetLoginUser(HttpServletRequest request, HttpServletResponse response,Model moder){
              Employee emp = new Employee();
              String token;
		try {    
			
			token=request.getHeader("Authorization");
			//token=request.getParameter("token");
			System.out.println("token received:"+token);
			if (token != null && !token.equals(""))
			{
			emp=JWT.unsign(token, Employee.class);
			System.out.println("Token Employee List:"+emp.toString());
			}
			/* 
			emp.setId((Integer) request.getSession().getAttribute("EmployeeID"));
			emp.setEmployeeNO((String) request.getSession().getAttribute("EmployeeNO"));
			emp.setRealName((String) request.getSession().getAttribute("RealName"));
			emp.setRole((String) request.getSession().getAttribute("Role"));
			emp.setDepartment((String) request.getSession().getAttribute("Department"));	
			Cookie[] cookies = request.getCookies();
	        if (null==cookies) {
	            System.out.println("没有cookies");
	        } else {
	            for(Cookie cookie : cookies){
	                //迭代时如果发现与指定cookieName相同的cookie，就修改相关数据
	                if(cookie.getName().equals("RealName")){
	                	emp.setRealName(cookie.getValue());
	                    cookie.setValue("RealName");//修改value
	                    cookie.setPath("/");
	                    cookie.setMaxAge(10 * 60);// 修改存活时间
	                    response.addCookie(cookie);//将修改过的cookie存入response，替换掉旧的同名cookie
	                    break;
	                }
	            }
	        } 
	        */ 
	    	} catch (Exception e) {
			   e.printStackTrace();
			   return "error";
			}

			return JSON.toJSONString(emp);
		}
	
		@RequestMapping(value="/InvoiceMainJSON")  
		@ResponseBody
		public String InvoiceMainJSON(HttpServletRequest request, HttpServletResponse response){
				
			List<Invoice> invList = null;
			String token;
			Employee emp;
	     	try {    
			    /*
	     		//if (request.getSession().getAttribute("EmployeeID") ==null)  return "redirect:login.do";	
				int EmployeeID=(Integer) request.getSession().getAttribute("EmployeeID");
				String EmployeeNO=(String) request.getSession().getAttribute("EmployeeNO");
				String RealName=(String) request.getSession().getAttribute("RealName");
				String Role=(String) request.getSession().getAttribute("Role");
				String Department=(String) request.getSession().getAttribute("Department");	
			
				System.out.println("EmployeeID:" + EmployeeID);
				System.out.println("Role:" +Role);
				System.out.println("Department:" +Department);	
				*/
				
				token=request.getHeader("Authorization");
				if (token == null || token.equals("")) return "";
				System.out.println("token received:"+token);
				emp=JWT.unsign(token, Employee.class);
               
				if (emp.getRole().equals("TL")) 
				{
					 invList =invoiceService.selectInvoiceByDepartment(emp.getDepartment());
					 Calendar cal=Calendar.getInstance();  
					 int d=cal.get(Calendar.DATE); 
					 System.out.println("current Day:"+d);
				}
					
				if (emp.getRole().equals("TM"))
				{
					 invList =invoiceService.selectInvoiceByEmployeeID(emp.getId());
				}
				if (emp.getRole().equals("ADMIN"))
				{
					 invList =invoiceService.selectInvoiceAll();
				}
				
				System.out.println("invoice List:"+invList.toString());
	    	    
	    	} catch (Exception e) {
			   e.printStackTrace();
			   return "error";
			}

		     return JSON.toJSONString(invList);
		}
			
		@RequestMapping(value="/InvoiceByIDJSON")  
		@ResponseBody
		 public String InvoiceByIDJSON(HttpServletRequest request, HttpServletResponse response,Invoice inv){
 
			System.out.println("input parameters:"+inv.toString());  
			Invoice inv2= invoiceService.selectInvoiceByID(inv.getId());
			System.out.println("output parameters:"+inv2.toString());  	
			return JSON.toJSONString(inv2);
		 }
		
		
		@RequestMapping(value="/InvoiceSaveJSON")  
		@ResponseBody
		public String InvoiceSaveJSON(HttpServletRequest request, HttpServletResponse response,Invoice inv){
			Message msg=new Message();
			Employee emp;
			String token;
		try {
			    /*
				int EmployeeID=(Integer) request.getSession().getAttribute("EmployeeID");
				String EmployeeNO=(String) request.getSession().getAttribute("EmployeeNO");
				String RealName=(String) request.getSession().getAttribute("RealName");
				String Role=(String) request.getSession().getAttribute("Role");
				String Department=(String) request.getSession().getAttribute("Department");	
				
				System.out.println("EmployeeID:" + EmployeeID);
				System.out.println("Role:" +Role);
				System.out.println("Department:" +Department);
				*/
			

			token=request.getHeader("Authorization");
			if (token == null || token.equals("")) return "";
			System.out.println("token received:"+token);
			emp=JWT.unsign(token, Employee.class);
			
			
				if (inv.getEmployeeID() ==null ||inv.getEmployeeID().equals("")||inv.getEmployeeID().equals("undefined")) inv.setEmployeeID(emp.getId());
				if (inv.getAmount() == null ||inv.getAmount().equals(0)) 
				{
					  msg.setFlag(-1);
					  msg.setMessage(" Please input request quantity!");	
				}
				else
				{
			    System.out.println("before update:"+inv.toString());
				invoiceService.insertInvoice(inv);
				 msg.setFlag(0);
				}
	    	    
	    	} catch (Exception e) {
			   e.printStackTrace();
			   msg.setFlag(-1);
			   msg.setMessage(e.toString());
			}

	        return JSON.toJSONString(msg);
		}
		
	
	
	 @RequestMapping("/InsertInvoice")
	 public String InsertInvoice(Model moder){
		 Invoice inv=new Invoice();
		 moder.addAttribute("Invoice",inv);
		 return "InvoiceModeified";
	 }
	 
	 
	 
	 @RequestMapping("/InvoiceEdit{id}")
	 public String EditInvoice(Model moder, @PathVariable int id){
		  moder.addAttribute("Invoice",invoiceService.selectInvoiceByID(id));
		 return "InvoiceModeified";
	 }
	
	
	@RequestMapping("/InvoiceSave")  
	public String InvoiceSave(HttpServletRequest request, HttpServletResponse response,Model moder,@ModelAttribute Invoice inv){
	
	try {
			
			int EmployeeID=(Integer) request.getSession().getAttribute("EmployeeID");
			String EmployeeNO=(String) request.getSession().getAttribute("EmployeeNO");
			String RealName=(String) request.getSession().getAttribute("RealName");
			String Role=(String) request.getSession().getAttribute("Role");
			String Department=(String) request.getSession().getAttribute("Department");	
			
			
			System.out.println("EmployeeID:" + EmployeeID);
			System.out.println("Role:" +Role);
			System.out.println("Department:" +Department);
			
			if (inv.getEmployeeID() ==null ||inv.getEmployeeID().equals("")) inv.setEmployeeID(EmployeeID);
			if (inv.getAmount() == null ||inv.getEmployeeID().equals(0)) 
			{
				  moder.addAttribute("error","Please input the request quantity,thanks");
				  return "error";
				
			}
			
			invoiceService.insertInvoice(inv);
			
    	    
    	} catch (Exception e) {
		   e.printStackTrace();
		   
		   moder.addAttribute("error",e.toString());
		   return "error";
		}

	return "redirect:InvoiceMain.do";
	}
	
  
}
