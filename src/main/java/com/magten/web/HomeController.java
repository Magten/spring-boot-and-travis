package com.magten.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HomeController {

	@RequestMapping({ "/", "/home" })
	public String home() {
		return "homepage";
	}
}
