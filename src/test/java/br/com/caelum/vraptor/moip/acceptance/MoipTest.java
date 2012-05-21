package br.com.caelum.vraptor.moip.acceptance;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.environment.DefaultEnvironment;
import br.com.caelum.vraptor.moip.Moip;
import br.com.caelum.vraptor.moip.MoipUnico;
import br.com.caelum.vraptor.moip.PaymentSituation;
import br.com.caelum.vraptor.moip.VRaptorMoipEnvironment;

public class MoipTest {

	private VRaptorMoipEnvironment env;
	private Moip moip;

	@Before
	public void before() throws IOException {
		this.env = new VRaptorMoipEnvironment(new DefaultEnvironment("testing"));
		this.moip = new Moip(env);
	}

	@Test
	public void shouldBeAbleToRetrieveTheToken() {
		MoipUnico moip = simplePayment();
		String token = moip.execute();
		Pattern pattern = Pattern.compile("\\w+");
		assertTrue(pattern.matcher(token).matches());
	}

	private MoipUnico simplePayment() {
		return this.moip.pagamentoUnico().descricao("TESTE")
				.id("OUR_ID_" + System.currentTimeMillis()).valor("100")
				.pagador("Teste Cliente Pagador").email("cliente@email.com.br")
				.identidade("11111111111")
				.returnUri("http://return_to_this_uri");
	}
	
	@Test
	public void shouldBeAbleToCheckStatus() {
		PaymentSituation situation = moip.retrieve("A2P0K1J2G0Y5T251M1Q4D0K242E8T5J5H130U080Z08000I0V6F4V960R6E2");
		assertTrue(situation.isCompletedAndSuccessful());
	}

}
