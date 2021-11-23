package com.company.application.data.address.repository;

import com.company.application.data.address.entity.AddressEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thorsten Zieres, 1297197
 */
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

}