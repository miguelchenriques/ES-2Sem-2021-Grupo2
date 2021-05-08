package parseFolderTest;

public class ParsingException extends Exception {
	int offset = -1, line = -1, column = -1;

	public ParsingException(int offset, int line, int column, String msg) {
		super(msg);
		this.offset = offset;
		this.line = line;
		this.column = column;
	}

	public String getMessage() {
		StringBuffer buf = new StringBuffer(super.getMessage());
		if (offset != -1 || line != -1 || column != -1) {
			buf.append(" [");
			if (offset != -1) {
				buf.append("offset:" + offset + ",");
			}
			if (line != -1) {
				buf.append("line:" + (line+1) + ",");
			}
			if (column != -1) {
				buf.append("column:" + (column+1));
			}
			if (buf.charAt(buf.length()-1) == ',') {
				buf.deleteCharAt(buf.length() - 1);
			}
			buf.append(']');
		}
		
		return buf.toString();
	}
}
