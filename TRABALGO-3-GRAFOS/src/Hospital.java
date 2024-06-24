import java.util.HashMap;
import java.util.Scanner;

public class Hospital {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Feriado> feriados = new HashMap<>();
        HashMap<String, Integer> maximoDiasTrabalhoPorMedico = new HashMap<>();
        HashMap<String, Integer> diasDisponiveisPorMedico = new HashMap<>();

        System.out.println("Digite o numero de feriados a serem organizados: ");
        int quantidade_feriados = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < quantidade_feriados; i++) {
            System.out.println("\nDigite o nome do " + (i + 1) + "° feriado: ");
            String nome_feriado = scanner.nextLine();

            System.out.println("\nDigite a quantidade de dias do feriado " + nome_feriado + ": ");
            int dias_feriado = scanner.nextInt();
            scanner.nextLine();

            Feriado feriado = new Feriado(nome_feriado);
            feriado.add_dias(dias_feriado);
            feriados.put(feriado.getNomeLower(), feriado);
        }

        LimparTela.limpar_console();

        System.out.println("Digite o número de médicos:");
        int numero_medicos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numero_medicos; i++) {
            System.out.println("\nDigite o nome do médico: ");
            String nome_medico = scanner.nextLine();

            System.out.println("\nDigite a quantidade total de dias de férias que " + nome_medico + " pode trabalhar: ");
            int maximo_dias_trabalho_medico = scanner.nextInt();
            scanner.nextLine();

            maximoDiasTrabalhoPorMedico.put(nome_medico.toLowerCase(), maximo_dias_trabalho_medico);
            diasDisponiveisPorMedico.put(nome_medico.toLowerCase(), maximo_dias_trabalho_medico);
        }

        LimparTela.limpar_console();

        while (true) {
            System.out.println("Digite o nome do médico que deseja adicionar ou 0 para ver se existem médicos suficientes por cada dia de feriado:");
            String medico = scanner.nextLine();

            LimparTela.limpar_console();

            if (medico.equals("0")) {
                break;
            } else {
                int total_dias_trabalho = 0;
                int maximo_dias_trabalho_medico = maximoDiasTrabalhoPorMedico.getOrDefault(medico.toLowerCase(), 0);
                int dias_disponiveis_medico = diasDisponiveisPorMedico.getOrDefault(medico.toLowerCase(), 0);
                while (true) {
                    System.out.println("Digite o feriado que " + medico + " irá trabalhar ou 0 para voltar ao cadastro de médico");
                    System.out.println("\nFeriados disponíveis:");
                    for (String nomeFeriado : feriados.keySet()) {
                        System.out.println(nomeFeriado);
                    }
                    System.out.println("");
                    String nome_feriado = scanner.nextLine();

                    if (nome_feriado.equals("0")) {
                        LimparTela.limpar_console();
                        break;
                    } else {
                        Feriado feriado = feriados.get(nome_feriado.toLowerCase());
                        if (feriado == null) {
                            System.out.println("Feriado inválido.");
                            continue;
                        }

                        LimparTela.limpar_console();
                        System.out.println("Dias restantes que " + medico + " pode ser escalado(a) para trabalhar durante as férias: " + dias_disponiveis_medico);
                        feriado.mostrar_dias_com_medico();
                        System.out.print("Digite qual dia do feriado que " + medico + " irá trabalhar:\n");
                        System.out.print("Dias para trabalho: " + feriado.getdias() + "\n");
                        
                        System.out.println("");
                        int dia_trabalho = scanner.nextInt();
                        scanner.nextLine();

                        LimparTela.limpar_console();

                        if (dia_trabalho < 1 || dia_trabalho > feriado.dias.size()) {
                            System.out.println("Dia inválido.");
                        } else {
                            total_dias_trabalho++;
                            dias_disponiveis_medico--;
                            if (total_dias_trabalho > maximo_dias_trabalho_medico) {
                                total_dias_trabalho--;
                                dias_disponiveis_medico++;
                                System.out.println("Os médicos não podem trabalhar mais que " + maximo_dias_trabalho_medico + " dias de férias.");
                                System.out.println("");
                            } else if (dias_disponiveis_medico < 0) {
                                total_dias_trabalho--;
                                dias_disponiveis_medico++;
                                System.out.println("O médico " + medico + " não tem mais dias disponíveis de férias.");
                                System.out.println("");
                            } else if (feriado.confere_medico_trabalha_naquele_feriado(medico)) {
                                System.out.println("Cada médico só pode trabalhar 01 (um) dia por feriado. Adicione " + medico + " para trabalhar em outro feriado");
                                System.out.println("");
                                total_dias_trabalho--;
                                dias_disponiveis_medico++;
                            } else {
                                feriado.medico_ferias_trabalhadas(dia_trabalho, medico);
                            }
                        }
                    }
                }
                diasDisponiveisPorMedico.put(medico.toLowerCase(), dias_disponiveis_medico);
            }
        }

        boolean medico_suficiente = false;

        for (Feriado feriado : feriados.values()) {
            if (!feriado.confere_feriado_tem_medico()) {
                medico_suficiente = true;
                break;
            }
        }

        if (medico_suficiente) {
            System.out.println("Não existe médico suficiente para cada dia de feriado! Necessário reorganização!");
            System.out.println("");
            System.out.println("Dias e médicos trabalhando em cada feriado:");
            for (Feriado feriado : feriados.values()) {
                System.out.println("Feriado: " + feriado.getNomeLower());
                for (Dia dia : feriado.getDias()) {
                      System.out.println("Dia " + dia.dia + ": " + dia.medicos);
              }
            }
        } else {
            System.out.println("Existe pelo menos um médico de plantão em cada dia de cada feriado!");
            System.out.println("");
            System.out.println("Dias e médicos trabalhando em cada feriado:");
            for (Feriado feriado : feriados.values()) {
                System.out.println("Feriado: " + feriado.getNomeLower());
                for (Dia dia : feriado.getDias()) {
                  if (dia.tem_medico) {
                      System.out.println("Dia " + dia.dia + ": " + dia.medicos);
                  }
              }
            }
        }

        scanner.close();
    }
}
