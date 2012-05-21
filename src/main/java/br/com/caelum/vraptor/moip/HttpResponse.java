package br.com.caelum.vraptor.moip;

public class HttpResponse {

	private final Integer status;
	private final String response;

	public HttpResponse(Integer status, String response) {
		this.status = status;
		this.response = response;
	}

	public String getContent() {
		return response;
	}
	
	public int getStatus() {
		return status;
	}

}
