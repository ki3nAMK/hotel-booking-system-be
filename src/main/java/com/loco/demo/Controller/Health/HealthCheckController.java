package com.loco.demo.Controller.Health;

import com.loco.demo.DTO.JSON.ExceptionResponseHandler;
import com.loco.demo.DTO.Status.StatusResponseAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/health")
@CrossOrigin
public class HealthCheckController {
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET,value = "/check")
    public ExceptionResponseHandler getDefaultAdminAPI() {
        return new ExceptionResponseHandler(StatusResponseAPI.OK , "001", "Check health done !!", "");
    }
}
