import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue; // Auxiliará na criação da fila
import java.util.Stack; // Auxiliará na criação da pilha

public class App {
    
    private List<String> vertices;
    
    private int[][] matrizAdjacencia;
    
    public App(int numVertices) {
        vertices = new ArrayList<>(numVertices);
        matrizAdjacencia = new int[numVertices][numVertices];
    }
    
    public void adicionarVertice(String vertice) {
        vertices.add(vertice);
    }
    
    public void adicionarAresta(int indiceVertice1, int indiceVertice2) {
        matrizAdjacencia[indiceVertice1][indiceVertice2] = 1;
        matrizAdjacencia[indiceVertice2][indiceVertice1] = 1;
    }
    
    public int menosVizinhos() {
        
        int minEstado = -1;
        int minVizinhos = Integer.MAX_VALUE;
        for (int i = 0; i < this.matrizAdjacencia.length; i++) {
            int vizinhos = 0;
            for (int j = 0; j < this.matrizAdjacencia[i].length; j++) {
                if (this.matrizAdjacencia[i][j] == 1) {
                    vizinhos++;
                }
            }
            if (vizinhos < minVizinhos) {
                minVizinhos = vizinhos;
                minEstado = i;
            }
        }
        
        return minEstado;
        
    }
    
    public int maisVizinhos() {
        
        // Encontra o estado com o maior número de estados vizinhos
        int maxEstado = -1;
        int maxVizinhos = Integer.MIN_VALUE;
        for (int i = 0; i < this.matrizAdjacencia.length; i++) {
            int vizinhos = 0;
            for (int j = 0; j < this.matrizAdjacencia[i].length; j++) {
                if (this.matrizAdjacencia[i][j] == 1) {
                    vizinhos++;
                }
            }
            if (vizinhos > maxVizinhos) {
                maxVizinhos = vizinhos;
                maxEstado = i;
            }
        }
        
        return maxEstado;

    }
    
