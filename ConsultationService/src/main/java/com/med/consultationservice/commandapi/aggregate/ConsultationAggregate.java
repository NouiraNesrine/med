package com.med.consultationservice.commandapi.aggregate;

import com.med.consultationservice.commandapi.command.CreateConsultationCommand;
import com.med.consultationservice.commandapi.event.CreatedConsultationEvent;
import com.med.sharedservice.comandapi.command.CancelConsultationCommand;
import com.med.sharedservice.comandapi.command.FinishConsultationCommand;
import com.med.sharedservice.comandapi.event.CancelledConsultationEvent;
import com.med.sharedservice.comandapi.event.FinishedConsultationEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
public class ConsultationAggregate {

    @AggregateIdentifier
    private String consultationId;
    private String userId;
    private Date consultationDate;
    private String consultationLocation;
    private String consultationStatus;

    public ConsultationAggregate() {
    }

    @CommandHandler
    public ConsultationAggregate(CreateConsultationCommand createConsultationCommand) {
        CreatedConsultationEvent createdConsultationEvent = new CreatedConsultationEvent();
        BeanUtils.copyProperties(createConsultationCommand,createdConsultationEvent);
        AggregateLifecycle.apply(createdConsultationEvent);
    }

    @EventSourcingHandler
    public void on(CreatedConsultationEvent createddConsultationEvent){
        this.consultationId = createddConsultationEvent.getConsultationId();
        this.userId = createddConsultationEvent.getUserId();
        this.consultationDate = createddConsultationEvent.getConsultationDate();
        this.consultationLocation = createddConsultationEvent.getConsultationLocation();
        this.consultationStatus = createddConsultationEvent.getConsultationStatus();
    }

    @CommandHandler
    public void handle(FinishConsultationCommand finishDiagnosisCommand){
       // whatvr
        FinishedConsultationEvent finishedConsultationEvent = FinishedConsultationEvent.builder()
                .consultationId(finishDiagnosisCommand.getConsultationId())
                .consultationStatus(finishDiagnosisCommand.getConsultationStatus())
                .build();

        AggregateLifecycle.apply(finishedConsultationEvent);
    }

    @EventSourcingHandler
    public void on(FinishedConsultationEvent finishedConsultationEvent){
        this.consultationStatus = finishedConsultationEvent.getConsultationStatus();
    }

    @CommandHandler
    public void handle(CancelConsultationCommand cancelConsultationCommand){
        // whatvr
        CancelledConsultationEvent cancelledConsultationEvent = CancelledConsultationEvent.builder()
                .consultationId(cancelConsultationCommand.getConsultationId())
                .consultationStatus(cancelConsultationCommand.getConsultationStatus())
                .build();

        AggregateLifecycle.apply(cancelConsultationCommand);
    }

    @EventSourcingHandler
    public void on(CancelledConsultationEvent cancelConsultationCommand){
        this.consultationStatus = cancelConsultationCommand.getConsultationStatus();
    }

}
