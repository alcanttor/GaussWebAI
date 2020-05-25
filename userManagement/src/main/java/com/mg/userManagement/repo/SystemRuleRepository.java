package com.mg.userManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.SystemRule;

@Repository
public interface SystemRuleRepository extends JpaRepository<SystemRule, Integer>{
	
	//public Optional<SystemRule> findBy

}
