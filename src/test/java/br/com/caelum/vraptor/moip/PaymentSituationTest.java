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
		List<String> statuses = PaymentSituation.retrieveStatuses("<ns1:ConsultarTokenResponse xmlns:ns1=\"http://www.moip.com.br/ws/alpha/\"><RespostaConsultar><ID>201205211623521850000000651284</ID><Status>Sucesso</Status><Autorizacao><Pagador><Nome>Guilherme Silveira</Nome><Email>guilherme.silveira@caelum.com.br</Email></Pagador><EnderecoCobranca><Logradouro>av</Logradouro><Numero>1</Numero><Complemento>2</Complemento><Bairro>VILA</Bairro><CEP>041</CEP><Cidade>SÃO PAULO</Cidade><Estado>AC</Estado><Pais>BRA</Pais><TelefoneFixo>(11)5571</TelefoneFixo></EnderecoCobranca><Recebedor><Nome>Guilherme Silveira</Nome><Email>guilherme.silveira@caelum.com.br</Email></Recebedor><Pagamento><Data>2012-05-21T14:02:48.000-03:00</Data><DataCredito>2012-06-04T00:00:00.000-03:00</DataCredito><TotalPago Moeda=\"BRL\">10.00</TotalPago><TaxaParaPagador Moeda=\"BRL\">0.00</TaxaParaPagador><TaxaMoIP Moeda=\"BRL\">1.13</TaxaMoIP><ValorLiquido Moeda=\"BRL\">8.87</ValorLiquido><FormaPagamento>CartaoDeCredito</FormaPagamento><InstituicaoPagamento>Visa</InstituicaoPagamento><Status Tipo=\"1\">Autorizado</Status><Parcela><TotalParcelas>1</TotalParcelas></Parcela><CodigoMoIP>0000.0005.8951</CodigoMoIP></Pagamento></Autorizacao></RespostaConsultar></ns1:ConsultarTokenResponse>");
		assertTrue(statuses.contains("Autorizado"));
		assertEquals(1, statuses.size());
	}

	
	@Test
	public void shouldIgnoreOtherStatus() {
		List<String> statuses = PaymentSituation.retrieveStatuses("<ns1:ConsultarTokenResponse xmlns:ns1=\"http://www.moip.com.br/ws/alpha/\"><RespostaConsultar><ID>201205211623521850000000651284</ID><Status>Sucesso</Status><Autorizacao><Pagador><Nome>Guilherme Silveira</Nome><Email>guilherme.silveira@caelum.com.br</Email></Pagador><EnderecoCobranca><Logradouro>av</Logradouro><Numero>1</Numero><Complemento>2</Complemento><Bairro>VILA</Bairro><CEP>041</CEP><Cidade>SÃO PAULO</Cidade><Estado>AC</Estado><Pais>BRA</Pais><TelefoneFixo>(11)5571</TelefoneFixo></EnderecoCobranca><Recebedor><Nome>Guilherme Silveira</Nome><Email>guilherme.silveira@caelum.com.br</Email></Recebedor><Pagamento><Data>2012-05-21T14:02:48.000-03:00</Data><DataCredito>2012-06-04T00:00:00.000-03:00</DataCredito><TotalPago Moeda=\"BRL\">10.00</TotalPago><TaxaParaPagador Moeda=\"BRL\">0.00</TaxaParaPagador><TaxaMoIP Moeda=\"BRL\">1.13</TaxaMoIP><ValorLiquido Moeda=\"BRL\">8.87</ValorLiquido><FormaPagamento>CartaoDeCredito</FormaPagamento><InstituicaoPagamento>Visa</InstituicaoPagamento><Status Tipo=\"1\">Autorizado</Status><Parcela><TotalParcelas>1</TotalParcelas></Parcela><CodigoMoIP>0000.0005.8951</CodigoMoIP></Pagamento></Autorizacao></RespostaConsultar><Status Tipo=\"1\">Nao autorizado</Status></ns1:ConsultarTokenResponse>");
		assertTrue(statuses.contains("Autorizado"));
		assertEquals(1, statuses.size());
	}

}
