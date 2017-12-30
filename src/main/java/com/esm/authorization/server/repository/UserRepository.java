package com.esm.authorization.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esm.authorization.server.domain.User;

/**
 * Access to the user data. JpaRepository grants us convenient access methods here
 */
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * Find a user by user name
	 *
	 * @param username the user's user name
	 * @return user which contains the user with the given user name or null.
	 */
	Optional<User> findByUsername(String username);
}
