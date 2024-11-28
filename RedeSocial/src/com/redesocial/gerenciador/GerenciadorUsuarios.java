package com.redesocial.gerenciador;

import com.redesocial.exception.UsuarioException;
import com.redesocial.exception.ValidacaoException;
import com.redesocial.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar operações relacionadas aos usuários da rede social.
 */
public class GerenciadorUsuarios {

    private List<Usuario> usuarios;
    private int proximoId;

    /**
     * Construtor da classe que inicializa a lista de usuários e o próximo ID disponível.
     */
    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1;
    }

    /**
     * Cadastra um novo usuário na rede social.
     *
     * @param usuario Usuário a ser cadastrado.
     * @throws UsuarioException Caso ocorra um erro durante o cadastro.
     */
    public void cadastrar(Usuario usuario) {
        try {
            validarUsuario(usuario);
            usuario.setId(proximoId++);
            usuarios.add(usuario);
        } catch (ValidacaoException e) {
            throw e;
        } catch (Exception e) {
            throw new UsuarioException("Erro ao cadastrar usuário: " + e.getMessage(), e);
        }
    }

    /**
     * Valida as informações do usuário antes do cadastro ou atualização.
     *
     * @param usuario Usuário a ser validado.
     * @throws ValidacaoException Caso as informações sejam inválidas.
     */
    private void validarUsuario(Usuario usuario) {
        for (Usuario user : usuarios) {
            if (!user.getId().equals(usuario.getId()) && user.getUsername().equalsIgnoreCase(usuario.getUsername())) {
                throw new ValidacaoException("Username existente! Informe um novo Username.");
            }
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new ValidacaoException("Email inválido! O email não pode ser nulo ou vazio.");
        }
        if (!usuario.getEmail().contains("@")) {
            throw new ValidacaoException("Email inválido! O símbolo '@' deve estar presente.");
        }

        if (usuario.getSenha().length() <= 6) {
            throw new ValidacaoException("Senha inválida! A senha deve conter no mínimo 6 caracteres.");
        }
        if ((usuario.getNome().isEmpty())) {
            throw new ValidacaoException("O campo Nome não pode ser vazio!");
        }
    }

    /**
     * Busca um usuário pelo ID.
     *
     * @param id ID do usuário a ser buscado.
     * @return Usuário correspondente ou null se não for encontrado.
     * @throws UsuarioException Caso ocorra um erro durante a busca.
     */
    public Usuario buscarPorId(int id) {
        try {
            for (Usuario user : usuarios) {
                if (user.getId() == id) {
                    return user;
                }
            }
            return null;
        } catch (Exception e) {
            throw new UsuarioException("Erro ao buscar usuário por ID: " + e.getMessage(), e);
        }
    }

    /**
     * Busca um usuário pelo username.
     *
     * @param username Username do usuário a ser buscado.
     * @return Usuário correspondente ou null se não for encontrado.
     * @throws UsuarioException Caso ocorra um erro durante a busca.
     */
    public Usuario buscarPorUsername(String username) {
        try {
            for (Usuario user : usuarios) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
            return null;
        } catch (Exception e) {
            throw new UsuarioException("Erro ao buscar usuário por username: " + e.getMessage(), e);
        }
    }

    /**
     * Busca usuários pelo nome ou parte do nome.
     *
     * @param nome Nome ou parte do nome a ser buscado.
     * @return Lista de usuários correspondentes.
     * @throws UsuarioException Caso ocorra um erro durante a busca.
     */
    public List<Usuario> buscarPorNome(String nome) {
        try {
            List<Usuario> user = new ArrayList<>();
            for (Usuario usuario1 : usuarios) {
                if (usuario1.getNome().contains(nome)) {
                    user.add(usuario1);
                }
            }
            return user;
        } catch (Exception e) {
            throw new UsuarioException("Erro ao buscar usuários por nome: " + e.getMessage(), e);
        }
    }

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param usuario Usuário com as informações atualizadas.
     * @return true se o usuário foi atualizado, false caso contrário.
     * @throws UsuarioException Caso ocorra um erro durante a atualização.
     */
    public boolean atualizar(Usuario usuario) {
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

    /**
     * Deleta um usuário pelo ID.
     *
     * @param id ID do usuário a ser deletado.
     * @return true se o usuário foi deletado, false caso contrário.
     * @throws UsuarioException Caso ocorra um erro durante a deleção.
     */
    public boolean deletar(int id) {
        try {
            return usuarios.removeIf(usuario -> usuario.getId() == id);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao deletar usuário: " + e.getMessage(), e);
        }
    }

    /**
     * Adiciona uma amizade entre dois usuários.
     *
     * @param idUsuario1 ID do primeiro usuário.
     * @param idUsuario2 ID do segundo usuário.
     * @throws UsuarioException Caso ocorra um erro ao adicionar amizade.
     */
    public void adicionarAmizade(int idUsuario1, int idUsuario2) {
        try {
            Usuario user1 = buscarPorId(idUsuario1);
            Usuario user2 = buscarPorId(idUsuario2);

            if (user1 == null || user2 == null) {
                throw new UsuarioException("Usuário não encontrado para adicionar amizade.");
            }

            user1.adicionarAmigo(user2);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao adicionar amizade: " + e.getMessage(), e);
        }
    }

    /**
     * Remove uma amizade entre dois usuários.
     *
     * @param idUsuario1 ID do primeiro usuário.
     * @param idUsuario2 ID do segundo usuário.
     * @throws UsuarioException Caso ocorra um erro ao remover amizade.
     */
    public void removerAmizade(int idUsuario1, int idUsuario2) {
        try {
            Usuario user1 = buscarPorId(idUsuario1);
            Usuario user2 = buscarPorId(idUsuario2);

            if (user1 == null || user2 == null) {
                throw new UsuarioException("Usuário não encontrado para remover amizade.");
            }

            user1.removerAmigo(user2);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao remover amizade: " + e.getMessage(), e);
        }
    }

    /**
     * Lista todos os usuários cadastrados.
     *
     * @return Lista de usuários.
     * @throws UsuarioException Caso ocorra um erro ao listar os usuários.
     */
    public List<Usuario> listarUsuarios() {
        try {
            return new ArrayList<>(usuarios);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao listar usuários: " + e.getMessage(), e);
        }
    }
}
