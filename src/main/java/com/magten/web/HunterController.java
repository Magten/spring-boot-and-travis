package com.magten.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magten.domain.Hunter;
import com.magten.repository.HunterRespositroy;

@RestController
@RequestMapping("/hunter")
public class HunterController {

	@Autowired
	public HunterRespositroy hunterRespositroy;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Hunter add(String username, String password) {
		Hunter hunter = new Hunter(username, password);
		hunter.setId(UUID.randomUUID().toString());
		hunter.setBalance(new BigDecimal(0));
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

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Hunter save(Hunter hunter) {
		return hunterRespositroy.save(hunter);
	}

}
