package com.cloven.dev.erpdev.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloven.dev.erpdev.core.dto.MessageDTO;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@GetMapping("/message")
	@Secured(value = "ROLE_ADMIN")
	public ResponseEntity<?> message(@AuthenticationPrincipal(expression = "name") String name) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		authentication.getAuthorities().forEach(authority ->{
			System.out.println(authority.getAuthority());
		});
		return ResponseEntity.ok(new MessageDTO("Admin message for " + name));
	}
}