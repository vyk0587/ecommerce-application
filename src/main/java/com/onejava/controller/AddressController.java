package com.onejava.controller;

import com.onejava.AddressDto;
import com.onejava.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final Map<String, AddressService> addressServiceMap;

    /*
        * The AddressController constructor takes a map of AddressService beans and stores it in a field.
     */
    @Autowired
    public AddressController(Map<String, AddressService> addressServiceMap) {
        this.addressServiceMap = addressServiceMap;
    }


    @GetMapping
    public List<AddressDto> filterAddresses(@RequestParam(required = false) String city,
                                            @RequestParam(required = false) String country,
                                            @RequestParam(required = false) String postalCode,
                                            @RequestParam(required = false) String state,
                                            @RequestParam(required = false) Integer userId,
                                            @RequestParam(required = false) String userName,
                                            @RequestParam(required = false, defaultValue = "jpa") String service) {
        AddressService addressService = addressServiceMap.get("addressService" + service.toUpperCase());
        if (addressService == null) {
            throw new IllegalArgumentException("Service not found for name: " + service);
        }
        return addressService.filterAddresses(city, country, postalCode, state, userId, userName);
    }


}