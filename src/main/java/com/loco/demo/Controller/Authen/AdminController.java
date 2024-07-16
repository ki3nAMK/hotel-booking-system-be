package com.loco.demo.Controller.Authen;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
@CrossOrigin("*")
public class AdminController {
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET,value = "")
    public String getDefaultAdminAPI() {
        return "YOUR ROLE IS ADMIN" ;
    }
}
