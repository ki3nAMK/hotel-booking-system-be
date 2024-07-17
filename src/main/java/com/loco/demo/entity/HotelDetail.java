package com.loco.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDetail {
    @Id
    @Column(name="id",length=50)
    private String id;
    private String title;
    @Lob
    private String content;
}
