
public class Graph {
	private boolean isDirectGraph;
	int vertices;
	int[][] representation;

	Graph(int numOfVertices, boolean d) {
		this.vertices = numOfVertices;
		isDirectGraph = d;
		representation = new int[numOfVertices][numOfVertices];
		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				representation[i][j] = 0;
			}
		}
	}

	void addEdge(int x, int y) {
		representation[x][y]++;
		if (isDirectGraph)
			representation[y][x]++;
	}

	void display() {
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				System.out.println(representation[i][j]);
			}
			System.out.println();
		}
	}
}
