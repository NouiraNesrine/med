package com.med.consultationservice.commandapi.saga;

import com.med.consultationservice.commandapi.event.CreatedConsultationEvent;
import com.med.sharedservice.comandapi.command.*;
import com.med.sharedservice.comandapi.event.*;
import com.med.sharedservice.comandapi.model.User;
import com.med.sharedservice.queryapi.query.GetUserHistoryDetailsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class consultationSaga {

    @Autowired
    private transient CommandGateway cmdGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    public consultationSaga() {

    }

    @StartSaga
    @SagaEventHandler(associationProperty ="consultationId")
    private void handle(CreatedConsultationEvent createdConsultationEvent){
        User user = null;
        log.info("consultation created event in saga for id = {}",createdConsultationEvent.getConsultationId());

        GetUserHistoryDetailsQuery getUserHistoryDetailsQuery = new GetUserHistoryDetailsQuery(createdConsultationEvent.getUserId());

        try{
            user = queryGateway.query(getUserHistoryDetailsQuery, ResponseTypes.instanceOf(User.class)).join();
            CheckedSymptomsCommand checkSymptomsCommand = CheckedSymptomsCommand.builder()
                    .consultationId(createdConsultationEvent.getConsultationId())
                    .userId(createdConsultationEvent.getUserId())
                    .symptomsId(UUID.randomUUID().toString())
                    .userHistory(user.getHistory())
                    .build();

            cmdGateway.sendAndWait(checkSymptomsCommand);

        }catch (Exception e){
            log.error(e.getMessage());
            //reroll transaction
            cancelCreateConsultationCommand(createdConsultationEvent.getConsultationId());
        }

    }



    //callnext

    @SagaEventHandler(associationProperty ="consultationId")
    private void handle(CheckedSymptomEvent checkedSymptomEvent){
        //create command that comes after symptomchecking
        // fever just for testing purpose
        //sendandwait but just for testing
        try{
            if(true) throw new Exception();
            ValidateIllnessCommand validateIllnessCommand = ValidateIllnessCommand.builder()
                    .userId(checkedSymptomEvent.getUserId())
                    .illnessId(UUID.randomUUID().toString())
                    .consultationId(checkedSymptomEvent.getConsultationId())
                    .illness("fever")
                    .build();
            cmdGateway.send(validateIllnessCommand);
            log.info("Checked Symptom Event event in saga for id = {}",checkedSymptomEvent.getConsultationId());

        }catch (Exception e){
            log.error(e.getMessage());

            cancelCheckSymptomCommand(checkedSymptomEvent);


            //reroll transaction
        }

    }

    @SagaEventHandler(associationProperty = "consultationId")
    public void on(ValidatedIlnnessEvent validateIllnessEvent){
        try{
        FinishConsultationCommand finishDiagnosisCommand= FinishConsultationCommand.builder()
                .consultationId(validateIllnessEvent.getConsultationId())
                .consultationStatus("finished")
                .build();
            cmdGateway.send(finishDiagnosisCommand);
            log.info("Validated Ilnness Event event in saga for id = {}",validateIllnessEvent.getConsultationId());

        }catch (Exception e){
            log.error(e.getMessage());
            //reroll transaction

        }

    }


    @SagaEventHandler(associationProperty = "consultationId")
    @EndSaga
    public void handle(FinishedConsultationEvent finishedConsultationEvent){
        // whatvr
        log.info("consultation finishd event in saga for id = {}",finishedConsultationEvent.getConsultationId());
    }

    @SagaEventHandler(associationProperty = "consultationId")
    @EndSaga
    public void handle(CancelledConsultationEvent cancelledConsultationEvent){
        // whatvr
        log.info("Cancelled Consultation event in saga for id = {}",cancelledConsultationEvent.getConsultationId());
    }
    @SagaEventHandler(associationProperty = "consultationId")
    public void handle(CancelledCheckSymptomsEvent cancelledCheckSymptomsEvent){
        // whatvr
        log.info("Cancelled Check Symptoms event in saga for id = {}",cancelledCheckSymptomsEvent.getConsultationId());
    }

    @CommandHandler
    private void cancelCreateConsultationCommand(String consultationId) {
        CancelConsultationCommand cancelledConsultationCommand = CancelConsultationCommand.builder()
                .consultationId(consultationId)
                .c
                .build();
        cmdGateway.send(cancelledConsultationCommand);
    }

    @CommandHandler
    private void cancelCheckSymptomCommand(CheckedSymptomEvent checkedSymptomEvent) {
        CancelledCheckSymptomsCommand cancelledCheckSymptomsCommand = CancelledCheckSymptomsCommand.builder()
                .consultationId(checkedSymptomEvent.getConsultationId())
                .symptomsId(checkedSymptomEvent.getSymptomsId())
                .userId(checkedSymptomEvent.getUserId())
                .build();
        cmdGateway.send(cancelledCheckSymptomsCommand);
    }



}
