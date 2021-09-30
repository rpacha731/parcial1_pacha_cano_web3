package pacha.cano.parcial1.negocio.exceptions;

public class PublicacionAsignadaException extends Exception {

	private static final long serialVersionUID = 5472509512704239504L;

	public PublicacionAsignadaException() {
	
	}

	public PublicacionAsignadaException(String message) {
		super(message);
		
	}

	public PublicacionAsignadaException(Throwable cause) {
		super(cause);
		
	}

	public PublicacionAsignadaException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PublicacionAsignadaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}
}
