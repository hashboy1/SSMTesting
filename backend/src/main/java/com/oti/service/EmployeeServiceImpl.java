package com.oti.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oti.dao.EmployeeDao;
import com.oti.domain.Employee;
import com.oti.util.BeanUtil;
import com.oti.util.PagedResult;

/**
 * 功能概要：UserService实现类
 * 
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Resource
	private EmployeeDao employeeDao;

	public Employee selectUserById(String  EmployeeNo) {
		return employeeDao.selectEmployeeByNo(EmployeeNo);
		
	}

}
