package idv.villebez.example;

import javax.ws.rs.core.Response;

import idv.villebez.exception.ValidateLogicException;
import idv.villebez.model.OffsetPagingModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * @author paul.chen
 * Swagger、JAX-RS annotation 寫在 Interface or Implement 皆可，建議分開，避免程式碼太過混雜。
 * 
 * 此範例將 Swagger annotation 寫在 Interface，JAX-RS annotation 寫在 Implement。
 */
@Api(value = "User")
public interface UserService {

	@ApiOperation(value = "取得使用者列表")
	OffsetPagingModel<User> list(@ApiParam(value="分頁起始點", defaultValue="0") int offset, @ApiParam(value="分頁筆數", defaultValue="10") int limit);

	@ApiOperation(value = "取得單筆使用者資料")
	User findOne(String id) throws ValidateLogicException;

	@ApiOperation(value = "新增使用者", code = 201)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "新增成功") })
	Response create(User user);

	@ApiOperation(value = "刪除使用者資料")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "已刪除") })
	Response delete(String id);

	@ApiOperation(value = "修改使用者資料")
	@ApiResponses(value={@ApiResponse(code = 200, message = "修改成功")})
	Response update(String id, User user) throws ValidateLogicException;

}