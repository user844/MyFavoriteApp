package com.example.demo.controllers;

import com.example.demo.models.Car;
import com.example.demo.models.Guest;
import com.example.demo.repositories.GuestRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by kanet on 15.01.2020.
 */
@Data
@Controller
public class GuestController {

    @Autowired
    GuestRepository guestRepository;

    @RequestMapping(value = "/guest", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        List<Guest> list = guestRepository.findAll();
        modelAndView.addObject("guests", list);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @RequestMapping(value = "/guest/add", method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        Guest guest = new Guest();
        modelAndView.addObject("guest", guest);
        modelAndView.setViewName("guest/add");
        return modelAndView;
    }

    @RequestMapping(value = "/guest/add", method = RequestMethod.POST)
    public ModelAndView save(Guest guest, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        guestRepository.save(guest);
        guest = new Guest();
        modelAndView.addObject("guest", guest);
        modelAndView.addObject("message", "Guest successfully created!");
        modelAndView.setViewName("guest/add");
        return modelAndView;
    }

    @RequestMapping(value = "/guest/update", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Guest guest = guestRepository.findById(id);
        modelAndView.addObject("guest", guest);
        modelAndView.setViewName("guest/update");
        return modelAndView;
    }

    @RequestMapping(value = "/guest/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid Guest guest, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Guest guest1 = guestRepository.findById(guest.id);
        guest1.setFullname(guest.getFullname());
        guest1.setSerialpass(guest.getSerialpass());
        guest1.setCountry(guest.getCountry());
        guest1.setComment(guest.getComment());
        guest1.setDaydate(guest.getDaydate());

        guestRepository.save(guest1);
        modelAndView.addObject("guest", guest1);
        modelAndView.addObject("message", "O'zgartirish saqlandi!");
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @RequestMapping(value = "/guest/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Guest guest1 = guestRepository.findById(id);
        guestRepository.delete(guest1);
        List<Guest> list = guestRepository.findAll();
        modelAndView.addObject("guests", list);
        modelAndView.addObject("message", "Obyekt o'chirildi!");
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }
}
