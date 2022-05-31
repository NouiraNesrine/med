package com.med.sharedservice.comandapi.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class FinishConsultationCommand {

    @TargetAggregateIdentifier
    private String consultationId;
    private String consultationStatus;


}
