package com.redesocial.modelo;

import com.redesocial.exception.ValidacaoException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private Integer id;

    private String nome;

    private String username;

    private String email;

    private String senha;

    private LocalDateTime dataCadastro;

    private List<Usuario> amigos;

    private List<Post> posts;

    public Usuario(String nome, String username, String email, String senha) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = LocalDateTime.now();
        this.amigos = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public Usuario() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  id=").append(id).append(",\n")
                .append("  nome='").append(nome).append("',\n")
                .append("  username='").append(username).append("',\n")
                .append("  email='").append(email).append("',\n")
                .append("  senha='").append(senha).append("',\n")
                .append("  data cadastro=").append(dataCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append(",\n")
                .append("  amigos=[\n");

        for (Usuario amigo : amigos) {
            sb.append("    ").append(amigo.getUsername()).append(",\n");
        }
        if (!amigos.isEmpty()) {
            sb.deleteCharAt(sb.length() - 2);
        }
        sb.append("  ],\n")
                .append("  posts=[\n");

        for (Post post : posts) {
            sb.append("    ").append(post.getConteudo()).append(",\n");
        }
        if (!posts.isEmpty()) {
            sb.deleteCharAt(sb.length() - 2);
        }

        sb.append("  ]\n")
                .append("}");

        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return getId().equals(usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public void adicionarAmigo(Usuario amigo) {
        if (!amigos.contains(amigo)) {
            amigos.add(amigo);
            amigo.adicionarAmigo(this);
        }
    }

    public void removerAmigo(Usuario amigo){
        if (amigos.contains(amigo)) {
            amigos.remove(amigo);
        }
    }

    public void adicionarPost(Post post){
        if (!posts.contains(post)) {
            posts.add(post);
        }
    }

}

