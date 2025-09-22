package main.oficinmaster.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdemDeServico {

    private final long id;
    private final Veiculo veiculo;
    private final LocalDateTime dataAbertura;
    private LocalDateTime dataConclusao;

    private String problemaRelatadoCliente;
    private String diagnosticoTecnico;

    private StatusOrdem status;
    private final List<ItemServico> itens;

    public OrdemDeServico(long id, Veiculo veiculo, String problemaRelatadoCliente) {
        if (veiculo == null) {
            throw new IllegalArgumentException("A Ordem de Serviço deve estar associada a um veículo.");
        }
    
        this.id = id;
        this.veiculo = veiculo;
        this.problemaRelatadoCliente = problemaRelatadoCliente;

        this.dataAbertura = LocalDateTime.now();
        this.status = StatusOrdem.ABERTA;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemServico item) {
        this.itens.add(item);
    }

    public double getValorTotal() {
        double total = 0.0;
        for (ItemServico item : this.itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public long getId() {return id;}
    public Veiculo getVeiculo() {return veiculo;}
    public LocalDateTime getDataAbertura() {return dataAbertura;}
    public LocalDateTime getDataConclusao() {return dataConclusao;}
    public String getProblemaRelatadoCliente() {return problemaRelatadoCliente;}
    public String getDiagnosticoTecnico() {return diagnosticoTecnico;}
    public StatusOrdem getStatus() {return status;}
    public List<ItemServico> getItens() {return new ArrayList<>(itens);}
 
    public void setDiagnosticoTecnico(String diagnosticoTecnico) {
        this.diagnosticoTecnico = diagnosticoTecnico;
    }

    public  void setStatus(StatusOrdem status) {
        this.status = status;
        if (status == StatusOrdem.CONCLUIDA) {
            this.dataConclusao = LocalDateTime.now();
        }
    }
}