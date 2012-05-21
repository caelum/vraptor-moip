package br.com.caelum.vraptor.moip;

/**
 * A simple moip wrapper
 * 
 * @author guilherme silveira
 */
public class Moip {

	private final MoipEnvironment env;

	public Moip(MoipEnvironment env) {
		this.env = env;
	}

	public MoipUnico pagamentoUnico() {
		return new MoipUnico(this);
	}

	public String execute(String body) {
		return new MoipHttp(env).execute(body);
	}
	
	public PaymentSituation retrieve(String token) {
		String response = new MoipHttp(env).confirmGet(token);
		return new PaymentSituation(response);
	}

}
