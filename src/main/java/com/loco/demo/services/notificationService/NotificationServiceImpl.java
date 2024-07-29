package com.loco.demo.services.notificationService;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.entity.Notification;
import com.loco.demo.repository.NotificationRepo.NotificationRepo;

@Service
public class NotificationServiceImpl implements NotificationService{
    private NotificationRepo notificationRepo;

    @Autowired
    public NotificationServiceImpl(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    @Override
    public Notification makeNotification(User user, String content, Date createAt) {
        Notification notification=new Notification();
        notification.setContent(content);
        notification.setCreateAt(createAt);
        notification.setId(UUID.randomUUID().toString());
        notification.setStatus((byte)0);
        notification.setUser(user);
        return notificationRepo.save(notification);
    }
}
