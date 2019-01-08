package com.ml.epic.ta.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.ml.epic.ta.model.Query;

@EnableScan
public interface QueryRepository extends CrudRepository<Query, String> {

}
