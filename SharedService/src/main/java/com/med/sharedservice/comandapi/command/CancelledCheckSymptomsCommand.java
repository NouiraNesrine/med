package com.med.sharedservice.comandapi.command;

import com.med.sharedservice.comandapi.model.UserHistory;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CancelledCheckSymptomsCommand {
    @TargetAggregateIdentifier
    private String symptomsId;
    private String consultationId;
    private String userId;

}
