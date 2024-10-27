package com.invoice.invoice_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.invoice_api.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
