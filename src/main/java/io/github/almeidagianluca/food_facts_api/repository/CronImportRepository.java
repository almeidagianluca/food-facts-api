package io.github.almeidagianluca.food_facts_api.repository;

import io.github.almeidagianluca.food_facts_api.model.CronImport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronImportRepository extends MongoRepository<CronImport, Integer> {
    CronImport findTopByOrderByDateDesc();
}
