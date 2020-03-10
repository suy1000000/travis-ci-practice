package idv.villebez.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import idv.villebez.util.XSSUtils;

public class XSSDeserializer extends StdDeserializer<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XSSDeserializer() {
		this(null);
	}

	protected XSSDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return XSSUtils.stripSpecificXSS(p.getText());
	}

}
