package pw.ollie.skyprint.exception;

public final class InvalidRotationException extends Exception {
	private static final long serialVersionUID = -7678738081864194461L;

	public InvalidRotationException() {
		super();
	}

	public InvalidRotationException(String c, Throwable t) {
		super(c, t);
	}

	public InvalidRotationException(String c) {
		super(c);
	}

	public InvalidRotationException(Throwable t) {
		super(t);
	}
}
