<div align="center">
  
  # 📋 Sistema de Ponto Eletrônico
  ### Desafio MouraTech 2026
  
  ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen?style=for-the-badge&logo=spring)
  ![React](https://img.shields.io/badge/React-18.3.1-61DAFB?style=for-the-badge&logo=react)
  ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14%2B-336791?style=for-the-badge&logo=postgresql)
  ![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk)
  
  **Sistema completo de registro de ponto eletrônico com interface moderna e API REST robusta**
</div>

---

## 📑 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Funcionalidades](#-funcionalidades)
- [Instalação](#-instalação)
- [Uso](#-uso)
- [API Endpoints](#-api-endpoints)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Banco de Dados](#-banco-de-dados)

---

## 🎯 Sobre o Projeto

Sistema desenvolvido para o **Desafio 01 do MouraTech**, permitindo que funcionários registrem seus horários de trabalho (check-in/check-out) e gestores visualizem relatórios completos com filtros avançados.

### ✨ Diferenciais

- **Interface moderna** com Tailwind CSS e design system Moura
- **Autenticação por perfil** (Admin e Usuário)
- **Dashboard administrativo** com filtros e paginação
- **Modo desenvolvimento** com dados mockados
- **Responsivo** para desktop e mobile
- **Documentação Swagger** completa da API
- **Performance otimizada** com paginação server-side

---

## 🛠 Tecnologias

### **Frontend**

[![React](https://img.shields.io/badge/React-18.3.1-61DAFB?style=flat&logo=react)](https://react.dev/)
[![Vite](https://img.shields.io/badge/Vite-5.4.11-646CFF?style=flat&logo=vite)](https://vitejs.dev/)
[![React Router](https://img.shields.io/badge/React_Router-7.1.1-CA4245?style=flat&logo=react-router)](https://reactrouter.com/)
[![Axios](https://img.shields.io/badge/Axios-1.7.9-5A29E4?style=flat&logo=axios)](https://axios-http.com/)
[![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-3.4.17-06B6D4?style=flat&logo=tailwind-css)](https://tailwindcss.com/)
[![Lucide React](https://img.shields.io/badge/Lucide_React-0.468.0-F56565?style=flat)](https://lucide.dev/)

### **Backend**

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.1-6DB33F?style=flat&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-Latest-6DB33F?style=flat&logo=spring)](https://spring.io/projects/spring-data-jpa)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14+-336791?style=flat&logo=postgresql)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=flat&logo=apache-maven)](https://maven.apache.org/)
[![Lombok](https://img.shields.io/badge/Lombok-Latest-BC4521?style=flat)](https://projectlombok.org/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3.0-85EA2D?style=flat&logo=swagger)](https://swagger.io/)

### **Ferramentas**

[![Git](https://img.shields.io/badge/Git-F05032?style=flat&logo=git&logoColor=white)](https://git-scm.com/)
[![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=flat&logo=intellij-idea)](https://www.jetbrains.com/idea/)
[![VS Code](https://img.shields.io/badge/VS_Code-007ACC?style=flat&logo=visual-studio-code)](https://code.visualstudio.com/)

---

## 🏗 Arquitetura

### **Frontend (SPA - Single Page Application)**

```
React Application
├── Rotas Protegidas (PrivateRoute)
├── Context API (AuthContext)
└── Componentes Reutilizáveis
```

### **Backend (Arquitetura em Camadas)**

```
┌─────────────────────────────────────┐
│         Controller Layer            │
│  (REST Endpoints + Validation)      │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│          Service Layer              │
│    (Business Logic + Rules)         │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│        Repository Layer             │
│     (Data Access + Queries)         │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│      PostgreSQL Database            │
└─────────────────────────────────────┘
```

---

## 🎯 Funcionalidades

### 👤 **Funcionário (USER)**

- ✅ Login com credenciais
- ✅ Registro de entrada (check-in)
- ✅ Registro de saída (check-out)
- ✅ Visualização do tempo trabalhado
- ✅ Validação de ponto duplicado

### 👨‍💼 **Gestor (ADMIN)**

- ✅ Todas as funcionalidades de funcionário
- ✅ Dashboard com lista completa de registros
- ✅ Filtro por nome de funcionário
- ✅ Filtro por data específica
- ✅ Paginação de resultados
- ✅ Visualização de duração total trabalhada

---

## 💻 Instalação

### **Pré-requisitos**

Antes de começar, certifique-se de ter instalado:

- [Node.js](https://nodejs.org/) 18+
- [Java JDK](https://www.oracle.com/java/technologies/downloads/) 17+
- [PostgreSQL](https://www.postgresql.org/download/) 14+
- [Maven](https://maven.apache.org/download.cgi) 3.8+
- [Git](https://git-scm.com/)

### **1. Clone o repositório**

```bash
git clone https://github.com/seu-usuario/sistema-ponto-moura.git
cd sistema-ponto-moura
```

### **2. Configure o Banco de Dados**

```sql
-- Acesse o PostgreSQL
psql -U postgres

-- Crie o banco de dados
CREATE DATABASE moura_db;

-- Saia do psql
\q
```

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/moura_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server Port
server.port=8080
```

### **3. Execute o Backend**

```bash
# Na raiz do projeto
mvn clean install
mvn spring-boot:run
```

✅ Backend rodando em: **http://localhost:8080**

📚 Swagger UI disponível em: **http://localhost:8080/swagger-ui.html**

### **4. Execute o Frontend**

```bash
# Entre na pasta frontend
cd frontend

# Instale as dependências
npm install

# Execute o projeto
npm run dev
```

✅ Frontend rodando em: **http://localhost:5173**

---

## 🚀 Uso

### **Usuários Pré-cadastrados**

O sistema cria automaticamente dois usuários para teste:

| Perfil | Email | Senha | Permissões |
|--------|-------|-------|------------|
| 👨‍💼 **Administrador** | `admin@moura.com` | `123` | Acesso total ao sistema |
| 👤 **Funcionário** | `funcionario@moura.com` | `123` | Registro de ponto |

### **Fluxo do Funcionário**

1. Acesse http://localhost:5173
2. Faça login com `funcionario@moura.com` / `123`
3. Clique em **Check-in** para registrar entrada
4. Após trabalhar, clique em **Check-out**
5. Visualize o resumo do dia com horários e duração

### **Fluxo do Gestor**

1. Acesse http://localhost:5173
2. Faça login com `admin@moura.com` / `123`
3. Visualize o dashboard administrativo
4. Filtre registros por nome ou data
5. Navegue pela paginação para ver histórico completo

---

## 📡 API Endpoints

### **Autenticação**

#### `POST /auth/login`

Realiza login e retorna informações do usuário.

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

### **Ponto Eletrônico**

#### `POST /work/checkin?employeeId={id}`

Registra entrada do funcionário.

**Response:**
```json
{
  "id": 1,
  "employee": {
    "id": 1,
    "name": "João Silva",
    "email": "funcionario@moura.com",
    "role": "USER"
  },
  "checkinTime": "2026-01-19T08:00:00",
  "checkoutTime": null,
  "duration": null
}
```

#### `POST /work/checkout?employeeId={id}`

Registra saída do funcionário.

**Response:**
```json
{
  "id": 1,
  "employee": {
    "id": 1,
    "name": "João Silva",
    "email": "funcionario@moura.com",
    "role": "USER"
  },
  "checkinTime": "2026-01-19T08:00:00",
  "checkoutTime": "2026-01-19T17:00:00",
  "duration": 540
}
```

#### `GET /work/list?page={page}&size={size}&name={nome}&date={data}`

Lista registros de ponto com filtros e paginação.

**Parâmetros:**
- `page`: Número da página (default: 0)
- `size`: Itens por página (default: 10)
- `name`: Nome do funcionário (opcional)
- `date`: Data no formato YYYY-MM-DD (opcional)

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "employee": {
        "id": 1,
        "name": "João Silva",
        "email": "funcionario@moura.com",
        "role": "USER"
      },
      "checkinTime": "2026-01-19T08:00:00",
      "checkoutTime": "2026-01-19T17:00:00",
      "duration": 540
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalPages": 1,
  "totalElements": 1
}
```

---

## 📁 Estrutura do Projeto

```
sistema-ponto-moura/
│
├── .idea/                                    # Configurações IntelliJ IDEA
│
├── frontend/                                 # Aplicação React
│   ├── src/
│   │   ├── api/
│   │   │   └── api.js                       # Configuração Axios
│   │   ├── components/
│   │   │   ├── button.jsx                   # Componente Button
│   │   │   ├── card.jsx                     # Componente Card
│   │   │   └── Navbar.jsx                   # Componente Navbar
│   │   ├── contexts/
│   │   │   └── AuthContext.jsx              # Context de Autenticação
│   │   ├── pages/
│   │   │   ├── Admin.jsx                    # Página Admin
│   │   │   ├── App.jsx                      # Rotas da Aplicação
│   │   │   ├── login_temp.jsx               # Página de Login
│   │   │   └── Ponto.jsx                    # Página de Ponto
│   │   ├── index.css                        # Estilos globais
│   │   └── main.jsx                         # Entry point
│   ├── package.json                         # Dependências Node
│   └── vite.config.js                       # Configuração Vite
│
├── src/main/java/com/desafiomoura/sistema_integrado_moura/
│   ├── config/
│   │   ├── DataInitializer.java             # Seed de usuários
│   │   ├── DataLoader.java                  # Configuração inicial
│   │   └── OpenApiConfig.java               # Configuração Swagger
│   ├── controller/
│   │   ├── AuthController.java              # Endpoints de autenticação
│   │   └── WorkController.java              # Endpoints de ponto
│   ├── dto/
│   │   ├── LoginRequest.java                # DTO de login
│   │   ├── LoginResponse.java               # DTO de resposta
│   │   └── WorkRecordDTO.java               # DTO de registro
│   ├── entity/
│   │   ├── Employee.java                    # Entidade Funcionário
│   │   ├── UserRole.java                    # Enum de perfis
│   │   └── WorkRecord.java                  # Entidade Registro
│   ├── repository/
│   │   ├── EmployeeRepository.java          # Repositório de funcionários
│   │   └── WorkRecordRepository.java        # Repositório de registros
│   ├── service/
│   │   ├── AuthService.java                 # Lógica de autenticação
│   │   └── WorkService.java                 # Lógica de ponto
│   └── Main.java                            # Classe principal
│
├── src/main/resources/
│   └── application.properties               # Configurações Spring
│
├── target/                                   # Build artifacts (Maven)
├── HELP.md                                   # Guia Spring Boot
├── README.md                                 # Este arquivo
└── pom.xml                                   # Dependências Maven
```

---

## 🗄 Banco de Dados

### **Modelo ER**

```
┌─────────────────────┐         ┌─────────────────────┐
│     EMPLOYEES       │         │    WORK_RECORDS     │
├─────────────────────┤         ├─────────────────────┤
│ id (PK)            │◄────────│ id (PK)            │
│ name               │         │ employee_id (FK)   │
│ email (UNIQUE)     │         │ checkin_time       │
│ password           │         │ checkout_time      │
│ role               │         │ duration           │
└─────────────────────┘         └─────────────────────┘
```

### **Tabelas**

#### `employees`
```sql
CREATE TABLE employees (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);
```

#### `work_records`
```sql
CREATE TABLE work_records (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    checkin_time TIMESTAMP NOT NULL,
    checkout_time TIMESTAMP,
    duration BIGINT,
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) 
        REFERENCES employees(id) ON DELETE CASCADE
);
```

---

## 🛡 Regras de Negócio

### **Validações Implementadas**

1. **Check-in duplicado**
   - Não permite novo check-in se já existe um ponto aberto (sem check-out)
   
2. **Check-out sem check-in**
   - Valida existência de check-in antes de permitir check-out
   
3. **Cálculo automático de duração**
   - Calcula minutos trabalhados automaticamente no check-out
   - Fórmula: `duration = (checkout_time - checkin_time) em minutos`

4. **Autenticação por perfil**
   - Rotas protegidas validam token e perfil do usuário
   - ADMIN tem acesso ao dashboard, USER apenas ao ponto

5. **Filtros de data**
   - Aceita formato ISO (YYYY-MM-DD)
   - Filtra início do dia (00:00) até fim do dia (23:59:59)

---

## 🎨 Design System

### **Paleta de Cores Moura**

| Cor | Hex | Uso |
|-----|-----|-----|
| 🔵 Moura Blue | `#003366` | Cor primária, backgrounds |
| 🟠 Moura Orange | `#F2A933` | Botões, destaques |
| ⚪ White | `#FFFFFF` | Cards, textos principais |
| ⚫ Gray 800 | `#1F2937` | Textos secundários |
| ⚫ Gray 400 | `#9CA3AF` | Labels, placeholders |

### **Componentes**

- **Card**: Container branco com sombra e bordas arredondadas
- **Button**: Botão laranja com hover effect e ícones Lucide
- **Navbar**: Header fixo com logo Moura e logout

---

## 🔄 Modo Desenvolvimento

O frontend possui um **modo dev** que funciona offline sem backend:

```javascript
// frontend/src/contexts/AuthContext.jsx
const DEV_MODE = true; // Altere para false para usar backend real
```

**Quando DEV_MODE = true:**
- ✅ Login usa usuários mockados
- ✅ Check-in/checkout geram timestamps locais
- ✅ Lista de registros exibe dados de exemplo
- ✅ Ideal para desenvolvimento do frontend sem backend rodando

---

## 🧪 Testes

### **Cenário 1: Registro de Ponto Completo**

1. Login como funcionário
2. Fazer check-in → Verificar timestamp exibido
3. Aguardar alguns minutos
4. Fazer check-out → Verificar cálculo de duração
5. Conferir dados salvos no banco

### **Cenário 2: Validação de Ponto Duplicado**

1. Login como funcionário
2. Fazer check-in
3. Tentar fazer check-in novamente → Deve retornar erro
4. Fazer check-out
5. Tentar fazer check-out novamente → Deve retornar erro

### **Cenário 3: Dashboard Administrativo**

1. Login como admin
2. Visualizar lista de todos os registros
3. Filtrar por nome de funcionário
4. Filtrar por data específica
5. Navegar entre páginas

---

## 🐛 Troubleshooting

### **Erro: Backend não conecta ao PostgreSQL**

```bash
# Verifique se o PostgreSQL está rodando
sudo service postgresql status

# Ou no Windows
pg_ctl status

# Teste a conexão
psql -U seu_usuario -d moura_db
```

### **Erro: Frontend não encontra a API**

Verifique o arquivo `frontend/src/api/api.js`:
```javascript
const api = axios.create({
  baseURL: "http://localhost:8080", // Porta correta?
});
```

### **Erro: CORS Policy**

O backend já está configurado com:
```java
@CrossOrigin(origins = "*")
```

Para produção, altere para domínios específicos:
```java
@CrossOrigin(origins = "https://seudominio.com")
```

### **Erro: Maven build failed**

```bash
# Limpe o cache Maven
mvn clean

# Force update das dependências
mvn clean install -U
```

---


</div>

---

## 📄 Licença

Este projeto foi desenvolvido como parte do **Desafio MouraTech 2026** do Grupo Moura.

---
