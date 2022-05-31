package com.med.consultationservice.commandapi.event;

import lombok.Data;

import java.util.Date;

@Data
public class CreatedConsultationEvent {

    private String consultationId;
    private String userId;
    private Date consultationDate;
    private String consultationLocation;
    private String consultationStatus;
}
