package com.med.symptomsservice.comandapi.aggregate;

import com.med.sharedservice.comandapi.command.CancelConsultationCommand;
import com.med.sharedservice.comandapi.command.CancelledCheckSymptomsCommand;
import com.med.sharedservice.comandapi.command.CheckedSymptomsCommand;
import com.med.sharedservice.comandapi.event.CancelledCheckSymptomsEvent;
import com.med.sharedservice.comandapi.event.CheckedSymptomEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class SymptomAggregate {

   @AggregateIdentifier
    private String symptomsId;
    private String consultationId;
    private String userId;

    public SymptomAggregate() {
    }

    @CommandHandler
    public SymptomAggregate(CheckedSymptomsCommand checkSymptomsCommand) {
        //validation & publish event
        log.info("executing check symptom command for consultation id : {}",checkSymptomsCommand.getConsultationId());

        CheckedSymptomEvent checkedSymptomEvent = CheckedSymptomEvent.builder()
                .symptomsId(checkSymptomsCommand.getSymptomsId())
                .consultationId(checkSymptomsCommand.getConsultationId())
                .userId(checkSymptomsCommand.getUserId())
                .build();

        AggregateLifecycle.apply(checkedSymptomEvent);
        log.info("checkedSymptomEvent applied");

    }

    @CommandHandler
    public void handle(CancelledCheckSymptomsCommand cancelledCheckSymptomsCommand) {
        //validation & publish event
        log.info("executing cancl symptom command for consultation id : {}",cancelledCheckSymptomsCommand.getConsultationId());

        CancelledCheckSymptomsEvent cancelledCheckSymptomsEvent = CancelledCheckSymptomsEvent.builder()
                .symptomsId(cancelledCheckSymptomsCommand.getSymptomsId())
                .consultationId(cancelledCheckSymptomsCommand.getConsultationId())
                .userId(cancelledCheckSymptomsCommand.getUserId())
                .build();

        AggregateLifecycle.apply(cancelledCheckSymptomsEvent);
        log.info(" cancelled Symptoms Event applied");

    }

    @EventSourcingHandler
    public void on(CheckedSymptomEvent checkedSymptomEvent){
        this.symptomsId=checkedSymptomEvent.getSymptomsId();
        this.consultationId=checkedSymptomEvent.getConsultationId();
        this.userId=checkedSymptomEvent.getUserId();
    }

    @EventSourcingHandler
    public void on(CancelledCheckSymptomsEvent cancelledCheckSymptomsEvent){
        this.symptomsId=cancelledCheckSymptomsEvent.getSymptomsId();
        this.consultationId=cancelledCheckSymptomsEvent.getConsultationId();
        this.userId=cancelledCheckSymptomsEvent.getUserId();
    }
}