    public void menorCaminho(int startState, int endState) {
        
        Queue<Integer> queue = new LinkedList<>();
        
        int[] path = new int[this.matrizAdjacencia.length];
        
        boolean[] visited = new boolean[this.matrizAdjacencia.length];
        visited[startState] = true;
        
        queue.add(startState);        
        while (!queue.isEmpty()) {
            int state = queue.remove();
            
            for (int i = 0; i < this.matrizAdjacencia[state].length; i++) {
                if (this.matrizAdjacencia[state][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    path[i] = state;
                    
                    if (i == endState) {
                        break;
                    }
                }
            }
        }
    
        System.out.print("O menor caminho entre dois estados: ");
        int state = endState;

        while (state != startState) {
            System.out.print(state + " -> ");
            state = path[state];
        }
        System.out.println(startState);
        
    }

    public void encontraCaminhos(int startState, int endState) {
        
        Stack<Integer> stack = new Stack<>();
        
        int[] path = new int[this.matrizAdjacencia.length];
    
        boolean[] visited = new boolean[this.matrizAdjacencia.length];
        visited[startState] = true;
        
        stack.push(startState);
        while (!stack.isEmpty()) {
            int state = stack.pop();
            
            for (int i = 0; i < this.matrizAdjacencia[state].length; i++) {

                if (this.matrizAdjacencia[state][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                    path[i] = state;
                    
                    if (i == endState) {
                        System.out.print("Caminho possível: ");
                        state = endState;
                        while (state != startState) {
                            System.out.print(state + " -> ");
                            state = path[state];
                        }
                        System.out.println(startState);
                        
                        // Retorna ao estado anterior e continua a busca pelo próximo
                        stack.pop();
                        visited[i] = false;
                
                    }
                }
            }
        }
        
    }
    
    public void estados() {
        
        this.adicionarVertice("Acre"); // 0
        this.adicionarVertice("Alagoas"); // 1
        this.adicionarVertice("Amapá"); // 2
        this.adicionarVertice("Amazonas"); // 3
        this.adicionarVertice("Bahia"); // 4
        this.adicionarVertice("Ceará"); // 5
        this.adicionarVertice("Distrito Federal"); // 6
        this.adicionarVertice("Espírito Santo"); // 7
        this.adicionarVertice("Goiás"); // 8
        this.adicionarVertice("Maranhão"); // 9
        this.adicionarVertice("Mato Grosso"); // 10
        this.adicionarVertice("Mato Grosso do Sul"); // 11
        this.adicionarVertice("Minas Gerais"); // 12
        this.adicionarVertice("Pará"); // 13
        this.adicionarVertice("Paraíba"); // 14
        this.adicionarVertice("Paraná"); // 15
        this.adicionarVertice("Pernambuco"); // 16
        this.adicionarVertice("Piauí"); // 17
        this.adicionarVertice("Rio de Janeiro"); // 18
        this.adicionarVertice("Rio Grande do Norte"); // 19
        this.adicionarVertice("Rio Grande do Sul"); // 20
        this.adicionarVertice("Rondônia"); // 21
        this.adicionarVertice("Roraima"); // 22
        this.adicionarVertice("Santa Catarina"); // 23
        this.adicionarVertice("São Paulo"); // 24
        this.adicionarVertice("Sergipe"); // 25
        this.adicionarVertice("Tocantins"); // 26
        
        // Conectando os estados com seus vizinhos
        // Acre <-> Amazonas
        this.adicionarAresta(0, 3);

        // Amazonas <-> Roraima
        this.adicionarAresta(3, 22);
        
        // Amazonas <-> Pará
        this.adicionarAresta(3, 13);
        
        // Amazonas <-> Rondônia
        this.adicionarAresta(3, 21);
        
        // Roraima <-> Pará
        this.adicionarAresta(22, 13);
        
        // Rondônia <-> Mato Grosso
        this.adicionarAresta(21, 10);
        
        // Pará <-> Amapá
        this.adicionarAresta(13, 2);
        
        // Pará <-> Mato Grosso
        this.adicionarAresta(13, 10);
        
        // Pará <-> Maranhão
        this.adicionarAresta(13, 9);
        
        // Pará <-> Tocantins
        this.adicionarAresta(13, 26);
        
        // Mato Grosso <-> Tocantins
        this.adicionarAresta(10, 26);
        
        // Mato Grosso <-> Goiás
        this.adicionarAresta(10, 8);
        
        // Mato Grosso <-> Mato Grosso do Sul
        this.adicionarAresta(10, 11);
        
        // Maranhão <-> Piauí
        this.adicionarAresta(9, 17);
        
        // Maranhão <-> Tocantins
        this.adicionarAresta(9, 26);
        
        // Maranhão <-> Piauí
        this.adicionarAresta(9, 17);
        
        // Piauí <-> Ceará
        this.adicionarAresta(17, 5);
        
        // Ceará <-> Rio Grande do Norte
        this.adicionarAresta(5, 19);
        
        // Ceará <-> Paraíba
        this.adicionarAresta(5, 14);
        
        // Ceará <-> Pernambuco
        this.adicionarAresta(5, 16);
        
        // Rio Grande do Norte <-> Paraíba
        this.adicionarAresta(19, 14);
        
        // Paraíba <-> Pernambuco
        this.adicionarAresta(14, 16);
        
        // Pernambuco <-> Alagoas
        this.adicionarAresta(16, 1);
        
        // Pernambuco <-> Bahia
        this.adicionarAresta(16, 4);
        
        // Pernambuco <-> Piauí
        this.adicionarAresta(16, 17);
        
        // Alagoas <-> Sergipe
        this.adicionarAresta(1, 25);
        
        // Sergipe <-> Bahia
        this.adicionarAresta(25, 4);
        
        // Bahia <-> Minas Gerais
        this.adicionarAresta(4, 12);
        
        // Bahia <-> Piauí
        this.adicionarAresta(4, 17);
        
        // Bahia <-> Tocantins
        this.adicionarAresta(4, 26);
        
        // Bahia <-> Espírito Santo
        this.adicionarAresta(4, 7);
        
        // Bahia <-> Goiás
        this.adicionarAresta(4, 8);
        
        // Goiás <-> Tocantins
        this.adicionarAresta(8, 26);
        
        // Goiás <-> Minas Gerais
        this.adicionarAresta(8, 12);
        
        // Goiás <-> Distrito Federal
        this.adicionarAresta(8, 6);
        
        // Distrito Federal <-> Minas Gerais
        this.adicionarAresta(6, 12);
        
        // Minas Gerais <-> Espírito Santo
        this.adicionarAresta(12, 7);
        
        // Minas Gerais <-> Rio de Janeiro
        this.adicionarAresta(12, 18);
        
        // Minas Gerais <-> São Paulo
        this.adicionarAresta(12, 24);
        
        // Minas Gerais <-> Mato Grosso
        this.adicionarAresta(12, 10);
        
        // Rio de Janeiro <-> Espírito Santo
        this.adicionarAresta(18, 7);
        
        // Rio de Janeiro <-> São Paulo
        this.adicionarAresta(18, 24);
        
        // Mato Grosso <-> São Paulo
        this.adicionarAresta(10, 24);
        
        // São Paulo <-> Paraná
        this.adicionarAresta(24, 15);
        
        // Paraná <-> Santa Catarina
        this.adicionarAresta(15, 23);
        
        // Paraná <-> Mato Grosso do Sul
        this.adicionarAresta(15, 11);
        
        // Santa Catarina <-> Rio Grande do Sul
        this.adicionarAresta(15, 20);
        
    }
    
    public static void main(String args[]) {

        App grafo = new App(27);
        
        grafo.estados();

        int max = grafo.maisVizinhos();
        int min = grafo.menosVizinhos();
        
        System.out.println("O estado com maior número de vizinhos: " + grafo.vertices.get(max) + ".");
        System.out.println("O estado com menor número de vizinhos: " + grafo.vertices.get(min) + ".");

        grafo.menorCaminho(0, 26);
        grafo.encontraCaminhos(0, 26);

    }
    
}