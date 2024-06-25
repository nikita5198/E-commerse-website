package com.nik.ecom.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nik.ecom.dto.ForgetPasswordDto;
import com.nik.ecom.dto.SignupRequest;
import com.nik.ecom.dto.UserDto;
import com.nik.ecom.entity.Order;
import com.nik.ecom.entity.User;
import com.nik.ecom.enums.OrderStatus;
import com.nik.ecom.enums.UserRole;
import com.nik.ecom.repository.OrderRepository;
import com.nik.ecom.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AuthserviceImpl implements AuthService {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public UserDto createUser(SignupRequest signupRequest) {
	
		User user=new User();
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(bcryptPasswordEncoder.encode(signupRequest.getPassword()));
		user.setRole(UserRole.CUSTOMER);
		UserDto userDto=new UserDto();
		User createdUser=userRepository.save(user);
		
		Order order=new Order();
		order.setAmount(0L);
		order.setTotalAmount(0L);
		order.setDiscount(0L);
		order.setUser(createdUser);
		order.setOrderStatus(OrderStatus.Pending);
		orderRepository.save(order);
		
		userDto.setId(createdUser.getId());
		return userDto;
	}
	public Boolean hasUserWithEmail(String email) {
		return userRepository.findFirstByEmail(email).isPresent();
	}
	
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount=userRepository.findByRole(UserRole.ADMIN);
		if(null==adminAccount) {
			User user=new User();
			user.setEmail("admin@test.com");
			user.setName("admin");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
	
	
}
