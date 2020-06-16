package com.mg.userManagement.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mg.userManagement.entity.Site;
import com.mg.userManagement.entity.User;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {
	
	public Optional<Site> getByName(String siteName);
	public Optional<List<Site>> getByUser (User user);
	@Query("Select site from Site site where site.name=:siteName and site.siteToken.token=:token")
	public Site getByNameAndToken(@Param("siteName") String siteName, @Param("token") String token);

}
