import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Minidisc {
	public Circle minidisc(List<Node> nodes){
		//System.out.println("-------------------");
		
		//1. randomize P
		long seed = System.nanoTime();
		Collections.shuffle(nodes, new Random(seed));
		//2. Let D2 be the smallest enclosing disc for {p1,p2}
		Circle c = new Circle(nodes.get(0),nodes.get(1));

		
		for(int i = 2; i<nodes.size(); i++){
			if(isInsideCircle(c, nodes.get(i))){ // size of disc might be wrong (should be 1 not 2)
				
			}
			else{
				//discs.add(e);
				c = minidiscWithPoint(nodes.subList(0, i),nodes.get(i));
			}
		}
		
		return c;
	}
	
	public Circle minidiscWithPoint(List<Node> nodes, Node q){
		// 1. Compute a random permutation p1, .... , pn of P
		long seed = System.nanoTime();
		Collections.shuffle(nodes, new Random(seed));
		
		//2. Let D1 be the smallest disc with q and p1 on its boundary
		Circle d = new Circle(q,nodes.get(0));
		
		
		
		// 3.
		for(int j = 1; j<nodes.size(); j++){
			// 4.
			if(isInsideCircle(d,nodes.get(j))){
				// 5.
				
			}
			else{
				// 6.
				d = minidiscWith2Points(nodes.subList(0, j),nodes.get(j),q);
			}
		}
		// 7.
		return d;
	}
	
	public Circle minidiscWith2Points(List<Node> nodes, Node q1, Node q2){
	
		// 1. Let D0 be the smallest disc with q1 and q2 on its boundary
		Circle d = new Circle(q1,q2);

		
		// 2. for k <- 1 to n
		for(int k = 0; k<nodes.size(); k++){
			
			// 3. do if pk exists in Dk-1
			if(isInsideCircle(d,nodes.get(k))){
				
				// 4. then Dk <- Dk-1
				
			}
			else{
				
				// 5. Dk <- the disc with q1, q2 and Pk on its boundary
				d = new Circle(q1,q2,nodes.get(k));
			}
		}
		// 6. return Dn
		return d;
	}
	
	
	
	
	public boolean isOutsideCircle(Circle c, Node node){
		float radiusToNode = (float) Math.sqrt((node.getX()-c.getX())*(node.getX()-c.getX())
				+(node.getY()-c.getY())*(node.getY()-c.getY()));
		
		
		if(radiusToNode < c.getR()){
			return false;
		}
		return true;
		
		
	}
	
	public boolean isInsideCircle(Circle c, Node node){
		
		float radiusToNode = ((node.getX()-c.getX())*(node.getX()-c.getX())
				+(node.getY()-c.getY())*(node.getY()-c.getY()));
		
		float circleR = c.getR()*c.getR();
			
		if(radiusToNode < circleR){
			return true;
		}
		return false;
			
			
	}
	
	
	
	
	
	public void clickRandom(List<Node> nodes,int maxX, int maxY, int padding) {

		Random rand = new Random();
		int n = rand.nextInt(1) + 5;
		int maxXt = 400;
		int maxYt = 400;
		int pad = 200;
		for (int i = 0; i < n; i++) {
			nodes.add(new Node(rand.nextInt(maxXt) + pad, rand.nextInt(maxYt) + pad));
		}
	}
	
	
	public void clickLoad(List<Node> nodes) {
		nodes.clear();
		nodes.add(new Node(5,5));
		nodes.add(new Node(6,-2));
		nodes.add(new Node(2,-4));
		Circle c = new Circle(nodes.get(0),nodes.get(1),nodes.get(2));
		System.out.println("circle "+"  x:"+c.getX()+"y:"+c.getY()+" r: "+c.getR());
		/*
		try {
			BufferedReader br = new BufferedReader(new FileReader("lab1-input3.txt"));

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				line = br.readLine();
				if (line != null) {
					String[] coordinates = line.split(" ");
					nodes.add(new Node(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
}
