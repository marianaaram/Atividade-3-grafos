import java.util.*;

public class Feriados {

    private String nome;
    private int duracao; // Duração em dias (CAPACIDADE)

    // Construtor
    public Feriados(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    // Método para obter o nome do feriado
    public String getNome() {
        return nome;
    }

    // Método para obter a duração do feriado
    public int getDuracao() {
        return duracao;
    }

    // Método para definir um novo nome para o feriado
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método para definir uma nova duração para o feriado
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
