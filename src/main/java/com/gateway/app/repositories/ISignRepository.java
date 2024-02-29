package com.gateway.app.repositories;

import com.gateway.app.model.Sign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISignRepository extends MongoRepository<Sign, String> {

}
