package com.serviceplataform.Pagamentos;

public class Pagamentos {

	public boolean pagarCom(Pagamento metodo, double valor) {
		return metodo.pagar(valor);
	}

	public boolean pagarPix(double valor) {
		return pagarCom(new PixPagamento(), valor);
	}

	public boolean pagarCartao(double valor) {
		return pagarCom(new CartaoCreditoPagamento(), valor);
	}

	public boolean pagarBoleto(double valor) {
		return pagarCom(new BoletoPagamento(), valor);
	}
}
