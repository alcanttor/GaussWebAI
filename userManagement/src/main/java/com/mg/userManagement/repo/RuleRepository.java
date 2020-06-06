package com.mg.userManagement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer>{
	
	public Optional<Rule> findBysystemRuleId(Integer systemRuleId);

}
