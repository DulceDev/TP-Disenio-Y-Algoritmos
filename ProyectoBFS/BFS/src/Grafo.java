import java.util.*;

public class Grafo {
    private List<Integer>[] adjList;
    private int numVertices;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        adjList = (List<Integer>[]) new ArrayList[numVertices + 1];
        for (int i = 1; i <= numVertices; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
    }

    public void agregarArista(int origen, int destino) {
        adjList[origen].add(destino);
        adjList[destino].add(origen);
    }

    public int BFS(int v) {
        Queue<Integer> cola = new LinkedList<>();
        int[] distancias = new int[numVertices + 1];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[v] = 0;
        cola.offer(v);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            for (int vecino : adjList[actual]) {
                if (distancias[vecino] == Integer.MAX_VALUE) {
                    distancias[vecino] = distancias[actual] + 1;
                    cola.offer(vecino);
                }
            }
        }

        int maxDistancia = 0;
        for (int distancia : distancias) {
            if (distancia != Integer.MAX_VALUE) {
                maxDistancia = Math.max(maxDistancia, distancia);
            }
        }
        return maxDistancia;
    }

    public int calcularDiametro() {
        int maxExcentricidad = 0;
        for (int i = 1; i <= numVertices; i++) {
            int excentricidad = BFS(i);
            maxExcentricidad = Math.max(maxExcentricidad, excentricidad);
        }
        return maxExcentricidad;
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo(11);

        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 6);
        grafo.agregarArista(2, 3);
        grafo.agregarArista(2, 7);
        grafo.agregarArista(3, 4);
        grafo.agregarArista(3, 8);
        grafo.agregarArista(3, 10);
        grafo.agregarArista(4, 5);
        grafo.agregarArista(6, 7);
        grafo.agregarArista(7, 9);
        grafo.agregarArista(7, 10);
        grafo.agregarArista(8, 10);
        grafo.agregarArista(10, 11);

        int diametro = grafo.calcularDiametro();
        System.out.println("El diámetro del grafo es: " + diametro);
    }
}
