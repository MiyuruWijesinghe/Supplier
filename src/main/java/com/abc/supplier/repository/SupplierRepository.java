package com.abc.supplier.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.abc.supplier.model.Supplier;

/**
 * Supplier Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   10-05-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {

	public Optional<Supplier> findById(String id);
	
	public List<Supplier> findByStatus(String status);

	public Optional<Supplier> findByPersonId(String personId);

	public Optional<Supplier> findByIdAndStatus(String id, String name);

	public Boolean existsByPersonId(String personId);

	public Boolean existsByPersonIdAndIdNotIn(String personId, String id);
	
	public void deleteById(String id);
}