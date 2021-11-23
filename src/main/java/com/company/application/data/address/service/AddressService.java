package com.company.application.data.address.service;

import com.company.application.data.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thorsten Zieres, 1297197
 */
@Service
public class AddressService {

    private AddressRepository repository;

    public AddressService(@Autowired AddressRepository repository) {
        this.repository = repository;
    }
}
