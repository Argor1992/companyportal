package com.company.application.data.address.controller;

import com.company.application.data.address.service.AddressService;
import org.springframework.stereotype.Controller;

@Controller
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
}
