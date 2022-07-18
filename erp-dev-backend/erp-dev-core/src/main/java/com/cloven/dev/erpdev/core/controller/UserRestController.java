package com.cloven.dev.erpdev.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.cognitoidp.model.AdminSetUserPasswordResult;
import com.cloven.dev.erpdev.core.dto.UserSignInRequest;
import com.cloven.dev.erpdev.core.dto.UserSignInResponse;
import com.cloven.dev.erpdev.core.dto.UserSignUpRequest;
import com.cloven.dev.erpdev.core.service.AuthenticateService;

@RestController
@CrossOrigin
@RequestMapping("/user-authenticate")
public class UserRestController {

	@Autowired
	private AuthenticateService authenticateService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserSignInRequest authenticationRequest,
			HttpServletRequest httpServletRequest) throws Exception {
		UserSignInResponse loginResponse = authenticateService.loginToErpDev(authenticationRequest);
		return ResponseEntity.ok(loginResponse);

	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserSignUpRequest signupRequest,
			HttpServletRequest httpServletRequest) throws Exception {
		AdminSetUserPasswordResult signupResponse = authenticateService.signupUserToCognitoForErpDev(signupRequest);
		return ResponseEntity.ok(signupResponse);

	}
}
