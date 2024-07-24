package com.onejava.service.strategy;

import com.onejava.AddressDto;
import com.onejava.service.AddressService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressServiceStrategyQDSL implements AddressServiceStrategy {
    private final AddressService addressService;

    /*
       Autowires addressServiceQDSL instance - Respective instance name should be used in method parameter
       to autowire addressServiceQDSL
     */
    public AddressServiceStrategyQDSL(AddressService addressServiceQDSL) {
        this.addressService = addressServiceQDSL;
    }

    @Override
    public List<AddressDto> filterAddresses(String city, String country, String postalCode, String state, Integer userId, String userName) {
        return addressService.filterAddresses(city, country, postalCode, state, userId, userName);
    }
}