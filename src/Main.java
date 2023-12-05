public class Main {
    public static void main(String[] args) {
        // Criando um grafo acíclico direcionado (DAG)
        Grafo<String> grafo = new Grafo<>(5);

        // Adicionando vértices
        Vertice<String> a = grafo.addVertice("A");
        Vertice<String> b = grafo.addVertice("B");
        Vertice<String> c = grafo.addVertice("C");
        Vertice<String> d = grafo.addVertice("D");
        Vertice<String> e = grafo.addVertice("E");

        // Adicionando arestas para formar um DAG
        grafo.addAresta("A", "B");
        grafo.addAresta("A", "C");
        grafo.addAresta("B", "D");
        grafo.addAresta("C", "D");
        grafo.addAresta("D", "E");

        // Exibindo o grafo
        System.out.println("Grafo:");
        for (int i = 0; i < grafo.quantVertices; i++) {
            for (int j = 0; j < grafo.quantVertices; j++) {
                System.out.print(grafo.arestas[i][j] + " ");
            }
            System.out.println();
        }

        // Executando a ordenação topológica e exibindo o resultado
        System.out.println("\nExecutando Ordenação Topológica:");
        grafo.ordenacaoTopologica();

        // Executando o metodo temciclo
        if(grafo.temCiclo()){
            System.out.println("O grafo possui ciclo");
        }else{
            System.out.println("O grafo não possui ciclo");
        }
    }
}