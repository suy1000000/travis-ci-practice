package com.e104.isgd.service;

import javax.ws.rs.core.Response;

import com.e104.isgd.model.User;

import idv.villebez.model.OffsetPagingModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value = "User")
public interface UserService {

	@ApiOperation(value = "取得使用者列表")
	OffsetPagingModel<User> list(@ApiParam(value="分頁起始點", defaultValue="0") int offset, @ApiParam(value="分頁筆數", defaultValue="10") int limit);

	@ApiOperation(value = "取得單筆使用者資料")
	User findOne(String id);

	@ApiOperation(value = "新增使用者", code = 201)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "新增成功") })
	Response create(User user);

	@ApiOperation(value = "刪除使用者資料")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "已刪除") })
	Response delete(String id);

	@ApiOperation(value = "修改使用者資料")
	@ApiResponses(value={@ApiResponse(code = 200, message = "修改成功")})
	Response update(String id, User user);
	
}