package br.com.caelum.vraptor.moip.testing;

import br.com.caelum.vraptor.moip.Moip;
import br.com.caelum.vraptor.moip.MoipUnico;
import br.com.caelum.vraptor.moip.PaymentSituation;

public class MockMoip implements Moip {

	private PaymentSituation expectedSituation;
	private String expectedAnswer;

	@Override
	public MoipUnico pagamentoUnico() {
		return new MoipUnico(this);
	}

	@Override
	public String execute(String body) {
		return expectedAnswer;
	}

	@Override
	public PaymentSituation retrieve(String token) {
		return expectedSituation;
	}

	public void setExpectedSituation(PaymentSituation expectedSituation) {
		this.expectedSituation = expectedSituation;
	}

	public void setExpectedAnswerToRequest(String expectedAnswer) {
		this.expectedAnswer = expectedAnswer;
	}
}
