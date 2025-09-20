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

    //Método Da Classe

    // Método para exibir os dados
    public void exibirDados() {
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("Cpf: " + this.cpf);
    }

    public String getNome() {
        return this.nome;
    }

    // Método para verificar se a pessoa é maior de idade
    public boolean verificadorMaioridade() {
        return this.idade >= 18;
    }

    // Método para atualizar a idade
    public void atualizarIdade(int novaIdade) {
        this.idade = novaIdade;
    }

}
