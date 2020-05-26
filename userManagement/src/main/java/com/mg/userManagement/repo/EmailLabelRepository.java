package com.mg.userManagement.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.EmailLabel;
import com.mg.userManagement.entity.User;

@Repository
public interface EmailLabelRepository extends JpaRepository<EmailLabel, Integer> {
	
	public Optional<List<EmailLabel>> findByUser (User user);

}
