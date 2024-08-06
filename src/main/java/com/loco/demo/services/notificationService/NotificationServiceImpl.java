package com.loco.demo.services.notificationService;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Notification;
import com.loco.demo.repository.NotificationRepo.NotificationRepo;
import com.loco.demo.services.userService.UserService;

@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepo notificationRepo;
    private UserService userService;

    @Autowired
    public NotificationServiceImpl(NotificationRepo notificationRepo, UserService userService) {
        this.notificationRepo = notificationRepo;
        this.userService = userService;
    }

    @Override
    public Notification makeNotification(User user, String content, Date createAt) {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setCreateAt(createAt);
        notification.setId(UUID.randomUUID().toString());
        notification.setStatus((byte) 0);
        notification.setUser(user);
        return notificationRepo.save(notification);
    }

    @Override
    public ListResponse<Notification> getListNotification(int page, int limit) {
        User myuser = userService.getMyInfo();
        Pageable pageable = PageRequest.of(page, limit);
        Page<Notification> pageNotification = notificationRepo.findByUserOrderByCreateAtDesc(pageable, myuser);
        return new ListResponse<Notification>(pageNotification.getContent(), pageNotification.getTotalElements());
    }

    @Override
    public void deleteNotification(String id) {
        String myUserId = userService.getMyInfo().getUserId();
        String deleteUserId = notificationRepo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("NOT FOUND NOTIFICATION IN DATABASE !!!"))
                .getUser().getUserId();
        if(myUserId.equals(deleteUserId)){
            notificationRepo.deleteById(id);
        }
        else throw new RuntimeException("You can't delete notifcation not your own");
    }
}
