package com.loco.demo.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.DTO.JSON.ExceptionResponseHandler;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.Status.StatusResponseAPI;
import com.loco.demo.entity.Notification;
import com.loco.demo.services.notificationService.NotificationService;
import com.loco.demo.services.userService.UserService;

@RequestMapping("/api/v1/client")
@RestController
@CrossOrigin("*")
public class UserNotificationController {
    private NotificationService notificationService;

    @Autowired
    public UserNotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notification")
    public ListResponse<Notification> getListNotification(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        return notificationService.getListNotification(page-1, limit);
    }

    @DeleteMapping("/delete/notification/{id}")
    public ResponseEntity<ExceptionResponseHandler> deleteNotification(@PathVariable String id){
        notificationService.deleteNotification(id);
        return ResponseEntity.ok().body(new ExceptionResponseHandler(StatusResponseAPI.OK, "000", "Successful deletion", ""));
    }
}
