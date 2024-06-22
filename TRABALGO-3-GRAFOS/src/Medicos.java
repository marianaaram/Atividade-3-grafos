import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Medicos {
    
    private String nome;
    private int diasDisponiveis; // Quantidade de dias disponíveis para plantão (FLUXO)

    // Lista estática para armazenar todos os médicos cadastrados
    private static List<Medicos> listaMedicos = new ArrayList<>();

    // Construtor
    public Medicos(String nome, int diasDisponiveis) {
        this.nome = nome;
        this.diasDisponiveis = diasDisponiveis;
    }

    // Método estático para adicionar um médico à lista
    public static void adicionarMedico(String nome, int diasDisponiveis) {
        Medicos medico = new Medicos(nome, diasDisponiveis);
        listaMedicos.add(medico);
    }

    // Método estático para acessar a lista de médicos cadastrados
    public static List<Medicos> getListaMedicos() {
        return listaMedicos;
    }

    // Método para exibir os médicos cadastrados
    void exibirMedicos() {
        List<Medicos> medicos = Medicos.getListaMedicos();
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.\n");
        } else {
            System.out.println("======= Médicos Cadastrados =======");
            for (int i = 0; i < medicos.size(); i++) {
                Medicos medico = medicos.get(i);
                System.out.println("\nMédico " + (i+1) + ":");
                System.out.println("\nNome: " + medico.getNome());
                System.out.println("Dias disponíveis para plantão: " + medico.getDiasDisponiveis());
                System.out.println();
            }
        }
    }

    // Método para obter o nome do médico
    public String getNome() {
        return nome;
    }

    // Método para obter a quantidade de dias disponíveis para plantão
    public int getDiasDisponiveis() {
        return diasDisponiveis;
    }

    // Método para definir a quantidade de dias disponíveis para plantão
    public void setDiasDisponiveis(int diasDisponiveis) {
        this.diasDisponiveis = diasDisponiveis;
    }

    // Método para verificar se o médico está disponível para mais um plantão
    public boolean isDisponivel() {
        return diasDisponiveis > 0;
    }

    // Método para designar um plantão ao médico (decrementa a quantidade de dias disponíveis)
    public boolean designarPlantao() {
        if (diasDisponiveis > 0) {
            diasDisponiveis--;
            return true;
        } else {
            return false;
        }
    }
   

}
