package com.invoice.invoice_api.Models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Invoice {
    @Id
    private String id;

    private Date createdAt;
    private Date paymentDue;
    private String description;
    private Integer paymentTerms;
    private String clientName;
    private String clientEmail;
    private String status;
    @SuppressWarnings("unused")
    private double total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<Item> items;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_address_id")
    private Address clientAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_address_id")
    private Address senderAddress;

    public Address getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(Address clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Address getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(Address senderAddress) {
        this.senderAddress = senderAddress;
    }

    public Invoice() {
    }

    public Invoice(Date createdAt, Date paymentDue, String description, Integer paymentTerms,
            String clientName, String clientEmail, String status, double total) {
        this.createdAt = createdAt;
        this.paymentDue = paymentDue;
        this.description = description;
        this.paymentTerms = paymentTerms;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.status = status;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(Date paymentDue) {
        this.paymentDue = paymentDue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(Integer paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        double sum = items.stream().mapToDouble(Item::getTotal).sum();
        BigDecimal roundedSum = BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP);
        return roundedSum.doubleValue();
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
