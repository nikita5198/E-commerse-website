package com.nik.ecom.services.auth;

import com.nik.ecom.dto.SignupRequest;
import com.nik.ecom.dto.UserDto;

public interface AuthService {
	UserDto createUser(SignupRequest signupRequest);
	Boolean hasUserWithEmail(String email);
}
