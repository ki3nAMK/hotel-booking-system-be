package com.loco.demo.entity;

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
@Table(name = "service")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Service {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "distant")
    private Integer distant;
    @Column(name = "rating")
    private Byte rating;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location locationId;
}
