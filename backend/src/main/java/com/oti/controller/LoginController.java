package com.oti.controller;


import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.oti.domain.Employee;
import com.oti.domain.Message;
import com.oti.service.EmployeeService;
import com.oti.util.Encryption;
import com.oti.util.PagedResult;


@Controller
public class LoginController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private EmployeeService employeeService;
	
	@RequestMapping("/login")  
	public String login(HttpServletRequest request, HttpServletResponse response,Model moder) throws IOException {
		
		
		response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");     
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		System.out.println("EmployeeNO:" + userName);
		System.out.println("Password:" + userPassword);

		if((userName == null) || (userPassword ==null) || (userName.equals("")) || (userPassword.equals(""))){
			moder.addAttribute("message","Please Input the EmployeeNo and Password！");
			return "login";
		}else{
			//get the employee information from DB
			Employee employee = employeeService.selectUserById(userName);  
			if(employee != null){
				
				String rightUserPassword = employee.getPassword();
				String EncryptPassword=Encryption.Encrypt(userPassword);
				
				if (EncryptPassword.equals(rightUserPassword)) {
					
					//moder.addAttribute("message", "Password Corrected！");	
					
					System.out.println("EmployeeID:" + employee.getId());
					System.out.println("EmployeeNO:" + employee.getEmployeeNO());
					System.out.println("RealName:" + employee.getRealName());
					System.out.println("Role:" + employee.getRole());
					System.out.println("Department:" + employee.getDepartment());
					
					//add the login information to Session
					request.getSession().setAttribute("EmployeeID", employee.getId());
					request.getSession().setAttribute("EmployeeNO", employee.getEmployeeNO());
					request.getSession().setAttribute("RealName", employee.getRealName());
					request.getSession().setAttribute("Role", employee.getRole());
					request.getSession().setAttribute("Department", employee.getDepartment());	
					
					 Cookie cookie = new Cookie("RealName",employee.getRealName());
					 cookie.setMaxAge(10 * 60);// 设置存在时间为10分钟
				     cookie.setPath("/");//设置作用域
				     response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
					
					return "redirect:InvoiceMain.do";

				} else {
					moder.addAttribute("message", "Wrong Password！");
					return "login";
				}
				
			}
	         else{
				moder.addAttribute("message","Unknown User！");
				return "login";
			}
	   }
	}
	

	@RequestMapping("/loginJSON")  
	@ResponseBody
	public String loginJSON(HttpServletRequest request, HttpServletResponse response,Employee empp) throws IOException {
		
		//response.setHeader("Access-Control-Allow-Origin", "*");
		Message msg=new Message();
		response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");     
		String userName = empp.getEmployeeNO();
		String userPassword = empp.getPassword();
		System.out.println("EmployeeNO:" + userName);
		System.out.println("Password:" + userPassword);

		if((userName == null) || (userPassword ==null) || (userName.equals("")) || (userPassword.equals(""))){
			
			msg.setFlag(1);
			msg.setMessage("Please Input the User ID and Password");
		}else{
			//get the employee information from DB
			Employee employee = employeeService.selectUserById(userName);  
			if(employee != null){
				String rightUserPassword = employee.getPassword();
				String EncryptPassword=Encryption.Encrypt(userPassword);
				
				if (EncryptPassword.equals(rightUserPassword)) {
					
					//moder.addAttribute("message", "Password Corrected！");	
					
					System.out.println("EmployeeID:" + employee.getId());
					System.out.println("EmployeeNO:" + employee.getEmployeeNO());
					System.out.println("RealName:" + employee.getRealName());
					System.out.println("Role:" + employee.getRole());
					System.out.println("Department:" + employee.getDepartment());
					
					//add the login information to Session
					request.getSession().setAttribute("EmployeeID", employee.getId());
					request.getSession().setAttribute("EmployeeNO", employee.getEmployeeNO());
					request.getSession().setAttribute("RealName", employee.getRealName());
					request.getSession().setAttribute("Role", employee.getRole());
					request.getSession().setAttribute("Department", employee.getDepartment());	
					
					msg.setFlag(0);
					msg.setMessage("login Successed");

				} else {
					
					msg.setFlag(1);
					msg.setMessage("Wrong Password!");
				}
				
			}
	         else{
			
	        	 msg.setFlag(1);
				 msg.setMessage("unknown user!");
			}
	
	   }
		return JSON.toJSONString(msg);
	
	}
   
}
