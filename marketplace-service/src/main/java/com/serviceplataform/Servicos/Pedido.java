package com.serviceplataform.Servicos;

import com.serviceplataform.Pagamentos.Pagamentos;

public class Pedido {
	protected String nomeCliente;
	protected String servicoEscolhido;
	protected double precoBase;
	protected String formaPagamento;
	protected double valorFinal;

	public Pedido(String nomeCliente, String servicoEscolhido, double precoBase, String formaPagamento) {
		this.nomeCliente = nomeCliente;
		this.servicoEscolhido = servicoEscolhido;
		this.precoBase = precoBase;
		this.formaPagamento = formaPagamento;
		this.valorFinal = precoBase;
	}

	public boolean processarPagamento() {
		Pagamentos pagamentos = new Pagamentos();
		if (formaPagamento == null) return false;
		String f = formaPagamento.toLowerCase();
		boolean ok = false;
		if (f.contains("pix")) {
			ok = pagamentos.pagarPix(precoBase);
			valorFinal = precoBase;
		} else if (f.contains("cart")) { // cartão
			ok = pagamentos.pagarCartao(precoBase);
			valorFinal = Math.round((precoBase * 1.03) * 100.0) / 100.0;
		} else if (f.contains("boleto")) {
			ok = pagamentos.pagarBoleto(precoBase);
			valorFinal = Math.round((precoBase + 5.0) * 100.0) / 100.0;
		}
		return ok;
	}

	public String getNomeCliente() { return nomeCliente; }
	public String getServicoEscolhido() { return servicoEscolhido; }
	public double getPrecoBase() { return precoBase; }
	public String getFormaPagamento() { return formaPagamento; }
	public double getValorFinal() { return valorFinal; }
}
