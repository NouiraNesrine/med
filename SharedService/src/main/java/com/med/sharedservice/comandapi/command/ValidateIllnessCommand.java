package com.med.sharedservice.comandapi.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
@Builder
public class ValidateIllnessCommand {
    @TargetAggregateIdentifier
    private String illnessId;
    private String consultationId;
    private String userId;
    private String illness;


}
