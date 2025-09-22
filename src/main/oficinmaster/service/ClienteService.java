package main.oficinmaster.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import main.oficinmaster.model.Cliente;

public class ClienteService {

    private  final List<Cliente> clientes = new ArrayList<>();
    private long proximoId = 1;

    public Cliente cadastrarCliente(String nome, String cpf, String telefone, String email) {
        Cliente novoCliente = new Cliente(proximoId++, nome, cpf, telefone, email);
        clientes.add(novoCliente);
        return novoCliente;
    }

    public Optional<Cliente> buscarPorId (long id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst();
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }
}
