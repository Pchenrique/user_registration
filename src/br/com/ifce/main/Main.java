package br.com.ifce.main;

import br.com.map.simplefactory.SimpleFactory;

import java.util.Scanner;

public class Main {

  /**
   * Main.
   */
  public static void main(String[] args) {
    IFacade facade = SimpleFactory.facade();
    Scanner sc = new Scanner(System.in);
    System.out.print("1. Adicionar Usuario;\n" + "2. Remover Usuario;\n" + "3. Adicionar OS;\n"
        + "4. Remover OS;\n" + "5. Resolver OS;\n" + "0. Sair; \nOpção: ");

    int op = sc.nextInt();

    while (op != 0) {
      if (op == 1) {
        System.out.println("CADASTRO DE USUARIO");

        System.out.print("Nome: ");
        String nome = sc.next();

        System.out.print("CPF: ");
        String cpf = sc.next();

        facade.inserirUsuario(nome, cpf);
        System.out.println("\nUsuario Inserido com sucesso!\n");

      } else if (op == 2) {
        System.out.println("REMOVER USUARIO");

        System.out.print("Cpf: ");
        String cpf = sc.next();

        if (facade.deletarUsuario(cpf)) {
          System.out.println("\nUsuario excluido com sucesso!\n");
        } else {
          System.out.println("\nUsuario não existe!\n");
        }

      } else if (op == 3) {
        System.out.println("CADASTRO DE OS");
        System.out.print("Titulo: ");
        String titulo = sc.next();
        System.out.print("Descrição: ");
        String descricao = sc.next();
        System.out.print("CPF do Usuario: ");
        String cpf = sc.next();

        if (facade.inserirOs(titulo, descricao, cpf)) {
          System.out.println("\nNova ordem de serviço inserida!\n");
        } else {
          System.out.println("\nOrdem de serviço não foi inserida!\n");
        }

      } else if (op == 4) {
        System.out.println("REMOÇÃO DE OS");
        System.out.print("CPF: ");
        String cpf = sc.next();
        System.out.print("Titulo: ");
        String titulo = sc.next();

        if (facade.deletarOs(cpf, titulo)) {
          System.out.println("\nOrdem excluido com sucesso!\n");
        } else {
          System.out.println("\nOrdem de serviço não existe!\n");
        }

      } else if (op == 5) {
        System.out.println("RESOLVER DE OS");
        System.out.print("Titulo: ");
        String titulo = sc.next();
        System.out.print("CPF: ");
        String cpf = sc.next();
          
        if (facade.resolverOs(cpf, titulo)) {
          System.out.println("\nOrdem resolvida com sucesso!\n");
        } else {
          System.out.println("\nOrdem ja foi resolvida ou não existe!\n");
        }

      }

      System.out.print("1. Adicionar Usuario;\n" + "2. Remover Usuario;\n" + "3. Adicionar OS;\n"
          + "4. Remover OS;\n" + "5. Resolver OS;\n" + "0. Sair; \nOpção: ");

      op = sc.nextInt();
    }
    System.out.println("Sistema Encerrado!");
    sc.close();
  }

}
