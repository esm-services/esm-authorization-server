package com.esm.authorization.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esm.authorization.server.domain.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByRole(String roleName);
}