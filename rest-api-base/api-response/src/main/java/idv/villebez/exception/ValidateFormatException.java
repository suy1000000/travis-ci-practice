package idv.villebez.exception;

/**
 * The class <pre>{@code ValidateFormatException}</pre> that indicates format error conditions that a reasonable
 * application might want to catch.
 * 
 */
public class ValidateFormatException extends ValidateParameterException {

	private static final long serialVersionUID = 7613306272759024482L;

	/**
	 * <p> Constructs a new validateFormatException with the specified field. </p>
	 * 
	 * @param field
	 * 		  Define format exception field.
	 */
	public ValidateFormatException(String field) {
		this(field, "wrong format!!");
	}

	/**
	 * <p> Constructs a new validateFormatException with field and the specified detail message. </p>
	 * 	
	 * @param field
	 * 		  Define format exception field.
	 * @param message 
	 * 	      The specified detail message.
	 * 
	 */
	public ValidateFormatException(String field, String message) {
		super(ValidateParameterException.ParameterType.FORMAT, field, message);
	}

	     
}