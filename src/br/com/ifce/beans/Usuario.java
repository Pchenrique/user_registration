package br.com.ifce.beans;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

  
  @Id
  @GeneratedValue
  private Long id;
  private String nome;
  private String cpf;
  @OneToMany
  private List<OrdemDeServico> ordens;

  /**
   * Construtor padrão.
   * 
   * @param nome do Usuario
   * @param cpf  do Usuario
   */
  public Usuario(String nome, String cpf) {
    this.nome = nome;
    this.cpf = cpf;
    this.ordens = new ArrayList<OrdemDeServico>();
  }
  
  public Usuario() {
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo que retorna a proxima OrdemDeServico do Usuario.
   * 
   * @return objeto do tipo OrdemDeServico
   */
  public OrdemDeServico getOss() {
    if (!this.ordens.isEmpty()) {
      return this.ordens.get(0);
    }
    return null;
  }

  /**
   * MÃ©todo que adiciona OS ao Usuario.
   * 
   * @param os objeto do tipo OrdemDeServico
   */
  public boolean addOs(OrdemDeServico os) {
    if (this.ordens.add(os)) {
      return true;
    }
    return false;
  }

  /**
   * Metodo Responsavel por remover ordem de serviço.
   */
  public OrdemDeServico removerOs(String titulo) {
    for (int i = 0; i < ordens.size(); i++) {
      OrdemDeServico os = ordens.get(i);

      if (os.getTitulo().equals(titulo)) {
        ordens.remove(os);
        return os;
      }
    }
    return null;
  }

  /**
   * metodo para resolver ordem de serviço.
   */
  public OrdemDeServico resolverOs(String titulo) {
    for (int i = 0; i < ordens.size(); i++) {
      OrdemDeServico os = ordens.get(i);

      if (os.getTitulo().equals(titulo) && os.getAberta() == true) {
        ordens.get(i).setAberta(false);
        return os;
      }
    }
    return null;
  }

}
