package com.med.consultationservice.commandapi.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Consultation{

    @Id
    private String consultationId;
    private String userId;
    private Date consultationDate;
    private String consultationLocation;
    private String consultationStatus;
}
