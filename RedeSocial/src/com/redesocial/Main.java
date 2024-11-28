package com.redesocial;

import com.redesocial.modelo.*;
import com.redesocial.gerenciador.*;
import com.redesocial.ui.*;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Instanciar os gerenciadores
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
        GerenciadorPosts gerenciadorPosts = new GerenciadorPosts();

        // Criar dados fictícios
        Usuario usuario1 = new Usuario(null, "Pedro Oliveira", "pedro123", "pedro@email.com", "senha123", LocalDateTime.now(), null, null);
        Usuario usuario2 = new Usuario(null, "Ana Mendonça", "ana456", "ana@email.com", "senha456", LocalDateTime.now(), null, null);

        // Cadastrar usuários
        gerenciadorUsuarios.cadastrar(usuario1);
        gerenciadorUsuarios.cadastrar(usuario2);

        // Criar posts para os usuários
        Post post1 = new Post(null, usuario1, "Meu primeiro post!", LocalDateTime.now(), null, null);
        Post post2 = new Post(null, usuario2, "Olá, rede social!", LocalDateTime.now(), null, null);

        // Publicar os posts
        gerenciadorPosts.criar(post1);
        gerenciadorPosts.criar(post2);

        // Adicionar amizade
        gerenciadorUsuarios.adicionarAmizade(usuario1.getId(), usuario2.getId());

        // Simular interações
        gerenciadorPosts.curtir(post1.getId(), usuario2.getId());
        gerenciadorPosts.comentar(new Comentario(null, usuario2, "Parabéns pelo post!", LocalDateTime.now(), post1));

        // Listar dados para validação
        System.out.println("=== Usuários Cadastrados ===");
        for (Usuario u : gerenciadorUsuarios.buscarPorNome("")) {
            System.out.println(u);
        }

        System.out.println("\n=== Posts Publicados ===");
        for (Post p : gerenciadorPosts.listarPorUsuario(usuario1.getId())) {
            System.out.println(p);
        }

        System.out.println("\n=== Feed de Notícias ===");
        List<Post> feed = gerenciadorPosts.listarPorUsuario(usuario1.getId()); // Exemplo simplificado
        for (Post p : feed) {
            System.out.println(p);
        }

        // Exibir Menu Principal
        MenuPrincipal menu = new MenuPrincipal(gerenciadorUsuarios, gerenciadorPosts);
        menu.exibirMenu();
    }
}
