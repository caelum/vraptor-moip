package br.com.caelum.vraptor.moip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class PaymentSituationTest {
	
	@Test
	public void shouldGrabNoStatusIfNoPaymentIsPresent() {
		List<String> statuses = PaymentSituation.retrieveStatuses("");
		assertTrue(statuses.isEmpty());
	}

	
	@Test
	public void shouldGrabTheStatusIfExisting() {
		List<String> statuses = PaymentSituation.retrieveStatuses("<Root><Pagamento><Status>paid</Status></Pagamento></Root>");
		assertTrue(statuses.contains("paid"));
		assertEquals(1, statuses.size());
	}

	
	@Test
	public void shouldIgnoreOtherStatus() {
		List<String> statuses = PaymentSituation.retrieveStatuses("<Root><Status>unpaid</Status><Pagamento><Status>paid</Status></Pagamento></Root>");
		assertTrue(statuses.contains("paid"));
		assertEquals(1, statuses.size());
	}

}
