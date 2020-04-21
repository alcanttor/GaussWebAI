package com.mg.userManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
