import java.util.*;



public class Algoritmo {

     // Implementação do algoritmo de Edmonds-Karp
     public static boolean algoritmoEdmondsKarp(int[][] capacidade, int numeroNos, int origem, int destino) {
        int[][] redeResidual = new int[numeroNos][numeroNos];
        for (int i = 0; i < numeroNos; i++) {
            for (int j = 0; j < numeroNos; j++) {
                redeResidual[i][j] = capacidade[i][j];
            }
        }

        int[] pai = new int[numeroNos]; // Array para armazenar caminhos
        int fluxoMaximo = 0;

        while (bfs(redeResidual, origem, destino, pai)) {
            int fluxoCaminho = Integer.MAX_VALUE;
            for (int v = destino; v != origem; v = pai[v]) {
                int u = pai[v];
                fluxoCaminho = Math.min(fluxoCaminho, redeResidual[u][v]);
            }

            for (int v = destino; v != origem; v = pai[v]) {
                int u = pai[v];
                redeResidual[u][v] -= fluxoCaminho;
                redeResidual[v][u] += fluxoCaminho;
            }

            fluxoMaximo += fluxoCaminho;
        }

        // Se o fluxo máximo for igual à soma das capacidades dos nós destino, então foi possível atribuir médicos a todos os feriados.
        return fluxoMaximo == somaCapacidadesNosDestino(capacidade, destino);
    }

    // Busca em largura (BFS) para encontrar caminho aumentante
    private static boolean bfs(int[][] redeResidual, int origem, int destino, int[] pai) {
        int numeroNos = redeResidual.length;
        boolean[] visitado = new boolean[numeroNos];
        Arrays.fill(visitado, false);

        Queue<Integer> fila = new LinkedList<>();
        fila.add(origem);
        visitado[origem] = true;
        pai[origem] = -1;

        while (!fila.isEmpty()) {
            int u = fila.poll();

            for (int v = 0; v < numeroNos; v++) {
                if (!visitado[v] && redeResidual[u][v] > 0) {
                    if (v == destino) {
                        pai[v] = u;
                        return true;
                    }
                    fila.add(v);
                    pai[v] = u;
                    visitado[v] = true;
                }
            }
        }

        return false;
    }

    // Calcula a soma das capacidades dos nós destino
    private static int somaCapacidadesNosDestino(int[][] capacidade, int destino) {
        int soma = 0;
        int numeroNos = capacidade.length;
        for (int i = 0; i < numeroNos; i++) {
            soma += capacidade[i][destino];
        }
        return soma;
    }

    // Método para construir o grafo e resolver o problema
    public static boolean resolverAtribuicaoMedicosFeriados(int numeroMedicos, int numeroFeriados, int[][] disponibilidadeMedicos, int[][] diasFeriados, int c) {
        int origem = 0;
        int destino = numeroMedicos + numeroFeriados + 1;
        int numeroNos = numeroMedicos + numeroFeriados + 2;

        int[][] capacidade = new int[numeroNos][numeroNos];

        // Nó de origem para médicos
        for (int i = 1; i <= numeroMedicos; i++) {
            capacidade[origem][i] = c;
        }

        // Médicos para feriados
        for (int i = 1; i <= numeroMedicos; i++) {
            for (int j = 1; j <= numeroFeriados; j++) {
                if (disponibilidadeMedicos[i-1][j-1] == 1) {
                    capacidade[i][numeroMedicos + j] = 1;
                }
            }
        }

        // Feriados para destino
        for (int j = 1; j <= numeroFeriados; j++) {
            capacidade[numeroMedicos + j][destino] = 1;
        }

        return algoritmoEdmondsKarp(capacidade, numeroNos, origem, destino);
    }
}


