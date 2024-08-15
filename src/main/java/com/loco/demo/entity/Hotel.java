package com.loco.demo.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.loco.demo.AuthenModel.User;
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
@Table(name = "hotel")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hotel {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @Column(name = "create_at", columnDefinition = "DATETIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Date createAt;
    @Column(name = "thumbnail", length = 255)
    private String thumbnail;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "location", length = 255)
    private String location;
    @Column(name = "price")
    private Integer price;
    @Column(name = "min_price")
    private Integer minPrice;
    @Column(name = "max_price")
    private Integer maxPrice;
    @Column(name = "min_period")
    private Byte minPeriod;
    @Column(name = "medium_period")
    private Byte mediumPeriod;
    @Column(name = "max_period")
    private Byte maxPeriod;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "furniture_id")
    private Furniture furniture;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private User seller;
    @Lob
    @Column(name = "img_list", columnDefinition = "TEXT")
    private String imgList;
    @Column(name = "amenities", length = 255)
    private String amenities;
    @Column(name = "safety", length = 255)
    private String safety;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "detail_id")
    private HotelDetail detail;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rating_id")
    private HotelRating rating;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "type")
    private Integer type;
    @Column(name = "status")
    private Byte status;
    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "location_id")
    private Location locationId;
    @Column(name = "slug", length = 255)
    private String slug;

    public List<String> getImgList() {
        return this.imgList != null ? Arrays.asList(this.imgList.split("\\*")) : Collections.emptyList();
    }

    public List<String> getAmenities() {
        return Arrays.asList(this.amenities.split("\\*"));
    }

    public List<String> getSafety() {
        return Arrays.asList(this.safety.split("\\*"));
    }

    public SecureUser getSeller() {
        return new SecureUser(this.seller);
    }

    public User OriginalUser() {
        return this.seller;
    }
}
