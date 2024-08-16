package com.loco.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.HotelDTO;
import com.loco.demo.utils.Converters.SecureUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "create_at", columnDefinition = "DATETIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Date createAt;
    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
    @JoinColumn(name = "rating_id")
    private HotelRating rating;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public SecureUser getUser() {
        return new SecureUser(this.user);
    }

    public HotelDTO getHotel() {
        return new HotelDTO(this.hotel);
    }
}
