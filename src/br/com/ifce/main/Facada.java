package br.com.ifce.main;

import java.util.HashMap;

import br.com.ifce.beans.OrdemDeServico;
import br.com.ifce.beans.Usuario;
import br.com.ifce.dao.DaoException;
import br.com.ifce.dao.OrdemDeServicoDAO;
import br.com.ifce.dao.UsuarioDAO;
import br.com.map.simplefactory.SimpleFactory;

public class Facada implements IFacade {
  private HashMap<String, Usuario> usuarios;

  public Facada(){
    this.usuarios = new HashMap<String, Usuario>();
    UsuarioDAO udao = new UsuarioDAO();
    try {
      for(Usuario u: udao.all()) {
        this.usuarios.put(u.getCpf(), u);
      }
    } catch (DaoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

  /**
   * metodo responsavel por inserir usuario.
   */
  public void inserirUsuario(String nome, String cpf) {
    Usuario usuario = SimpleFactory.usuario(nome, cpf);
    this.usuarios.put(cpf, usuario);
    UsuarioDAO usuariodao = new UsuarioDAO();
    try {
      usuariodao.save(usuario);
    } catch (DaoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * metodo responsavel por inserir um nova ordem.
   */
  public boolean inserirOs(String titulo, String descricao, String cpf) {
    OrdemDeServico ordemDeServico = SimpleFactory.ordemDeServico(titulo, descricao);
    Usuario u = this.usuarios.get(cpf);
    OrdemDeServicoDAO dao = new OrdemDeServicoDAO();
    UsuarioDAO udao = new UsuarioDAO();
    if (u.addOs(ordemDeServico)) {
      try {
        dao.save(ordemDeServico);
      } catch (DaoException e) {
        e.printStackTrace();
      }
      try {
        udao.update(u);
      } catch (DaoException e) {
        e.printStackTrace();
      }
      return true;
    }
    return false;
  }

  /**
   * Metodo responsavel por excluir usuario.
   */
  public boolean deletarUsuario(String cpf) {
    Usuario u = this.usuarios.get(cpf);
    if (u != null) { 
      this.usuarios.remove(cpf);
      UsuarioDAO dao = new UsuarioDAO();
      try {
        dao.remove(u);
      } catch (DaoException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return true;
    }
    return false;
  }

  /**
   * metodo para deletar uma ordem de serviço.
   */
  public boolean deletarOs(String cpf, String titulo) {
    Usuario u = this.usuarios.get(cpf);
    OrdemDeServico os = u.removerOs(titulo);
    if (os != null) {
      OrdemDeServicoDAO dao = new OrdemDeServicoDAO();
      UsuarioDAO udao = new UsuarioDAO();
      try {
        udao.update(u);
      } catch (DaoException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      try {
        dao.remove(os);
      } catch (DaoException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return true;
    }
    return false;
  }

  /**
   * metodo para resolver uma OS.
   */
  public boolean resolverOs(String cpf, String titulo) {
    OrdemDeServico os = this.usuarios.get(cpf).resolverOs(titulo);
    if (os != null) {
      OrdemDeServicoDAO dao = new OrdemDeServicoDAO();
      try {
        dao.update(os);
      } catch (DaoException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return true;
    }
    return false;
  }

}
