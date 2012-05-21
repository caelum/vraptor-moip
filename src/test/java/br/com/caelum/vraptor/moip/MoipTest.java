package br.com.caelum.vraptor.moip;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.environment.DefaultEnvironment;

public class MoipTest {

	private VRaptorMoipEnvironment env;
	@Before
	public void before() throws IOException {
		this.env = new VRaptorMoipEnvironment(new DefaultEnvironment("testing"));
	}
	@Test
	public void shouldBeAbleToRetrieveTheToken() {
		MoipUnico moip = new Moip(env).pagamentoUnico()
				.descricao("TESTE").id("OUR_ID_" + System.currentTimeMillis())
				.valor("100").pagador("Teste Cliente Pagador")
				.email("cliente@email.com.br")
				.identidade("11111111111")
				.returnUri("http://return_to_this_uri");
		String token = moip.execute();
		Pattern pattern = Pattern.compile("\\w+");
		assertTrue(pattern.matcher(token).matches());
	}

}
