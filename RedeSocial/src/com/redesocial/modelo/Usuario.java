package com.redesocial.modelo;

import com.redesocial.exception.ValidacaoException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;

/**
 * A classe Usuario representa um usuário dentro de uma rede social.
 * Um usuário possui atributos como nome, username, email, senha, lista de amigos, lista de posts e a data de cadastro.
 * A classe também fornece métodos para adicionar amigos, remover amigos, adicionar posts,
 * e manipular atributos como nome, username, email e senha.
 */
public class Usuario {

    /**
     * ID único do usuário
     */
    private Integer id;

    /**
     * Nome do usuário
     */
    private String nome;

    /**
     * Username do usuário (identificador único na plataforma)
     */
    private String username;

    /**
     * Endereço de email do usuário
     */
    private String email;

    /**
     * Senha do usuário
     */
    private String senha;

    /**
     * Data e hora em que o usuário foi cadastrado
     */
    private LocalDateTime dataCadastro;

    /**
     * Lista de amigos do usuário
     */
    private List<Usuario> amigos;

    /**
     * Lista de posts criados pelo usuário
     */
    private List<Post> posts;

    /**
     * Construtor da classe Usuário. Inicializa o nome, username, email, senha e define a data de cadastro
     * como a data e hora atual. Também inicializa as listas de amigos e posts.
     *
     * @param nome     O nome do usuário
     * @param username O username do usuário
     * @param email    O email do usuário
     * @param senha    A senha do usuário
     */
    public Usuario(String nome, String username, String email, String senha) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = LocalDateTime.now();
        this.amigos = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    /**
     * Métodos getters e setters
     */
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

    /**
     * Método toString que retorna uma representação textual do usuário.
     * A representação inclui o id, nome, username, email, senha, data de cadastro,
     * a lista de amigos e a lista de posts do usuário.
     *
     * @return String representando o usuário
     */
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

    /**
     * Método {@code equals} que verifica se dois objetos {@code Usuario} são iguais.
     * A igualdade é determinada com base no id do usuário.
     *
     * @param o O objeto com o qual será comparado
     * @return {@code true} se os usuários possuem o mesmo id, {@code false} caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return getId().equals(usuario.getId());
    }

    /**
     * Método {@code hashCode} que gera um código hash baseado no id do usuário.
     *
     * @return O código hash do usuário
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Adiciona um amigo à lista de amigos do usuário. Caso o amigo não esteja na lista, ele é adicionado.
     * O amigo também é adicionado à lista de amigos do outro usuário.
     *
     * @param amigo O usuário a ser adicionado como amigo
     */
    public void adicionarAmigo(Usuario amigo) {
        if (!amigos.contains(amigo)) {
            amigos.add(amigo);
            amigo.adicionarAmigo(this);
        }
    }

    /**
     * Remove um amigo da lista de amigos do usuário.
     *
     * @param amigo O usuário a ser removido da lista de amigos
     */
    public void removerAmigo(Usuario amigo) {
        if (amigos.contains(amigo)) {
            amigos.remove(amigo);
        }
    }

    /**
     * Adiciona um post à lista de posts do usuário. O post será adicionado somente se ainda não estiver na lista.
     *
     * @param post O post a ser adicionado
     */
    public void adicionarPost(Post post) {
        if (!posts.contains(post)) {
            posts.add(post);
        }
    }

}

