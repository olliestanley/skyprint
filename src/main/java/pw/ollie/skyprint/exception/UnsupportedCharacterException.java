package pw.ollie.skyprint.exception;

public final class UnsupportedCharacterException extends Exception {
	private static final long serialVersionUID = 7468280828814598365L;

	public UnsupportedCharacterException() {
		super();
	}

	public UnsupportedCharacterException(String c, Throwable t) {
		super(c, t);
	}

	public UnsupportedCharacterException(String c) {
		super(c);
	}

	public UnsupportedCharacterException(Throwable t) {
		super(t);
	}
}
