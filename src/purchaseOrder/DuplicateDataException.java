package purchaseOrder;

public class DuplicateDataException extends RuntimeException {
	
	public DuplicateDataException() {
		super("Duplicate data given.");
	}
	
	public DuplicateDataException(String s) {
		super(s);
	}

}
