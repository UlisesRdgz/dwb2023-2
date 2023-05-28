package com.customer.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.api.entity.Region;

@Repository
public interface RepoRegion extends JpaRepository<Region, Integer>{
	
	@Query(value = "SELECT * FROM region WHERE status = :status", nativeQuery = true)
	List<Region> findByStatus(@Param("status") Integer status);

	@Query(value = "SELECT * FROM region WHERE region_id = :region_id AND status = 1", nativeQuery = true)
	Region findByRegionId(@Param("region_id") Integer region_id);
	
	@Query(value = "SELECT * FROM region WHERE region = :region", nativeQuery = true)
	Region findByRegion(@Param("region") String region);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO region (region,status) VALUES(:region,1)", nativeQuery = true)
	void createRegion(@Param("region") String region);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE region SET region = :region WHERE region_id = :region_id", nativeQuery = true)
	Integer updateRegion(@Param("region_id") Integer region_id, @Param("region") String region);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE region SET status = 1 WHERE region_id = :region_id", nativeQuery = true)
	Integer activateRegion(@Param("region_id") Integer region_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE region SET status = 0 WHERE region_id = :region_id", nativeQuery = true)
	void deleteById(@Param("region_id") Integer region_id);
}
