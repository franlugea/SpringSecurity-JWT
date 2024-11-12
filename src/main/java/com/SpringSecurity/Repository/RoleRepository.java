package com.SpringSecurity.Repository;

import com.SpringSecurity.Entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RolesEntity,Long> {
    List<RolesEntity> findRolesEntitiesByRoleIn(List<String> roleName);
}
