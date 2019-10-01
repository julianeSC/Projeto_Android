package juliane.s.c.cadastrocliente;

/**
 * Created by Aluno on 12/08/2017.
 */

public class PromocaoCadastro {
    private String key;
    private String nome;
    private String servico;
    private double valor;

    public PromocaoCadastro() {
    }

    public PromocaoCadastro(String key, String nome, String servico, double valor) {
        this.key = key;
        this.nome = nome;
        this.servico = servico;
        this.valor = valor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return
                "\nNome do responsável: " + nome +
                "\nServiço: " + servico +
                "\nValor: " + valor ;
    }
}
