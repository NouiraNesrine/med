package com.med.sharedservice.comandapi.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelledCheckSymptomsEvent {

    private String symptomsId;
    private String consultationId;
    private String userId;
}
