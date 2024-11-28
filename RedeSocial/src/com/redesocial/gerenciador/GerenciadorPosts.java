package com.redesocial.gerenciador;

import com.redesocial.exception.PostException;
import com.redesocial.exception.ValidacaoException;
import com.redesocial.modelo.Comentario;
import com.redesocial.modelo.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GerenciadorPosts {

    private List<Post> posts;

    private int proximoId;

    private GerenciadorUsuarios gerenciadorUsuarios;

    public GerenciadorPosts(GerenciadorUsuarios gerenciadorUsuarios) {
        this.posts = new ArrayList<>();
        this.proximoId = 1;
        this.gerenciadorUsuarios = gerenciadorUsuarios;
    }

    public void criar(Post post) {
        try {
            validarPost(post);
            post.setId(proximoId++);
            posts.add(post);
        }catch (ValidacaoException e) {
            throw e;
        } catch (Exception e) {
            throw new PostException("Erro ao criar post:  " + e.getMessage(), e);
        }
    }

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

    public Post buscarPorId(int id) {
        for (Post post1 : posts) {
            if (post1.getId() == id) {
                return post1;
            }
        }
        return null;
    }

    public List<Post> listarPorUsuario(int idUsuario) {
        return posts.stream()
                .filter(post -> post.getAutor().getId() == idUsuario)
                .collect(Collectors.toList());
    }

    public List<Post> getPosts() {
        return posts;

    }

    public void curtir(int idPost, int idUsuario) {
        buscarPorId(idPost).adicionarCurtida(gerenciadorUsuarios.buscarPorId(idUsuario));
    }

    public void descurtir(int idPost, int idUsuario) {
        buscarPorId(idPost).removerCurtida(gerenciadorUsuarios.buscarPorId(idUsuario));
    }

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
            post.adicionarComentario(comentario);

        } catch (ValidacaoException e) {
            throw e;
        } catch (Exception e) {
            throw new PostException("Erro ao adicionar comentário: " + e.getMessage(), e);
        }
    }
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
    public boolean deletar(int id){
        Optional<Post> postEncontrado = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
        if (postEncontrado.isEmpty()) {
            return false;
        }
        return true;
    }
}
