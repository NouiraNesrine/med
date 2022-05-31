package com.med.sharedservice.comandapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String fullName;
    private String userId;
    private String age;
    private String weight;
    private String location;
    private String status;
    private UserHistory history;

}
