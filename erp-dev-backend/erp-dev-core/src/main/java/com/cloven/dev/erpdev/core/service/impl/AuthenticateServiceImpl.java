package com.cloven.dev.erpdev.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException;
import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupRequest;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.AdminSetUserPasswordRequest;
import com.amazonaws.services.cognitoidp.model.AdminSetUserPasswordResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
import com.amazonaws.services.cognitoidp.model.DeliveryMediumType;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.MessageActionType;
import com.cloven.dev.erodev.model.entity.User;
import com.cloven.dev.erpdev.core.dto.UserSignInRequest;
import com.cloven.dev.erpdev.core.dto.UserSignInResponse;
import com.cloven.dev.erpdev.core.dto.UserSignUpRequest;
import com.cloven.dev.erpdev.core.exception.CoreException;
import com.cloven.dev.erpdev.core.repository.UserRepository;
import com.cloven.dev.erpdev.core.service.AuthenticateService;
import com.cloven.dev.erpdev.core.utils.RoleEnum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {

	@Value(value = "${aws.cognito.userPoolId}")
	private String userPoolId;

	@Value(value = "${aws.cognito.clientId}")
	private String clientId;

	@Autowired
	private AWSCognitoIdentityProvider cognitoClient;

	@Autowired
	private UserRepository userRepository;

	@Override
	public AdminSetUserPasswordResult signupUserToCognitoForErpDev(UserSignUpRequest userSignUpRequest) {
		log.info("Initializing the Method to Sign Up User to ERP-DEV");
		AdminSetUserPasswordResult result = new AdminSetUserPasswordResult();
		try {

			AttributeType emailAttr = new AttributeType().withName("email").withValue(userSignUpRequest.getEmail());
			AttributeType emailVerifiedAttr = new AttributeType().withName("email_verified").withValue("true");

			AdminCreateUserRequest userRequest = new AdminCreateUserRequest().withUserPoolId(userPoolId)
					.withUsername(userSignUpRequest.getEmail()).withTemporaryPassword(userSignUpRequest.getPassword())
					.withUserAttributes(emailAttr, emailVerifiedAttr).withMessageAction(MessageActionType.SUPPRESS)
					.withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);

			AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(userRequest);

			System.out.println("User " + createUserResult.getUser().getUsername() + " is created. Status: "
					+ createUserResult.getUser().getUserStatus());

			// Disable force change password during first login
			AdminSetUserPasswordRequest adminSetUserPasswordRequest = new AdminSetUserPasswordRequest()
					.withUsername(userSignUpRequest.getEmail()).withUserPoolId(userPoolId)
					.withPassword(userSignUpRequest.getPassword()).withPermanent(true);

			addUserToGroup(createUserResult.getUser().getUsername(), userSignUpRequest.getRoles());
			userRepository.saveCustomer(User.builder().email(userSignUpRequest.getEmail())
					.username(userSignUpRequest.getEmail()).firstName(userSignUpRequest.getFistname())
					.lastName(userSignUpRequest.getLastname()).build());
			return cognitoClient.adminSetUserPassword(adminSetUserPasswordRequest);

		} catch (AWSCognitoIdentityProviderException e) {
			System.out.println(e.getErrorMessage());
		} catch (Exception e) {
			System.out.println("Failed to Sign up user" + e.getCause());
		}
		return result;
	}

	@Override
	public UserSignInResponse loginToErpDev(UserSignInRequest userSignInRequest) {
		log.info("Initializing the Method to Login User to ERP-DEV");

		UserSignInResponse userSignInResponse = new UserSignInResponse();

		final Map<String, String> authParams = new HashMap<>();
		authParams.put("USERNAME", userSignInRequest.getUsername());
		authParams.put("PASSWORD", userSignInRequest.getPassword());

		final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
		authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withClientId(clientId).withUserPoolId(userPoolId)
				.withAuthParameters(authParams);

		try {
			AdminInitiateAuthResult result = cognitoClient.adminInitiateAuth(authRequest);

			AuthenticationResultType authenticationResult = null;

			if (result.getChallengeName() != null && !result.getChallengeName().isEmpty()) {

				System.out.println("Challenge Name is " + result.getChallengeName());

				if (result.getChallengeName().contentEquals("NEW_PASSWORD_REQUIRED")) {
					if (userSignInRequest.getPassword() == null) {
						throw new CoreException("User must change password " + result.getChallengeName());

					} else {

						final Map<String, String> challengeResponses = new HashMap<>();
						challengeResponses.put("USERNAME", userSignInRequest.getUsername());
						challengeResponses.put("PASSWORD", userSignInRequest.getPassword());
						// add new password
						// challengeResponses.put("NEW_PASSWORD", userSignInRequest.getNewPassword());

						final AdminRespondToAuthChallengeRequest request = new AdminRespondToAuthChallengeRequest()
								.withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
								.withChallengeResponses(challengeResponses).withClientId(clientId)
								.withUserPoolId(userPoolId).withSession(result.getSession());

						AdminRespondToAuthChallengeResult resultChallenge = cognitoClient
								.adminRespondToAuthChallenge(request);
						authenticationResult = resultChallenge.getAuthenticationResult();

						userSignInResponse.setAccessToken(authenticationResult.getAccessToken());
						userSignInResponse.setIdToken(authenticationResult.getIdToken());
						userSignInResponse.setRefreshToken(authenticationResult.getRefreshToken());
						userSignInResponse.setExpiresIn(authenticationResult.getExpiresIn());
						userSignInResponse.setTokenType(authenticationResult.getTokenType());
					}

				} else {
					throw new CoreException("User has other challenge " + result.getChallengeName());
				}
			} else {

				System.out.println("User has no challenge");
				authenticationResult = result.getAuthenticationResult();

				userSignInResponse.setAccessToken(authenticationResult.getAccessToken());
				userSignInResponse.setIdToken(authenticationResult.getIdToken());
				userSignInResponse.setRefreshToken(authenticationResult.getRefreshToken());
				userSignInResponse.setExpiresIn(authenticationResult.getExpiresIn());
				userSignInResponse.setTokenType(authenticationResult.getTokenType());
			}

		} catch (InvalidParameterException e) {
			throw new CoreException(e.getErrorMessage());
		} catch (Exception e) {
			throw new CoreException(e.getMessage());
		}
		return userSignInResponse;
	}

	public void addUserToGroup(String username, String groupname) {

		if (groupname.equals("Admin")) {
			groupname = RoleEnum.ROLE_ADMIN.getDescription();
		} else if (groupname.equals("Teacher")) {
			groupname = RoleEnum.ROLE_TEACHER.getDescription();
		} else if (groupname.equals("Student")) {
			groupname = RoleEnum.ROLE_STUDENT.getDescription();
		} else if (groupname.equals("Super Admin")) {
			groupname = RoleEnum.ROLE_SUPERADMIN.getDescription();
		}
		AdminAddUserToGroupRequest addUserToGroupRequest = new AdminAddUserToGroupRequest().withGroupName(groupname)
				.withUserPoolId(userPoolId).withUsername(username);

		cognitoClient.adminAddUserToGroup(addUserToGroupRequest);

	}

}
