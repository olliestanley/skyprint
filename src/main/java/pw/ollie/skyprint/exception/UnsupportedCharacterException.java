package pw.ollie.skyprint.exception;

public final class UnsupportedCharacterException extends Exception {
	private static final long serialVersionUID = 7468280828814598365L;

	public UnsupportedCharacterException() {
		super();
	}

	public UnsupportedCharacterException(final String c, final Throwable t) {
		super(c, t);
	}

	public UnsupportedCharacterException(final String c) {
		super(c);
	}

	public UnsupportedCharacterException(final Throwable t) {
		super(t);
	}
}
