package com.med.consultationservice.commandapi.controller;

import com.med.consultationservice.commandapi.command.CreateConsultationCommand;
import com.med.consultationservice.commandapi.model.ConsultationRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    private CommandGateway cmdGateway;

    public ConsultationController(CommandGateway cmdGateway) {
        this.cmdGateway = cmdGateway;
    }

    @PostMapping
    public String startConsultation(@RequestBody ConsultationRestModel consultationRestModel ){

        String consultationId = UUID.randomUUID().toString();
        CreateConsultationCommand createConsultationCommand = CreateConsultationCommand.builder()
                .consultationId(consultationId)
                .userId(consultationRestModel.getUserId())
                .consultationLocation(consultationRestModel.getConsultationLocation())
                .consultationDate(consultationRestModel.getConsultationDate())
                .consultationStatus("Creating Consultation")
                .build();

        cmdGateway.sendAndWait(createConsultationCommand);
        return createConsultationCommand.toString();
    }
}
