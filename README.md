
# 📚 API Senaclick – Spring Boot

Reescrita da API desenvolvida originalmente em Node.js para um projeto semestral da faculdade, agora utilizando **Spring Boot** com foco em boas práticas como **arquitetura em camadas**, **princípios SOLID**, **Clean Code** e **DRY**.

---

## 🚀 Objetivo do Projeto

Esse projeto tem como principal objetivo aprofundar meus conhecimentos em **Java com Spring Boot**, colocando em prática conceitos de arquitetura de software, organização de código e construção de APIs RESTful bem estruturadas.

---

## 🧠 Tecnologias e Conceitos Aplicados

- Java 21  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Banco de Dados MySQL
- Lombok  
- Arquitetura em camadas (`controller`, `service`, `repository`, `model`, `dto`, `mapper`, `exception`, `enum`, `orchestrator`)  
- Princípios **SOLID**  
- **Clean Code** e **DRY**

---


## 📁 Estrutura do Projeto

```
 src/ 
 ├── controller/ # Endpoints da aplicação 
 ├── service/ # Regras de negócio 
 ├── repository/ # Interface com banco de dados 
 ├── model/ # Entidades JPA 
 ├── dto/ # Objetos de transferência de dados 
 ├── mapper/ # Conversão entre entidades e DTOs 
 ├── exception/ # Tratamento de erros personalizados 
 ├── enum/ # Tipagens estáticas da aplicação 
 ├── orchestrator/ # Coordenação de fluxos complexos
 ```

 ### Pré-requisitos

- Java 21
- IDE (recomendado: IntelliJ)

### Passos

```bash
# Clone o repositório
git clone https://github.com/ErickGX/apiSenaclickSpringboot.git

# Acesse a pasta do projeto
cd apiSenaclickSpringboot

# Rode a aplicação
./mvnw spring-boot:run
```
A API estará disponível em http://localhost:8081

Edite o arquivo application.yml para mudar a porta e as credenciais do banco de dados para os da sua maquina.


---


## 📌Próximos Passos

- Evoluir a API para seguir completamente o padrão RESTful

- Implementar autenticação e autorização (ex: Spring Security com JWT)

- Adicionar testes automatizados (JUnit, Mockito)

- Documentação da API com Swagger

- Dockerizar a API + Banco de Dados com Docker

---

## 💡Aprendizados
Esse projeto tem sido fundamental para consolidar conhecimentos sobre:

- Como estruturar uma aplicação Spring Boot com clareza e escalabilidade.

- A importância da separação de responsabilidades e modularidade.

- Como aplicar os princípios de SOLID de forma prática.

- Aplicação do conceito DRY e Clean Code

---

## 🔗 Link do projeto original em Node.js
- https://github.com/ErickGX/ApiRestSenaClick


---

## 📬 Contato
Se quiser trocar ideias ou sugerir melhorias, fique à vontade para me chamar por aqui ou via:

LinkedIn: https://www.linkedin.com/in/erickgs/

GitHub: @ErickGX
