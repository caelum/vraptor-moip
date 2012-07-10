package br.com.caelum.vraptor.moip;

/**
 * A simple moip wrapper
 * 
 * @author guilherme silveira
 */
public class DefaultMoip implements Moip {

	private final MoipEnvironment env;

	public DefaultMoip(MoipEnvironment env) {
		this.env = env;
	}

	@Override
	public MoipUnico pagamentoUnico() {
		return new MoipUnico(this);
	}

	@Override
	public String execute(String body) {
		return new MoipHttp(env).execute(body);
	}
	
	@Override
	public PaymentSituation retrieve(String token) {
		String response = new MoipHttp(env).confirmGet(token);
		return new PaymentSituation(response);
	}

}
