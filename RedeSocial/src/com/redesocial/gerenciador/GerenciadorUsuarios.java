package com.redesocial.gerenciador;

import com.redesocial.exception.ValidacaoException;
import com.redesocial.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuarios {

    private List<Usuario> usuarios;
    private int proximoId;

    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1;
    }

    public void cadastrar(Usuario usuario) {
        validarUsuario(usuario);

        usuario.setId(proximoId++);
        
    }

    public void validarUsuario(Usuario usuario) {
        for (Usuario user : usuarios) {
            if (user.getUsername().equalsIgnoreCase(usuario.getUsername())) {
                throw new ValidacaoException("Username existente! Informe um novo Username.");
            }
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@") || !usuario.getEmail().contains(".")) {
            throw new ValidacaoException("Email inválido! O símbolo '@' deve estar presente.");
        }

        if(usuario.getSenha().length() <= 6){
            throw new ValidacaoException("Senha inválida! A senha deve conter no mínimo 6 caracteres");
        }
        if((usuario.getNome() == null || usuario.getNome().isEmpty())){
            throw new ValidacaoException("O campo Nome não pode ser vazio!");
        }
    }
}
