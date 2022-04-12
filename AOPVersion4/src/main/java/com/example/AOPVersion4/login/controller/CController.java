package com.example.AOPVersion4.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CController {

	@GetMapping("/C")
	public String cString() {
		log.info("C controller");
		return "C";
	}
}
