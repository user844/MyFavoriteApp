package com.example.demo.repositories;

import com.example.demo.models.Car;
import com.example.demo.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    public List<Guest> findByComment(String comment);
    Guest findById(int id);
}