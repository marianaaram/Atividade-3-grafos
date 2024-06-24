import java.util.ArrayList;

public class Feriado {

    ArrayList<Dia> dias;
    String nome;

    public Feriado(String nome) {
        this.nome = nome;
        this.dias = new ArrayList<>();
    }

    public void add_dias(int quantidade_dias) {
        for (int i = 1; i <= quantidade_dias; i++) {
            dias.add(new Dia(i));
        }
    }

    public void medico_ferias_trabalhadas(int dia_trabalho, String nome_medico) {
        Dia dia = dias.get(dia_trabalho - 1);
        dia.tem_medico = true;
        dia.medicos.add(nome_medico);
    }

    public boolean confere_medico_trabalha_naquele_feriado(String nome_medico) {
        for (Dia dia : dias) {
            if (dia.medicos.contains(nome_medico)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getdias() {
        ArrayList<Integer> Lista_dias = new ArrayList<>();
        for (Dia dia : dias) {
            Lista_dias.add(dia.dia); 
        }
        return Lista_dias;
    }

    public boolean confere_feriado_tem_medico() {
        for (Dia dia : dias) {
            if (!dia.tem_medico) {
                return false;
            }
        }
        return true;
    }

    public void mostrar_dias_com_medico() {
        boolean algum_medico = false;
        for (Dia dia : dias) {
            if (dia.tem_medico) {
                algum_medico = true;
                break;
            }
        }
        if (algum_medico) {
            System.out.println("Dia(s) com médico trabalhando no " + this.nome + ":");
            for (Dia dia : dias) {
                if (dia.tem_medico) {
                    System.out.println("Dia " + dia.dia);
                }
            }
        } else {
            System.out.println("Nenhum médico está trabalhando no " + this.nome + " ainda.");
        }
        System.out.println();
    }

    public String getNomeLower() {
        return nome.toLowerCase();
    }

    // Método para verificar se um feriado tem o mesmo nome (independentemente de maiúsculas e minúsculas)
    public boolean temMesmoNome(String nome) {
        return getNomeLower().equals(nome.toLowerCase());
    }
}