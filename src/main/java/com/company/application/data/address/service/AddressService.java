package com.company.application.data.address.service;

import com.company.application.data.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private AddressRepository repository;

    public AddressService(@Autowired AddressRepository repository) {
        this.repository = repository;
    }
}
