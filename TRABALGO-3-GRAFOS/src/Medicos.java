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



    //Verificar disponibilidade 
    void disponibilidade(String nomeMedico, String feriado) {
        List<Medicos> medicos = Medicos.getListaMedicos();
        List<Feriados> feriados = Feriados.getListaFeriados();

        nomeMedico = nomeMedico.trim().toLowerCase(); 
        feriado = feriado.trim().toLowerCase();

        int diasDoMedico = -1;
        int diasFeriado = -1;
        int aux1 = 0;
        int aux2 = 0;
       
        for (int i = 0; i < medicos.size(); i++) {
            Medicos medico = medicos.get(i);
            if(nomeMedico.equals(medico.nome.trim().toLowerCase())){
                 diasDoMedico = medico.getDiasDisponiveis();
                 aux1 = 1;
                break;
            }  
        }
        if(aux1 == 0){
            System.out.println("\nMédico não encontrado!");
            return; 
        }
       //Encontrar feriado
        for (int i = 0; i < feriados.size(); i++) {
            Feriados feriado2 = feriados.get(i);
            if(feriado.equals(feriado2.getNome().trim().toLowerCase())){
                diasFeriado = feriado2.getDuracao();
                aux2 =1;
                break;
            }   
        }
        if(aux2 == 0){
            System.out.println("\nFeriado não encontrado!"); 
            return;
        }

        //Resposta
        if (diasDoMedico < diasFeriado){
            System.out.println("\nO médico não pode fazer plantão nesse feriado pois tem a duração maior do que seus dias disponiveis!");
        }
        else{
            System.out.println("\nO médico pode trabalhar nesse feriado");
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
