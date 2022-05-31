package com.med.sharedservice.comandapi.event;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ValidatedIlnnessEvent {

    private String illnessId;
    private String consultationId;
    private String userId;
    private String illness;
}
