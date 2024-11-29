
# Sistema de Rede Social

## 📜 Descrição do Projeto

O Sistema de Rede Social é um aplicativo em Java que simula funcionalidades básicas de uma rede social, permitindo que os usuários interajam por meio de postagens, amizades e comentários. O sistema foi projetado com foco em modularidade, segurança e facilidade de uso.

**Principais Funcionalidades:**
- Cadastro de usuários com validações.
- Criação, edição e exclusão de posts.
- Adição e remoção de amigos.
- Curtidas e comentários em postagens.
- Visualização de feed de notícias personalizado.
- Perfis de usuário com informações detalhadas.

## 📂 Estrutura do Projeto

O código está organizado em pacotes para facilitar o entendimento e a escalabilidade:

```plaintext
com.redesocial/
├── modelo/         # Representação dos dados (Usuário, Post, Comentário)
├── gerenciador/    # Controle e validação de dados
├── ui/             # Interface com o usuário via console
├── util/           # Ferramentas auxiliares
└── exception/      # Tratamento de erros personalizados
```

### Componentes Relevantes
- **Camada Modelo**: Representa entidades como `Usuario`, `Post` e `Comentario`.
- **Gerenciadores**: Manipulam e validam dados dos usuários e posts.
- **Interface de Usuário**: Oferece menus interativos para navegação.
- **Exceções**: Garantem feedback claro para erros comuns.

## 🚀 Como Executar

1. **Pré-requisitos**:
   - Java 17 ou superior.
   - IDE ou terminal configurado para execução de aplicativos Java.

2. **Passos**:
   - Clone o repositório:
     ```bash
     git clone <url-do-repositório>
     ```
   - Compile os arquivos Java:
     ```bash
     javac -d bin src/com/redesocial/Main.java
     ```
   - Execute a aplicação:
     ```bash
     java -cp bin com.redesocial.Main
     ```

3. **Navegue pelo menu**:
   - Siga as instruções apresentadas no console para explorar funcionalidades como cadastro, posts e feed.

## ✨ Exemplos de Uso

### Menu Usuário
```plaintext
=== Cadastro de Usuário ===
Digite seu nome: Ana Silva
Digite seu username: ana123
Digite seu email: ana@email.com
Digite sua senha: ******
Usuário cadastrado com sucesso!
```

### Criação de Post
```plaintext
=== Novo Post ===
Digite seu post:
Meu primeiro post na rede social!
Post publicado com sucesso!
```

### Interações
- Curtir um post:
  ```plaintext
  Você curtiu o post "Meu primeiro post na rede social!".
  ```
- Comentar:
  ```plaintext
  Comentário adicionado: "Muito interessante!".
  ```

## 📊 Estruturas Visuais (Exemplo de Menu)

```plaintext
=== Menu Principal ===
1. Fazer Login
2. Cadastrar Novo Usuário
3. Sair
```

**Tela de Perfil do Usuário**:
```plaintext
=== Perfil: João Silva ===
Amigos: 3
Posts: 5
1. Adicionar Amigo
2. Criar Post
3. Voltar ao Menu Principal
```

## 🛠️ Decisões de Implementação

- **Separação de Camadas**: 
  Divisão clara entre a lógica de negócios (gerenciadores) e a interface do usuário (console).
  
- **Validações**:
  - Username único.
  - Conteúdo de post e comentários obrigatório.
  - Senhas com mínimo de 6 caracteres.

- **Estrutura Modular**:
  Organização em pacotes para manutenção e expansão.

- **Segurança**:
  - Controle de acesso com validação de sessão.

## 🧪 Testes

- **Fluxos básicos**:
  - Cadastro e login de usuário.
  - Criação e exclusão de posts.
  - Adição e remoção de amizades.

- **Casos de erro**:
  - Login com credenciais inválidas.
  - Tentativa de criar post sem conteúdo.
  - Exclusão de usuário inexistente.

---

🖥️ Desenvolvido para aprendizado e aprimoramento de conceitos em Java!
```
