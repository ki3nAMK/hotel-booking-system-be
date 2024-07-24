package com.loco.demo.Controller.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Service;
import com.loco.demo.services.serviceService.ServiceService;

@RestController
@RequestMapping("/api/v1/service")
@CrossOrigin("*")
public class ServiceController {
    private ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ListResponse<Service> findAll(){
        return serviceService.findAll();
    }
}
