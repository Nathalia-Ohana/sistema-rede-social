package com.redesocial.gerenciador;

import com.redesocial.exception.PostException;
import com.redesocial.exception.ValidacaoException;
import com.redesocial.modelo.Comentario;
import com.redesocial.modelo.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe responsável por gerenciar as operações relacionadas a posts em uma rede social.
 */
public class GerenciadorPosts {

    private List<Post> posts;

    private int proximoId;

    private GerenciadorUsuarios gerenciadorUsuarios;

    /**
     * Construtor da classe.
     *
     * @param gerenciadorUsuarios Gerenciador de usuários associado.
     */

    public GerenciadorPosts(GerenciadorUsuarios gerenciadorUsuarios) {
        this.posts = new ArrayList<>();
        this.proximoId = 1;
        this.gerenciadorUsuarios = gerenciadorUsuarios;
    }

    /**
     * Cria um novo post após validação.
     *
     * @param post Post a ser criado.
     * @throws PostException Se houver erro na criação do post.
     */

    public void criar(Post post) {
        try {
            validarPost(post);
            post.setId(proximoId++);
            posts.add(post);
        } catch (ValidacaoException e) {
            throw e;
        } catch (Exception e) {
            throw new PostException("Erro ao criar post:  " + e.getMessage(), e);
        }
    }

    /**
     * Valida as regras de negócio para criação de um post.
     *
     * @param post Post a ser validado.
     * @throws ValidacaoException Se o post não atender às regras de validação.
     */
    private void validarPost(Post post) {
        if (post.getConteudo().isEmpty()) {
            throw new ValidacaoException("O conteúdo do post não pode ser vazio!");
        }
        if (!gerenciadorUsuarios.listarUsuarios().contains(post.getAutor())) {
            throw new ValidacaoException("Autor inválido!O autor precisa estar cadastrado");
        }
        if (post.getConteudo().length() > 280) {
            throw new ValidacaoException("Os caracteres dessa mensagem excedem o limite máximo!");
        }
    }

    /**
     * Busca um post pelo seu ID.
     *
     * @param id ID do post a ser buscado.
     * @return O post correspondente ou null se não for encontrado.
     */
    public Post buscarPorId(int id) {
        for (Post post1 : posts) {
            if (post1.getId() == id) {
                return post1;
            }
        }
        return null;
    }

    /**
     * Lista todos os posts criados por um usuário específico.
     *
     * @param idUsuario ID do usuário autor dos posts.
     * @return Lista de posts criados pelo usuário.
     */
    public List<Post> listarPorUsuario(int idUsuario) {
        return posts.stream()
                .filter(post -> post.getAutor().getId() == idUsuario)
                .collect(Collectors.toList());
    }

    public List<Post> getPosts() {
        return posts;

    }

    /**
     * Adiciona uma curtida a um post.
     *
     * @param idPost    ID do post a ser curtido.
     * @param idUsuario ID do usuário que está curtindo o post.
     */
    public void curtir(int idPost, int idUsuario) {
        buscarPorId(idPost).adicionarCurtida(gerenciadorUsuarios.buscarPorId(idUsuario));
    }

    /**
     * Remove uma curtida de um post.
     *
     * @param idPost ID do post a ser descurtido.
     * @param idUsuario ID do usuário que está removendo a curtida.
     */
    public void descurtir(int idPost, int idUsuario) {
        buscarPorId(idPost).removerCurtida(gerenciadorUsuarios.buscarPorId(idUsuario));
    }

    /**
     * Adiciona um comentário a um post.
     *
     * @param comentario Comentário a ser adicionado.
     * @throws PostException Se ocorrer erro ao adicionar o comentário.
     */
    public void comentar(Comentario comentario) {
        Post post = posts.stream()
                .filter(p -> p.getId().equals(comentario.getId()))
                .findFirst()
                .orElse(null);

        if (post == null) {
            System.out.println("Post não encontrado.");
            return;
        }

        try {
            validarComentario(comentario);
            post.adicionarComentario(comentario);

        } catch (ValidacaoException e) {
            throw e;
        } catch (Exception e) {
            throw new PostException("Erro ao adicionar comentário: " + e.getMessage(), e);
        }
    }

    /**
     * Valida as regras de negócio para adição de um comentário.
     *
     * @param comentario Comentário a ser validado.
     * @throws ValidacaoException Se o comentário não atender às regras de validação.
     */
    private void validarComentario(Comentario comentario) {
        if ((comentario.getConteudo() == null || comentario.getConteudo().isEmpty())) {
            throw new ValidacaoException("O Comentário não pode ser vazio!");
        }

        if (!gerenciadorUsuarios.listarUsuarios().contains(comentario.getAutor())) {
            throw new ValidacaoException("Autor inválido!");
        }
        boolean postExiste = posts.stream()
                .anyMatch(post -> post.getId().equals(comentario.getPost().getId()));
        if (!postExiste) {
            throw new ValidacaoException("Post inválido! O post não existe.");
        }

    }

    /**
     * Deleta um post pelo ID.
     *
     * @param id ID do post a ser deletado.
     * @return true se o post foi deletado, false se não foi encontrado.
     */
    public boolean deletar(int id) {
        try {
            return posts.removeIf(post -> post.getId().equals(id));
        } catch(Exception e){
            throw new PostException("Erro ao deletar post: " + e.getMessage());
        }
    }
}
