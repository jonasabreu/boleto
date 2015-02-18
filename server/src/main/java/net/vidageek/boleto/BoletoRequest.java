package net.vidageek.boleto;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.bancos.Bradesco;

public class BoletoRequest {

	private final HttpServletRequest req;

	public BoletoRequest(HttpServletRequest req) {
		this.req = req;
	}

	public Calendar vencimento() {
		return new GregorianCalendar(intParam("vencimento-ano"), intParam("vencimento-mes") - 1,
				intParam("vencimento-dia"));
	}

	public String beneficiario() {
		return stringParam("beneficiario");
	}
	
	public String documento() {
		return stringParam("beneficiario-documento");
	}

	public String agencia() {
		return stringParam("agencia");
	}

	public String agenciaDigito() {
		return stringParam("agencia-digito");
	}

	public String conta() {
		return stringParam("conta");
	}

	public String contaDigito() {
		return stringParam("conta-digito");
	}

	public String carteira() {
		return stringParam("carteira");
	}

	public String nossoNumero() {
		return stringParam("nosso-numero");
	}

	public String nome() {
		return stringParam("nome");
	}

	public Banco banco() {
		return new Bradesco();
	}

	public String valor() {
		return stringParam("valor");
	}

	private String stringParam(String param) {
		String val = req.getParameter(param);
		System.out.println(String.format("recuperando parametro %s com valor %s", param, val));
		return val;
	}

	private int intParam(String param) {
		return Integer.parseInt(stringParam(param));
	}

}
