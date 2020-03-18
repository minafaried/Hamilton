import java.util.ArrayList;
import java.util.List;

public class Graph {
	private boolean isDirectGraph;
	int vertices;
	int[][] representation;
	List<Edge> edges;

	Graph(int numOfVertices, boolean d) {
		edges = new ArrayList<Edge>();
		this.vertices = numOfVertices;
		isDirectGraph = d;
		representation = new int[numOfVertices][numOfVertices];
		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				representation[i][j] = 0;
			}
		}
	}

	void addEdge(int x, int y, int cost) {
		representation[x][y]++;
		edges.add(new Edge(x, y, cost));
		if (!isDirectGraph) {
			representation[y][x]++;
			edges.add(new Edge(y, x, cost));
		}
	}

	void displayRepresentation() {
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				System.out.println(representation[i][j]);
			}
			System.out.println();
		}
	}
	void displayeadgs()
	{
		for (int i = 0; i < edges.size(); i++) {
			edges.get(i).displayedge();
		}
	}
}
