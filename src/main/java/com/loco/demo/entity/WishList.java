package com.loco.demo.entity;

import java.util.Date;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.HotelDTO;
import com.loco.demo.utils.Converters.SecureUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @Column(name = "create_at", columnDefinition = "DATETIME")
    private Date createAt;

    public SecureUser getUser() {
        return new SecureUser(this.user);
    }

    public HotelDTO getHotel() {
        return new HotelDTO(this.hotel);
    }
}
