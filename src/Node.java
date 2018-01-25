public class Node {

	private float x;
	private float y;
	private Node nextNode = null;
	
	public Node() {
		this.x = 0;
		this.y = 0;
	}
	public Node(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Node getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node node) {
		this.nextNode = node;
	}
}
