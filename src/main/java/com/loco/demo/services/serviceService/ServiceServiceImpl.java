package com.loco.demo.services.serviceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Service;
import com.loco.demo.repository.Service.ServiceRepo;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService{
    private ServiceRepo serviceRepo;

    @Autowired
    public ServiceServiceImpl(ServiceRepo serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    @Override
    public ListResponse<Service> findAll() {
        List<Service> list=serviceRepo.findAll();
        return new ListResponse<Service>(list, list.size()) ;
    }
}
