package br.com.caelum.vraptor.moip;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class PaymentSituation {

	private final static Logger logger = LoggerFactory
			.getLogger(PaymentSituation.class);
	private final List<String> statuses;

	public PaymentSituation(String response) {
		this.statuses = retrieveStatuses(response);
	}

	static List<String> retrieveStatuses(String response) {
		List<String> statuses = new ArrayList<String>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = factory.newDocumentBuilder();
			ByteArrayInputStream is = new ByteArrayInputStream(
					response.getBytes("UTF-8"));
			Document doc = builder.parse(is);
			XPathFactory xfactory = XPathFactory.newInstance();
			XPath xpath = xfactory.newXPath();
			XPathExpression expr = xpath.compile("//Pagamento/Status/text()");
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {
				String value = nodes.item(i).getNodeValue();
				statuses.add(value);
			}
		} catch (Exception e) {
			logger.error("Unable to process MOIP data", e);
		}
		return statuses;
	}

	public boolean isCompletedAndSuccessful() {
		return statuses.contains("Concluido")
				|| statuses.contains("Autorizado");
	}

	public boolean isWaiting() {
		return statuses.contains("EmAnalise")
				|| statuses.contains("BoletoImpresso");
	}

	public boolean isCancelled() {
		return statuses.contains("Cancelado");
	}

	public boolean hasStarted() {
		return statuses.contains("Iniciado");
	}

}
