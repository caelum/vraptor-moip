package br.com.caelum.vraptor.moip;

import java.net.MalformedURLException;
import java.net.URL;

public interface MoipEnvironment {
	
	String getEnviarInstrucaoUrl();
	String getConsultaUrl();
	String getToken();
	String getKey();
	URL getMoipPaymentUrlFor(String token) throws MalformedURLException;

}
