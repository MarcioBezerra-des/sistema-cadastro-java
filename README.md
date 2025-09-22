# Sistema-cadastro-java

## **Visão Geral**
**OficinaMaster** é um projeto educacional que simula um sistema de gestão para uma oficina mecânica, desenvolvido inteiramente em Java e executado via console. A aplicação foi projetada com uma arquitetura de software moderna, separando as responsabilidades em camadas de **Modelo (Model) e Serviço (Service)**, preparando o terreno para uma futura migração para um ambiente web com Spring Boot.

O principal objetivo deste projeto é a aplicação prática de conceitos avançados de **Programação Orientada a Objetos, Arquitetura em Camadas**, e o gerenciamento de um fluxo de negócio completo, desde o cadastro de um cliente até a abertura de uma Ordem de Serviço.

Status do Projeto: `Em Desenvolvimento` (Arquitetura de camadas implementada).

## **Funcionalidades da Versão Atual (v1.0)**

* **Arquitetura em Camadas**: O código é organizado em pacotes (`model`, `service`) para garantir a separação de responsabilidades e a manutenibilidade.
* **Menu Interativo**: A aplicação é controlada por um menu principal no console, oferecendo uma experiência de usuário clara e objetiva.
* **Cadastro de Clientes e Veículos**: Sistema para registrar clientes e vincular múltiplos veículos a eles.
* **Abertura de Ordem de Serviço (OS)**: Funcionalidade para criar uma nova OS, associando um veículo e o problema relatado pelo cliente.
* **Listagem de Dados**: Exibição de todos os clientes e Ordens de Serviço cadastradas durante a sessão.

## **Tecnologias Utilizadas**
* **Linguagem**: Java (JDK 11+)
* **IDE**: Visual Studio Code
* **Estrutura de Dados**: `ArrayList` para gerenciamento dinâmico dos dados em memória.
* **Controle de Versão**: Git & GitHub

## **Como Executar o Projeto**
Para executar este projeto em sua máquina local, siga os passos abaixo.

### **Pré-requisitos**
* Java Development Kit (JDK) instalado e configurado.
* Git Instalado.
* Visual Studio Code com o Extension Pack for Java.

### **Passos para Execução**

**1. Clone o repositório:**
~~~
git clone https://github.com/MarcioBezerra-des/sistema-cadastro-java.git
~~~
**2. Abra o projeto no VS Code:**
~~~
cd sistema-cadastro-java
code .
~~~
**3. Execute a Aplicação:**
* Aguarde alguns segundos para que a extensão Java carregue o projeto.
* Navegue e abra o arquivo principal:
  `src/main/oficinmaster/OficinaMasterApplication.java`.
* Clique no botão "Run" que aparece acima do método `main`.
* A aplicação será iniciada no painel "TERMINAL" do VS Code.

## **Arquitetura do Código**
AA aplicação segue o princípio da Separação de Responsabilidades, dividindo o código em pacotes lógicos:
~~~
src/main/oficinmaster/
├── OficinaMasterApplication.java  // Classe principal (UI do Console)
├── model/                         // Contém as classes que representam os dados
│   ├── Cliente.java
│   ├── Veiculo.java
│   ├── OrdemDeServico.java
│   ├── ItemServico.java
│   └── StatusOrdem.java
└── service/                       // Contém a lógica de negócio do sistema
    ├── ClienteService.java
    ├── VeiculoService.java
    └── GestaoOrdemDeServico.java
~~~
* **Pacote** `model`: Define a estrutura dos dados (as "plantas" dos nossos objetos).
* **Pacote** `service`: Contém as regras de negócio. É o cérebro da aplicação, responsável por manipular os objetos do modelo.
* `OficinaMasterApplication.java`: Atua como a camada de Apresentação (UI). Sua única responsabilidade é exibir menus, coletar a entrada do usuário e delegar as ações para a camada de serviço.

## **Roadmap (Próximos Passos)**
* [] Completar o CRUD: Implementar as funcionalidades de Editar e Excluir para Clientes, Veículos e Ordens de Serviço.
* [] Interatividade da OS: Desenvolver as opções de menu para Adicionar Itens (peças/serviços) e Mudar o Status de uma OS existente.
* [] Persistência de Dados: Salvar os dados em um arquivo (`.csv` ou `.json`) para que não se percam ao fechar a aplicação.
* [] Busca e Filtros: Criar uma funcionalidade de busca para encontrar clientes por nome/CPF ou Ordens de Serviço por placa/status.
* [] Testes Unitários (JUnit): Adicionar testes automatizados para validar a lógica da camada de serviço.
* [] Migração para Spring Boot: Evoluir o projeto para uma aplicação web com banco de dados e API REST.
