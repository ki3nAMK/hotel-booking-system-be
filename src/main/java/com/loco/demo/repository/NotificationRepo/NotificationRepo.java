package com.loco.demo.repository.NotificationRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loco.demo.entity.Notification;

public interface NotificationRepo extends JpaRepository<Notification,String>{
}
