package br.com.caelum.vraptor.moip;


public class PaymentException extends RuntimeException {

	private static final long serialVersionUID = -1643354235842602911L;

	public PaymentException(String key, Exception e) {
		super(key, e);
	}

	public PaymentException(String key) {
		super(key);
	}

}
