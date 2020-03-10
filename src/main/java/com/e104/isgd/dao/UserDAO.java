package com.e104.isgd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.e104.isgd.model.User;

import org.springframework.stereotype.Component;

import idv.villebez.exception.ValidateLogicException;
import idv.villebez.exception.ValidateLogicException.LogicType;
import idv.villebez.model.OffsetPagingModel;

@Component
@Singleton
public class UserDAO extends BaseDAO {

	protected UserDAO() {
		super("ap-northeast-1");
	}

	public List<User> getAll() {
		return mapper.scan(User.class, new DynamoDBScanExpression());
	}

	public User findById(String id) {
		User user = mapper.load(User.class, id);
		if (user != null) {
			return user;
		} else {
			throw new ValidateLogicException(LogicType.NODATA, "資料不存在!!");
		}
	}

	public String save(User user) {
		String id = UUID.randomUUID().toString();
		user.setId(id);
		mapper.save(user);
		return id;
	}

	public void update(String id, User user) {
		if (mapper.load(User.class, id) != null) {
			user.setId(id);
			mapper.save(user);
		} else {
			throw new ValidateLogicException(LogicType.NODATA, "資料不存在!!");
		}
	}

	public void delete(String id) {
		mapper.delete(mapper.load(User.class, id));
	}

	public OffsetPagingModel<User> queryForPage(int offset, int limit) {
		List<User> list = this.getAll();
		int total = list.size();

		List<User> dataList = null;
		if (offset < total) {
			int fromIndex = offset;
			int toIndex = offset + limit;
			dataList = list.subList(fromIndex, toIndex <= total ? toIndex : total);
		} else {
			dataList = new ArrayList<User>();
		}

		int next = offset + dataList.size();

		OffsetPagingModel<User> result = new OffsetPagingModel<User>();
		result.setDataList(dataList);
		result.setTotal(total);
		result.setOffset(next);
		result.setHasNext(next < total);
		return result;
	}

}
