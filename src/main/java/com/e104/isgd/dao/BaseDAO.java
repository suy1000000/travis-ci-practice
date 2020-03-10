package com.e104.isgd.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

/**
 * [核心] - 建構 DynamoDB Client 工具
 * 1.設置 AWS Region
 * 2.設定 Table Name
 * 3.建構 Client Tool
 * @author paul.chen
 */
public class BaseDAO {

	protected final AmazonDynamoDB dynamoClient;
	protected final DynamoDB dynamoDB;
	protected final DynamoDBMapper mapper;


	/* Base Handler Constructor */
	protected BaseDAO(String aws_region) {
		dynamoClient = AmazonDynamoDBClientBuilder.standard().withRegion(aws_region).build();
		dynamoDB = new DynamoDB(dynamoClient);
		mapper = new DynamoDBMapper(dynamoClient);
	}

}