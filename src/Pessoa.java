public class Pessoa {
    // Variaveis
    private final int id = 1;
    private final String nome;
    private int idade;
    private final String cpf;

    //Método Construtores
    public Pessoa(int id, String nome, int idade, String cpf){
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    // --- MÉTODOS GETTERS ---

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    //Método Da Classe


    // Método para verificar se a pessoa é maior de idade
    public boolean verificarMaioridade() {
        return this.idade >= 18;
    }

    // Método para atualizar a idade
    public void atualizarIdade(int novaIdade) {
        this.idade = novaIdade;
    }
}