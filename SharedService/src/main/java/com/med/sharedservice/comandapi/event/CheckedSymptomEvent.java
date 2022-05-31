package com.med.sharedservice.comandapi.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckedSymptomEvent {

    private String symptomsId;
    private String consultationId;
    private String userId;

}
