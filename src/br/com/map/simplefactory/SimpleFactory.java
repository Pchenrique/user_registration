package br.com.map.simplefactory;

import br.com.ifce.beans.OrdemDeServico;
import br.com.ifce.beans.Usuario;
import br.com.ifce.main.Facada;
import br.com.ifce.main.IFacade;

public class SimpleFactory {
  
  private SimpleFactory() {
    
  }
  
  public static IFacade facade() {
    return new Facada();
  }
  
  public static OrdemDeServico ordemDeServico(String titulo, String descricao) {
    return new OrdemDeServico(titulo, descricao);
  }
  
  public static Usuario usuario(String nome, String cpf) {
    return new Usuario(nome, cpf);
  }

}
