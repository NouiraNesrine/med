package com.med.symptomsservice.comandapi.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Symptoms {
    @Id
    private String symptomsId;
    private String userId;
    private String consultationId;
    private Date date;
    private String status = "created";
    @OneToMany
    private List<Symp> list = new ArrayList<>();
}
