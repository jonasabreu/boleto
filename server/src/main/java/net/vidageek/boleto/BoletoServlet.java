package net.vidageek.boleto;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;

public class BoletoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Boleto boleto = createBoleto(new BoletoRequest(req));
		GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);

		resp.setContentType("application/pdf");
		resp.getOutputStream().write(gerador.geraPDF());
		
        
	}

	private Boleto createBoleto(BoletoRequest req) {

		Datas datas = Datas.novasDatas().comDocumento(new GregorianCalendar())
				.comProcessamento(new GregorianCalendar())
				.comVencimento(req.vencimento());

		Beneficiario beneficiario = Beneficiario.novoBeneficiario()
				.comNomeBeneficiario(req.beneficiario())
				.comDocumento(req.documento())
				.comAgencia(req.agencia())
				.comDigitoAgencia(req.agenciaDigito())
				.comCodigoBeneficiario(req.conta())
				.comDigitoCodigoBeneficiario(req.contaDigito())
				.comCarteira(req.carteira()).comNossoNumero(req.nossoNumero());

		Pagador pagador = Pagador.novoPagador().comNome(req.nome());

		return Boleto.novoBoleto().comBanco(req.banco()).comDatas(datas)
				.comBeneficiario(beneficiario).comPagador(pagador)
				.comValorBoleto(req.valor());

	}

}
