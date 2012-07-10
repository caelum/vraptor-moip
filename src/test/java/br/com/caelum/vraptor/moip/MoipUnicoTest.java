package br.com.caelum.vraptor.moip;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.vraptor.moip.testing.MockMoip;


public class MoipUnicoTest {
	@Test
	public void shouldBeAbleToRetrieveTheToken() {
		String token = "T2N0L0X8E0S71217H3W1T4F4S4G4K731D010V0S0V0S080M010E0Q082X2";

		MockMoip moip = new MockMoip();
		moip.setExpectedAnswerToRequest(
			"<ns1:EnviarInstrucaoUnicaResponse xmlns:ns1=\"https://desenvolvedor.moip.com.br/sandbox/\">" +
				"<Resposta>" +
					"<ID>200807272314444710000000000022</ID>" +
					"<Status>Sucesso</Status>" +
					"<Token>T2N0L0X8E0S71217H3W1T4F4S4G4K731D010V0S0V0S080M010E0Q082X2</Token>" +
				"</Resposta>" +
			"</ns1:EnviarInstrucaoUnicaResponse>");
		MoipUnico payment = new MoipUnico(moip);
		String result = payment.execute();
		assertEquals(token, result);
	}

	@Test(expected=MoipException.class)
	public void shouldThrowAnExceptionIfTheResponseIsNotSuccessful() throws Exception {
		MockMoip moip = new MockMoip();
		moip.setExpectedAnswerToRequest(
			"<ns1:EnviarInstrucaoUnicaResponse xmlns:ns1=\"https://desenvolvedor.moip.com.br/sandbox/\">" +
				"<Resposta>" +
					"<ID>200807272314444710000000000022</ID>" +
					"<Status>Erro</Status>" +
					"<Erro>Uma mensagem de erro aqui</Erro>" +
				"</Resposta>" +
			"</ns1:EnviarInstrucaoUnicaResponse>");
		MoipUnico payment = new MoipUnico(moip);
		payment.execute();
	}
}
