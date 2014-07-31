package pw.ollie.skyprint.exception;

/**
 * Thrown if a given rotation value is invalid
 */
public final class InvalidRotationException extends Exception {
	private static final long serialVersionUID = -7678738081864194461L;

	public InvalidRotationException() {
		super();
	}

	public InvalidRotationException(final String c, final Throwable t) {
		super(c, t);
	}

	public InvalidRotationException(final String c) {
		super(c);
	}

	public InvalidRotationException(final Throwable t) {
		super(t);
	}
}
