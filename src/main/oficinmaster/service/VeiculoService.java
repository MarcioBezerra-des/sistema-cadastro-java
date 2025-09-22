package main.oficinmaster.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import main.oficinmaster.model.Cliente;
import main.oficinmaster.model.Veiculo;

public class VeiculoService {
    private final List<Veiculo> veiculos = new ArrayList<>();
    private long proximoId = 1;

    private final ClienteService clienteService;

    public VeiculoService(ClienteService clienteService) {
        this.clienteService  = clienteService;
    }

    public Optional<Veiculo> cadastrarVeiculoo(long clienteId, String placa, String marca, String modelo, int ano) {
        Optional<Cliente> proprietarioOpt = clienteService.buscarPorId(clienteId);

        if (proprietarioOpt.isEmpty()) {
            System.out.println("Erro: Cliente com ID " + clienteId + " não encontrado.");
            return Optional.empty();
        }

        Cliente proprietario = proprietarioOpt.get();
        Veiculo novoVeiculo = new Veiculo(proximoId++, placa, marca, modelo, ano, proprietario);
        veiculos.add(novoVeiculo);
        return Optional.of(novoVeiculo);
    }

    public Optional<Veiculo> buscarPorId(long id) {
        return veiculos.stream().filter(v -> v.getId() == id).findFirst();
    }

    public List<Veiculo> listarTodos() {
        return new ArrayList<>(veiculos);
    }
}
