package com.nik.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nik.ecom.entity.User;
import com.nik.ecom.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findFirstByEmail(String email);

	User findByRole(UserRole userRole);
}
