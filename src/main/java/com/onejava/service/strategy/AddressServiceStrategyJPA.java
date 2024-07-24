package com.onejava.service.strategy;

import com.onejava.AddressDto;
import com.onejava.service.AddressService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressServiceStrategyJPA implements AddressServiceStrategy {
    private final AddressService addressService;

    /*
       Autowires addressServiceJPA instance - Respective instance name should be used in method parameter
       to autowire addressServiceJPA
     */
    public AddressServiceStrategyJPA(AddressService addressServiceJPA) {
        this.addressService = addressServiceJPA;
    }

    @Override
    public List<AddressDto> filterAddresses(String city, String country, String postalCode, String state, Integer userId, String userName) {
        return addressService.filterAddresses(city, country, postalCode, state, userId, userName);
    }
}