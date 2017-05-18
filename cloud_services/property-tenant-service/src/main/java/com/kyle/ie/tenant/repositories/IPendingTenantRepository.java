package com.kyle.ie.tenant.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.kyle.ie.tenant.models.PendingTenant;

@EnableScan
public interface IPendingTenantRepository extends CrudRepository<PendingTenant, String>{
	
	PendingTenant findByPropertyId(String propertyId);
}
