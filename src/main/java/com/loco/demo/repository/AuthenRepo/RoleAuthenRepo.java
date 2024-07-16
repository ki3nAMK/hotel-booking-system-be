package com.loco.demo.repository.AuthenRepo;

import com.loco.demo.AuthenModel.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleAuthenRepo extends JpaRepository<Role, String> {
    public Optional<Role> findAllByAuthority(String authority) ;
    public Role findByRoleId(String role_id) ;
    public Optional<Role> findByAuthority(String authority) ;
}
