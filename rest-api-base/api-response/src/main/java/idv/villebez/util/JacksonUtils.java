package idv.villebez.util;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class JacksonUtils {
	/**
	 * 建立ObjectMapper
	 * 
	 * @return ObjectMapper
	 */
	protected static ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModules(new GuavaModule(), new JodaModule());
		return objectMapper;
	}

	/**
	 * 建立ObjectMapper(設定日期格式)
	 * 
	 * @param dateFormat
	 *            日期格式
	 * @return ObjectMapper
	 */
	protected static ObjectMapper createObjectMapper(String dateFormat) {
		ObjectMapper objectMapper = createObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModules(new GuavaModule(), new JodaModule());
		objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
		return objectMapper;
	}

	/**
	 * Bean 轉 JSON
	 * 
	 * @param object
	 *            Bean
	 * @return JSON 字串
	 */
	public static String writeBeanToJson(Object object) {
		ObjectMapper objectMapper = createObjectMapper();
		StringWriter writer = new StringWriter();
		JsonGenerator jsonGen;
		String json = null;

		try {
			jsonGen = new JsonFactory().createGenerator(writer);
			objectMapper.writeValue(jsonGen, object);
			jsonGen.close();
			json = writer.toString();
			writer.close();
			// } catch (JsonGenerationException e) {
			// throw new RuntimeException("JacksonUtils 錯誤", e);
			// } catch (JsonMappingException e) {
			// throw new RuntimeException("JacksonUtils 錯誤", e);
			// } catch (IOException e) {
			// throw new RuntimeException("JacksonUtils 錯誤", e);
		} catch (Exception e) {
			throw new RuntimeException("JacksonUtils 錯誤, json=[" + json + "]", e);
		} finally {
			objectMapper = null;
		}

		return json;
	}

	/**
	 * JSON 轉 Bean
	 * 
	 * @param json
	 *            JSON字串
	 * @param cls
	 *            Bean
	 * @return Bean
	 */
	public static <T> T writeJsonToBean(String json, Class<T> cls) {
		ObjectMapper objectMapper = createObjectMapper();
		T bean = null;

		try {
			bean = objectMapper.readValue(json, cls);
			// } catch (JsonParseException e) {
			// throw new RuntimeException("JacksonUtils 錯誤, json=["+json+"]",
			// e);
			// } catch (JsonMappingException e) {
			// throw new RuntimeException("JacksonUtils 錯誤, json=["+json+"]",
			// e);
			// } catch (IOException e) {
			// throw new RuntimeException("JacksonUtils 錯誤", e);
		} catch (Exception e) {
			throw new RuntimeException("JacksonUtils 錯誤, json=[" + json + "]", e);
		} finally {
			objectMapper = null;
		}

		return bean;
	}

	/**
	 * JSON 轉 Bean (設定日期格式)
	 * 
	 * @param json
	 *            JSON 字串
	 * @param cls
	 *            Bean
	 * @param dateFormat
	 *            日期格式
	 * @return Bean
	 */
	public static <T> T writeJsonToBean(String json, Class<T> cls, String dateFormat) {
		ObjectMapper objectMapper = createObjectMapper(dateFormat);
		T bean = null;

		try {
			bean = objectMapper.readValue(json, cls);
			// } catch (JsonParseException e) {
			// throw new RuntimeException("JacksonUtils 錯誤, json=["+json+"]",
			// e);
			// } catch (JsonMappingException e) {
			// throw new RuntimeException("JacksonUtils 錯誤, json=["+json+"]",
			// e);
			// } catch (IOException e) {
			// throw new RuntimeException("JacksonUtils 錯誤", e);
		} catch (Exception e) {
			throw new RuntimeException("JacksonUtils 錯誤, json=[" + json + "]", e);
		} finally {
			objectMapper = null;
		}

		return bean;
	}

	/**
	 * List 轉 JSON
	 * 
	 * @param list
	 *            list
	 * @return JSON 字串
	 */
	public static String writeListToJson(List<?> list) {
		ObjectMapper objectMapper = createObjectMapper();
		String json = null;

		try {
			json = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("JacksonUtils 錯誤", e);
		} finally {
			objectMapper = null;
		}

		return json;
	}

	/**
	 * JSON 轉 List
	 * 
	 * @param json
	 *            JSON 字串
	 * @param cls
	 *            Class
	 * @return List
	 */
	public static <T> List<T> writeJsonToList(String json, Class<T> cls) {
		ObjectMapper objectMapper = createObjectMapper();
		List<T> list = null;

		try {
			list = objectMapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, cls));
			// } catch (JsonParseException e) {
			// throw new RuntimeException("JacksonUtils 錯誤, json=["+json+"]",
			// e);
			// } catch (JsonMappingException e) {
			// throw new RuntimeException("JacksonUtils 錯誤, json=["+json+"]",
			// e);
			// } catch (IOException e) {
			// throw new RuntimeException("JacksonUtils 錯誤", e);
		} catch (Exception e) {
			throw new RuntimeException("JacksonUtils 錯誤, json=[" + json + "]", e);
		} finally {
			objectMapper = null;
		}

		return list;
	}

	/**
	 * JSON 轉 List (設定日期格式)
	 * 
	 * @param json
	 *            JSON 字串
	 * @param cls
	 *            Class
	 * @param dateFormat
	 *            日期格式
	 * @return List
	 */
	public static <T> List<T> writeJsonToList(String json, Class<T> cls, String dateFormat) {
		ObjectMapper objectMapper = createObjectMapper(dateFormat);
		List<T> list = null;

		try {
			list = objectMapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, cls));
			// } catch (JsonParseException e) {
			// throw new RuntimeException("JacksonUtils 錯誤, json=["+json+"]",
			// e);
			// } catch (JsonMappingException e) {
			// throw new RuntimeException("JacksonUtils 錯誤, json=["+json+"]",
			// e);
			// } catch (IOException e) {
			// throw new RuntimeException("JacksonUtils 錯誤", e);
		} catch (Exception e) {
			throw new RuntimeException("JacksonUtils 錯誤, json=[" + json + "]", e);
		} finally {
			objectMapper = null;
		}

		return list;
	}

	/**
	 * JSON 轉 JsonNode
	 * 
	 * @param json
	 *            JSON 字串
	 * @return JsonNode
	 */
	public static JsonNode writeJsonToJsonNode(String json) {
		ObjectMapper objectMapper = createObjectMapper();
		JsonNode jsonNode = null;

		try {
			jsonNode = objectMapper.readTree(json);
			// } catch (JsonProcessingException e) {
			// throw new RuntimeException("JacksonUtils 錯誤, json=["+json+"]",
			// e);
			// } catch (IOException e) {
			// throw new RuntimeException("JacksonUtils 錯誤", e);
		} catch (Exception e) {
			throw new RuntimeException("JacksonUtils 錯誤, json=[" + json + "]", e);
		} finally {
			objectMapper = null;
		}

		return jsonNode;
	}

	/**
	 * Bean 轉 JsonNode
	 * 
	 * @param object
	 *            Bean
	 * @return JsonNode
	 */
	public JsonNode writeBeanToJsonNode(Object object) {
		ObjectMapper objectMapper = createObjectMapper();
		JsonNode jsonNode = null;

		try {
			TokenBuffer buffer = new TokenBuffer(objectMapper);
			objectMapper.writeValue(buffer, object);
			jsonNode = objectMapper.readTree(buffer.asParser());
			buffer.close();
		} catch (JsonGenerationException e) {
			throw new RuntimeException("JacksonUtils 錯誤", e);
		} catch (JsonMappingException e) {
			throw new RuntimeException("JacksonUtils 錯誤", e);
		} catch (IOException e) {
			throw new RuntimeException("JacksonUtils 錯誤", e);
		} finally {
			objectMapper = null;
		}

		return jsonNode;
	}

	/**
	 * List 轉 JsonNode
	 * 
	 * @param list
	 *            list
	 * @return JsonNode
	 */
	public static JsonNode writeListToJsonNode(List<?> list) {
		ObjectMapper objectMapper = createObjectMapper();
		JsonNode jsonNode = null;

		try {
			String json = objectMapper.writeValueAsString(list);
			jsonNode = objectMapper.readTree(json);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("JacksonUtils 錯誤", e);
		} catch (IOException e) {
			throw new RuntimeException("JacksonUtils 錯誤", e);
		} finally {
			objectMapper = null;
		}

		return jsonNode;
	}

	/**
	 * 建立ObjectNode
	 * 
	 * @return ObjectNode
	 */
	public static ObjectNode createObjectNode() {
		ObjectMapper objectMapper = createObjectMapper();
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectMapper = null;
		return objectNode;
	}

	/**
	 * 建立ArrayNode
	 * 
	 * @return ArrayNode
	 */
	public static ArrayNode createArrayNode() {
		ObjectMapper objectMapper = createObjectMapper();
		ArrayNode arrayNode = objectMapper.createArrayNode();
		objectMapper = null;
		return arrayNode;
	}

	/**
	 * JsonNode 轉 Bean (設定日期格式)
	 * 
	 * @param node
	 *            node
	 * @param cls
	 *            cls
	 * @param dateFormat
	 *            dateFormat
	 * @return Object
	 */
	public static <T> T writeJsonNodeToBean(JsonNode node, Class<T> cls, String dateFormat) {
		ObjectMapper objectMapper = createObjectMapper(dateFormat);
		T bean = null;
		try {
			bean = objectMapper.readValue(node.traverse(), cls);
		} catch (Exception e) {
			throw new RuntimeException("JacksonUtils 錯誤, node=[" + node + "]", e);
		} finally {
			objectMapper = null;
		}
		return bean;
	}

	/**
	 * JsonNode 轉 Bean
	 * 
	 * @param node
	 *            node
	 * @param cls
	 *            cls
	 * @return Object
	 */
	public static <T> T writeJsonNodeToBean(JsonNode node, Class<T> cls) {
		ObjectMapper objectMapper = createObjectMapper();
		T bean = null;
		try {
			bean = objectMapper.readValue(node.traverse(), cls);
		} catch (Exception e) {
			throw new RuntimeException("JacksonUtils 錯誤, node=[" + node + "]", e);
		} finally {
			objectMapper = null;
		}
		return bean;
	}

	/**
	 * JSON 轉 List
	 * 
	 * @param node
	 *            JSON 字串
	 * @param cls
	 *            Class
	 * @return List
	 */
	public static <T> List<T> writeJsonNodeToList(JsonNode node, Class<T> cls) {
		ObjectMapper objectMapper = createObjectMapper();
		List<T> list = null;

		try {
			list = objectMapper.readValue(node.traverse(),
					TypeFactory.defaultInstance().constructCollectionType(List.class, cls));
		} catch (Exception e) {
			throw new RuntimeException("JacksonUtils 錯誤, node=[" + node + "]", e);
		} finally {
			objectMapper = null;
		}

		return list;
	}

	/**
	 * JSON 轉 List (設定日期格式)
	 *
	 * @param node
	 *            JSON 字串
	 * @param cls
	 *            Class
	 * @param dateFormat
	 *            日期格式
	 * @return List
	 */
	public static <T> List<T> writJsonNodeToList(JsonNode node, Class<T> cls, String dateFormat) {
		ObjectMapper objectMapper = createObjectMapper(dateFormat);
		List<T> list = null;

		try {
			list = objectMapper.readValue(node.traverse(),
					TypeFactory.defaultInstance().constructCollectionType(List.class, cls));
		} catch (Exception e) {
			throw new RuntimeException("JacksonUtils 錯誤, node=[" + node + "]", e);
		} finally {
			objectMapper = null;
		}

		return list;
	}
}
