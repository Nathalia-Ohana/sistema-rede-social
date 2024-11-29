package com.redesocial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Post representa um post dentro de uma rede social.
 * Cada post possui um autor, conteúdo, data de publicação, lista de curtidas e uma lista de comentários.
 * Esta classe permite adicionar e remover curtidas e adicionar comentários a um post.
 */
public class Post {

    /**
     * ID único do post
     */
    private Integer id;

    /**
     * Autor do post
     */
    private Usuario autor;

    /**
     * Conteúdo do post
     */
    private String conteudo;

    /**
     * Data e hora da publicação do post
     */
    private LocalDateTime dataPublicacao;

    /**
     * Lista de usuários que curtiram o post
     */
    private List<Usuario> curtidas;

    /**
     * Lista de comentários no post
     */
    private List<Comentario> comentarios;

    /**
     * Construtor da classe {@code Post}. Inicializa o autor, conteúdo e define a data de publicação
     * como a data e hora atual. Também inicializa as listas de curtidas e comentários.
     *
     * @param autor    O autor do post
     * @param conteudo O conteúdo do post
     */
    public Post(Usuario autor, String conteudo) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = LocalDateTime.now();
        this.curtidas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    // Métodos getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Usuario> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(List<Usuario> curtidas) {
        this.curtidas = curtidas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Método toString que retorna uma representação textual do post.
     *
     * @return String representando o post, incluindo o id, autor, conteúdo, data de publicação, curtidas e comentários
     */
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", autor=" + autor +
                ", conteudo='" + conteudo + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", curtidas=" + curtidas +
                ", comentarios=" + comentarios +
                '}';
    }

    /**
     * Adiciona uma curtida ao post, se o usuário ainda não tiver curtido o post.
     *
     * @param usuario O usuário que está curtindo o post
     */

    public void adicionarCurtida(Usuario usuario) {
        if (!curtidas.contains(usuario)) {
            curtidas.add(usuario);
        }
    }


    /**
     * Remove uma curtida do post, caso o usuário tenha curtido.
     *
     * @param usuario O usuário que deseja descurtir o post
     */
    public void removerCurtida(Usuario usuario) {
        if (curtidas.contains(usuario)) {
            curtidas.add(usuario);
        }
    }

    /**
     * Adiciona um comentário ao post.
     *
     * @param comentario O comentário a ser adicionado
     */
    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }
}
