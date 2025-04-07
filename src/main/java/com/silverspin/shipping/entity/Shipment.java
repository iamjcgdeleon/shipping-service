package com.silverspin.shipping.entity;

import com.silverspin.shipping.enums.ShippingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "tracking_number", nullable = false)
    private String trackingNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ShippingStatus shippingStatus = ShippingStatus.PROCESSING;

    @Builder.Default
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate = LocalDate.from(LocalDateTime.now());

    @Column(name = "shipment_date")
    private LocalDate shipmentDate;

    @Column(name = "remarks")
    private String remarks;

}
