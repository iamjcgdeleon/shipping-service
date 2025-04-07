package com.silverspin.shipping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    private int orderID;
    private String trackingNumber;
    private String orderStatus;
}
