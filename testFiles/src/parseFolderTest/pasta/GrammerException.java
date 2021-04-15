package parseFolderTest.pasta;

public class GrammerException extends ParsingException {

	public GrammerException(int offset, String msg) {
		super(offset, msg);
	}

	public GrammerException(int offset, int line, int column, String msg) {
		super(offset, line, column, msg);
	}

}
