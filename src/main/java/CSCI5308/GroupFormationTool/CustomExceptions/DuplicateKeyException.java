package CSCI5308.GroupFormationTool.CustomExceptions;

@SuppressWarnings("serial")
public class DuplicateKeyException extends Exception {
	public DuplicateKeyException(String message) {
		super(message);
	}
}
