package com.loco.demo.repository.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loco.demo.entity.Service;

@Repository
public interface ServiceRepo extends JpaRepository<Service, String> {
    
}
