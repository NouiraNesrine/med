package com.med.consultationservice.commandapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRestModel {

    private String consultationId;
    private String userId;
    private Date consultationDate;
    private String consultationLocation;
    private String consultationStatus;
}
