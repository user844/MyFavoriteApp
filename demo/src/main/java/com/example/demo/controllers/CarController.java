package com.example.demo.controllers;

import com.example.demo.models.Car;
import com.example.demo.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


/**
 * Created by kanet on 15.01.2020.
 */

@Controller
public class CarController {

    @Autowired
    CarRepository carRepository;

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        List<Car> list = carRepository.findAll();
        modelAndView.addObject("cars", list);
        modelAndView.setViewName("car/index");
        return modelAndView;
    }

    @RequestMapping(value = "/car/add", method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        Car car = new Car();
        modelAndView.addObject("car", car);
        modelAndView.setViewName("car/add");
        return modelAndView;
    }

    @RequestMapping(value = "/car/add", method = RequestMethod.POST)
    public ModelAndView save(Car car, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        carRepository.save(car);
        car = new Car();
        modelAndView.addObject("car", car);
        modelAndView.addObject("message", "Car successfully created!");
        modelAndView.setViewName("car/add");
        return modelAndView;
    }

    @RequestMapping(value = "/car/update", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Car car = carRepository.findById(id);
        modelAndView.addObject("car", car);
        modelAndView.setViewName("car/update");
        return modelAndView;
    }

    @RequestMapping(value = "/car/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid Car car, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Car car1 = carRepository.findById(car.id);
        car1.setModel(car.getModel());
        car1.setMaxSpeed(car.getMaxSpeed());
        car1.setPrice(car.getPrice());

        carRepository.save(car1);
        modelAndView.addObject("car", car1);
        modelAndView.addObject("message", "O'zgartirish saqlandi!");
        modelAndView.setViewName("car/index");
        return modelAndView;
    }

    @RequestMapping(value = "/car/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Car car1 = carRepository.findById(id);
        carRepository.delete(car1);
        List<Car> list = carRepository.findAll();
        modelAndView.addObject("cars", list);
        modelAndView.addObject("message", "Obyekt o'chirildi!");
        modelAndView.setViewName("car/index");
        return modelAndView;
    }
}
