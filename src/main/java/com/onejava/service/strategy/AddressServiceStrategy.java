package com.onejava.service.strategy;

import com.onejava.AddressDto;
import java.util.List;

public interface AddressServiceStrategy {
    List<AddressDto> filterAddresses(String city, String country, String postalCode, String state, Integer userId, String userName);
}