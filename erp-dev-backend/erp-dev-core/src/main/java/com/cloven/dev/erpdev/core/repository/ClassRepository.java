package com.cloven.dev.erpdev.core.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.cloven.dev.erodev.model.entity.Class;

@EnableScan
public interface ClassRepository extends CrudRepository<Class,Integer> {

}
