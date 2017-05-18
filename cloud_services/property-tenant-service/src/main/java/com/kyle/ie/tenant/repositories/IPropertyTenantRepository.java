package com.kyle.ie.tenant.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.kyle.ie.tenant.models.PropertyTenant;

@EnableScan
public interface IPropertyTenantRepository extends CrudRepository<PropertyTenant, String> {

	List<PropertyTenant> findByPropertyId(String propertyId);
	
	PropertyTenant findByAccountId(int accountId);
}
