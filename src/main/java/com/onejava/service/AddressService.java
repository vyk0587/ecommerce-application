package com.onejava.service;

import com.onejava.AddressDto;
import com.onejava.entity.Address;

import java.util.List;

public interface AddressService {

    static AddressDto convertEntityToDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressDto.UserDto userDto = null;
        if (address.getUser() != null) {
            userDto = new AddressDto.UserDto(address.getUser().getId(), address.getUser().getUserName());
        }
        return new AddressDto(userDto, address.getStreet(), address.getCity(), address.getState(),
                address.getPostalCode(), address.getCountry());
    }

    List<AddressDto> filterAddresses(String city, String country, String postalCode, String state, Integer userId, String userName);
}