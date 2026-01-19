# 🕐 Sistema de Ponto Eletrônico | Desafio Moura Tech

> Projeto desenvolvido como parte do processo seletivo do **Moura Tech**

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.1-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-61DAFB?style=for-the-badge&logo=react&logoColor=black)](https://reactjs.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![TailwindCSS](https://img.shields.io/badge/Tailwind-3.4-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)](https://tailwindcss.com/)

---

## 📋 Sobre o Projeto

Sistema completo de **registro de ponto eletrônico** que permite que funcionários marquem entrada/saída e gestores visualizem relatórios detalhados com filtros avançados.

### ✨ Destaques Técnicos

- 🎯 **Arquitetura em Camadas** (Controller → Service → Repository)
- 🔐 **Sistema de Autenticação** com controle de acesso baseado em roles
- ⏱️ **Contador em Tempo Real** com precisão de segundos
- 📊 **Paginação e Filtros Dinâmicos** no painel administrativo
- 📚 **Documentação Interativa** com Swagger/OpenAPI
- 🎨 **Interface Moderna** com design responsivo

---

## 🚀 Funcionalidades

### 👤 Para Funcionários
- ✅ Login seguro com email e senha
- ✅ Registro de entrada (check-in) com validação de ponto aberto
- ✅ Contador em tempo real mostrando tempo trabalhado (hh:mm:ss)
- ✅ Registro de saída (check-out) com cálculo automático de duração
- ✅ Visualização do resumo do dia (entrada, saída e total trabalhado)
- ✅ Persistência de ponto aberto (se fechar o navegador e voltar, continua contando)

### 👨‍💼 Para Gestores
- ✅ Painel administrativo com listagem completa de registros
- ✅ Filtro por **nome do funcionário** (busca parcial)
- ✅ Filtro por **data específica** (formato yyyy-MM-dd)
- ✅ Paginação otimizada para grandes volumes de dados
- ✅ Visualização de pontos em aberto
- ✅ Cálculo automático de horas trabalhadas

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação
- **Spring Boot 4.x** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL 16** - Banco de dados relacional
- **Lombok** - Redução de boilerplate
- **SpringDoc OpenAPI** - Documentação automática (Swagger)
- **Maven** - Gerenciamento de dependências

### Frontend
- **React 18** - Biblioteca JavaScript
- **Vite** - Build tool e dev server
- **React Router v7** - Navegação entre páginas
- **Axios** - Cliente HTTP
- **Tailwind CSS** - Estilização
- **Lucide React** - Ícones modernos

---

## 📐 Arquitetura do Projeto

### Backend (Estrutura de Pacotes)
```
sistema_integrado_moura/
├── config/          # Configurações (Swagger, DataInitializer)
├── controller/      # Endpoints REST
│   ├── AuthController.java
│   └── WorkController.java
├── service/         # Lógica de negócio
│   ├── AuthService.java
│   └── WorkService.java
├── repository/      # Acesso a dados (JPA)
│   ├── EmployeeRepository.java
│   └── WorkRecordRepository.java
├── entity/          # Entidades do banco
│   ├── Employee.java
│   ├── WorkRecord.java
│   └── UserRole.java
└── dto/             # Objetos de transferência
    ├── LoginRequest.java
    ├── LoginResponse.java
    └── WorkRecordDTO.java
```

### Frontend (Estrutura de Pastas)
```
src/
├── api/             # Configuração do Axios
├── components/      # Componentes reutilizáveis
│   ├── button.jsx
│   ├── card.jsx
│   └── Navbar.jsx
├── contexts/        # Context API (AuthContext)
├── pages/           # Páginas da aplicação
│   ├── App.jsx
│   ├── Login.jsx
│   ├── Ponto.jsx
│   └── Admin.jsx
└── index.css        # Estilos globais (Tailwind)
```

---

## 🔧 Configuração e Instalação

### Pré-requisitos
- ☕ **Java 17+** ([Download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
- 🐘 **PostgreSQL 12+** ([Download](https://www.postgresql.org/download/))
- 📦 **Node.js 18+** ([Download](https://nodejs.org/))
- 🔨 **Maven 3.8+** (geralmente vem com IDEs)

---

### 🗄️ Passo 1: Configurar o Banco de Dados

Abra o terminal do PostgreSQL e execute:

```sql
CREATE DATABASE moura_db;
```

---

### ⚙️ Passo 2: Configurar o Backend

1. **Clone o repositório**
   ```bash
   git clone <seu-repo>
   cd sistema-ponto-moura/backend
   ```

2. **Configure o `application.properties`**

   Abra `src/main/resources/application.properties` e ajuste:

   ```properties
   spring.application.name=sistema-integrado-moura

   # Database
   spring.datasource.url=jdbc:postgresql://localhost:5432/moura_db
   spring.datasource.username=seu_usuario_postgres
   spring.datasource.password=sua_senha_postgres

   # JPA
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true

   # Server
   server.port=8080

   # Swagger
   springdoc.api-docs.path=/api-docs
   springdoc.swagger-ui.path=/swagger-ui.html
   ```

3. **Rode o backend**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Verifique se funcionou**
   - Backend: http://localhost:8080
   - Swagger: http://localhost:8080/swagger-ui.html

---

### 🎨 Passo 3: Configurar o Frontend

1. **Navegue até a pasta do frontend**
   ```bash
   cd ../frontend
   ```

2. **Instale as dependências**
   ```bash
   npm install
   ```

3. **Rode o frontend**
   ```bash
   npm run dev
   ```

4. **Acesse a aplicação**
   - Frontend: http://localhost:5173

---

## 👥 Usuários de Teste

O sistema cria automaticamente dois usuários ao iniciar:

| Email | Senha | Tipo | Acesso |
|-------|-------|------|--------|
| `admin@moura.com` | `123` | ADMIN | Painel administrativo + Ponto |
| `funcionario@moura.com` | `123` | USER | Apenas registro de ponto |

---

## 📡 Documentação da API

### 🔐 Autenticação

#### `POST /auth/login`
Realiza login e retorna token + informações do usuário

**Request:**
```json
{
  "email": "admin@moura.com",
  "password": "123"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Gestor Moura",
  "token": "token-fake-123",
  "role": "ADMIN"
}
```

---

### ⏱️ Registro de Ponto

#### `POST /work/checkin?employeeId={id}`
Registra a entrada do funcionário

**Response:**
```json
{
  "id": 1,
  "employee": { "id": 2, "name": "João Silva", ... },
  "checkinTime": "2026-01-17T08:00:00",
  "checkoutTime": null,
  "duration": null
}
```

**Regras:**
- ❌ Não permite check-in se já houver ponto aberto
- ✅ Registra horário atual automaticamente

---

#### `POST /work/checkout?employeeId={id}`
Registra a saída do funcionário e calcula duração

**Response:**
```json
{
  "id": 1,
  "employee": { ... },
  "checkinTime": "2026-01-17T08:00:00",
  "checkoutTime": "2026-01-17T17:30:00",
  "duration": 570
}
```

**Regras:**
- ❌ Só funciona se houver check-in aberto
- ✅ Calcula duração em minutos automaticamente

---

#### `GET /work/list`
Lista registros com paginação e filtros

**Query Parameters:**
| Parâmetro | Tipo | Obrigatório | Default | Descrição |
|-----------|------|-------------|---------|-----------|
| `page` | int | Não | 0 | Número da página |
| `size` | int | Não | 10 | Itens por página |
| `name` | string | Não | - | Filtro por nome (busca parcial) |
| `date` | string | Não | - | Filtro por data (yyyy-MM-dd) |

**Exemplo:**
```
GET /work/list?page=0&size=10&name=João&date=2026-01-17
```

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "employee": { "name": "João Silva" },
      "checkinTime": "2026-01-17T08:00:00",
      "checkoutTime": "2026-01-17T17:30:00",
      "duration": 570
    }
  ],
  "totalElements": 1,
  "totalPages": 1,
  "size": 10,
  "number": 0
}
```

---

## 🧪 Testando o Sistema

### Fluxo Completo de Teste

1. **Login como Funcionário**
   - Acesse http://localhost:5173
   - Login: `funcionario@moura.com` / `123`
   - Você será redirecionado para `/ponto`

2. **Registrar Ponto**
   - Clique em **"Check-in"**
   - Observe o contador em tempo real (hh:mm:ss)
   - Aguarde alguns segundos/minutos
   - Clique em **"Check-out"**
   - Veja o resumo com total trabalhado

3. **Visualizar como Gestor**
   - Saia e faça login com `admin@moura.com` / `123`
   - Você será redirecionado para `/admin`
   - Veja o registro na tabela com duração calculada
   - Teste os filtros por nome e data

---

## 🎯 Diferenciais Implementados

### ⚡ Performance
- Paginação otimizada para grandes volumes
- Queries JPA com índices adequados
- Caching de configurações

### 🔒 Segurança
- Validação de dados em todos os endpoints
- Proteção contra check-ins duplicados
- Rotas protegidas no frontend (PrivateRoute, AdminRoute)

### 🎨 UX/UI
- Design responsivo (mobile-first)
- Feedback visual em todas as ações
- Estados de loading
- Mensagens de erro claras
- Contador em tempo real com precisão de segundos

### 📊 Dados
- Persistência completa no PostgreSQL
- Recuperação de ponto aberto ao retornar
- Filtros combinados (nome + data)
- Ordenação por data decrescente

---

## 🐛 Troubleshooting

### Backend não inicia
**Erro:** `Connection refused to PostgreSQL`

**Solução:**
1. Verifique se o PostgreSQL está rodando
2. Confirme usuário/senha no `application.properties`
3. Teste a conexão: `psql -U postgres -d moura_db`

---

### Filtros não funcionam
**Causa:** Frontend em modo DEV

**Solução:**
1. Abra `src/contexts/AuthContext.jsx`
2. Mude `DEV_MODE = false`
3. Reinicie o frontend

---

### CORS Error
**Erro:** `Access-Control-Allow-Origin`

**Solução:**
- Verifique se `@CrossOrigin(origins = "*")` está nos controllers
- Confirme que backend está em http://localhost:8080

---

## 📚 Documentação Adicional

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **API Docs JSON:** http://localhost:8080/api-docs

---

## 📄 Licença

Este projeto foi desenvolvido exclusivamente como parte do processo seletivo da **Moura Tech** e segue os requisitos do Desafio 01.

---

<div align="center">

### 🔋 Feito com dedicação para a Moura Tech

**Sistema de Ponto Eletrônico | 2026**

</div>
