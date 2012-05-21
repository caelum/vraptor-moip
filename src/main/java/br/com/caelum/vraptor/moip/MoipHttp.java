package br.com.caelum.vraptor.moip;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class MoipHttp {

	private final MoipEnvironment env;

	public MoipHttp(MoipEnvironment env) {
		this.env = env;
	}

	public String execute(String body) {
		PostMethod post = new PostMethod(env.getEnviarInstrucaoUrl());

		authThis(post);

		try {
			StringRequestEntity requestEntity = new StringRequestEntity(body,
					"application/x-www-formurlencoded", "UTF-8");
			post.setRequestEntity(requestEntity);
			return invokeExecution(post);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private HttpResponse invoke(HttpMethod method) {
		try {
			return HttpUtil.invoke(method);
		} catch (Exception e) {
			throw new PaymentException("no-token", e);
		}
	}

	private String invokeExecution(HttpMethod method) {
		HttpResponse response = invoke(method);
		try {
			if (response.getStatus() != 200) {
				IllegalStateException base = new IllegalStateException(
						method.getResponseBodyAsString());
				throw new PaymentException("no-token", base);
			}
		} catch (IOException e) {
			throw new PaymentException("unknown", e);
		}
		return response.getContent();
	}

	private void authThis(HttpMethod method) {
		String authHeader = env.getToken() + ":" + env.getKey();
		String encoded = new String(Base64.encodeBase64(authHeader.getBytes(),
				false));
		method.setRequestHeader("Authorization", "Basic " + encoded);
		method.setDoAuthentication(true);
	}

	public String confirmGet(String token) {
		GetMethod get = new GetMethod(env.getConsultaUrl() + token);
		authThis(get);
		return invokeExecution(get);
	}

}
