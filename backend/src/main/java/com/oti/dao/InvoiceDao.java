package com.oti.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oti.domain.Invoice;

/**
 * 功能概要：User的DAO类

 */
public interface InvoiceDao {

	List<Invoice> selectInvoiceByEmployeeID(int EmployeeID);
	List<Invoice> selectInvoiceByDepartment(String Department);
	List<Invoice> selectInvoiceAll();
	Invoice selectInvoiceByID(int id);  
	void insertInvoice(Invoice invoice);
	void updateInvoice(Invoice invoice);
	

}
