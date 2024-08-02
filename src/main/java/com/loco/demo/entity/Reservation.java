package com.loco.demo.entity;

import java.util.Date;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.utils.Converters.SecureUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @Column(name = "check_in", columnDefinition = "DATE")
    private Date checkIn;
    @Column(name = "check_out", columnDefinition = "DATE")
    private Date checkOut;
    @Column(name = "guest")
    private Integer guest;
    @Column(name = "price")
    private Integer price;
    @Column(name = "is_past")
    private Boolean isPast;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;
    @Column(name = "status")
    private Byte status;

    public SecureUser getUser(){
        return new SecureUser(this.user);
    }

    public User getOriginalUser(){
        return user;
    }

    public SecureUser getOwner(){
        return new SecureUser(this.owner);
    }

    public User getOriginalOwner(){
        return owner;
    }
}
