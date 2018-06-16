package be.solodoukhin.exception;

import be.solodoukhin.model.Article;

public class RestaurantDeleteException extends RestaurantException {

	private final Article a;

	public RestaurantDeleteException(String message, Article a) {
		super(message);
		this.a = a;
	}

	public Article getA() {
		return a;
	}

}
