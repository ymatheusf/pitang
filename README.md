# Pitang Desafio Técnico

## **Api Hospedada na Host**

**https://pitang-production.up.railway.app**

### Sem Autenticação
#### (GET,POST)https://pitang-production.up.railway.app/api/users

#### (GET,UPDATE,DELETE)https://pitang-production.up.railway.app/api/users/{ID}

### Retorna o JWT
#### (POST)https://pitang-production.up.railway.app/api/signin

### Requer Autenticação JWT
#### (GET)https://pitang-production.up.railway.app/api/users/me

#### (GET,POST)https://pitang-production.up.railway.app/api/cars

#### (GET.POST,DELETE)https://pitang-production.up.railway.app/api/cars{ID}




## Rota /api/signin

### **História de Usuário: Autenticação de Usuário**

Como um usuário, quero poder fornecer meu login e senha para obter um token de acesso JWT para usar a API.
Critérios de Aceitação:
Se o login e senha forem válidos, o sistema deve gerar e retornar um token JWT.
Se o login ou senha forem inválidos, o sistema deve retornar uma mensagem de erro "Invalid login or password" com o código de erro 1.

## Rota /api/users

História de Usuário: Listar Todos os Usuários

Como um usuário autenticado, quero poder listar todos os usuários cadastrados.
História de Usuário: Cadastrar Novo Usuário

Como um usuário, quero poder cadastrar um novo usuário com as informações necessárias.
Critérios de Aceitação:
Se o cadastro for bem-sucedido, o sistema deve retornar o usuário recém-criado com o código de status 201.
Se o e-mail já estiver cadastrado, o sistema deve retornar uma mensagem de erro "Email already exists" com o código de erro 2.
Se o login já estiver cadastrado, o sistema deve retornar uma mensagem de erro "Login already exists" com o código de erro 3.
Se os campos fornecidos forem inválidos, o sistema deve retornar uma mensagem de erro "Invalid fields" com o código de erro 4.
Se campos obrigatórios não forem preenchidos, o sistema deve retornar uma mensagem de erro "Missing fields" com o código de erro 5.

### História de Usuário: Buscar Usuário por ID

Como um usuário autenticado, quero poder buscar informações de um usuário específico pelo seu ID.

### **História de Usuário: Remover Usuário por ID**

Como um usuário autenticado, quero poder remover um usuário específico pelo seu ID.

### História de Usuário: Atualizar Usuário por ID

Como um usuário autenticado, quero poder atualizar informações de um usuário específico pelo seu ID.
Critérios de Aceitação:
Se a atualização for bem-sucedida, o sistema deve retornar uma mensagem de sucesso.
Se os campos fornecidos forem inválidos, o sistema deve retornar uma mensagem de erro "Invalid fields" com o código de erro 4. 
Se campos obrigatórios não forem preenchidos, o sistema deve retornar uma mensagem de erro "Missing fields" com o código de erro 5.

## Rota /api/me

### História de Usuário: Obter Minhas Informações

Como um usuário autenticado, quero poder obter minhas próprias informações de perfil.

## Rota /api/cars

### História de Usuário: Listar Meus Carros

Como um usuário autenticado, quero poder listar todos os carros associados à minha conta.

### História de Usuário: Cadastrar Novo Carro

Como um usuário autenticado, quero poder cadastrar um novo carro associado à minha conta.
Critérios de Aceitação:
Se o cadastro for bem-sucedido, o sistema deve retornar o carro recém-criado com o código de status 201.
Se a placa do carro já estiver cadastrada, o sistema deve retornar uma mensagem de erro "License plate already exists" com o código de erro 3.
Se os campos fornecidos forem inválidos, o sistema deve retornar uma mensagem de erro "Invalid fields" com o código de erro 4.
Se campos obrigatórios não forem preenchidos, o sistema deve retornar uma mensagem de erro "Missing fields" com o código de erro 5.

### História de Usuário: Buscar Carro por ID

Como um usuário autenticado, quero poder buscar informações de um carro específico pelo seu ID.

### História de Usuário: Remover Carro por ID

Como um usuário autenticado, quero poder remover um carro específico pelo seu ID.

### História de Usuário: Atualizar Carro por ID

Como um usuário autenticado, quero poder atualizar informações de um carro específico pelo seu ID.
Critérios de Aceitação:
Se a atualização for bem-sucedida, o sistema deve retornar uma mensagem de sucesso.
Se os campos fornecidos forem inválidos, o sistema deve retornar uma mensagem de erro "Invalid fields" com o código de erro 4.
Se campos obrigatórios não forem preenchidos, o sistema deve retornar uma mensagem de erro "Missing fields" com o código de erro 5.
