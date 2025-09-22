package main.oficinmaster.model;

public class ItemServico {
    private final String descricao;
    private final int quantidade;
    private final double precoUnitario;
    private final boolean isPeca;

    public ItemServico(String descricao, int quantidade, double precoUnitario, boolean isPeca) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.isPeca = isPeca;
    }

    public String getDescricao() {return descricao;}
    public int  getQuantidade() {return quantidade;}
    public double  getPrecoUnitario() {return precoUnitario;}
    public boolean  getIsPeca() {return isPeca;}

    public double getSubtotal() {
        return quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        String tipo = isPeca ? "Peça":"Serviço";
        return String.format("%s: %s (Qtd: %d, Preço Un: R$%.2f, Subtotal: R$%.2f)",tipo, descricao, quantidade, precoUnitario, getSubtotal());
    }
}
