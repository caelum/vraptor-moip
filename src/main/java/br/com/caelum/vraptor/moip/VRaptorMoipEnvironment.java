package br.com.caelum.vraptor.moip;

import java.net.MalformedURLException;
import java.net.URL;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@ApplicationScoped
@Component
public class VRaptorMoipEnvironment implements MoipEnvironment{

	private final Environment env;

	public VRaptorMoipEnvironment(Environment env) {
		this.env = env;
	}

	@Override
	public String getEnviarInstrucaoUrl() {
		return env.get("moip.enviar.instrucao.url");
	}

	@Override
	public String getConsultaUrl() {
		return env.get("moip.consulta");
	}

	@Override
	public String getToken() {
		return env.get("moip.token");
	}

	@Override
	public String getKey() {
		return env.get("moip.key");
	}

	public URL getMoipPaymentUrlFor(String token) throws MalformedURLException {
		return new URL(env.get("moip.payment.return") + token);
	}

}
