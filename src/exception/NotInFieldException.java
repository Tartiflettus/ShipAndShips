package exception;


/**
 * Exception thrown when given invalid position referring to the battlefield
 * (< 0 or >= size)
 * @author Victor
 *
 */
public class NotInFieldException extends ShipException {

	public NotInFieldException() {
		// TODO Auto-generated constructor stub
	}

	public NotInFieldException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotInFieldException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NotInFieldException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotInFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
