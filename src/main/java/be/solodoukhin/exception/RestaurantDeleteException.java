package be.solodoukhin.exception;


public class RestaurantDeleteException extends RestaurantException {

	private final Object a;

	public RestaurantDeleteException(String message, Object a) {
		super(message);
		this.a = a;
	}

	public Object getA() {
		return a;
	}

}
