package idv.villebez.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * The class <pre>{@code ValidateParameterException}</pre> that indicates parameter error conditions that a reasonable
 * application might want to catch.
 * 
 */
public class ValidateParameterException extends ValidateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 895526538873991131L;
	
	/**
	 *  <p> The class object of the enum parameter type from which to return a constant. </p>
	 *  ParameterType that can be used :
	 *  <ul>
	 *  <li>REQUIRED</li>
	 *  <li>LENGTH</li>
	 *  <li>FORMAT</li>
	 *  <li>OTHER</li>
	 *  </ul>
	 */
	public enum ParameterType {
		REQUIRED("01"), LENGTH("02"), FORMAT("03"), OTHER("99");

		private String value;

		private ParameterType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	private ParameterType parameterType;
	private String message = "";
	private String field = "";
	
	/**
	 * <p> Constructs a new ValidateParameterException with parameterType {@link ParameterType}、field and the specified detail message. </p>
	 * 	
	 * @param parameterType
	 *   	  Define the parameter type {@link ParameterType}.	 
	 * @param field
	 * 		  Define parameter exception field.
	 * @param message 
	 * 	      The specified detail message.
	 */
	public ValidateParameterException(ParameterType parameterType, String field, String message) {
		super(message);
		this.parameterType = parameterType;
		if(StringUtils.isNoneBlank(message)) this.message = message;
		if(StringUtils.isNoneBlank(field)) this.field = field;
	}

	@Override
	protected String getCode() {
		return ValidateException.Type.PARAMETER.getValue() + parameterType.value;
	}

	@Override
	protected String getDesc() {
		String desc = field;
		if(StringUtils.isNoneBlank(message)) desc = desc + " : " + message;
		return desc;
	}
	

}