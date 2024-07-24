package com.onejava.service.impl;

import com.onejava.AddressDto;
import com.onejava.entity.Address;
import com.onejava.repository.AddressRepository;
import com.onejava.repository.specification.AddressQueryDSL;
import com.onejava.service.AddressService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceQDSL implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceQDSL(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDto> filterAddresses(String city, String country, String postalCode, String state, Integer userId, String userName) {
        Predicate predicate = AddressQueryDSL.combinedSpecification(city, country, postalCode, state, state, userId, userName);
        List<Address> addresses = (List<Address>) addressRepository.findAll(predicate);
        return addresses.stream().map(AddressService::convertEntityToDTO).collect(Collectors.toList());
    }
}