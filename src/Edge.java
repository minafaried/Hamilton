
public class Edge {

	Integer first,second,cost;
	public Edge(int f,int s, int c) {
		first=f;
		second=s;
		cost=c;
	}
	void displayedge(){
		System.out.println("first :"+first+" second :"+second+" cost :"+cost);
	}
	
}
