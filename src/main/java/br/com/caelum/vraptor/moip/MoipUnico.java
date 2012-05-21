package br.com.caelum.vraptor.moip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MoipUnico {

	private String descricao;
	private String pagador;
	private String email;
	private String valor;
	private String id;
	private String returnUri;
	private String identidade;
	private final Moip moip;
	
	public MoipUnico(Moip moip) {
		this.moip = moip;
	}

	public MoipUnico descricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public String execute() {
	    String body = "<EnviarInstrucao>\n"+
	            "<InstrucaoUnica>\n"+
	              "<Razao>" +descricao+"</Razao>\n"+
	              "<IdProprio>" +id+"</IdProprio>\n"+
	              "<Valores>\n"+
	                "<Valor moeda=\"BRL\">" +valor+"</Valor>\n"+
	              "</Valores>\n"+
	              "<Pagador>\n"+
	                "<Nome>" +pagador+"</Nome>\n"+
	                "<Email>" +email+"</Email>\n"+
	                "<Identidade>" +identidade+"</Identidade>\n"+
	              "</Pagador>\n"+
	              "<URLRetorno>" +returnUri+"</URLRetorno>\n"+
	            "</InstrucaoUnica>\n"+
	          "</EnviarInstrucao>";
	        String response = moip.execute(body);
	        Pattern pattern = Pattern.compile(".*<Token>(.*)</Token>.*");
	        Matcher matcher = pattern.matcher(response);
	        matcher.matches();
	        return matcher.group(1);
	}

	public MoipUnico id(String id) {
		this.id = id;
		return this;
	}

	public MoipUnico valor(String valor) {
		this.valor = valor;
		return this;
	}

	public MoipUnico pagador(String pagador) {
		this.pagador = pagador;
		return this;
	}
	public MoipUnico email(String email) {
		this.email = email;
		return this;
	}
	public MoipUnico identidade(String identidade) {
		this.identidade = identidade;
		return this;
	}

	public MoipUnico returnUri(String returnUri) {
		this.returnUri = returnUri;
		return this;
	}


}
