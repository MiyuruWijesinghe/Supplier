package com.abc.supplier.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.abc.supplier.exception.NoRecordFoundException;
import com.abc.supplier.exception.ValidateRecordException;
import com.abc.supplier.model.Supplier;
import com.abc.supplier.repository.SupplierRepository;
import com.abc.supplier.resource.SupplierResource;
import com.abc.supplier.service.SupplierService;

/**
 * Supplier Service Implementation
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   10-05-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		return format.format(date);
	}
	
	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public Optional<Supplier> findById(String id) {
		Optional<Supplier> supplier = supplierRepository.findById(id);
		if (supplier.isPresent()) {
			return Optional.ofNullable(supplier.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Supplier> findByStatus(String status) {
		return supplierRepository.findByStatus(status);
	}

	@Override
	public Optional<Supplier> findByPersonId(String personId) {
		Optional<Supplier> supplier = supplierRepository.findByPersonId(personId);
		if (supplier.isPresent()) {
			return Optional.ofNullable(supplier.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Supplier saveSupplier(String username, SupplierResource supplierResource) {
		Supplier supplier = new Supplier();
		
        if (supplierRepository.existsByPersonId(supplierResource.getPersonId())) {
        	throw new ValidateRecordException(environment.getProperty("supplier.duplicate"), "message");
		}
		
        supplier.setPersonId(supplierResource.getPersonId());
        supplier.setStatus(supplierResource.getStatus());
        supplier.setCreatedUser(username);
        supplier.setCreatedDate(formatDate(new Date()));
        supplierRepository.save(supplier);
		return supplier;
	}

	@Override
	public Supplier updateSupplier(String id, String username, SupplierResource supplierResource) {
		Optional<Supplier> isPresentSupplier = supplierRepository.findById(id);
		if (!isPresentSupplier.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (supplierRepository.existsByPersonIdAndIdNotIn(supplierResource.getPersonId(), id)) {
			throw new ValidateRecordException(environment.getProperty("supplier.duplicate"), "message");
		}
		
		Supplier supplier = isPresentSupplier.get();
		supplier.setPersonId(supplierResource.getPersonId());
		supplier.setStatus(supplierResource.getStatus());
		supplier.setModifiedUser(username);
		supplier.setModifiedDate(formatDate(new Date()));
		supplierRepository.save(supplier);
		return supplier;
	}

	@Override
	public String deleteSupplier(String id) {
		Optional<Supplier> isPresentSupplier = supplierRepository.findById(id);
		if (!isPresentSupplier.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		supplierRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

}
