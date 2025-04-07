package com.silverspin.shipping.mapper;

import com.silverspin.shipping.dto.ShippingDTO;
import com.silverspin.shipping.entity.Shipment;

public class ShippingMapper {

    public static ShippingDTO toDTO(Shipment shipment) {
        return new ShippingDTO(shipment.getId(), shipment.getOrderId(), shipment.getTrackingNumber(), shipment.getAddress(), shipment.getCustomerName(), shipment.getShippingStatus(), shipment.getCreatedDate(), shipment.getShipmentDate(), shipment.getRemarks());
    }
}
