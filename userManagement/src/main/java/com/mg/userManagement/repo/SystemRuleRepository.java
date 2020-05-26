package com.mg.userManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.SystemRule;

@Repository
public interface SystemRuleRepository extends JpaRepository<SystemRule, Integer>{
	
	//public Optional<SystemRule> findBy
	public List<SystemRule> findByConnectorId(Integer connectorId);
	public List<SystemRule> findByConnectorIdAndSystemEventId(Integer connectorId,Integer SystemEventId);
}
