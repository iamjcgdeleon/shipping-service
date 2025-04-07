package com.silverspin.shipping.dto;

import com.silverspin.shipping.enums.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class ShippingUpdateRequestDTO {
    
    private ShippingStatus shippingStatus;
    private String remarks;
}
