package idv.villebez.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import idv.villebez.exception.ValidateException;
import idv.villebez.exception.ValidateException.Type;
import idv.villebez.exception.ValidateLogicException.LogicType;

/**
 * 傳入參數驗證失敗的結果處理<br>
 * 預設為400型不正確的請求錯誤<br>
 * 若有無資料可操作的情形則為404型找不到資源的請求錯誤
 * @see {@link Response.Status}
 */
@Provider
public class ValidateExceptionMapper implements ExceptionMapper<ValidateException> {
	
	@Override
	public Response toResponse(ValidateException exception) {
		Status status = Response.Status.BAD_REQUEST;
		if(exception.getWarning().getCode().equals(Type.LOGIC.getValue()+LogicType.NODATA.getValue())){
			status = Status.NOT_FOUND;
		}
		return Response.status(status).entity(exception.getWarning()).build();
	}

}
