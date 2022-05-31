package com.med.sharedservice.comandapi.command;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CancelConsultationCommand {

    @TargetAggregateIdentifier
    private String consultationId;
    private String consultationStatus = "cancelled";
}
