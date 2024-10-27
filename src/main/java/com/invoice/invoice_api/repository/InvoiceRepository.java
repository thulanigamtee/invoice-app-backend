package com.invoice.invoice_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.invoice_api.Model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    boolean existsById(@SuppressWarnings("null") String id);
}
