package com.silverspin.shipping.dto;

import com.silverspin.shipping.enums.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class ShippingDTO {

    private int id;
    private int orderId;
    private String trackingNumber;
    private String address;
    private String customerName;
    private ShippingStatus shippingStatus;
    private LocalDate createdDate;
    private LocalDate shipmentDate;
    private String remarks;
}
