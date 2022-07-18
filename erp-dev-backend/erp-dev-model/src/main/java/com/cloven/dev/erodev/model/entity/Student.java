package com.cloven.dev.erodev.model.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.cloven.dev.erpdev.model.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBTable(tableName = "erp_student")
@Builder
@Data
@DynamoDBDocument
public class Student {

	private User user;

	private Integer classId;

	private Integer sectionId;

	private String fatherName;

	private String motherName;

	private Integer rollNumber;

	private String imageUrl;

	private String mobileNumber;

	private Long admissionNumber;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT_DDMMYYYY_HHMMSS)
	private String addmissionDate;

}
