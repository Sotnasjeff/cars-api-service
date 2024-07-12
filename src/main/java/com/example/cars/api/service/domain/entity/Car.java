package com.example.cars.api.service.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String description;
    @Column(name = "url_photo")
    private String urlPhoto;
    @Column(name = "url_video")
    private String urlVideo;
    private String latitude;
    private String longitude;


    public Car(String name, String type) {
        this.name = name;
        this.type = type;
    }

}
