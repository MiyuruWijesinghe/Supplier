package com.abc.supplier.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.abc.supplier.model.Supplier;
import com.abc.supplier.resource.SupplierResource;

/**
 * Supplier Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   10-05-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface SupplierService {

	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Supplier> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Supplier> findById(String id);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<Supplier> findByStatus(String status);
	
	
	/**
	 * Find by person id.
	 *
	 * @param personId - the person id
	 * @return the optional
	 */
	public Optional<Supplier> findByPersonId(String personId);
	
	
	/**
	 * Save supplier.
	 *
	 * @param username - the username
	 * @param supplierResource - the supplier resource
	 * @return the supplier
	 */
	public Supplier saveSupplier(String username, SupplierResource supplierResource);
	
	
	/**
	 * Update supplier.
	 *
	 * @param id - the id
	 * @param username - the username
	 * @param supplierResource - the supplier resource
	 * @return the supplier
	 */
	public Supplier updateSupplier(String id, String username, SupplierResource supplierResource);
	
	
	/**
	 * Delete supplier.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteSupplier(String id);
	
}
