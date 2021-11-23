package com.company.application.data.address.controller;

import com.company.application.data.address.service.AddressService;
import org.springframework.stereotype.Controller;

/**
 * @author Thorsten Zieres, 1297197
 */
@Controller
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
}
