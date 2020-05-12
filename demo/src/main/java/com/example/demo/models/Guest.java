package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kanet on 15.01.2020.
 */
@Data
@Entity
@Table(name = "tbl_guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;

    @Column(name = "fullname")
    public String fullname;

    @Column(name = "serialpass")
    public String serialpass;

    @Column(name = "country")
    public String country;


    @Column(name = "comment")
    public String comment;

    @Column(name = "daydate")
    public String daydate;


}
