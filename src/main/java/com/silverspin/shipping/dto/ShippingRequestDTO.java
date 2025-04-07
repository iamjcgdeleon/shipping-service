package com.silverspin.shipping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ShippingRequestDTO {

    private int orderID;
    private String address;
    private String customerName;
}
