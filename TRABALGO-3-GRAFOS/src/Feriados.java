import java.util.*;

public class Feriados {

    private String nome;
    private int duracao; // Duração em dias (CAPACIDADE)

      // Lista estática para armazenar todos os médicos cadastrados
      private static List<Feriados> listaFeriados = new ArrayList<>();

    // Construtor
    public Feriados(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    // Método estático para adicionar um feriado à lista
    public static void adicionarFeriado(String nome, int duracao) {
        Feriados feriado = new Feriados(nome, duracao);
        listaFeriados.add(feriado);
    }

     // Método estático para acessar a lista de feriados cadastrados
     public static List<Feriados> getListaFeriados() {
        return listaFeriados;
    }


     // Método para exibir os feriados cadastrados
     void exibirFeriados() {
        List<Feriados> feriados = Feriados.getListaFeriados();
        if (feriados.isEmpty()) {
            System.out.println("Nenhum feriado cadastrado.\n");
        } else {
            System.out.println("======= Feriados Cadastrados =======");
            for (int i = 0; i < feriados.size(); i++) {
                Feriados feriado = feriados.get(i);
                System.out.println("\nFeriado " + (i+1) + ":");
                System.out.println("\nNome: " + feriado.getNome());
                System.out.println("Dias de duração do feriado: " + feriado.getDuracao());
                System.out.println();
            }
        }
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
