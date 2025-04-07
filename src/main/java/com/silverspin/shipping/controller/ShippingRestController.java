package com.silverspin.shipping.controller;

import com.silverspin.shipping.dto.ShippingDTO;
import com.silverspin.shipping.dto.ShippingRequestDTO;
import com.silverspin.shipping.dto.ShippingUpdateRequestDTO;
import com.silverspin.shipping.service.ShippingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shipments")
@Slf4j
public class ShippingRestController {

    private final ShippingService shippingService;

    public ShippingRestController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping
    public ResponseEntity<ShippingDTO> createShipment(@RequestBody ShippingRequestDTO shippingRequestDTO) {
        log.info("Started creating new shipment");
        ShippingDTO newShipment = shippingService.createShipment(shippingRequestDTO);
        log.info("New shipment created : {}", newShipment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newShipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingDTO> updateShipmentByID(@PathVariable int id, @RequestBody ShippingUpdateRequestDTO shippingUpdateRequestDTO) {
        log.info("Fetching shipment with ID: {}", id);
        ShippingDTO updatedShipment = shippingService.updateShipmentByID(id, shippingUpdateRequestDTO);
        log.info("Fetched shipment : {}", updatedShipment);
        return ResponseEntity.ok(updatedShipment);
    }
}
