package com.oti.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oti.domain.Employee;

/**
 * 功能概要：User的DAO类

 */
public interface EmployeeDao {

	 Employee selectEmployeeByNo(String EmployeeNo);
	
	  

}
