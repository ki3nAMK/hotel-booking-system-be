package com.loco.demo.repository.NotificationRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.entity.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,String>{
    public Page<Notification> findByUserOrderByCreateAtDesc(Pageable pageable,User user);
}
