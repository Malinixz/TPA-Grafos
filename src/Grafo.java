import java.util.ArrayList;
import java.util.Stack;

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    public int arestas[][];
    int quantVertices;

    public Grafo(int quantVertices){
        this.vertices = new ArrayList<Vertice<T>>();
        this.arestas  = new int[quantVertices][quantVertices];
        this.quantVertices = quantVertices;
    }

    public Vertice<T> addVertice(T valor){  // ADICIONA E RETORNA UM NOVO VERTICE
        Vertice novoVertice = new Vertice<T>(valor);
        this.vertices.add(novoVertice);
        return novoVertice;
    }

    private int getIndexVertice(T valor){   // PESQUISA ÍNDICE A PARTIR DE VALOR
        Vertice v;
        for(int i = 0; i < this.vertices.size(); i++){
            v = this.vertices.get(i);
            if(v.getValor() == valor){
                return i;
            }
        }
        return -1; // CASO NÃO EXISTA VERTICE COM O VALOR PASSADO
    }

    public void addAresta(T valorOrigem, T valorDestino){
        Vertice verticeOrigem, verticeDestino;

        int indexOrigem = getIndexVertice(valorOrigem);   // PROCURA ÍNDICE DA ORIGEM NA LISTA DE VERTICES DO GRAFO
        if(indexOrigem == -1){                            // CASO O VERTICE NÃO EXISTA, O ÍNDICE É -1
            verticeOrigem = addVertice(valorOrigem);
            indexOrigem = this.vertices.indexOf(verticeOrigem);   // GUARDA O NOVO ÍNDICE DO VERTICE ORIGEM
        }

        int indexDestino = getIndexVertice(valorDestino); // PROCURA ÍNDICE DO DESTINO NA LISTA DE VERTICES DO GRAFO
        if(indexDestino == -1){
            verticeDestino = addVertice(valorDestino);
            indexDestino = this.vertices.indexOf(verticeDestino);
        }

        this.arestas[indexOrigem][indexDestino] = 1;
    }

    public boolean temCiclo() {
        boolean[] visitado = new boolean[quantVertices];
        boolean[] pilhaRecursao = new boolean[quantVertices];

        for (int i = 0; i < quantVertices; i++) {
            if (!visitado[i]) {
                if (temCicloUtil(i, visitado, pilhaRecursao)) {
                    return true; // Ciclo encontrado
                }
            }
        }

        return false; // Nenhum ciclo encontrado
    }

    private boolean temCicloUtil(int vertice, boolean[] visitado, boolean[] pilhaRecursao) { // Realiza Busca em profundidade
        visitado[vertice] = true;
        pilhaRecursao[vertice] = true;

        for (int vizinho = 0; vizinho < quantVertices; vizinho++) {
            if (arestas[vertice][vizinho] == 1) {
                if (!visitado[vizinho]) {
                    if (temCicloUtil(vizinho, visitado, pilhaRecursao)) {
                        return true;
                    }
                } else if (pilhaRecursao[vizinho]) {
                    return true; // Ciclo encontrado
                }
            }
        }

        pilhaRecursao[vertice] = false; // Remover da pilha de recursão ao retroceder
        return false;
    }

    public void ordenacaoTopologica() {
        Stack<Integer> pilha = new Stack<>();               // Pilha para ordenar os vertices na DFS
        boolean[] visitado = new boolean[quantVertices];    // Lista para marcar os vertices visitados

        for (int i = 0; i < quantVertices; i++) {
            if (!visitado[i]) {
                ordenacaoTopologicaUtil(i, visitado, pilha);
            }
        }

        // Exibir a ordenação topológica
        System.out.println("Ordenação Topológica:");
        while (!pilha.isEmpty()) {
            System.out.print(vertices.get(pilha.pop()).getValor() + " "); // Retira elemento da pilha e exibe seu valor
        }
        System.out.println();
    }

    private void ordenacaoTopologicaUtil(int vertice, boolean[] visitado, Stack<Integer> pilha) { // Realiza Busca em profundidade
        visitado[vertice] = true;

        for (int vizinho = 0; vizinho < quantVertices; vizinho++) {
            if (arestas[vertice][vizinho] == 1 && !visitado[vizinho]) { // Verifica se ha vertices adjacentes e se esta visitado
                ordenacaoTopologicaUtil(vizinho, visitado, pilha);
            }
        }

        pilha.push(vertice);
    }

}
