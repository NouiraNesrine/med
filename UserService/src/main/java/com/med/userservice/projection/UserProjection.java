package com.med.userservice.projection;

import com.med.sharedservice.comandapi.model.User;
import com.med.sharedservice.comandapi.model.UserHistory;
import com.med.sharedservice.queryapi.query.GetUserHistoryDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserHistoryDetails(GetUserHistoryDetailsQuery getUserHistoryDetailsQuery){

        // for verification purpose
        UserHistory userHistory = UserHistory.builder()
                .historyId(UUID.randomUUID().toString())
                .illnessDate(new Date())
                .illnessStatus("cured")
                .lastIllness("fever")
                .build();

        return  User.builder()
                .userId(getUserHistoryDetailsQuery.getUserId())
                .fullName("Flen ben Foulen")
                .history(userHistory)
                .age("15")
                .status("consulting")
                .weight("45")
                .location("somewhere")
                .build();

    }

}
