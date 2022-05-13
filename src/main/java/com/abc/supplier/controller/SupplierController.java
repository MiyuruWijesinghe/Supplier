package com.abc.supplier.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abc.supplier.model.Supplier;
import com.abc.supplier.resource.SuccessAndErrorDetailsResource;
import com.abc.supplier.resource.SupplierResource;
import com.abc.supplier.service.SupplierService;

/**
 * Supplier Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   10-05-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/supplier")
@CrossOrigin(origins = "*")
public class SupplierController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private SupplierService supplierService;
	
	
	/**
	 * Gets the all suppliers.
	 *
	 * @return the all suppliers
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllSuppliers() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Supplier> supplier = supplierService.findAll();
		if (!supplier.isEmpty()) {
			return new ResponseEntity<>((Collection<Supplier>) supplier, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the supplier by id.
	 *
	 * @param id - the id
	 * @return the supplier by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getSupplierById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Supplier> isPresentSupplier = supplierService.findById(id);
		if (isPresentSupplier.isPresent()) {
			return new ResponseEntity<>(isPresentSupplier, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the suppliers by status.
	 *
	 * @param status - the status
	 * @return the suppliers by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getSuppliersByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Supplier> supplier = supplierService.findByStatus(status);
		if (!supplier.isEmpty()) {
			return new ResponseEntity<>((Collection<Supplier>) supplier, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
		
	
	/**
	 * Gets the supplier by person id.
	 *
	 * @param personId - the person id
	 * @return the supplier by person id
	 */
	@GetMapping(value = "/person/{personId}")
	public ResponseEntity<Object> getSupplierByPersonId(@PathVariable(value = "personId", required = true) String personId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Supplier> isPresentSupplier = supplierService.findByPersonId(personId);
		if (isPresentSupplier.isPresent()) {
			return new ResponseEntity<>(isPresentSupplier, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the supplier.
	 *
	 * @param username - the username
	 * @param supplierResource - the supplier resource
	 * @return the response entity
	 */
	@PostMapping(value = "/{username}/save")
	public ResponseEntity<Object> addSupplier(@PathVariable(value = "username", required = true) String username,
											  @Valid @RequestBody SupplierResource supplierResource) {
		Supplier supplier = supplierService.saveSupplier(username, supplierResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), supplier);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update supplier.
	 *
	 * @param username - the username
	 * @param id - the id
	 * @param supplierResource - the supplier resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{username}/{id}")
	public ResponseEntity<Object> updateSupplier(@PathVariable(value = "username", required = true) String username,
											     @PathVariable(value = "id", required = true) String id,
											     @Valid @RequestBody SupplierResource supplierResource) {
		Supplier supplier = supplierService.updateSupplier(id, username, supplierResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), supplier);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete supplier.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteSupplier(@PathVariable(value = "id", required = true) String id) {
		String message = supplierService.deleteSupplier(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
}