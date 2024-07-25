package com.onejava.controller;

import com.onejava.AddressDto;
import com.onejava.service.AddressService;
import com.onejava.service.AddressServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressServiceFactory addressServiceFactory;

    @Autowired
    public AddressController(AddressServiceFactory addressServiceFactory) {
        this.addressServiceFactory = addressServiceFactory;
    }

    @GetMapping
    public List<AddressDto> filterAddresses(@RequestParam(required = false) String city,
                                         @RequestParam(required = false) String country,
                                         @RequestParam(required = false) String postalCode,
                                         @RequestParam(required = false) String state,
                                         @RequestParam(required = false) Integer userId,
                                         @RequestParam(required = false) String userName,
                                         @RequestParam(required = false, defaultValue = "jpa") String service) {
        AddressService addressService = addressServiceFactory.getService("AddressService" + service);
        return addressService.filterAddresses(city, country, postalCode, state, userId, userName);
    }
}