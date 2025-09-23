package main.oficinmaster;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import main.oficinmaster.model.Cliente;
import main.oficinmaster.model.OrdemDeServico;
import main.oficinmaster.model.Veiculo;
import main.oficinmaster.service.ClienteService;
import main.oficinmaster.service.GestaoOrdemDeServico;
import main.oficinmaster.service.VeiculoService;

public class OficinaMasterApplication {

    private static final ClienteService clienteService = new ClienteService();
    private static final VeiculoService veiculoService = new VeiculoService(clienteService);
    @SuppressWarnings("unused")
    private static final GestaoOrdemDeServico osService = new GestaoOrdemDeServico();

    private static final Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        try (leitor) {
            String escolha;
            do {
                System.out.println("\n--- OFICINA MASTER ---");
                System.out.println("1 - Cadastrar Cliente");
                System.out.println("2 - Listar Clientes");
                System.out.println("3 - Cadastrar Veículo");
                System.out.println("4 - Abrir Ordem de Serviço");
                System.out.println("5 - Listar Ordens de Serviço");
                System.out.println("6 - Sair");
                System.out.println("Escolha uma opção: ");
                escolha = leitor.nextLine();
                
                switch (escolha) {
                    case "1" -> cadastrarClienteUI();
                    case "2" -> listarClienteUI();
                    case "3" -> cadastrarVeiculoUI();
                    case "4" -> abrirOrdemDeServicoUI();
                    case "5" -> listarOrdemDeServicoUI();
                    case "6" -> System.out.println("Encerrando o programa...");
                    default -> System.out.println("Erro: Opção inválida.");
                }
            } while (!escolha.equals("6"));
        }
        System.out.println("\n Fim do programa.");
    }

    private static void cadastrarClienteUI() {
        System.out.println("\n--- Cadastro de Novo Cliente ---");
        System.out.println("Nome completo: ");
        String nome = leitor.nextLine();
        System.out.println("CPF (formato xxx.xxx.xxx-xx): ");
        String cpf = leitor.nextLine();
        System.out.println("Telefone: ");
        String telefone = leitor.nextLine();
        System.out.println("Email: ");
        String email = leitor.nextLine();

        Cliente clienteCadastrado = clienteService.cadastrarCliente(nome, cpf, telefone, email);
        System.out.println("Cliente cadastrado com sucesso! ID: " + clienteCadastrado.getId());
    }
    
    private static void listarClienteUI() {
        System.out.println("\n--- Clientes Cadastrados ---");
        List<Cliente> clientes = clienteService.listarTodos();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

       String[] cabecalhos = {"ID", "NOME COMPLETO", "CPF", "TELEFONE", "EMAIL"};
    int[] larguras = new int[cabecalhos.length];
    
    // Inicia as larguras com o tamanho dos próprios títulos
    for (int i = 0; i < cabecalhos.length; i++) {
        larguras[i] = cabecalhos[i].length();
    }

    // Passa pelos dados para encontrar o maior valor para cada coluna
    for (Cliente cliente : clientes) {
        larguras[0] = Math.max(larguras[0], String.valueOf(cliente.getId()).length());
        larguras[1] = Math.max(larguras[1], cliente.getNomeCompleto().length());
        larguras[2] = Math.max(larguras[2], cliente.getCpf().length());
        larguras[3] = Math.max(larguras[3], cliente.getTelefone().length());
        larguras[4] = Math.max(larguras[4], cliente.getEmail().length());
    }
    
    // PASSO 2: IMPRIMIR A TABELA
    imprimirLinhaSeparadora(larguras);
    imprimirCabecalho(cabecalhos, larguras);
    imprimirLinhaSeparadora(larguras);

    // Imprime as linhas de dados
    for (Cliente cliente : clientes) {
        System.out.printf("| %-" + larguras[0] + "s | %-" + larguras[1] + "s | %-" + larguras[2] + "s | %-" + larguras[3] + "s | %-" + larguras[4] + "s |\n",
            cliente.getId(),
            cliente.getNomeCompleto(),
            cliente.getCpf(),
            cliente.getTelefone(),
            cliente.getEmail()
        );
    }
    imprimirLinhaSeparadora(larguras);
}

    private static void cadastrarVeiculoUI() {
        System.out.println("\n--- Cadastro de Novo Veículo ---");
        System.out.println("Digite o ID do cliente proprietário: ");
        long clienteId = Long.parseLong(leitor.nextLine());

        System.out.println("Placa (formato Mercosul): ");
        String placa = leitor.nextLine();
        System.out.println("Marca: ");
        String marca = leitor.nextLine();
        System.out.println("Modelo: ");
        String modelo = leitor.nextLine();
        System.out.println("Ano de Fabricação: ");
        int ano = Integer.parseInt(leitor.nextLine());

        Optional<Veiculo> veiculoCadastradoOpt = veiculoService.cadastrarVeiculoo(clienteId, placa, marca, modelo, ano);
        veiculoCadastradoOpt.ifPresent(
            veiculo -> System.out.println("Veículo cadastrado com sucesso para o cliente " + veiculo.getProprietario().getNomeCompleto())
        );
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private static void abrirOrdemDeServicoUI() {
        System.out.println("\n Abertura de Ordem de Serviço ---");
        System.out.println("Digite o ID do veículo: ");
        long veiculoId = Long.parseLong(leitor.nextLine());

        Optional<Veiculo> veiculoOpt = veiculoService.buscarPorId(veiculoId);

        if (veiculoOpt.isEmpty()) {
            System.out.println("Erro: Veículo com ID " + veiculoId + " não encontrado.");
            return;
        }
        
        System.out.println("Descreva o problema relatado pelo cliente: ");
        String problema = leitor.nextLine();

        OrdemDeServico novaOS = osService.abrirNovaOS(veiculoOpt.get(), problema);
        System.out.println("Ordem de Serviço nº " + novaOS.getId() + " aberta com sucesso para o veículo de placa" + novaOS.getVeiculo().getPlaca());
    }

    private static void listarOrdemDeServicoUI() {
        System.out.println("\n--- Relatório de Ordens de Serviço ---");
    List<OrdemDeServico> todasAsOS = osService.listarTodas();

    if (todasAsOS.isEmpty()) {
        System.out.println("Nenhuma Ordem de Serviço cadastrada.");
        return;
    }

    // PASSO 1: CALCULAR LARGURAS
    String[] cabecalhos = {"OS ID", "CLIENTE", "VEÍCULO", "PLACA", "STATUS", "DATA ABERTURA"};
    int[] larguras = new int[cabecalhos.length];

    for (int i = 0; i < cabecalhos.length; i++) {
        larguras[i] = cabecalhos[i].length();
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    for (OrdemDeServico os : todasAsOS) {
        larguras[0] = Math.max(larguras[0], String.valueOf(os.getId()).length());
        larguras[1] = Math.max(larguras[1], os.getVeiculo().getProprietario().getNomeCompleto().length());
        larguras[2] = Math.max(larguras[2], os.getVeiculo().getModelo().length());
        larguras[3] = Math.max(larguras[3], os.getVeiculo().getPlaca().length());
        larguras[4] = Math.max(larguras[4], os.getStatus().toString().length());
        larguras[5] = Math.max(larguras[5], os.getDataAbertura().format(formatter).length());
    }

    // PASSO 2: IMPRIMIR A TABELA
    imprimirLinhaSeparadora(larguras);
    imprimirCabecalho(cabecalhos, larguras);
    imprimirLinhaSeparadora(larguras);

    // Imprime as linhas de dados
    for (OrdemDeServico os : todasAsOS) {
        System.out.printf("| %-" + larguras[0] + "s | %-" + larguras[1] + "s | %-" + larguras[2] + "s | %-" + larguras[3] + "s | %-" + larguras[4] + "s | %-" + larguras[5] + "s |\n",
            os.getId(),
            os.getVeiculo().getProprietario().getNomeCompleto(),
            os.getVeiculo().getModelo(),
            os.getVeiculo().getPlaca(),
            os.getStatus(),
            os.getDataAbertura().format(formatter)
        );
    }
    imprimirLinhaSeparadora(larguras);
}

    @SuppressWarnings("unused")
    private static void imprimirTabela(String[] cabecalhos, int[] larguras, List<Cliente> clientes, List<OrdemDeServico> ordensDeServico) {
        // Implemente a lógica de impressão de tabela conforme necessário
    }

    private static void imprimirLinhaSeparadora(int[] larguras) {
        for (int largura : larguras) {
            System.out.print("+-" + "-".repeat(largura) + "-");
        }
        System.out.println("+");
    }

    private static void imprimirCabecalho(String[] cabecalhos, int[] larguras) {
        for (int i = 0; i < cabecalhos.length; i++) {
            System.out.printf("| %-" + larguras[i] + "s ", cabecalhos[i]);
        }
        System.out.println("|");
    }
}
