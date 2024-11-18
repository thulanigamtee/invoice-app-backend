package com.invoice.invoice_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.invoice_api.Models.Invoice;
import com.invoice.invoice_api.Service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable String id) {
        return invoiceService.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice createdInvoice = invoiceService.saveInvoice(invoice);
        return ResponseEntity.ok(createdInvoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable String id, @RequestBody Invoice invoiceDetails) {
        return invoiceService.getInvoiceById(id)
                .map(invoice -> {
                    invoice.setCreatedAt(invoiceDetails.getCreatedAt());
                    invoice.setPaymentDue(invoiceDetails.getPaymentDue());
                    invoice.setDescription(invoiceDetails.getDescription());
                    invoice.setPaymentTerms(invoiceDetails.getPaymentTerms());
                    invoice.setClientName(invoiceDetails.getClientName());
                    invoice.setClientEmail(invoiceDetails.getClientEmail());
                    invoice.setStatus(invoiceDetails.getStatus());
                    invoice.setSenderAddress(invoiceDetails.getSenderAddress());
                    invoice.setClientAddress(invoiceDetails.getClientAddress());
                    invoice.setItems(invoiceDetails.getItems());
                    invoice.setTotal(invoiceDetails.getTotal());
                    Invoice updatedInvoice = invoiceService.updateInvoice(invoice);
                    return ResponseEntity.ok(updatedInvoice);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable String id) {
        return invoiceService.getInvoiceById(id).map((invoice) -> {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
