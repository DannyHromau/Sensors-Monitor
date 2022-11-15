package com.dannyhromau.sensors.repositories;

import com.dannyhromau.sensors.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByStatus(String status);
}
