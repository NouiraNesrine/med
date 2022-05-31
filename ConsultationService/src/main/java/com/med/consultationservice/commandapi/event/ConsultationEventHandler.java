package com.med.consultationservice.commandapi.event;

import com.med.consultationservice.commandapi.data.Consultation;
import com.med.consultationservice.commandapi.data.ConsultationRepository;
import com.med.sharedservice.comandapi.event.CancelledConsultationEvent;
import com.med.sharedservice.comandapi.event.FinishedConsultationEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ConsultationEventHandler {

    private ConsultationRepository consultationRepository;

    public ConsultationEventHandler(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @EventHandler
    public void on(CreatedConsultationEvent createdConsultationEvent){

        Consultation consultation = new Consultation();
        BeanUtils.copyProperties(createdConsultationEvent,consultation);
        consultationRepository.save(consultation);
    }

    @EventHandler
    public void on(FinishedConsultationEvent finishedConsultationEvent){

        Consultation consultation = consultationRepository.getById(finishedConsultationEvent.getConsultationId());
        // chang and update
        consultation.setConsultationStatus(finishedConsultationEvent.getConsultationStatus());
        consultationRepository.save(consultation);
    }

    @EventHandler
    public void on(CancelledConsultationEvent cancelledConsultationEvent){

        Consultation consultation = consultationRepository.getById(cancelledConsultationEvent.getConsultationId());
        // chang and update
        consultation.setConsultationStatus(cancelledConsultationEvent.getConsultationStatus());
        consultationRepository.save(consultation);
    }
}
