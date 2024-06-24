import java.util.ArrayList;

public class Dia {
    int dia;
    boolean tem_medico;
    ArrayList<String> medicos;

    public Dia(int dia) {
        this.dia = dia;
        this.tem_medico = false;
        this.medicos = new ArrayList<>();
    }
}