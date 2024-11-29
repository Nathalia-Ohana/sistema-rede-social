package com.redesocial.ui;

import com.redesocial.exception.AutenticacaoException;
import com.redesocial.exception.ValidacaoException;
import com.redesocial.gerenciador.GerenciadorPosts;
import com.redesocial.modelo.Usuario;
import com.redesocial.gerenciador.GerenciadorUsuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por exibir o menu principal da aplicação de rede social.
 * Permite ao usuário fazer login, cadastrar novos usuários e acessar funcionalidades do sistema.
 */
public class MenuPrincipal {
    /**
     * Gerenciador de usuários e posts, utilizados para realizar as operações do sistema
     */

    private final GerenciadorUsuarios gerenciadorUsuarios;
    private final GerenciadorPosts gerenciadorPosts;


    /**
     * Construtor da classe MenuPrincipal.
     * Inicializa os gerenciadores de usuários e posts.
     */
    public MenuPrincipal() {
        this.gerenciadorUsuarios = new GerenciadorUsuarios();
        this.gerenciadorPosts = new GerenciadorPosts(gerenciadorUsuarios);
    }

    /**
     * Lista de usuários cadastrados
     */
    List<Usuario> usuarios = new ArrayList<>();

    /**
     * Scanner para capturar entradas do usuário
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Método para acessar o gerenciador de posts.
     *
     * @return gerenciador de posts
     */
    public GerenciadorPosts getGerenciadorPosts() {
        return gerenciadorPosts;
    }

    public GerenciadorUsuarios getGerenciadorUsuarios() {
        return gerenciadorUsuarios;
    }

    /**
     * Método que exibe o menu principal e permite ao usuário escolher uma ação.
     * As opções são: Login, Cadastro de novo usuário ou Sair.
     */
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

    /**
     * Método para realizar o login de um usuário.
     * Solicita o username e a senha, e valida as informações.
     * Caso o login seja bem-sucedido, exibe o menu de opções para o usuário.
     */
    private void fazerLogin() {
        System.out.println("===== Menu de Login =====");

        System.out.print("Digite seu username: ");
        String username = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = gerenciadorUsuarios.buscarPorUsername(username);
        if (usuario == null) {
            throw new AutenticacaoException("Username inválido!");
        }
        if (!usuario.getSenha().equals(senha)) {
            throw new AutenticacaoException("Senha incorreta!");
        }
        exibirMenuLogado(usuario);
    }

    /**
     * Método para cadastrar um novo usuário no sistema.
     * Solicita informações como nome, username, email e senha, e tenta cadastrar o usuário.
     * Caso haja erro de validação, a exceção é tratada.
     */
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

    /**
     * Método para exibir o menu do usuário logado.
     * Chama o menu do usuário com base no objeto Usuario.
     *
     * @param usuario O usuário logado
     */
    private void exibirMenuLogado(Usuario usuario) {
        MenuUsuario menuUsuario = new MenuUsuario(usuario, gerenciadorUsuarios, gerenciadorPosts);
        menuUsuario.exibirMenu();
    }
}

