import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {
	private ArrayList<Vertix> cities;
	private ArrayList<Boolean> visited;
	private int[] parentVertex;
	public Graph() {
		cities = new ArrayList<>();
		visited = new ArrayList<>();
	}

	public void add(Vertix x) {
		cities.add(x);
		visited.add(false);
		parentVertex = new int[cities.size()];
	}

	public double shortestPath(Vertix start, Vertix destination) {

		double distance[] = new double[cities.size()];
		for (int x = 0; x < distance.length; x++) {
			distance[x] = Integer.MAX_VALUE;
			visited.set(x, false);
		}
		
		if (start.getName().contains(destination.getName())) {
			return 0;
		} else {

			PriorityQueue<DirectedEdge> pq = new PriorityQueue<DirectedEdge>((e1, e2) -> (int)e1.getDistance() - (int)e2.getDistance());
			distance[getIndex(start)] = 0;
			parentVertex[getIndex(start)] = -1;
			pq.add(new DirectedEdge(start, 0));
			while (pq.isEmpty() == false) {
				Vertix vert = pq.poll().getTarget();
				int index = getIndex(vert);
				visited.set(index, true);
				LinkedList<DirectedEdge> adj = vert.getNeighbors();
				for (int x = 0; x < adj.size(); x++) {
					DirectedEdge v = adj.get(x);
					double dist = v.getDistance();
					if (visited.get(getIndex(adj.get(x).getTarget())) != true) {
						if (distance[index] + dist < distance[getIndex(adj.get(x).getTarget())]) {
							distance[getIndex(adj.get(x).getTarget())] = distance[index] + dist;
							v.setDistance(distance[index] + dist);
							parentVertex[getIndex(adj.get(x).getTarget())] = index;
							pq.add(v);
						}
					}
				}
			}
			
		}

		return distance[getIndex(destination)];

	}
	public String[] path(Vertix destination) {
		int parent = parentVertex[getIndex(destination)];
		String[] arr = new String[cities.size()];
		System.out.print(cities.get(getIndex(destination)).getName() + " ");
		arr[0]=cities.get(getIndex(destination)).getName();
		for (int x = 1; x < parentVertex.length; x++) {
			arr[x]=cities.get(parent).getName();
			parent = parentVertex[parent];
			if (parent == -1)
				break;
		}
		return arr;
	}
	public int[] getParents() {
		return parentVertex;
	}
	public int getIndex(Vertix v) {
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i).getName().contains(v.getName())) {
				return i;
			}
		}
		return 0;
	}

}
