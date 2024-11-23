package com.redesocial.gerenciador;

import com.redesocial.exception.UsuarioException;
import com.redesocial.exception.ValidacaoException;
import com.redesocial.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GerenciadorUsuarios {

    private List<Usuario> usuarios;
    private int proximoId;

    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1;
    }

    public void cadastrar(Usuario usuario) {
        try{
            validarUsuario(usuario);
            usuario.setId(proximoId++);
            usuarios.add(usuario);
        }catch (ValidacaoException e) {
            throw e;
        } catch (Exception e) {
            throw new UsuarioException("Erro ao cadastrar usuário" + e.getMessage(), e);
        }
    }

    private void validarUsuario(Usuario usuario) {
        for (Usuario user : usuarios) {
            if (user.getUsername().equalsIgnoreCase(usuario.getUsername())) {
                throw new ValidacaoException("Username existente! Informe um novo Username.");
            }
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@") || !usuario.getEmail().contains(".")) {
            throw new ValidacaoException("Email inválido! O símbolo '@' deve estar presente.");
        }

        if (usuario.getSenha().length() <= 6) {
            throw new ValidacaoException("Senha inválida! A senha deve conter no mínimo 6 caracteres.");
        }
        if ((usuario.getNome() == null || usuario.getNome().isEmpty())) {
            throw new ValidacaoException("O campo Nome não pode ser vazio!");
        }
    }

    public Usuario buscarPorId(int id) {
        for (Usuario user : usuarios) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Usuario buscarPorUsername(String username) {
        for (Usuario user : usuarios) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<Usuario> buscarPorNome(String nome) {
        List<Usuario> user = new ArrayList<>();
        for (Usuario usuario1 : usuarios) {
            if (usuario1.getNome().contains(nome)) {
                user.add(usuario1);
            }
        }
        return user;
    }
    public boolean atualizar(Usuario usuario){
        try {
            validarUsuario(usuario);

            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getId().equals(usuario.getId())) {
                    usuarios.set(i, usuario);
                    return true;
                }
            }
            return false;
        } catch (ValidacaoException e) {
            throw e;
        } catch (Exception e) {
            throw new UsuarioException("Erro ao atualizar usuário" + e.getMessage(), e);
        }
    }
    public boolean deletar(int id){
        return usuarios.removeIf(usuario -> usuario.getId() == id);
    }
    public void adicionarAmizade(int idUsuario1, int idUsuario2){
        buscarPorId(idUsuario1).adicionarAmigo(buscarPorId(idUsuario2));
    }
    public void removerAmizade(int idUsuario1, int idUsuario2){
        buscarPorId(idUsuario1).removerAmigo(buscarPorId(idUsuario2));
    }
}
