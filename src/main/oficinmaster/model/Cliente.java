package main.oficinmaster.model;

public class Cliente {

    // --- Atributos ---
    private final long id;
    private String nomeCompleto;
    private final String cpf;
    private String telefone;
    private String email;



    //--- Construtores ---
    public Cliente(long id, String nomeCompleto, String cpf, String telefone, String email){
        // Validação para garantir que dados essenciais não sejam nulos ou vazios
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome completo não pode ser nulo ou vazio.");
        }
        if(cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF não pode ser nulo ou vazio.");
        }
        
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    // --- MÉTODOS GETTERS ---

    public long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }

    //--- Métodos Setters ---
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //---Método para exibição ---
    @Override
    public String toString() {
        return "Cliente [ID= "+ id + ", NOME="+ nomeCompleto +", CPF="+ cpf +", Telefone="+ telefone +", Email=" + email + "]";
    }

}
