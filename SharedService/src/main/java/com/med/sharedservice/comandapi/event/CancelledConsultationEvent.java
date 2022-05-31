package com.med.sharedservice.comandapi.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelledConsultationEvent {

    private String consultationId;
    private String consultationStatus;
}
