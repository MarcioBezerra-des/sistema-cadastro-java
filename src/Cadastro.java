import java.util.ArrayList;
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
                return  String.format("%s.%s.%s-%s",
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

    private  static void adicionarCadastro(Scanner leitor, ArrayList<Pessoa> pessoas, ArrayList<Carro> carros) {
        
           System.out.println("\n-> Iniciando novo cadastro (ID: " + (pessoas.size() + 1) + ")");
        System.out.println("   (Digite 'exit' a qualquer momento para cancelar)");

        String nomePessoa = lerStringNaoVazia(leitor, "Nome da pessoa: ");
        if (nomePessoa.equalsIgnoreCase("exit")) { System.out.println("Cadastro cancelado."); return; }

        int idadePessoa = lerInt(leitor, "Idade da pessoa: ");

        String cpfFormatado = lerCpf(leitor);
        if (cpfFormatado.equalsIgnoreCase("exit")) { System.out.println("Cadastro cancelado."); return; }

        String modeloCarro = lerStringNaoVazia(leitor, "Modelo do carro: ");
        if (modeloCarro.equalsIgnoreCase("exit")) { System.out.println("Cadastro cancelado."); return; }
        
        int anoCarro = lerInt(leitor, "Ano do carro: ");

        String placaCarro = lerPlaca(leitor);
        if (placaCarro.equalsIgnoreCase("exit")) { System.out.println("Cadastro cancelado."); return; }

        String renavamCarro = lerRenavam(leitor);
        if (renavamCarro.equalsIgnoreCase("exit")) { System.out.println("Cadastro cancelado."); return; }

        System.out.println("\n--- Por favor, confirme os dados ---");
        System.out.println("  Pessoa: " + nomePessoa + ", " + idadePessoa + " anos, CPF: " + cpfFormatado);
        System.out.println("  Carro: " + modeloCarro + ", ano " + anoCarro + ", Placa: " + placaCarro);
        System.out.print("As informações estão corretas? (S para Sim / N para Não): ");
        String confirmacao = leitor.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            int novoId = pessoas.size() + 1;
            pessoas.add(new Pessoa(novoId, nomePessoa, idadePessoa, cpfFormatado));
            carros.add(new Carro(renavamCarro, placaCarro, modeloCarro, anoCarro));
            System.out.println("Cadastro salvo com sucesso!");
        } else {
            System.out.println("\nOk, cadastro descartado.");
        }
    }
            
            // --- Exibição Final dos Dados ---
            private static  void exibirRelatorio(ArrayList<Pessoa> pessoas, ArrayList<Carro> carros) {
                System.out.println("\n=============================================");
                System.out.println("--- RELATÓRIO DE DADOS CADASTRADOS ---");
                System.out.println("=============================================");

                if (!pessoas.isEmpty()) {
                    String[] cabecalhos = {"ID", "NOME", "IDADE", "CPF", "STATUS IDADE", "MODELO", "ANO", "PLACA", "RENANAM", "STATUS CARRO"};
                    int[] larguras = new int[cabecalhos.length];

                    for (int i = 0; i < cabecalhos.length; i++) {
                    larguras[i] = cabecalhos[i].length();
                    }

                    for (int i = 0; i < pessoas.size(); i++) {
                    Pessoa p = pessoas.get(i);
                    Carro c = carros.get(i);

                    larguras[0] = Math.max(larguras[0], String.valueOf(p.getId()).length());
                    larguras[1] = Math.max(larguras[1], p.getNome().length());
                    larguras[2] = Math.max(larguras[2], String.valueOf(p.getIdade()).length());
                    larguras[3] = Math.max(larguras[3], p.getCpf().length());
                    larguras[4] = Math.max(larguras[4], (p.verificarMaioridade() ? "Maior de Idade" : "Menor de Idade").length());
                    larguras[5] = Math.max(larguras[5], c.getModelo().length());
                    larguras[6] = Math.max(larguras[6], String.valueOf(c.getAno()).length());
                    larguras[7] = Math.max(larguras[7], c.getPlaca().length());
                    larguras[8] = Math.max(larguras[8], c.getRenavam().length());
                    larguras[9] = Math.max(larguras[9], (c.verificarCarroAntigo() ? "Antigo" : "Regular").length());
                    }

                    StringBuilder formato = new StringBuilder();
                    for (int largura : larguras) {
                        formato.append("| %-").append(largura).append("s ");
                }
                formato.append("|\n");

                StringBuilder linhaSeparadora = new StringBuilder();
                for (int largura : larguras) {
                    linhaSeparadora.append("+");
                    for (int i = 0; i < largura + 2; i++){
                        linhaSeparadora.append("-");
                    }
                }
                linhaSeparadora.append("+\n");

                System.out.print(linhaSeparadora);
                System.out.printf(formato.toString(), (Object[]) cabecalhos);
                System.out.print(linhaSeparadora);

                for (int i = 0; i < pessoas.size(); i++) {
                    Pessoa p = pessoas.get(i);
                    Carro c = carros.get(i);

                    String statusIdade = p.verificarMaioridade() ? "Maior de idade":"Menor de idade";
                    String statusCarro = c.verificarCarroAntigo() ? "Antigo" : "Regular";

                    System.out.printf(formato.toString(), 
                        p.getId(), p.getNome(), p.getIdade(), p.getCpf(), statusIdade, 
                        c.getModelo(), c.getAno(), c.getPlaca(), c.getRenavam(), statusCarro);
                    }
                    System.out.print(linhaSeparadora);
                } else {
                    System.err.println("Nenhum dado foi cadastrado.");
                }
            }

            // --- MÉTODO PRINCIPAL COM A ESTRUTURA DE MENU ---
            public static void main(String[] args) {
                try (Scanner leitor = new Scanner(System.in)) {
                    ArrayList<Pessoa> pessoas = new ArrayList<>(); 
                    ArrayList<Carro> carros = new ArrayList<>();
                    String escolha;

                    do {
                        System.out.println("\n--- MENU PRINCIPAL ---");
                        System.out.println("1 - Adicionar Novo Cadastro");
                        System.out.println("2 - Listar Todos os Cadastros");
                        System.out.println("3 - Sair");
                        System.out.println("Escolha uma opção: ");
                        escolha = leitor.nextLine();

                        switch (escolha) {
                            case "1":
                                adicionarCadastro(leitor, pessoas, carros);
                                break;
                            case "2":
                                exibirRelatorio(pessoas, carros);
                                break;
                            case "3":
                                System.out.println("Encerrando o programa...");
                                break;
                            default:
                            System.out.println("Erro: Opção inválida. Por favor, tente novamente.");   
                        }
                    } while (!escolha.equals("3"));
                }
                System.out.println("\nFim do programa.");
            }    
}