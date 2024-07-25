package com.onejava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AddressServiceFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceFactory.class);
    private final Map<String, AddressService> serviceMap;

    @Autowired
    public AddressServiceFactory(List<AddressService> services) {
        if (services.isEmpty()) {
            LOGGER.warn("No AddressService beans found. Check your Spring configuration.");
        } else {
            LOGGER.info("Found {} AddressService beans.", services.size());
        }
        this.serviceMap = services.stream()
                .collect(Collectors.toMap(service -> service.getClass().getSimpleName().toLowerCase(), Function.identity()));
    }

    public AddressService getService(String serviceName) {
        AddressService addressService = serviceMap.get(serviceName.toLowerCase());
        if (addressService == null) {
            LOGGER.error("Service not found for name: {}", serviceName);
            throw new IllegalArgumentException("Service not found for name: " + serviceName);
        }
        LOGGER.info("Selected Service: {}", addressService.getClass().getSimpleName());
        return addressService;
    }
}
