package com.oti.controller;


import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
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
import com.oti.service.EmployeeService;
import com.oti.service.EmployeeServiceImpl;
import com.oti.service.InvoiceService;
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
		try {    
			emp.setId((Integer) request.getSession().getAttribute("EmployeeID"));
			emp.setEmployeeNO((String) request.getSession().getAttribute("EmployeeNO"));
			emp.setRealName((String) request.getSession().getAttribute("RealName"));
			emp.setRole((String) request.getSession().getAttribute("Role"));
			emp.setDepartment((String) request.getSession().getAttribute("Department"));	
			System.out.println("Employee List:"+JSON.toJSONString(emp));
			
	    	} catch (Exception e) {
			   e.printStackTrace();
			   return "error";
			}

			return JSON.toJSONString(emp);
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
