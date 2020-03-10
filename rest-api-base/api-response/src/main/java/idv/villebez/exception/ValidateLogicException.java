package idv.villebez.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * The class <pre>{@code ValidateLogicException}</pre> that indicates logic error conditions that a reasonable
 * application might want to catch.
 * 
 */
public class ValidateLogicException extends ValidateException {

	private static final long serialVersionUID = 8362987233908270648L;
	private LogicType logicType = LogicType.DEFAULT;
	private String message;

	/**
	 *  <p> The class object of the enum logic type from which to return a constant. </p>
	 *  LogicType that can be used :
	 *  <ul>
	 *  <li>DUPLICATE</li>
	 *  <li>NODATA</li>
	 *  <li>UPTOLIMIT</li>
	 *  <li>NO_PERMISSION</li>
	 *  <li>DEFAULT</li>
	 *  </ul>
	 */
	public enum LogicType {
		DUPLICATE("10"), NODATA("11"), UPTOLIMIT("12"), NO_PERMISSION("13"), DEFAULT("");

		private String value;

		private LogicType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public static LogicType getEnum(String value) {
			for (LogicType v : values())
				if (v.getValue().equalsIgnoreCase(value))
					return v;
			
			return DEFAULT;
		}
	}

	/**
	 * <p> Constructs a new ValidateLogicException with code {@link LogicType} and the specified detail message. </p>
	 * 	
	 * @param code
	 * 		  Define logic exception type {@link LogicType}.
	 * @param message 
	 * 	      The specified detail message.
	 */
	public ValidateLogicException(String code, String message) {
		super(message);
		if (StringUtils.isNotBlank(code)) {
			this.logicType = LogicType.getEnum(code);
		}
		this.message = message;
	}
	
	/**
	 * <p> Constructs a new ValidateLogicException with logicType {@link LogicType} and the specified detail message. </p>
	 * 	
	 * @param logicType
	 * 		  Define the logic exception type {@link LogicType}.
	 * @param message 
	 * 	      The specified detail message.
	 */
	public ValidateLogicException(LogicType logicType, String message) {
		super(message);
		if (logicType != null)
			this.logicType = logicType;
		this.message = message;
	}

	@Override
	protected String getCode() {
		return ValidateException.Type.LOGIC.getValue() + logicType.value;
	}

	@Override
	protected String getDesc() {
		return message;
	}

}