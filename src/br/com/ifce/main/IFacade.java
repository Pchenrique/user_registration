package br.com.ifce.main;

public interface IFacade {
  
  public void inserirUsuario(String nome, String cpf);
  
  public boolean inserirOs(String titulo, String descricao, String cpf);
  
  public boolean deletarUsuario(String cpf);
  
  public boolean deletarOs(String cpf, String titulo);
  
  public boolean resolverOs(String cpf, String titulo);
}
