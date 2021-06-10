
public class DirectedEdge implements Comparable<DirectedEdge>{
	private Vertix target;
	 private double distance;
	 private double updatedDistance;
	public DirectedEdge(Vertix target,double distance) {
		super();
		this.target=target;
		this.distance = distance;
	}
	public Vertix getTarget() {
		return target;
	}
	public void setTarget(Vertix v1) {
		target = v1;
	}
	
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		updatedDistance = distance;
	}
	@Override
	public int compareTo(DirectedEdge o) {
		if(this.getDistance() < o.getDistance()) {
			return 1;
		}else if(this.getDistance() > o.getDistance()) {
			return -1;
			}
		return 0;
	}
	
}
