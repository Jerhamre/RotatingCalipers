public class Line<node1, node2> {
	public Node node1 = null;
	public Node node2 = null;
	
	public Line(Node node1, Node node2) { 
		this.node1 = node1; 
		this.node2 = node2; 
	}	
	
	@Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Line)){
            return false;
        }

        Line<node1,node2> other_ = (Line<node1,node2>) other;

        // this may cause NPE if nulls are valid values for x or y. The logic may be improved to handle nulls properly, if needed.
        return other_.node1.equals(this.node1) && other_.node2.equals(this.node2);
    }
}
