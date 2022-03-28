package br.com.alura.leilao.acceptance.steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class PropondoLanceSteps {

	private Lance lance1;
	private Leilao leilao;
	//private Lance lance10;
	//private Lance lance15;
	private ArrayList<Lance> lista;
	
	@Dado("um leilao valido")
	public void dado_um_lance_valido() {
		Usuario usuario = new Usuario("fulano");
	    lance1 = new Lance(usuario, BigDecimal.TEN);
	    leilao = new Leilao("Macbook Pro 15");
	}

	@Quando("propoe um lance")
	public void quando_propoe_o_lance() {
		leilao.propoe(lance1);
	}
	
	@Entao("o lance eh aceito")
	public void entao_o_lance_eh_aceito() {
		Assertions.assertEquals(1,  leilao.getLances().size());
		Assertions.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}
	
	//-----------SEGUNDO CENARIO
//	@Dado("um leilao especifico")
//	public void um_leilao_especifico() {
//		Usuario usuario1 = new Usuario("fulano");
//	    lance10 = new Lance(usuario1, BigDecimal.TEN);
//	    Usuario usuario2 = new Usuario("beltrano");
//	    lance15 = new Lance(usuario2, new BigDecimal("15.00"));
//	    leilao = new Leilao("Macbook Pro 15");
//	}
	
	@Before
	public void setup() {
		this.lista = new ArrayList<Lance>();
		leilao = new Leilao("Macbook Pro 15");
	}
	
	@Dado("um lance de {double} reais do usuario {string}")
	public void um_lance_de_reais_do_usuario(Double valor, String nomeUsuario) {
//---- Feature chama o metodo duas vezes
		// prova de que roda duas vezes
//		System.out.println(valor);
//	    System.out.println(nomeUsuario);
	    
	    //Logica do teste
	    Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
	    lista.add(lance); 
	}

	@Quando("propoe varios lances ao leilao")
	public void propoe_varios_lances_ao_leilao() {
		this.lista.forEach(lance -> leilao.propoe(lance));
	}
	
	@Entao("os lances sao aceitos")
	public void os_lances_sao_aceitos() {
		Assertions.assertEquals(this.lista.size(), leilao.getLances().size());
		Assertions.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
		Assertions.assertEquals(this.lista.get(1).getValor(), leilao.getLances().get(1).getValor());
	}
	
	/*
	 * Também existem os hooks @BeforeStep e @AfterStep. Como o nome já indica, nessas anotações o evento é a execução de um step.
	 * Ou seja, cada vez que um métodos anotado com @Dado @Quando ou @Entao é chamado, será chamado o hook (antes ou depois, 
	 * dependendo da anotação).
	 * */
	
	@Dado("um lance invalido de {double} reais do {string}")
	public void um_lance_invalido_de_reais_do(Double valor, String nomeUsuario) {
//		System.out.println(valor);
//	    System.out.println(nomeUsuario);
	    
	  //Logica do teste
	    Assertions.assertThrows(IllegalArgumentException.class, () -> new Lance(new Usuario(nomeUsuario), new BigDecimal(valor)));
	}
	
	@Dado("dois lances")
	public void dois_lances(DataTable dataTable) {
	    List<Map<String, String>> variaveisTeste = dataTable.asMaps();
	    
	    for (Map<String, String> mapa : variaveisTeste) {
	    	//Testando dados que chegam
			System.out.println(mapa.values());
			System.out.println(mapa.keySet());
			
			//Captura os dados em variaveis  
			String valor = mapa.get("valor");
			String nomeUsuario = mapa.get("nomeUsuario");
			//Instancia o lance
			Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
			//Adicona na lista da classe
			lista.add(lance);
		}
	}

	@Quando("propoe ao leilao")
	public void propoe_ao_leilao() {
		this.lista.forEach(lance -> leilao.propoe(lance));
	}
	
	@Entao("o lance nao eh aceito")
	public void o_lance_nao_eh_aceito() {
		Assertions.assertEquals(0, leilao.getLances().size());
	}
	
	@Entao("o segundo lance nao eh aceito")
	public void o_segundo_lance_nao_eh_aceito() {
		Assertions.assertEquals(1, leilao.getLances().size());
		Assertions.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> leilao.getLances().get(1).getValor());
	}
	
}
