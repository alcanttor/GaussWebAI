package com.mg.userManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.RuleGroup;

@Repository
public interface RuleGroupRepository extends JpaRepository<RuleGroup, Integer>{
	
}
