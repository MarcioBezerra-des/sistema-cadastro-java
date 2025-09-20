import java.util.Scanner;
import java.util.regex.Pattern;

public class Cadastro {

    // --- MÉTODOS AUXILIARES PARA VALIDAÇÃO ---

    // Lê uma String e garante que não está vazia
    private static String lerStringNaoVazia(Scanner leitor, String mensagem) {
        while (true) {
            System.out.println(mensagem);
            String texto = leitor.nextLine();
            if (texto != null && !texto.trim().isEmpty()) {
                return texto;
            }
            System.out.println("Erro: O campo não pode ser vazio. Tente novamente.");
        }
    }
    
    // Lê um CPF, valida e formata
    private static String lerCpf(Scanner leitor) {
        while (true) {
            System.out.println("CPF da pessoa (apenas números): ");
            String cpf = leitor.nextLine();
            if (cpf.equalsIgnoreCase("exit")) return "exit";

            if (cpf.matches("\\d{11}")) { // Verifica se contém exatamente 11 dígitos
                // Formata o CPF: xxx.xxx.xxx-xx
                return  String.format("%s.%s.%s.-%s",
                    cpf.substring(0, 3), cpf.substring(3, 6),
                    cpf.substring(6, 9), cpf.substring(9, 11));
            }
            System.out.println("Erro: CPF inválido. Deve conter 11 números. Tente novamente.");
        }
    }

    // Lê uma Placa e valida o formato Mercosul
    private static String lerPlaca(Scanner leitor) {
        while (true) { 
            System.out.println("Placa do carro (formato LLLNLNN, ex: ABC1D23): ");
            String placa = leitor.nextLine();
            if (placa.equalsIgnoreCase("exit")) return "exit";

            // Expressão Regular para o padrão Mercosul (3 letras, 1 número, 1 letra, 2 números)
            if (Pattern.matches("[A-Z]{3}\\d[A-Z]\\d{2}", placa.toUpperCase())) {
                return placa.toUpperCase();
            }
            System.out.println("Erro: Formato de placa inválido. Tente novamente.");
        }
    }

    // Lê um Renavam e valida o tamanho
    private static String lerRenavam(Scanner leitor) {
        while (true) { 
            System.out.println("Renavam do carro (11 números): ");
            String renavam = leitor.nextLine();
            if (renavam.equalsIgnoreCase("exit")) return "exit";

            if (renavam.matches("\\d{11}")) {
                return renavam;
            }
            System.out.println("Erro: Renavam inválido. Deve conter 11 números. Tente novamente.");
        }
    }

    // Lê um número inteiro
    private static int lerInt(Scanner leitor, String mensagem) {
        while (true) { 
            System.out.println(mensagem);
            String input = leitor.nextLine();
            try{return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número. Tente novamente.");
            }
            
        }
    }

    public static void main(String[] args) {
        
        try (Scanner leitor = new Scanner(System.in)) {
            Pessoa[] pessoas = new Pessoa[10];
            Carro[] carros = new Carro[10];
            int indice = 0;

            boolean programaRodando = true;
            
            System.out.println("--- Sistema de Cadastro ---");
            
            while (indice < 10 && programaRodando) {
                boolean dadosCorretos = false; // Variável de controle para o loop de correção
                
                // Loop para repetir o cadastro em caso de erro
                do{
                    System.out.println("\n-> Iniciando cadastro (ID: " + (indice + 1) + ")");
                    System.out.println("(Digite 'exit' a qualquer momento para encerrar)");
                    
                    // --- Coleta de dados da Pessoa ---
                   String nomePessoa = lerStringNaoVazia(leitor, "Nome da pessoa: ");
                if (nomePessoa.equalsIgnoreCase("exit")) { programaRodando = false; break; }

                int idadePessoa = lerInt(leitor, "Idade da pessoa: ");

                String cpfFormatado = lerCpf(leitor);
                if (cpfFormatado.equalsIgnoreCase("exit")) { programaRodando = false; break; }

                String modeloCarro = lerStringNaoVazia(leitor, "Modelo do carro: ");
                if (modeloCarro.equalsIgnoreCase("exit")) { programaRodando = false; break; }
                
                int anoCarro = lerInt(leitor, "Ano do carro: ");

                String placaCarro = lerPlaca(leitor);
                if (placaCarro.equalsIgnoreCase("exit")) { programaRodando = false; break; }

                String renavamCarro = lerRenavam(leitor);
                if (renavamCarro.equalsIgnoreCase("exit")) { programaRodando = false; break; }
                    
                    // --- Verificação ---
                    System.out.println("\n--- Por favor, confirme os dados inserido ---");
                    System.out.println("Pessoa: " + nomePessoa + ", " + idadePessoa + " anos, CPF: " + cpfFormatado);
                    System.out.println("Carro: " + modeloCarro + ", ano " + anoCarro);
                    System.out.println("As informações estão corretas? (S para Sim / N para Não): ");
                    
                    String confirmacao = leitor.nextLine();
                    
                    if (confirmacao.equalsIgnoreCase("S")) {
                        // Se estiver correto, cria os objetos e armazena
                        pessoas[indice] = new Pessoa(indice + 1, nomePessoa, idadePessoa, cpfFormatado);
                        carros[indice] = new Carro(renavamCarro, placaCarro, modeloCarro, anoCarro);
                        dadosCorretos = true; // Sinaliza que os dados estão corretos para sair do loop
                        System.out.println("Cadastro salvo com sucesso!");
                    } else {
                        System.out.println("\nOK, vamos tentar novamente. Por favor, insira os dados mais uma vez.");
                        // Se estiver incorreto, o loop 'do-while' vai repetir
                    }
                } while (!dadosCorretos); // O loop continua enquanto 'dadosCorretos' for falso
                
                if (!programaRodando) {
                    break;
                }
                
                indice++; // Só avança para o próximo índice se o cadastro foi salvo com sucesso
            }
            
            // --- Exibição Final dos Dados ---
            System.out.println("\n=============================================");
            System.out.println("--- RELATÓRIO DE DADOS CADASTRADOS ---");
            System.out.println("=============================================");
            
            for (int i = 0; i < indice; i++){
                System.out.println("\n--- Cadastro ---");
                
                // Exibindo dados da pessoa
                System.out.println("Dados da Pessoa:");
                pessoas[i].exibirDados();
                if (pessoas[i].verificadorMaioridade()) {
                    System.out.println("Status: É maior de idade.");
                } else {
                    System.out.println("Status: É menor de idade.");
                }
                
                System.out.println("\nDados do Carro:");
                carros[i].exibirDados();
                if (carros[i].verificarCarroAntigo()){
                    System.out.println("Status: Este é um carro antigo.");
                } else {
                    System.out.println("Status: Este não é um carro antigo.");
                }
            }
            
            System.out.println("\nFim do Programa.");
        }
    }
}