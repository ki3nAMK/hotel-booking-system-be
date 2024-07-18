package com.loco.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel_detail")
public class HotelDetail {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @Column(name = "title", length = 255)
    private String title;
    @Lob
    @Column(columnDefinition = "TEXT", name = "content")
    private String content;
}
