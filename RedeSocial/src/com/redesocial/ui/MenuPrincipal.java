package com.redesocial.ui;

import com.redesocial.exception.AutenticacaoException;
import com.redesocial.exception.ValidacaoException;
import com.redesocial.gerenciador.GerenciadorPosts;
import com.redesocial.modelo.Usuario;
import com.redesocial.gerenciador.GerenciadorUsuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MenuPrincipal {
    private final GerenciadorUsuarios gerenciadorUsuarios;
    private final GerenciadorPosts gerenciadorPosts;

    public MenuPrincipal() {
        this.gerenciadorUsuarios = new GerenciadorUsuarios();
        this.gerenciadorPosts = new GerenciadorPosts(gerenciadorUsuarios);
    }

    List<Usuario> usuarios = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public MenuPrincipal(GerenciadorUsuarios gerenciadorUsuarios, GerenciadorPosts gerenciadorPosts, GerenciadorUsuarios gerenciadorUsuarios1, GerenciadorPosts gerenciadorPosts1) {
        this.gerenciadorUsuarios = gerenciadorUsuarios1;
        this.gerenciadorPosts = gerenciadorPosts1;
    }

    public GerenciadorPosts getGerenciadorPosts() {
        return gerenciadorPosts;
    }

    public GerenciadorUsuarios getGerenciadorUsuarios() {
        return gerenciadorUsuarios;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("===== Menu Principal =====");
            System.out.println("1. Login");
            System.out.println("2. Cadastrar novo usuário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }

    private void fazerLogin() {
        System.out.println("===== Menu de Login =====");

        System.out.print("Digite seu username: ");
        String username = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = gerenciadorUsuarios.buscarPorUsername(username);
        if(usuario == null){
            throw new AutenticacaoException("Username inválido!");
        }
        if(!usuario.getSenha().equals(senha)){
            throw new AutenticacaoException("Senha incorreta!");
        }
        exibirMenuLogado(usuario);
    }

    public void cadastrarUsuario() {
        System.out.println("===== Menu de Cadastro =====");

        System.out.print("Preencha o campo Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preencha o campo Username: ");
        String username = scanner.nextLine();

        System.out.print("Preencha o campo Email: ");
        String email = scanner.nextLine();

        System.out.print("Preencha o campo Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario1 = new Usuario(nome, username, email, senha);

        try {
            gerenciadorUsuarios.cadastrar(usuario1);
            usuarios.add(usuario1);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (ValidacaoException e) {
            throw new ValidacaoException("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
    private void exibirMenuLogado(Usuario usuario){
        MenuUsuario menuUsuario = new MenuUsuario(usuario, gerenciadorUsuarios, gerenciadorPosts);
        menuUsuario.exibirMenu();
    }
}

