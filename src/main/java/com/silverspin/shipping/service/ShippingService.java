package com.silverspin.shipping.service;

import com.silverspin.shipping.dto.ShippingDTO;
import com.silverspin.shipping.dto.ShippingRequestDTO;
import com.silverspin.shipping.dto.ShippingUpdateRequestDTO;

public interface ShippingService {

    ShippingDTO createShipment(ShippingRequestDTO shippingRequestDTO);

    ShippingDTO updateShipmentByID(int id, ShippingUpdateRequestDTO shipmentRequestDTO);
}
