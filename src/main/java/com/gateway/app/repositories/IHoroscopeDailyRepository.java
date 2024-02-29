package com.gateway.app.repositories;

import com.gateway.app.model.HoroscopeDaily;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHoroscopeDailyRepository extends MongoRepository<HoroscopeDaily, String> {

    @Aggregation({
            "{$match: {sign: ?0} }",
            "{$sort: { startedAt: -1 } }",
            "{$limit: 1 }"
    })
    HoroscopeDaily findLatestDailyHoroscopeBySign(String sign);



}
