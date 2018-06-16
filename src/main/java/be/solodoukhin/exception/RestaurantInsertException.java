package be.solodoukhin.exception;

import be.solodoukhin.model.Article;

public class RestaurantInsertException extends RestaurantException {
	private final Article a;

	public Article getA() {
		return a;
	}

	public RestaurantInsertException(String message, Article a) {
		super(message);
		this.a = a;
	}
}
