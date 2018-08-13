package com.oti.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.oti.domain.Employee;
import com.oti.util.PagedResult;

/**
 * 功能概要：EmployeeService接口类
 */
public interface EmployeeService {
	
	
	
	public Employee selectUserById(String  EmployeeNo);

}
