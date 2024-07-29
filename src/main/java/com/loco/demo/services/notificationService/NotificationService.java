package com.loco.demo.services.notificationService;

import java.util.Date;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.entity.Notification;

public interface NotificationService {
    public Notification makeNotification(User user, String content,Date createAt);
}
