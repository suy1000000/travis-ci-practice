package idv.villebez.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import idv.villebez.exception.ValidateException;

/**
 * 標準的API回應結果
 * 
 * @author paul.chen
 *
 */
@JsonInclude(Include.NON_NULL)
public class ApiResponse {

	private ObjectMapper mapper = new ObjectMapper();

	/** API回傳結果 */
	private Object response;
	/** API錯誤情形 */
	private Error error;
	/** API錯誤情形 */
	private Warning warning;

	/**
	 * <p>
	 * Contains the successs result when the response was successful.
	 * </p>
	 * 
	 * @param result
	 *            The response data.
	 * 
	 * @return String
	 */
	public static String success(Object result) {
		ApiResponse resp = new ApiResponse();
		resp.setResponse(result);
		return resp.toString();
	}

	/**
	 * <p>
	 * Contains the fail result when the response was fail.
	 * </p>
	 * 
	 * @param e
	 *            Contain the Exception message
	 * 
	 * @return String
	 */
	public static String fail(Exception e) {
		ApiResponse resp = new ApiResponse();
		resp.setError(new Error(e.getClass().getName(), e.getMessage()));
		return resp.toString();
	}

	/**
	 * <p>
	 * Contains the validateException result when the response was threw the
	 * related exception.
	 * </p>
	 * 
	 * @param e
	 *            Contain the ValidateException message
	 * 
	 * @return String
	 */
	public static String warn(ValidateException e) {
		ApiResponse resp = new ApiResponse();
		resp.setWarning(e.getWarning());
		return resp.toString();
	}

	@Override
	public String toString() {
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public Warning getWarning() {
		return warning;
	}

	public void setWarning(Warning warning) {
		this.warning = warning;
	}

}
