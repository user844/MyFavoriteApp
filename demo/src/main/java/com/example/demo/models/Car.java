package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by kanet on 15.01.2020.
 */
@Data
@Entity
@Table(name = "tbl_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;

    @Column(name = "model")
    public String model;

    @Column(name = "max_speed")
    public int maxSpeed;

    @Column(name = "price")
    public double price;
}
