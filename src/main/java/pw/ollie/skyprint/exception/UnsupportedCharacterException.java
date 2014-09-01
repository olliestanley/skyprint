package pw.ollie.skyprint.exception;

/**
 * Thrown if SkyPrint doesn't support a character given in the /skywrite command
 */
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
