package com.mg.userManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.Connector;

@Repository
public interface ConnectorRepository extends JpaRepository<Connector, Integer>{
	
	//public Optional<Rule>

}
