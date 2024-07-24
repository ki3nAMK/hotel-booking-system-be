package com.loco.demo.services.serviceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loco.demo.repository.Service.ServiceRepo;

@Service
public class ServiceServiceImpl implements ServiceService{
    private ServiceRepo serviceRepo;

    @Autowired
    public ServiceServiceImpl(ServiceRepo serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    @Override
    public List<com.loco.demo.entity.Service> findAll() {
        return serviceRepo.findAll();
    }
}
