package com.e104.isgd.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import io.swagger.annotations.ApiModelProperty;

@DynamoDBTable(tableName = "User")
public class User {
	@DynamoDBHashKey
	@DynamoDBTyped(DynamoDBAttributeType.S)
	@ApiModelProperty(value = "使用者", readOnly = true)
	private String id;
	
	@DynamoDBAttribute(attributeName = "name")
	@DynamoDBTyped(DynamoDBAttributeType.S)
	@ApiModelProperty(value = "名稱", required = true, example = "userA")
	private String name;

	public User() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
