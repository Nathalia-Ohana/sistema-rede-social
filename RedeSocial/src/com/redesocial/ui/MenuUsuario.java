package com.redesocial.ui;

import com.redesocial.exception.PostException;
import com.redesocial.exception.UsuarioException;
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
    private final Usuario usuario;

    private final GerenciadorUsuarios gerenciadorUsuarios;

    private final GerenciadorPosts gerenciadorPosts;

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

            switch (opcao) {
                case 1 -> criarPost();
                case 2 ->verPerfil();
                case 3 -> buscarUsuarios();
                case 4 -> gerenciarAmizades();
                case 5 -> verFeedNoticias();
                case 6 -> System.out.println("Logout realizado com sucesso.");
                default -> System.out.println("Selecione uma opção");
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
            System.out.println("Post criado com sucesso!");
        } catch (Exception e) {
            throw new PostException("Erro ao criar post: " + e.getMessage());
        }
    }

    private void verPerfil() {
        int opcao;
        try {
            do {
                System.out.println(usuario);
                System.out.println("\n===== Menu de Perfil ======");
                System.out.println("1. Editar perfil \n2.Excluir conta \n3.Excluir post \n4. Voltar");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> editarPerfil();
                    case 2 -> deletarUsuario();
                    case 3 -> deletarPost();
                    case 4 -> System.out.println("Voltando ao menu pincipal...");
                }
            }while(opcao != 4);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao exibir perfil: " + e.getMessage());
        }
    }

    private void editarPerfil() {
        try {
            System.out.println("Nome (" + usuario.getNome() + "): ");
            String nome = scanner.nextLine();
            System.out.println("Username (" + usuario.getUsername() + "): ");
            String username = scanner.nextLine();
            System.out.println("Email (" + usuario.getEmail() + "): ");
            String email = scanner.nextLine();
            System.out.println("Senha (" + usuario.getSenha() + "): ");
            String senha = scanner.nextLine();

            if (!nome.isEmpty()) {
                usuario.setNome(nome);
            }
            if (!username.isEmpty()) {
                usuario.setUsername(username);
            }
            if (!email.isEmpty()) {
                usuario.setEmail(email);
            }
            if (!senha.isEmpty()) {
                usuario.setSenha(senha);
            }
            gerenciadorUsuarios.atualizar(usuario);
            System.out.println("Perfil atualizado com sucesso!");
        } catch (Exception e) {
            throw new UsuarioException("Erro ao atualizar perfil: " + e.getMessage());
        }
    }

    private void buscarUsuarios() {
        try {
            System.out.println("Digite o username: ");
            String username = scanner.nextLine();

            List<Usuario> usuarios = gerenciadorUsuarios.listarUsuarios();
            boolean encontrado = false;

            for (Usuario user : usuarios) {
                if (user.getUsername().equals(username)) {
                    System.out.println(user);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("Usuário não encontrado.");
            }
        } catch (Exception e) {
            throw new UsuarioException("Erro ao buscar usuários: " + e.getMessage());
        }
    }

    private void gerenciarAmizades() {
        try {
            int opcao;
            do {
                System.out.println("\n===== Menu Gerenciador de Amizade =====");
                System.out.println("\n1. Adicionar amizade \n2. Remover amizade \n3. Listar Amizades \n4. Voltar \nSelecione uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1 -> adicionarAmizade();
                    case 2 -> removerAmizade();
                    case 3 -> listarAmizade();
                    case 4 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Selecione uma opção");
                }
            }
            while (opcao != 4);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao gerenciar amizades: " + e.getMessage());
        }
    }

    private void adicionarAmizade() {
        try {
            System.out.println("Digite o username: ");
            String username = scanner.nextLine();

            gerenciadorUsuarios.adicionarAmizade(usuario.getId(), gerenciadorUsuarios.buscarPorUsername(username).getId());
            System.out.println("Amizade adicionada com sucesso!");
        } catch (Exception e) {
            throw new UsuarioException("Erro ao adicionar amizade: " + e.getMessage());
        }
    }

    private void removerAmizade() {
        try {
            System.out.println("Digite o username: ");
            String username = scanner.nextLine();

            gerenciadorUsuarios.removerAmizade(usuario.getId(), gerenciadorUsuarios.buscarPorUsername(username).getId());
        } catch (Exception e) {
            throw new UsuarioException("Erro ao remover amizade: " + e.getMessage());
        }
    }

    private void listarAmizade() {
        try {
            if (!usuario.getAmigos().isEmpty()) {
                for (Usuario usuario : usuario.getAmigos()) {
                    System.out.println(usuario.getUsername() + " - " + usuario.getNome());
                }
            } else {
                System.out.println("Você ainda não tem amigos.");
            }
        } catch (Exception e) {
            throw new UsuarioException("Erro ao listar amizades: " + e.getMessage());
        }
    }

    private void verFeedNoticias() {
        try {
            System.out.println("\n===== Feed de Notícias =====");
            List<Post> posts = gerenciadorPosts.getPosts().stream()
                    .filter(post -> usuario.getAmigos().contains(post.getAutor()) || usuario.getPosts().equals(usuario))
                    .sorted(Comparator.comparing(Post::getDataPublicacao)).toList();
            if (!posts.isEmpty()) {
                for (Post post : posts) {
                    System.out.println(post);
                }
                System.out.println("Digite o ID do post para intergir");
                int id = scanner.nextInt();

                int opcao;
                do {
                    System.out.println("1. Curtir \n2. Comentar \n3.Voltar");
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 1 -> gerenciadorPosts.curtir(id, usuario.getId());
                        case 2 -> comentar(id);
                        default -> System.out.println("Selecione uma opção");
                    }
                } while (opcao != 3);
            } else {
                System.out.println("Nenhum post encontrado no feed.");
            }
        } catch (Exception e) {
            throw new UsuarioException("Erro ao acessar o feed de notícias: " + e.getMessage());
        }
    }

    private void comentar(int id) {
        try {
            System.out.println("Digite o comentário: ");
            String comentario = scanner.nextLine();
            gerenciadorPosts.comentar(new Comentario(usuario, comentario, gerenciadorPosts.buscarPorId(id)));
            System.out.println("Comentário adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao comentar no post: " + e.getMessage());
        }
    }
    private void deletarPost(){
        try{
            System.out.println("Digite o id do post: ");
            int id = scanner.nextInt();
            gerenciadorPosts.deletar(id);
            System.out.println("Post deletado com sucesso!");
        }catch (Exception e){
            throw new PostException("Erro ao deletar post: " + e.getMessage());
        }
    }
        private void deletarUsuario(){
            try{
                System.out.println("Tem certeza que deseja excluir seu perfil? (1.Sim/2.Não");
                int opcao = scanner.nextInt();
                if(opcao == 1){
                    gerenciadorPosts.deletar(usuario.getId());
                }else{
                    System.out.println("Foi cancelada a tentativa de exclusão do seu perfil");
                }
            }catch(Exception e){
                throw  new UsuarioException("Erro ao deletar perfil: " + e.getMessage());
            }
        }
}
