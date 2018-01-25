import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class Bruteforce {
	public Circle bruteForceAlgorithm(List<Node> nodes){
		
		Circle cmin = new Circle(0,0,Float.MAX_VALUE);
		
		// 2points
		for(Node a : nodes){
			bloop: for(Node b : nodes){
				if(a.equals(b)){
					continue;
				}
				Circle c_2p = new Circle(a,b);
				for(Node d : nodes){
					if(d.equals(a)){
						continue;
					}
					if(d.equals(b)){
						continue;
					}
					if(isOutsideCircle(c_2p,d)){
						continue bloop;
					}
				}
				
				if(c_2p.getR() < cmin.getR()){
					cmin = c_2p;
				}
			}	
		}
		// 3 points
		for(Node a : nodes){
			for(Node b : nodes){
				if(b.equals(a)){
					continue;
				}
				cloop: for(Node c : nodes){
					if(c.equals(a)){
						continue;
					}
					if(c.equals(b)){
						continue;
					}
					Circle c_3p = new Circle(a,b,c);
					for(Node d : nodes){
						//System.out.println("-----------------------------");
						if(d.equals(a)){
							continue;
						}
						if(d.equals(b)){
							continue;
						}
						if(d.equals(c)){
							continue;
						}
						//System.out.println("d.x "+d.getX()+" d.y "+d.getY());
						if(isOutsideCircle(c_3p,d)){
							//System.out.println("outside");
							continue cloop;
						}
					}
					if(c_3p.getR() < cmin.getR()){
						//System.out.println("THIS SHOULD NEVER FUCKING HAPPEN");
						cmin = c_3p;
					}
				}
			}
		}
		
		return cmin;
	}// bruteforce
	
	public boolean isOutsideCircle(Circle c, Node node){
		/*
		float dx = (node.getX()-c.getX());
		float dy = (node.getY()-c.getY());
		float radiusToNode = (float) Math.sqrt((Math.pow(dx,2)+Math.pow(dx,2)));
		
		//System.out.println("dx: "+(node.getX()-c.getX()));
		//System.out.println("dy: "+(node.getY()-c.getY()));
		
		//float circleRadius = (float) Math.pow(c.getR(),2);
		float circleRadius = c.getR();
		//System.out.println("node r: "+radiusToNode +" node xpos"+node.getX()+" ypos "+node.getY());
		//System.out.println("c r: "+circleRadius);
		//System.out.println(radiusToNode-circleRadius);
		/*
		if(dx > c.getR()){
			return true;
		}
		
		if(dy > c.getR()){
			return true;
		}
		
		if(dx+dy <= c.getR()){
			return false;
		}
		
		if(radiusToNode < circleRadius){
			return false;
		}
		return true;
		*/
		float radiusToNode = (float) Math.sqrt((node.getX()-c.getX())*(node.getX()-c.getX())
				+(node.getY()-c.getY())*(node.getY()-c.getY()));
		
		
		if(radiusToNode < c.getR()){
			return false;
		}
		return true;
		
		
		
		
	}
	
	public boolean isInsideCircle(Node a, Node b, Node c, Node d){
		
		float adx = a.getX() - d.getX();
		float ady = a.getY() - d.getY();
		float bdx = b.getX() - d.getX();
		float bdy = b.getY() - d.getY();
		float cdx = c.getX() - d.getX();
		float cdy = c.getY() - d.getY();
		float abdet = adx*bdy - bdx*ady;
		float bcdet = bdx*cdy - cdx*bdy;
		float cadet = cdx*ady - adx*cdy;
		float alift = adx*adx + ady*ady;
		float blift = bdx*bdx + bdy*bdy;
		float clift = cdx*cdx + cdy*cdy;
		float sign = alift*bcdet + blift*cadet + clift*abdet;
		
		
		if(sign >= 0){
			return true;
		}
		
		if(sign == 0){
			return true;
		}
		return false;
	}
	
	
	
	
	
	public void clickRandom(List<Node> nodes,int maxX, int maxY, int padding) {

		Random rand = new Random();
		int n = rand.nextInt(1) + 275;
		int maxXt = 400;
		int maxYt = 400;
		int pad = 200;
		for (int i = 0; i < n; i++) {
			nodes.add(new Node(rand.nextInt(maxXt) + pad, rand.nextInt(maxYt) + pad));
		}
	}
	
	
	public void clickLoad(List<Node> nodes) {
		nodes.clear();
		nodes.add(new Node(200,200));
		nodes.add(new Node(500,270));
		nodes.add(new Node(300,150));
		nodes.add(new Node(100,340));
		//nodes.add(new Node(300,300));
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
