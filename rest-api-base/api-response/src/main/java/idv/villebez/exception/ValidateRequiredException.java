package idv.villebez.exception;

/**
 * The class <pre>{@code ValidateRequiredException}</pre> that indicates required error conditions that a reasonable
 * application might want to catch.
 * 
 */
public class ValidateRequiredException extends ValidateParameterException {

	private static final long serialVersionUID = 3259872948995972547L;

	/**
	 * <p> Constructs a new ValidateRequiredException with field. </p>
	 * 	
	 * @param field
	 * 		  Define required exception field.
	 */
	public ValidateRequiredException(String field) {
		this(field, "can not be null!!");
	}

	/**
	 * <p> Constructs a new ValidateRequiredException with field and the specified detail message. </p>
	 * 	
	 * @param field
	 * 		  Define required exception field.
	 * @param message 
	 * 	      The specified detail message.
	 */
	public ValidateRequiredException(String field, String message) {
		super(ValidateParameterException.ParameterType.REQUIRED, field, message);
	}

}