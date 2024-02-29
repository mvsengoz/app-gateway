package com.gateway.app.repositories;

import com.gateway.app.model.HoroscopeWeekly;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHoroscopeWeeklyRepository extends MongoRepository<HoroscopeWeekly, String> {
    @Aggregation({
            "{$match: {sign: ?0} }",
            "{$sort: { startedAt: -1 } }",
            "{$limit: 1 }"
    })
    HoroscopeWeekly findLatestWeeklyHoroscopeBySign(String sign);

}
