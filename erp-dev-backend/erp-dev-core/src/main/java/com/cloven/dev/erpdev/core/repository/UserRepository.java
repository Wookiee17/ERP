package com.cloven.dev.erpdev.core.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cloven.dev.erodev.model.entity.User;

@Repository
@EnableScan
public class UserRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	public User saveCustomer(User user) {
		dynamoDBMapper.save(user);
		return user;
	}
}