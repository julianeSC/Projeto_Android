package juliane.s.c.cadastrocliente;

/**
 * Created by Aluno on 11/08/2017.
 */

public class Cliente {
    private String key;
    private String nome;
    private int telefone;
    private int data;
    private int horario;
    private String escolha;

    public Cliente() {
    }

    public Cliente(String key, String nome, int telefone, int data, int horario, String escolha) {
        this.key = key;
        this.nome = nome;
        this.telefone = telefone;
        this.data = data;
        this.horario = horario;
        this.escolha = escolha;
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public String getEscolha() {
        return escolha;
    }

    public void setEscolha(String escolha) {
        this.escolha = escolha;
    }

    @Override
    public String toString() {
        return
                "\nCliente: "+nome+
                "\nData: " + data +
                "\nHorário: "+horario+
                "\nOpção: " + escolha;
    }
}
