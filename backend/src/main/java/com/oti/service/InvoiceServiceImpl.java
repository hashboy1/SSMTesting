package com.oti.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oti.dao.InvoiceDao;
import com.oti.domain.Invoice;
import com.oti.util.BeanUtil;
import com.oti.util.PagedResult;

/**
 * 功能概要：UserService实现类
 * 
 */
@Service
public class InvoiceServiceImpl implements InvoiceService{
	@Resource
	private InvoiceDao invoiceDao;

	public List<Invoice> selectInvoiceByEmployeeID(int EmployeeID) {
		return invoiceDao.selectInvoiceByEmployeeID(EmployeeID);
		
	}
    
	public List<Invoice> selectInvoiceByDepartment(String Department) {
		return invoiceDao.selectInvoiceByDepartment(Department);
		
	}
	public List<Invoice> selectInvoiceAll() {
		return invoiceDao.selectInvoiceAll();
		
	}

	public Invoice selectInvoiceByID(int id) {
		return invoiceDao.selectInvoiceByID(id);
	}
	
	public void insertInvoice(Invoice invoice)
	{
		if (invoice.getId() == null || invoice.getId().equals(""))	
			{
			invoiceDao.insertInvoice(invoice);
			}
		else
		{
			this.updateInvoice(invoice);
		}
	}
	
	public void updateInvoice(Invoice invoice)
	{
		invoiceDao.updateInvoice(invoice);
		
	}
}
