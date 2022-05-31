package com.med.symptomsservice.comandapi.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Symp {
    @Id
    private String sympId;
    private String description;
    private int periode;
}
