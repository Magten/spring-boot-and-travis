package com.magten.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magten.domain.Hunter;
import com.magten.repository.HunterRespositroy;

@RestController
@RequestMapping("/hunter")
public class HunterController {

	@Autowired
	public HunterRespositroy hunterRespositroy;

	// hard code in this moment
	@RequestMapping("/add")
	public Hunter add(String username) {
		Hunter hunter = new Hunter(username);
		hunter.setID(UUID.randomUUID().toString());
		hunter.setBalance(new BigDecimal(10));
		hunter.setIsActivated(false);
		hunter.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		hunterRespositroy.save(hunter);
		return hunter;
	}

	@RequestMapping("/all")
	public List<Hunter> getAll() {
		return hunterRespositroy.findAll();
	}

	@RequestMapping("/find")
	public Hunter getHunter(String username) {
		return hunterRespositroy.findByUsername(username);
	}

	@RequestMapping("/remove")
	public Hunter remove(String username) {
		Hunter hunter = hunterRespositroy.findByUsername(username);
		hunterRespositroy.deleteByUsername(username);
		return hunter;
	}

	// hard code in this moment
	@RequestMapping("/save")
	public Hunter save(String username) {
		Hunter hunter = hunterRespositroy.findByUsername(username);
		hunter.setBalance(hunter.getBalance().add(hunter.getBalance()));
		return hunterRespositroy.save(hunter);
	}
}
