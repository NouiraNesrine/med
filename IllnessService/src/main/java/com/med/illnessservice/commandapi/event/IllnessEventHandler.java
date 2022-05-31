package com.med.illnessservice.commandapi.event;

import com.med.illnessservice.commandapi.data.Illness;
import com.med.illnessservice.commandapi.data.IllnessRepository;
import com.med.sharedservice.comandapi.event.ValidatedIlnnessEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class IllnessEventHandler {

    private IllnessRepository illnessRepository;

    public IllnessEventHandler(IllnessRepository illnessRepository) {
        this.illnessRepository = illnessRepository;
    }

    @EventHandler
    public void on(ValidatedIlnnessEvent validatedIlnnessEvent){
        Illness illness = Illness.builder()
                .illnessId(validatedIlnnessEvent.getIllnessId())
                .name(validatedIlnnessEvent.getIllness())
                .build();

        illnessRepository.save(illness);
    }
}
