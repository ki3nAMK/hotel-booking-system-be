package com.loco.demo.services.serviceService;

import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Service;

public interface ServiceService {
    public ListResponse<Service> findAll();
}

