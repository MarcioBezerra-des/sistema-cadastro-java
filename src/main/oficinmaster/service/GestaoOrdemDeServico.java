package main.oficinmaster.service;

//---Importe dos modelos das clases---
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import main.oficinmaster.model.ItemServico;
import main.oficinmaster.model.OrdemDeServico;
import main.oficinmaster.model.StatusOrdem;
import main.oficinmaster.model.Veiculo;

//---Abre uma nova Ordem de Serviço para um veículo com um problema relatado---
public class GestaoOrdemDeServico {

    private  final List<OrdemDeServico> bancoDeDadosSimulado = new ArrayList<>();
    private long proximoId = 1;

    public OrdemDeServico abrirNovaOS(Veiculo veiculo, String problemaRelatado) {
        OrdemDeServico novaOS = new OrdemDeServico(proximoId, veiculo, problemaRelatado);
        bancoDeDadosSimulado.add(novaOS);
        proximoId++;
        return novaOS;
    }

    
    public Optional<OrdemDeServico> buscarPorId(long osId) {
            return this.bancoDeDadosSimulado.stream()
                    .filter(os -> os.getId() == osId)
                    .findFirst();
        }
        
    //Adiciona um item (peça ou serviço) a uma Ordem de Serviço existente.
    public void adicionarItem(long osId, ItemServico item) {
        Optional<OrdemDeServico> osOptional = buscarPorId(osId);

        if (osOptional.isPresent()) {
            OrdemDeServico os = osOptional.get();
            os.adicionarItem(item);
        } else{
            System.out.println("Erro: Ordem de Serviço com ID " + osId + " não encontrada.");
        }
    }

    //Atualiza o status de uma Ordem de Serviço.
    public void atualizarStatus(long osId, StatusOrdem novoStatus) {
        Optional<OrdemDeServico> osOptional = buscarPorId(osId);

        osOptional.ifPresent(os -> {
            os.setStatus(novoStatus);
        });
    }
    public List<OrdemDeServico> listarTodas() {
        return new ArrayList<>(this.bancoDeDadosSimulado);
    }
}
