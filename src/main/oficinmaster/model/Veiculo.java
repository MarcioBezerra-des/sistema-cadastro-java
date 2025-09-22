package main.oficinmaster.model;

import java.time.Year;

public class Veiculo {


    //---Atributos---
    private final long id;
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private  Cliente proprietario; // <<< Vínculo com a classe Cliente

    //---Construtores---
    public Veiculo(long id, String placa, String marca, String modelo, int anoFabricacao, Cliente proprietario) {
        // Validação básica para garantir que os dados não sejam nulos ou inválidos
        if (proprietario == null) {
            throw  new IllegalArgumentException("O veículo deve ter um proprietário.");
        }
        if (placa == null || placa.trim().isEmpty()) {
            throw  new IllegalArgumentException("A placa não pode ser nula ou vazia.");
        }

        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.proprietario = proprietario;
    }

    //---Métodos Getters---
    public long  getId() {
        return id;
    }
    public String getPlaca() {
        return placa;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public int getAnoFabricacao() {
        return anoFabricacao;
    }
    public Cliente getProprietario() {
        return proprietario;
    }

    //---Métodos Setters---
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public void setAnoFabricacao(int anoFabricacao){
        this.anoFabricacao = anoFabricacao;
    }
    
    //---Método de Comportamento---
    public boolean isClassico() {
        int anoAtual = Year.now().getValue();
        return (anoAtual - this.anoFabricacao) > 25;
    }

    //---Método para exibição---
    @Override
    public String toString() {
        return "Veiculo [ID=" + id + ", Placa=" + placa + ", Marca=" + marca + ", Modelo=" + modelo + ", Ano=" + anoFabricacao + ", Proprietário=" + proprietario.getNomeCompleto() + "]";
    }
}   
