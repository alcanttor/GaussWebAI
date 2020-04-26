package com.mg.userManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.Action;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer>{
	
	//public Optional<Rule>

}
