package com.loco.demo.entity;

import com.loco.demo.AuthenModel.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wish_list")
public class WishList {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id",unique = true)
    private User user;
}
