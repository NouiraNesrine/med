package com.med.sharedservice.comandapi.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserHistory {

    private String historyId;
    private String lastIllness;
    private Date illnessDate;
    private String illnessStatus;
}
