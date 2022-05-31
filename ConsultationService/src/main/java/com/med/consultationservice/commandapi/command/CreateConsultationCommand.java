package com.med.consultationservice.commandapi.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@Builder
@Data
public class CreateConsultationCommand {

    @TargetAggregateIdentifier
    private String consultationId;
    private String userId;
    private Date consultationDate;
    private String consultationLocation;
    private String consultationStatus;
}
