package com.invoice.invoice_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.invoice_api.Model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
