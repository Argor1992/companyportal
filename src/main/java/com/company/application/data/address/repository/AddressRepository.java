package com.company.application.data.address.repository;

import com.company.application.data.address.entity.AddressEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

}