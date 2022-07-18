package com.cloven.dev.erpdev.core.service;

import com.amazonaws.services.cognitoidp.model.AdminSetUserPasswordResult;
import com.cloven.dev.erpdev.core.dto.UserSignInRequest;
import com.cloven.dev.erpdev.core.dto.UserSignInResponse;
import com.cloven.dev.erpdev.core.dto.UserSignUpRequest;

public interface AuthenticateService {

	AdminSetUserPasswordResult signupUserToCognitoForErpDev(UserSignUpRequest signupRequest);

	UserSignInResponse loginToErpDev(UserSignInRequest userSignInRequest);

}
