package com.onejava.service.impl;

import com.onejava.AddressDto;
import com.onejava.entity.Address;
import com.onejava.repository.AddressRepository;
import com.onejava.repository.specification.AddressSpecification;
import com.onejava.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceJPA implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceJPA(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDto> filterAddresses(String city, String country, String postalCode, String state, Integer userId, String userName) {
        Specification<Address> spec = AddressSpecification.combinedSpecification(city, country, postalCode, state, state, userId, userName);
        List<Address> addresses = addressRepository.findAll(spec);
        return addresses.stream().map(AddressService::convertEntityToDTO).collect(Collectors.toList());

    }
}