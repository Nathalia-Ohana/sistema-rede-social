package com.redesocial;

import com.redesocial.modelo.*;
import com.redesocial.gerenciador.*;
import com.redesocial.ui.*;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            MenuPrincipal menu = new MenuPrincipal();

            Usuario usuario1 = new Usuario("Pedro Oliveira", "pedro123", "pedro@email.com", "senha123");
            Usuario usuario2 = new Usuario("Ana Mendonça", "ana456", "ana@email.com", "senha456");

            menu.getGerenciadorUsuarios().cadastrar(usuario1);
            menu.getGerenciadorUsuarios().cadastrar(usuario2);

            Post post1 = new Post(usuario1, "Olá, mundo! Este é meu primeiro post na rede social!🎉");
            Post post2 = new Post(usuario2, "Olá, rede social!👋🏽");

            menu.getGerenciadorPosts().criar(post1);
            menu.getGerenciadorPosts().criar(post2);

            menu.exibirMenu();
        } catch (Exception e) {
            System.err.println("Ocorreu um erro no sistema: " + e.getMessage());
        }
    }
}
