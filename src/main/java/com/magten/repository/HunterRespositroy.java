package com.magten.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.magten.domain.Hunter;

@Repository
public interface HunterRespositroy extends JpaRepository<Hunter, String> {

	public Hunter findByUsername(String username);

	@Transactional
	public void deleteByUsername(String username);
}
