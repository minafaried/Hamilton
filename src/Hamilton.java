import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Hamilton {

	Graph graph;

	public Hamilton(Graph g) {
		graph = g;
	}

	Boolean check(int v , int adj[][] , int vaildPath[] , int count , int vertices , Graph temp) {
        if (count == vertices) {
            return true;
        }
        for (int i = 0; i < vertices; i++) {
            if (adj[v][i] > 0 && vaildPath[i] == 0) {
                vaildPath[i] = 1;
                temp.addEdge(v , i , adj[v][i]);
                if (check(i , adj , vaildPath , count + 1 , vertices , temp)) {
                    return true;
                }
                vaildPath[i] = 1;
            }
        }
        return false;
    }

    boolean isValid(int adj[][] , int vertices , Graph g) {
        int path[] = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            path[i] = 0;
        }
        for (int i = 0; i < vertices; i++) {
            path[i] = 1;
            if (check(i , adj , path , 1 , vertices , g)) {
                return true;
            }
            path[i] = 0;
        }
        return false;
    }

    Graph hamiltonPath()// mona
    {
        Graph finalGraph = new Graph(graph.vertices , true);
        boolean flag = isValid(graph.representation , graph.vertices , finalGraph);

        if (flag == true) {
            return finalGraph;
        }
        else {
            return null;
        }
    }

	Graph haminltonCircuit()// mayada
	{

		return null;
	}

	boolean isedge(int currentNode, List<Edge> copyEdge) {
		for (int i = 0; i < copyEdge.size(); i++) {
			if (copyEdge.get(i).first == currentNode) {
				return true;
			}
		}
		return false;
	}

	List<List<Integer>> getPaths(List<Edge> copyEdge, int startNode) {
		List<Edge> adj = new ArrayList<Edge>();
		for (int i = 0; i < copyEdge.size(); i++) {
			if (copyEdge.get(i).first == startNode) {
				adj.add(copyEdge.get(i));
			}
		}
		List<Integer> visited = new ArrayList<Integer>();

		List<List<Integer>> paths = new ArrayList<List<Integer>>();

		for (int i = 0; i < adj.size(); i++) {
			int j = 0, currentNode = startNode;
			boolean isCircuit = false;
			visited.clear();
			List<Integer> path = new ArrayList<Integer>();
			path.add(startNode);
			for (int k = 0; k < copyEdge.size(); k++) {
				if (copyEdge.get(k).first == adj.get(i).first && copyEdge.get(k).second == adj.get(i).second) {
					j = k;
					break;
				}
			}
			visited.add(startNode);
			currentNode = copyEdge.get(j).second;
			visited.add(currentNode);
			path.add(currentNode);
			while (true)

			{
				if (currentNode == startNode) {
					isCircuit = true;
					break;
				}

				if (!isedge(currentNode, copyEdge)) {
					isCircuit = false;
					break;
				}
				int temp = currentNode;

				boolean flag = false;
				for (int k = 0; k < copyEdge.size(); k++) {
					if ((currentNode == copyEdge.get(k).first && copyEdge.get(k).second == temp)
							|| visited.contains(copyEdge.get(k).second)) {
						continue;
					}
					if (currentNode == copyEdge.get(k).first) {
						j = k;
						// visited.add(currentNode);
						flag = true;
						break;
					}
				}
				if (!flag) {
					for (int k = 0; k < copyEdge.size(); k++) {

						if (currentNode == copyEdge.get(k).first && copyEdge.get(k).second == startNode) {
							j = k;
							// visited.add(currentNode);
							flag = true;
							break;
						}
					}
				}
				if (!flag) {
					isCircuit = false;
					break;
				}

				currentNode = copyEdge.get(j).second;
				path.add(currentNode);
				visited.add(currentNode);
			}
			// System.out.println(visited.size());
			if (isCircuit && visited.size() - 1 == graph.vertices) {
				paths.add(path);
			}

		}
		// System.out.println(paths);
		return paths;
	}

	void shiftRight(List<Integer> path, int startNode) {
		while (path.get(0) != startNode) {
			Integer temp = path.get(path.size() - 1);
			path.remove(path.size() - 1);
			path.add(0, temp);
		}
		path.add(startNode);
	}

	Graph minmumHamiltonCircuit(int startNode)// mina
	{
		if (graph.vertices < 7) {
			List<Edge> copyEdge = new ArrayList<Edge>();
			copyEdge.addAll(graph.edges);

			Collections.sort(copyEdge, new Comparator<Edge>() {
				public int compare(Edge e1, Edge e2) {
					return Integer.valueOf(e1.first.compareTo(e2.first));
				}
			});
			List<List<Integer>> paths = new ArrayList<List<Integer>>();
			for (int i = 0; i < graph.vertices; i++) {
				paths.addAll(getPaths(copyEdge, i));
			}
			List<List<Integer>> res = new ArrayList<List<Integer>>();
			for (int i = 0; i < paths.size(); i++) {
				if (paths.get(i).get(0) != startNode) {
					paths.get(i).remove(graph.vertices);
					shiftRight(paths.get(i), startNode);
				}
				if (!res.contains(paths.get(i))) {
					res.add(paths.get(i));
				}
			}
			// System.out.println(res);

			int min = 10000000;
			Graph finalGraph = new Graph(graph.vertices, true);
			Graph temp = new Graph(graph.vertices, true);
			for (int i = 0; i < res.size(); i++) {

				int totalpathcost = 0;
				for (int j = 0; j < res.get(i).size() - 1; j++) {
					int cost = 0;
					cost += graph.getEdgeCost(res.get(i).get(j), res.get(i).get(j + 1));
					temp.addEdge(res.get(i).get(j), res.get(i).get(j + 1), cost);
					totalpathcost += cost;
				}
				if (totalpathcost < min) {
					min = totalpathcost;
					finalGraph.reset();
					finalGraph.copyGraph(temp);
				}
				temp.reset();
			}

			return finalGraph;
		}
		// finalGraph.displayeadgs();
		Graph finalGraph = new Graph(graph.vertices, true);
		
		return finalGraph;
	}
}
