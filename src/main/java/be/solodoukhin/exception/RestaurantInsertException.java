package be.solodoukhin.exception;

public class RestaurantInsertException extends RestaurantException {
	private final Object o;

	public Object getA() {
		return o;
	}

	public RestaurantInsertException(String message, Object a) {
		super(message);
		this.o = a;
	}
}
