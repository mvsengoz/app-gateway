package com.gateway.app.repositories;

import com.gateway.app.model.HoroscopeMonthly;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHoroscopeMonthlyRepository extends MongoRepository<HoroscopeMonthly, String> {
    @Aggregation({
            "{$match: {sign: ?0} }",
            "{$sort: { startedAt: -1 } }",
            "{$limit: 1 }"
    })
    HoroscopeMonthly findLatestMonthlyHoroscopeBySign(String sign);

}
