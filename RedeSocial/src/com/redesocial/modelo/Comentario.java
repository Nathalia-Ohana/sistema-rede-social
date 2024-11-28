package com.redesocial.modelo;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A classe Comentario representa um comentário feito por um usuário em um post.
 * Cada comentário possui um autor, conteúdo, data de criação, e está associado a um post específico.
 */
public class Comentario {

    /** Contador estático para geração automática de IDs únicos para os comentários. */

    private static final AtomicInteger contador = new AtomicInteger(0);

    /** Identificador único do comentário. */
    private Integer id;

    /** Usuário autor do comentário. */
    private Usuario autor;

    /** Conteúdo textual do comentário. */
    private String conteudo;

    /** Data e hora em que o comentário foi feito. */
    private LocalDateTime dataComentario;

    /** Post ao qual o comentário está associado. */
    private Post post;


    /**
     * Construtor que inicializa os atributos de um comentário.
     *
     * @param autor    o autor do comentário.
     * @param conteudo o conteúdo do comentário.
     * @param post     o post ao qual o comentário está associado.
     */
    public Comentario(Usuario autor, String conteudo, Post post) {
        this.id = contador.incrementAndGet();
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataComentario = LocalDateTime.now();
        this.post = post;
    }

    // Métodos Getters e Setters
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

    public LocalDateTime getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(LocalDateTime dataComentario) {
        this.dataComentario = dataComentario;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * Retorna uma representação em formato de string dos atributos do comentário.
     *
     * @return uma string representando o comentário.
     */
    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", autor=" + autor +
                ", conteudo='" + conteudo + '\'' +
                ", dataComentario=" + dataComentario +
                ", post=" + post +
                '}';
    }
}
