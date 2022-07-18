package com.cloven.dev.erpdev.core.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloven.dev.erodev.model.entity.Batch;

@EnableScan
@Repository
public interface BatchRepository extends CrudRepository<Batch, Integer> {

}
