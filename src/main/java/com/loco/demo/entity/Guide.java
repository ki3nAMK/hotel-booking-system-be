package com.loco.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guide")
public class Guide {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @Column(name = "thumbnail", length = 255)
    private String thumbnail;
}
