package idv.villebez.jaxrs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Singleton;

import idv.villebez.exception.ValidateLogicException;
import idv.villebez.exception.ValidateLogicException.LogicType;
import idv.villebez.model.OffsetPagingModel;


@Singleton
public class UserDAO {

	private Map<String, User> mockData = new HashMap<String, User>();

	public UserDAO() {
		User user = new User(UUID.randomUUID().toString(), "Paul", 20);
		mockData.put(user.getId(), user);
		user = new User(UUID.randomUUID().toString(), "Allen", 30);
		mockData.put(user.getId(), user);
		user = new User(UUID.randomUUID().toString(), "Eric", 40);
		mockData.put(user.getId(), user);
	}

	public List<User> getAll() {
		return new ArrayList<User>(mockData.values());
	}

	public User findById(String id) throws ValidateLogicException {
		if (mockData.get(id) != null) {
			return mockData.get(id);
		} else {
			throw new ValidateLogicException(LogicType.NODATA, "資料不存在!!");
		}
	}

	public String save(User user) {
		String id = UUID.randomUUID().toString();
		user.setId(id);
		mockData.put(id, user);
		return id;
	}

	public void update(String id, User user) throws ValidateLogicException {
		if (mockData.get(id) != null) {
			user.setId(id);
			mockData.put(id, user);
		} else {
			throw new ValidateLogicException(LogicType.NODATA, "資料不存在!!");
		}
	}

	public void delete(String id) {
		mockData.remove(id);
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
