package idv.villebez.exception;

/**
 * The class <pre>{@code ValidateLengthException}</pre> that indicates length error conditions that a reasonable
 * application might want to catch.
 * 
 */
public class ValidateLengthException extends ValidateParameterException {

	private static final long serialVersionUID = 4945065758437430420L;

	/**
	 * <p> Constructs a new validateLengthException with field. </p>
	 * 
	 * @param field
	 * 		  Define length exception field.
	 */
	public ValidateLengthException(String field) {
		this(field, "parameter length error!!");
	}
	
	/**
	 * <p> Constructs a new validateLengthException with field and the specified detail message. </p>
	 * 	
	 * @param field
	 * 		  Define length exception field.
	 * @param message 
	 * 	      The specified detail message.
	 */
	public ValidateLengthException(String field, String message) {
		super(ValidateParameterException.ParameterType.LENGTH, field, message);
	}

}