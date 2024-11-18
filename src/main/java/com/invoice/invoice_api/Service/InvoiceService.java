package com.invoice.invoice_api.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.invoice_api.Models.Invoice;
import com.invoice.invoice_api.repository.InvoiceRepository;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(String id) {
        return invoiceRepository.findById(id);
    }

    public Invoice saveInvoice(Invoice invoice) {
        invoice.setId(generateUniqueId());
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    private String generateUniqueId() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        String uniqueId;
        do {
            sb.setLength(0);
            for (int i = 0; i < 2; i++) {
                sb.append(letters.charAt(random.nextInt(letters.length())));
            }
            for (int i = 0; i < 4; i++) {
                sb.append(random.nextInt(10));
            }

            uniqueId = sb.toString();
        } while (invoiceRepository.existsById(uniqueId));
        return uniqueId;
    }

    public void deleteInvoice(String id) {
        invoiceRepository.deleteById(id);
    }
}
