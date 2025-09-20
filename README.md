# Sistema-cadastro-java

## **Visão Geral**

Este repositório contém um projeto educacional desenvolvido em Java: um sistema de cadastro via console. A aplicação permite o registro de Pessoas e Veículos, implementando validações de dados em tempo real para garantir a integridade das informações.

O principal objetivo deste projeto é a aplicação prática de conceitos fundamentais da programação orientada a objetos, estrutura de dados, tratamento de exceções e controle de fluxo, utilizando a linguagem Java.

**Status do Projeto**: Em ´Desenvolvimento´ (A versão ´v1.0´ estável foi concluída).

## **Funcionalidades da Versão Atual (v1.0)**

* **Cadastro de Entidades**: Sistema para registrar Pessoas e Carros com seus respectivos atributos.
* **Validação de Dados em Tempo Real**:
  * **CPF**: Verifica se a entrada contém 11 dígitos numéricos e a formata para o padrão `xxx.xxx.xxx-xx`.
  * **Placa Veicular**: Valida se a placa segue o padrão Mercosul `(LLLNLNN)`.
  * **Renavam**: Valida se a entrada contém 11 dígitos numéricos.
* **Interface Interativa**: Toda a interação com o usuário é feita através do console de forma guiada.
* **Confirmação de Dados**: Antes de salvar cada registro, o sistema exibe os dados inseridos e solicita a confirmação do usuário, permitindo a correção de erros.
* **Relatório Final**: Ao final da execução, exibe uma lista com todos os cadastros realizados na sessão.

## **Tecnologias Utilizadas**
* **Linguagem**: Java (JDK 11+)
* **IDE**: Visual Studio Code
* **Controle de Versão**: Git & GitHub

## **Como Executar o Projeto**
Para executar este projeto em sua máquina local, siga os passos abaixo.
