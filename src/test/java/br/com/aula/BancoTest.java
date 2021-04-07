package br.com.aula;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.aula.exception.ContaJaExistenteException;
import br.com.aula.exception.ContaNaoExistenteException;
import br.com.aula.exception.ContaSemSaldoException;
import br.com.aula.exception.ValorTransferenciaInvalido;
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
	public void deveEfetuarTransferenciaContasCorrentes() throws ContaSemSaldoException, ContaNaoExistenteException, ValorTransferenciaInvalido {

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
	public void deveEfetuarTransferenciaContasCorrenteEPoupanca() throws ContaSemSaldoException, ContaNaoExistenteException, ValorTransferenciaInvalido {
		Cliente cliente = new Cliente("Joao");
		Conta contaOrigem = new Conta(cliente, 123, 0, TipoConta.CORRENTE);
		
		Cliente cliente2 = new Cliente("Maria");
		Conta contaDestino = new Conta(cliente2, 456, 0, TipoConta.POUPANCA);
		
		Banco banco = new Banco(Arrays.asList(contaOrigem, contaDestino));


		banco.efetuarTransferencia(contaOrigem.getNumeroConta(), contaDestino.getNumeroConta(), 100);


		assertEquals(-100, contaOrigem.getSaldo());
		assertEquals(100, contaDestino.getSaldo());
	}

	@Test(expected = ContaNaoExistenteException.class)
	public void deveVerificarContaOrigemExistente() throws ContaSemSaldoException, ContaNaoExistenteException, ValorTransferenciaInvalido {
		Cliente cliente = new Cliente("Joao");
		Conta contaDestino = new Conta(cliente, 123, 0, TipoConta.CORRENTE);

		Banco banco = new Banco(Arrays.asList(contaDestino));


		banco.efetuarTransferencia(999, contaDestino.getNumeroConta(), 1000);


		assertEquals(0, contaDestino.getSaldo());
		Assert.fail();
	}

	@Test(expected = ContaSemSaldoException.class)
	public void naoDevePermitirContaPoupancaComSaldoNegativo() throws ContaSemSaldoException, ContaNaoExistenteException, ValorTransferenciaInvalido {
		Cliente cliente = new Cliente("Joao");
		Conta contaOrigem = new Conta(cliente, 123, 0, TipoConta.POUPANCA);
		
		Cliente cliente2 = new Cliente("Maria");
		Conta contaDestino = new Conta(cliente2, 456, 0, TipoConta.CORRENTE);
		
		Banco banco = new Banco(Arrays.asList(contaOrigem, contaDestino));


		banco.efetuarTransferencia(contaOrigem.getNumeroConta(), contaDestino.getNumeroConta(), 100);


		assertEquals(0, contaDestino.getSaldo());
		assertEquals(0, contaOrigem.getSaldo());
		Assert.fail();
	}

	@Test(expected = ContaNaoExistenteException.class)
	public void deveVerificarContaDestinoExistente() throws ContaSemSaldoException, ContaNaoExistenteException, ValorTransferenciaInvalido {
		Cliente cliente = new Cliente("Joao");
		Conta contaOrigem = new Conta(cliente, 123, 0, TipoConta.CORRENTE);

		Banco banco = new Banco(Arrays.asList(contaOrigem));


		banco.efetuarTransferencia(contaOrigem.getNumeroConta(), 999, 1000);


		assertEquals(0, contaOrigem.getSaldo());
		Assert.fail();
	}

	@Test(expected = ValorTransferenciaInvalido.class)
	public void naoDeveTransferirValorNegativo() throws ContaSemSaldoException, ContaNaoExistenteException, ValorTransferenciaInvalido {
		Cliente cliente = new Cliente("Joao");
		Conta contaOrigem = new Conta(cliente, 123, 0, TipoConta.CORRENTE);
		
		Cliente cliente2 = new Cliente("Maria");
		Conta contaDestino = new Conta(cliente2, 456, 0, TipoConta.CORRENTE);
		
		Banco banco = new Banco(Arrays.asList(contaOrigem, contaDestino));


		banco.efetuarTransferencia(contaOrigem.getNumeroConta(), contaDestino.getNumeroConta(), -100);


		assertEquals(0, contaDestino.getSaldo());
		assertEquals(0, contaOrigem.getSaldo());
		Assert.fail();
	}
}
