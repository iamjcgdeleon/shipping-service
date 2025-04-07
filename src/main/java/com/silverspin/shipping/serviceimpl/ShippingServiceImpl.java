package com.silverspin.shipping.serviceimpl;

import com.silverspin.shipping.dto.OrderRequestDTO;
import com.silverspin.shipping.dto.ShippingDTO;
import com.silverspin.shipping.dto.ShippingRequestDTO;
import com.silverspin.shipping.dto.ShippingUpdateRequestDTO;
import com.silverspin.shipping.entity.Shipment;
import com.silverspin.shipping.mapper.ShippingMapper;
import com.silverspin.shipping.repository.ShippingRepository;
import com.silverspin.shipping.service.ShippingService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Slf4j
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;

    private final RestTemplate restTemplate;

    public ShippingServiceImpl(ShippingRepository shippingRepository, RestTemplate restTemplate) {
        this.shippingRepository = shippingRepository;
        this.restTemplate = restTemplate;
    }


    @Transactional
    @Override
    public ShippingDTO createShipment(ShippingRequestDTO shippingRequestDTO) {
        log.info("Creating shipment for order ID: {}, customer: {}", shippingRequestDTO.getOrderID(), shippingRequestDTO.getCustomerName());
        String trackingNumber = "SHIP-" + LocalDateTime.now().toLocalDate() + "-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        Shipment shipment = Shipment.builder().orderId(shippingRequestDTO.getOrderID()).trackingNumber(trackingNumber).customerName(shippingRequestDTO.getCustomerName()).address(shippingRequestDTO.getAddress()).build();

        Shipment shipmentRecord = shippingRepository.save(shipment);
        log.info("New Shipment has been created: {}", shipmentRecord);
        return ShippingMapper.toDTO(shipmentRecord);
    }

    @Transactional
    @Override
    public ShippingDTO updateShipmentByID(int id, ShippingUpdateRequestDTO shippingRequestDTO) {
        log.info("Updating shipment ID: {}", id);

        if (id < 1) throw new IllegalArgumentException(id + " is not a valid shipping id");
        Shipment shipment = shippingRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Shipping id " + id + " has no record."));

        if (shipment.getShipmentDate() == null) shipment.setShipmentDate(LocalDate.from(LocalDateTime.now()));
        if (shippingRequestDTO.getShippingStatus() != null)
            shipment.setShippingStatus(shippingRequestDTO.getShippingStatus());
        if (shippingRequestDTO.getRemarks() != null) shipment.setRemarks(shippingRequestDTO.getRemarks());

        Shipment updatedShipment = shippingRepository.save(shipment);

        OrderRequestDTO orderRequestDTO = OrderRequestDTO.builder().orderID(updatedShipment.getOrderId()).trackingNumber(updatedShipment.getTrackingNumber()).orderStatus(updatedShipment.getShippingStatus().toString()).build();

        try {
            //rest template to update the shipment details in order after tracking number
            restTemplate.put("http://localhost:8081/api/orders", orderRequestDTO, OrderRequestDTO.class);

        } catch (Exception e) {
            log.error("Failed to call Order Service for order {}: {}", updatedShipment.getOrderId(), e.getMessage());
        }

        log.info("Shipment has been updated: {}", updatedShipment);
        return ShippingMapper.toDTO(updatedShipment);
    }
}
