package br.com.caelum.vraptor.moip;

public interface Moip {

	MoipUnico pagamentoUnico();

	String execute(String body);

	PaymentSituation retrieve(String token);

}