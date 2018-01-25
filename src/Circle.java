public class Circle {
	
	private float x;
	private float y;
	private float r;
	private Node a;
	private Node b;
	private Node c;
	private Node mid;
	
	public Circle(float x, float y, float r){
		this.x = x;
		this.y = y;
		this.r = r;
		this.mid = new Node(this.x, this.y);
	}
	
	public Circle(Node node, Node node2){
		this.x = (node.getX() + node2.getX())/2;
		this.y = (node.getY() + node2.getY())/2;
		// calculate radius with pythagoras
		this.r = (float) Math.sqrt((node.getX() - this.x)*(node.getX() - this.x) + (node.getY() - this.y)*(node.getY() - this.y));
		this.a = node;
		this.b = node2;
		this.mid = new Node(this.x, this.y);
	}
	
	public Circle(Node node1, Node node2, Node node3){
		// http://www.regentsprep.org/regents/math/geometry/gcg6/RCir.htm
		float mr = (node2.getY()-node1.getY())/(node2.getX()-node1.getX());
		float mt = (node3.getY()-node2.getY())/(node3.getX()-node2.getX());
		
		
		// xcoordinate of the center
		this.x = (mr*mt*(node3.getY()-node1.getY())+mr*(node2.getX()+node3.getX())-mt*(node1.getX()+node2.getX()))/(2*(mr-mt));
		
		//substistute for y
		this.y = -(1/mr)*(this.x-(node1.getX()+node2.getX())/2)+(node1.getY()+node2.getY())/2;
		
		
		// calculate radius
		this.r = (float)Math.sqrt(((node1.getX()-this.x)*(node1.getX()-this.x)) + ((node1.getY()-this.y)*(node1.getY()-this.y)));
	}
	
	public Node getA(){
		return this.a;
	}
	public Node getB(){
		return this.b;
	}
	public Node getC(){
		return this.c;
	}
	public Node getMid(){
		return this.mid;
	}

	public float getX() {
		return this.x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getR() {
		return this.r;
	}

	public void setR(float r) {
		this.r = r;
	}
	

	
}
