/**
 * 
 */
package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Role;
import com.deloitte.elrr.repository.RoleRepository;

/**
 * @author mnelakurti
 *
 */

@Service
public class RoleSvc implements CommonSvc<Role, Long> {
	private final RoleRepository roleRepository;

	public RoleSvc(final RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public CrudRepository<Role, Long> getRepository() {
		return this.roleRepository;
	}

	@Override
	public Long getId(Role role) {
		return role.getRoleid();
	}

	@Override
	public Role save(Role role) {
		return CommonSvc.super.save(role);
	}

}
