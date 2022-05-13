package com.example.task_1_prekt.Repository;


import com.example.task_1_prekt.Entity.Role;
import com.example.task_1_prekt.Entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findAllByName(RoleName name);
}
