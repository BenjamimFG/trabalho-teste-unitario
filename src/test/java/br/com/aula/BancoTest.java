package br.com.aula;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.aula.exception.ContaJaExistenteException;
import br.com.aula.exception.ContaNaoExistenteException;
import br.com.aula.exception.ContaSemSaldoException;
import br.com.aula.exception.ContaNumeroInvalidoException;

public class BancoTest {

	@Test
	public void deveCadastrarConta() throws ContaJaExistenteException, ContaNumeroInvalidoException {

		// Cenario
		Cliente cliente = new Cliente("Joao");
		Conta conta = new Conta(cliente, 123, 0, TipoConta.CORRENTE);
		Banco banco = new Banco();

		// Ação
		banco.cadastrarConta(conta);

		// Verificação
		assertEquals(1, banco.obterContas().size());
	}

	@Test(expected = ContaJaExistenteException.class)
	public void naoDeveCadastrarContaNumeroRepetido() throws ContaJaExistenteException, ContaNumeroInvalidoException {

		// Cenario
		Cliente cliente = new Cliente("Joao");
		Conta conta1 = new Conta(cliente, 123, 0, TipoConta.CORRENTE);

		Cliente cliente2 = new Cliente("Maria");
		Conta conta2 = new Conta(cliente2, 123, 0, TipoConta.POUPANCA);

		Banco banco = new Banco();

		// Ação
		banco.cadastrarConta(conta1);
		banco.cadastrarConta(conta2);

		Assert.fail();
	}

	@Test(expected = ContaNumeroInvalidoException.class)
	public void naoDeveCadastrarContaNumeroInvalido() throws ContaJaExistenteException, ContaNumeroInvalidoException {
		Cliente cliente = new Cliente("Joao");
		Conta conta = new Conta(cliente, -5, 0, TipoConta.CORRENTE);
		Banco banco = new Banco();

		banco.cadastrarConta(conta);

		Assert.fail();
	}

	@Test(expected = ContaJaExistenteException.class)
	public void naoDeveCadastrarContaNomeRepetido() throws ContaJaExistenteException, ContaNumeroInvalidoException {

		// Cenario
		Cliente cliente = new Cliente("Joao");
		Conta conta1 = new Conta(cliente, 123, 0, TipoConta.CORRENTE);

		Cliente cliente2 = new Cliente("Joao");
		Conta conta2 = new Conta(cliente2, 456, 0, TipoConta.POUPANCA);

		Banco banco = new Banco();

		// Ação
		banco.cadastrarConta(conta1);
		banco.cadastrarConta(conta2);

		Assert.fail();
	}

	@Test
	public void deveEfetuarTransferenciaContasCorrentes() throws ContaSemSaldoException, ContaNaoExistenteException {

		// Cenario
		Cliente cliente = new Cliente("Joao");
		Conta contaOrigem = new Conta(cliente, 123, 0, TipoConta.CORRENTE);

		Cliente cliente2 = new Cliente("Maria");
		Conta contaDestino = new Conta(cliente2, 456, 0, TipoConta.CORRENTE);

		Banco banco = new Banco(Arrays.asList(contaOrigem, contaDestino));

		// Ação
		banco.efetuarTransferencia(123, 456, 100);

		// Verificação
		assertEquals(-100, contaOrigem.getSaldo());
		assertEquals(100, contaDestino.getSaldo());
	}

	@Test
	public void deveEfetuarTransferenciaContasCorrenteEPoupanca() throws ContaSemSaldoException, ContaNaoExistenteException {
		Cliente cliente = new Cliente("Joao");
		Conta contaOrigem = new Conta(cliente, 123, 0, TipoConta.CORRENTE);
		
		Cliente cliente2 = new Cliente("Maria");
		Conta contaDestino = new Conta(cliente2, 456, 0, TipoConta.POUPANCA);
		
		Banco banco = new Banco(Arrays.asList(contaOrigem, contaDestino));


		banco.efetuarTransferencia(contaOrigem.getNumeroConta(), contaDestino.getNumeroConta(), 100);


		assertEquals(-100, contaOrigem.getSaldo());
		assertEquals(100, contaDestino.getSaldo());
	}
}
