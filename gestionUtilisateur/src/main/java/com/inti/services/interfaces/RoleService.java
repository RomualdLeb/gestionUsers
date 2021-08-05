package com.inti.services.interfaces;

import java.util.List;

import com.inti.entities.Role;

public interface RoleService {

	Role saveRole(Role role);
	List<Role> findAll();
	Role findOne(Long id);
	void deleteRole(Long id);
	
	
}
