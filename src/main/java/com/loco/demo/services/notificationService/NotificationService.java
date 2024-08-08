package com.loco.demo.services.notificationService;

import java.util.Date;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Notification;

public interface NotificationService {
    public Notification makeNotification(User user, String content,Date createAt);

    public ListResponse<Notification> getListNotification(int page, int limit);

    public void deleteNotification(String id);
}
