package com.loco.demo.entity;

import java.util.Date;

import com.loco.demo.AuthenModel.User;

import jakarta.persistence.CascadeType;
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

@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @OneToOne(fetch = FetchType.EAGER)
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
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;
    @Column(name = "status")
    private Byte status;

}
