
public class Main {

	public static void main(String[] args) {
		Graph g= new Graph(4,false);
		g.addEdge(0, 1, 1);
		g.addEdge(1, 2, 10);
		g.addEdge(0, 2, 1);
		g.addEdge(2, 3, 1);
		g.addEdge(3, 0, 10);
		g.addEdge(1, 3, 1);
		Hamilton h=new Hamilton(g);
		//g.displayeadgs();
		Graph minpath=h.minmumHamiltonCircuit(0);
		minpath.displayRepresentation();
		minpath.displayeadgs();
	}

}
