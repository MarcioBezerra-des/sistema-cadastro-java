public class Carro {

    //Variaveis
    private final String renavam;
    private final String placa;
    private final String modelo;
    private final int ano;

    //Método Construtores
    public Carro(String renavam, String placa, String modelo, int ano) {
        this.renavam = renavam;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
    }

    //Método Da Classe
    public void exibirDados() {
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Ano " + this.ano);
        System.out.println("Placa: " + this.placa);
        System.out.println("Renavam: " + this.renavam);
    }

    public String getModelo() {
        return this.modelo;
    }

    public boolean verificarCarroAntigo() {
        return this.ano < 2000;
    }
}