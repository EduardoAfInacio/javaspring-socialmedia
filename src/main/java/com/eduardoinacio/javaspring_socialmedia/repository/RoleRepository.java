package com.eduardoinacio.javaspring_socialmedia.repository;

import com.eduardoinacio.javaspring_socialmedia.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
