package main.oficinmaster;

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
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente.toString());
            }
        }
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
        for (OrdemDeServico os : todasAsOS) {
            System.out.println("--------------------");
            System.out.println("OS ID: " + os.getId() + " | Status: " + os.getStatus());
            System.out.println("Cliente: " + os.getVeiculo().getProprietario().getNomeCompleto());
            System.out.println("Veículo: " + os.getVeiculo().getModelo() + " | Placa: " + os.getVeiculo().getPlaca());
            System.out.println("Problema Relatado: " + os.getProblemaRelatadoCliente());
        }
    }
}
