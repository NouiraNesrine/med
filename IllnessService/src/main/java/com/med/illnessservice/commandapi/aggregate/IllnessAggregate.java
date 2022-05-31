package com.med.illnessservice.commandapi.aggregate;

import com.med.sharedservice.comandapi.event.ValidatedIlnnessEvent;
import com.med.sharedservice.comandapi.command.ValidateIllnessCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class IllnessAggregate {
    @AggregateIdentifier
    private String illnessId;
    private String consultationId;
    private String userId;
    private String illness;

    public IllnessAggregate() {
    }

    @CommandHandler
    public IllnessAggregate(ValidateIllnessCommand validateIllnessCommand) {

        ValidatedIlnnessEvent validatedIlnnessEvent = ValidatedIlnnessEvent.builder()
                .illnessId(validateIllnessCommand.getIllnessId())
                .userId(validateIllnessCommand.getUserId())
                .consultationId(validateIllnessCommand.getConsultationId())
                .illness(validateIllnessCommand.getIllness())
                .build();

        AggregateLifecycle.apply(validatedIlnnessEvent);
    }

    @EventSourcingHandler
    public void on(ValidatedIlnnessEvent validatedIlnnessEvent)
    {
        this.illnessId = validatedIlnnessEvent.getIllnessId();
        this.consultationId= validatedIlnnessEvent.getConsultationId();
        this.userId= validatedIlnnessEvent.getUserId();
        this.illness = validatedIlnnessEvent.getIllness();
    }
}
