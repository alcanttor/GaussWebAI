package com.mg.userManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.SystemEvent;

@Repository
public interface SystemEventRepository extends JpaRepository<SystemEvent, Integer>{
	
	//public Optional<Rule>

}
