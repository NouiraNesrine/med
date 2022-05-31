package com.med.sharedservice.queryapi.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserHistoryDetailsQuery {

    private String userId;

}
