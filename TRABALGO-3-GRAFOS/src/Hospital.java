import java.util.HashMap;
import java.util.Scanner;

public class Hospital {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Feriado> feriados = new HashMap<>();

        System.out.println("Digite o numero de feriados a serem organizados: ");
        int quantidade_feriados = scanner.nextInt();

        System.out.println("\nDigite a quantidade total de dias de férias que um médico pode trabalhar: ");
        int maximo_dias_trabalho = scanner.nextInt();

        scanner.nextLine(); // Consume newline

        for (int i = 0; i < quantidade_feriados; i++) {
            System.out.println("\nDigite o nome do " + (i + 1) + "° feriado: ");
            String nome_feriado = scanner.nextLine();

            System.out.println("\nDigite a quantidade de dias do feriado " + nome_feriado + ": ");
            int dias_feriado = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Feriado feriado = new Feriado(nome_feriado);
            feriado.add_dias(dias_feriado);
            feriados.put(feriado.getNomeLower(), feriado);
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
                        feriado.mostrar_dias_com_medico();
                    
                        System.out.print("Digite qual dia do feriado que " + medico + " irá trabalhar:\n");
                        System.out.print("Dias para trabalho: " + feriado.getdias() + "\n");
                        System.out.println("");
                        int dia_trabalho = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        LimparTela.limpar_console();

                        if (dia_trabalho < 1 || dia_trabalho > feriado.dias.size()) {
                            System.out.println("Dia inválido.");
                        } else {
                            total_dias_trabalho++;
                            if (total_dias_trabalho > maximo_dias_trabalho) {
                                total_dias_trabalho--;
                                System.out.println("Os médicos não podem trabalhar mas que " + maximo_dias_trabalho + " dias de férias.");
                                System.out.println("");
                            } else if (feriado.confere_medico_trabalha_naquele_feriado(medico)) {
                                System.out.println("Cada médico só pode trabalhar 01 (um) dia por feriado. Adicione " + medico + " para trabalhar em outro feriado");
                                System.out.println("");
                            } else {
                                feriado.medico_ferias_trabalhadas(dia_trabalho, medico);
                            }
                        }
                    }
                }
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