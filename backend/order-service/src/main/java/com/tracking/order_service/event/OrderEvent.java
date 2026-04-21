package com.tracking.order_service.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private String eventType; // CREATED, UPDATED
    private Long orderId;
    private String status;
}
