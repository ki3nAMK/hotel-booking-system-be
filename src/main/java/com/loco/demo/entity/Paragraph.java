package com.loco.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paragraph")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Paragraph {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @Lob
    @Column(name = "header", columnDefinition = "TEXT")
    private String header;
    @Column(name = "info", columnDefinition = "TEXT")
    private String info;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guide_id")
    private Guide guide;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Service service;
    @Column(name = "thumbnail", length = 255)
    private String thumbnail;
}
