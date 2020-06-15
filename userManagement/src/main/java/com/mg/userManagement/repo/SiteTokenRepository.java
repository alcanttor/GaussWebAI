package com.mg.userManagement.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.Site;
import com.mg.userManagement.entity.SiteToken;
import com.mg.userManagement.entity.User;

@Repository
public interface SiteTokenRepository extends JpaRepository<SiteToken, Integer> {
	
	public Optional<SiteToken> findByToken(String siteToken);

}
