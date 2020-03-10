package idv.villebez.util;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import idv.villebez.exception.ValidateParameterException;
import idv.villebez.exception.ValidateParameterException.ParameterType;

/**
 * 
 * @see idv.villebez.model.CursorPagingModel
 * 
 * @author paul.chen
 * @since 0.1.0
 */
public class CursorUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	private static Base64.Decoder decoder = Base64.getUrlDecoder();
	private static Base64.Encoder encoder = Base64.getUrlEncoder();

	
	/**
	 * 將cursor資料解碼至指定的類別物件 {@link Class} (解碼方式:Base64)
	 * @param <T> 指定的映射類別
	 * @param cursor 指標資料
	 * @param classOfT 指定的映射類別
	 * @return 指定類別物件(內容為指標資料)
	 * @throws ValidateParameterException
	 * @throws IOException 
	 */
	public static <T> T decode(String cursor, Class<T> classOfT) throws ValidateParameterException, IOException {
		String json = null;
		T obj;
		try{
			json = new String(decoder.decode(cursor.getBytes()));
			obj = mapper.readValue(json, classOfT);
		}catch(JsonParseException | JsonMappingException e){
			throw new ValidateParameterException(ParameterType.OTHER, "cursor", e.getMessage());
		}
		return obj;
	}

	/**
	 * 將物件轉為JSON字串並進行編碼(編碼方式:Base64)
	 * @param obj 傳入物件
	 * @return 編碼完成的字串
	 * @throws JsonProcessingException 
	 */
	public static String encode(Object obj) throws JsonProcessingException {
		return encoder.encodeToString(mapper.writeValueAsString(obj).getBytes());
	}

}
