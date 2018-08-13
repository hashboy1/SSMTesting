package com.oti.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.oti.domain.Invoice;
import com.oti.util.PagedResult;

/**
 * 功能概要：EmployeeService接口类
 */
public interface InvoiceService {
	
	
	
	public List<Invoice> selectInvoiceByEmployeeID(int EmployeeID);
	public List<Invoice> selectInvoiceByDepartment(String Department);
	public List<Invoice> selectInvoiceAll();
	public Invoice selectInvoiceByID(int id); 
	public void insertInvoice(Invoice invoice);
	public void updateInvoice(Invoice invoice);
}
