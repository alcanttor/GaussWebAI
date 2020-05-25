package com.mg.userManagement.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.EmailLable;
import com.mg.userManagement.entity.User;

@Repository
public interface EmailLableRepository extends JpaRepository<EmailLable, Integer> {
	
	public Optional<List<EmailLable>> findByUser (User user);

}
