package com.med.symptomsservice.comandapi.event;

import com.med.sharedservice.comandapi.command.CancelledCheckSymptomsCommand;
import com.med.sharedservice.comandapi.event.CheckedSymptomEvent;
import com.med.symptomsservice.comandapi.data.SympRepository;
import com.med.symptomsservice.comandapi.data.Symptoms;
import com.med.symptomsservice.comandapi.data.SymptomsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.sql.Driver;
import java.util.Date;

@Component
public class SymptomEventHandler {

    private SymptomsRepository symptomsRepository;
    private SympRepository sympRepository;

    public SymptomEventHandler(SymptomsRepository symptomsRepository, SympRepository sympRepository) {
        this.symptomsRepository = symptomsRepository;
        this.sympRepository = sympRepository;
    }

    @EventHandler
    public void on(CheckedSymptomEvent checkedSymptomEvent){
        //just for test purpose change to get data from proj later
        Symptoms symptoms = Symptoms.builder()
                .symptomsId(checkedSymptomEvent.getSymptomsId())
                .consultationId(checkedSymptomEvent.getConsultationId())
                .userId(checkedSymptomEvent.getUserId())
                .date(new Date())
                .list(null)
                .build();

        symptomsRepository.save(symptoms);
    }

    @EventHandler
    public void on(CancelledCheckSymptomsCommand cancelledCheckSymptomsCommand){
        // delete list first ! foreach .... keep or delete
        Symptoms symptoms = symptomsRepository.getById(cancelledCheckSymptomsCommand.getSymptomsId());
            symptoms.setStatus("deleted");
        symptomsRepository.save(symptoms);
    }
}
