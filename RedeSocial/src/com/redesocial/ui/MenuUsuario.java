package com.redesocial.ui;

import com.redesocial.gerenciador.GerenciadorPosts;
import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.modelo.Comentario;
import com.redesocial.modelo.Post;
import com.redesocial.modelo.Usuario;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MenuUsuario {
    Scanner scanner = new Scanner(System.in);
    private Usuario usuario;

    private GerenciadorUsuarios gerenciadorUsuarios;

    private GerenciadorPosts gerenciadorPosts;

    public MenuUsuario(Usuario usuario, GerenciadorUsuarios gerenciadorUsuarios, GerenciadorPosts gerenciadorPosts) {
        this.usuario = usuario;
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.gerenciadorPosts = gerenciadorPosts;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("===== Menu do Usuário =====");
            System.out.println("1. Criar post");
            System.out.println("2. Ver meu perfil");
            System.out.println("3. Buscar usuários");
            System.out.println("4. Gerenciar amizades");
            System.out.println("5. Ver feed de notícias");
            System.out.println("6. Logout");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    criarPost();
                case 2:
                    verPerfil();
                case 3:
                    buscarUsuarios();
                case 4:
                    gerenciarAmizades();
                case 5:
                    verFeedNoticias();
                default:
                    System.out.println("Selecione uma opção");
            }
        } while (opcao != 6);
    }

    private void criarPost() {
        try {
            System.out.println("Digite o conteúdo do post: ");
            String conteudo = scanner.nextLine();
            Post post = new Post(usuario, conteudo);
            gerenciadorPosts.criar(post);
            usuario.adicionarPost(post);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void verPerfil() {
        System.out.println(usuario);
        System.out.println("1. Editar perfil \n2. Sair");
        int opcao = scanner.nextInt();

        if (opcao == 1) {
            editarPerfil();
        }
    }

    private void editarPerfil() {
        System.out.println("Nome (" + usuario.getNome() + "): ");
        String nome = scanner.nextLine();
        System.out.println("Username (" + usuario.getUsername() + "): ");
        String username = scanner.nextLine();
        System.out.println("Email (" + usuario.getEmail() + "): ");
        String email = scanner.nextLine();
        System.out.println("Senha (" + usuario.getSenha() + "): ");
        String senha = scanner.nextLine();

        if (!usuario.getNome().isEmpty()) {
            usuario.setNome(nome);
        }
        if (!usuario.getUsername().isEmpty()) {
            usuario.setUsername(username);
        }
        if (!usuario.getEmail().isEmpty()) {
            usuario.setEmail(email);
        }
        if (!usuario.getSenha().isEmpty()) {
            usuario.setSenha(senha);
        }
        gerenciadorUsuarios.atualizar(usuario);
    }

    private void buscarUsuarios() {
        System.out.println("Digite o username: ");
        String username = scanner.nextLine();

        for (Usuario usuario : gerenciadorUsuarios.listarUsuarios()) {
            if (usuario.getUsername().equals(username)) {
                System.out.println(usuario);
            }
        }
    }

    private void gerenciarAmizades() {
        int opcao;
        do {

            System.out.println("1. Adicionar amizade \n2. Remover amizade \n3. Listar Amizades \n4. Voltar");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    adicionarAmizade();
                case 2:
                    removerAmizade();
                case 3:
                    listarAmizade();
                default:
                    System.out.println("Selecione uma opção");
            }
        }
        while (opcao != 4);
    }

    private void adicionarAmizade() {
        System.out.println("Digite o username: ");
        String username = scanner.nextLine();

        gerenciadorUsuarios.adicionarAmizade(usuario.getId(), gerenciadorUsuarios.buscarPorUsername(username).getId());
    }

    private void removerAmizade() {
        System.out.println("Digite o username: ");
        String username = scanner.nextLine();

        gerenciadorUsuarios.removerAmizade(usuario.getId(), gerenciadorUsuarios.buscarPorUsername(username).getId());
    }

    private void listarAmizade() {
        for (Usuario usuario : usuario.getAmigos()) {
            System.out.println(usuario);
        }
    }

    private void verFeedNoticias() {
        List<Post> posts = gerenciadorPosts.getPosts().stream()
                .filter(post -> usuario.getAmigos().contains(post.getAutor()) || usuario.getPosts().equals(usuario))
                .sorted(Comparator.comparing(Post::getDataPublicacao)).toList();
        if (!posts.isEmpty()) {
            for (Post post : posts) {
                System.out.println(post);
            }
        }
        System.out.println("Digite o id do post para intergir");
        int id = scanner.nextInt();

        int opcao;
        do {
            System.out.println("1. Curtir \n2. Comentar \n3.Voltar");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gerenciadorPosts.curtir(id, usuario.getId());
                case 2:
                    comentar(id);
                default:
                    System.out.println("Selecione uma opção");
            }
        }
        while (opcao != 3);
    }

    private void comentar(int id) {
        System.out.println("Digite o comentário: ");
        String comentario = scanner.nextLine();
        gerenciadorPosts.comentar(new Comentario(usuario, comentario, gerenciadorPosts.buscarPorId(id)));
    }

}
