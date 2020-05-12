package com.example.demo.repositories;

import com.example.demo.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kanet on 15.01.2020.
 */
@Repository("carRepository")
public interface CarRepository extends JpaRepository<Car, Long> {
    public List<Car> findByModel(String model);

    Car findById(int id);
}
