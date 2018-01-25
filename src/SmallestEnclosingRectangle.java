import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class SmallestEnclosingRectangle {
	
	public Double[] smallestEnclosingRectangle(List<Node> nodes, List<Line> line){
		AndrewsAlgorithm convexHull = new AndrewsAlgorithm();
		RotatingCalipers rotatingCaliper = new RotatingCalipers();
		
		List<Node> convexhull = convexHull.andrewsAlgorithm(nodes, line);
		
		Point2D.Double[] smallestRectangle = rotatingCaliper.getMinimumBoundingRectangle(convexhull);
		return smallestRectangle;
	}
	
	
	
	public void clickRandom(List<Node> nodes,int maxX, int maxY, int padding) {

		Random rand = new Random();
		int n = rand.nextInt(10) + 10;

		for (int i = 0; i < n; i++) {
			nodes.add(new Node(rand.nextInt(maxX) + padding, rand.nextInt(maxY) + padding));
		}
	}
	
	public void clickLoad(List<Node> nodes, List<Line> lines) {
		nodes.clear();
		lines.clear();

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

	}
	
	
}
