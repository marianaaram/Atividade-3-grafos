import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        //Variaveis
        Scanner scanner = new Scanner(System.in);
        int num;

        // Listas para armazenar médicos e feriados cadastrados
        Medicos medicos = new Medicos(null, 0);
        Feriados feriados = new Feriados(null, 0);
       


        //MENU
        do{
            System.out.println("======= Menu =======\n");
            System.out.println("1 - Cadastrar médico ");
            System.out.println("2 - Cadastrar feriado");
            System.out.println("3 - Exibir feriados");
            System.out.println("4 - Exibir médicos cadastrados");
            System.out.println("5 - Inserir disponibilidade para plantão");
            System.out.println("6 - Gerar Relatório de Plantão");
            System.out.println("0 - SAIR\n");
            System.out.print("Escolha uma opção: ");
    
            num = scanner.nextInt();

           
            
            switch (num) {

              case 1:
                LimparTela.limpar_console();

                System.out.println("Digite o nome do medico:");
                scanner.nextLine(); // Limpar o buffer pendente
                String nome = scanner.nextLine(); // Ler a entrada do usuário
                
                System.out.println("\nDigite a quantidade de dias disponiveis para fazer plantão:");
                int diasDisponiveis = scanner.nextInt();

                medicos.adicionarMedico(nome, diasDisponiveis);

                System.out.println("\nMedico adicionado com sucesso!");
                new java.util.Scanner(System.in).nextLine(); //Pausa ate o enter

                
                break;

              case 2:

                LimparTela.limpar_console();

                System.out.println("Digite o nome do feriado:");
                scanner.nextLine(); // Limpar o buffer pendente
                String nomeFeriado = scanner.nextLine(); // Ler a entrada do usuário
                
                System.out.println("\nDigite a duração do feriado:");
                int duracao = scanner.nextInt();

                feriados.adicionarFeriado(nomeFeriado, duracao);

                System.out.println("\nFeriado adicionado com sucesso!");
                new java.util.Scanner(System.in).nextLine(); //Pausa ate o enter
                
                
                break;

              case 3:

                LimparTela.limpar_console();
                feriados.exibirFeriados();

                System.out.println("\nDe enter para continuar");
                new java.util.Scanner(System.in).nextLine(); //Pausa ate o enter
                
               
                break;

              case 4:

                LimparTela.limpar_console();
                medicos.exibirMedicos();

                System.out.println("\nDe enter para continuar");
                new java.util.Scanner(System.in).nextLine(); //Pausa ate o enter
                
               break;

              case 5:
                LimparTela.limpar_console();

                System.out.println("Digite o nome do medico:");
                scanner.nextLine(); // Limpar o buffer pendente
                String nomeMedico = scanner.nextLine(); // Ler a entrada do usuário
                
                System.out.println("\nDigite o nome do feriado:");
                String  feriadoDisponivel = scanner.nextLine();


                medicos.disponibilidade(nomeMedico, feriadoDisponivel);

                System.out.println("\nDe enter para continuar");
                new java.util.Scanner(System.in).nextLine(); //Pausa ate o enter
                    
                break;

              case 6:
              
              
                break;

              


              default:
        
                break;
            }
           LimparTela.limpar_console();
          } while (num!=0);

        }






 
    }




