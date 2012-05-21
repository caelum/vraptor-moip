package br.com.caelum.vraptor.moip;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;


public class HttpUtil {

	public static HttpResponse invoke(HttpMethod method) throws HttpException,
			IOException {
		HttpClient client = new HttpClient();
		try {
			Integer status = client.executeMethod(method);
			String response = method.getResponseBodyAsString();
			return new HttpResponse(status, response);
		} finally {
			method.releaseConnection();
		}
	}

	public static HttpResponse get(String uri) throws HttpException, IOException {
		String encoded = URIUtil.encodePath(uri);
		GetMethod get = new GetMethod(encoded);
		return invoke(get);
	}

}
