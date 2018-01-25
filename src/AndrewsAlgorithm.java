import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AndrewsAlgorithm {
	
	public List<Node> andrewsAlgorithm(List<Node> nodes, List<Line> lines) {
		// Sort in O(n log(n)) time
		MergeSort mergeSort = new MergeSort();
		Object[] test = mergeSort.mergeSort(nodes.toArray());
		nodes.clear();
		lines.clear();
		for (Object n : test) {
			nodes.add((Node) n);
		}

		List<Node> upperHull = new ArrayList<Node>();

		upperHull.add(nodes.get(0));
		upperHull.add(nodes.get(1));
		lines.add(new Line(upperHull.get(0), upperHull.get(1)));

		for (int i = 2; i < nodes.size(); i++) {
			upperHull.add(nodes.get(i));
			lines.add(new Line(nodes.get(i - 1), nodes.get(i)));
			float oritentation = -1;
			int removeIndex = upperHull.size() - 1;
			while (oritentation <= 0 && upperHull.size() > 2) {
				oritentation = upperHull.get(removeIndex - 2).getX() * upperHull.get(removeIndex - 1).getY()
						+ upperHull.get(removeIndex).getX() * upperHull.get(removeIndex - 2).getY()
						+ upperHull.get(removeIndex - 1).getX() * upperHull.get(removeIndex).getY()
						- upperHull.get(removeIndex).getX() * upperHull.get(removeIndex - 1).getY()
						- upperHull.get(removeIndex - 1).getX() * upperHull.get(removeIndex - 2).getY()
						- upperHull.get(removeIndex - 2).getX() * upperHull.get(removeIndex).getY();

				if (oritentation <= 0) {
					Node n = upperHull.remove(removeIndex - 1);
					lines.remove(lines.size() - 1);
					lines.remove(lines.size() - 1);
					lines.add(new Line(upperHull.get(removeIndex - 2), upperHull.get(removeIndex - 1)));
				}
				removeIndex--;
			}
		}

		List<Node> lowerHull = new ArrayList<Node>();

		lowerHull.add(nodes.get(nodes.size() - 1));
		lowerHull.add(nodes.get(nodes.size() - 2));
		lines.add(new Line(lowerHull.get(0), lowerHull.get(1)));

		for (int i = nodes.size() - 2; i >= 0; i--) {
			lowerHull.add(nodes.get(i));
			lines.add(new Line(nodes.get(i + 1), nodes.get(i)));
			float oritentation = -1;
			int removeIndex = lowerHull.size() - 1;
			while (oritentation <= 0 && lowerHull.size() > 2) {
				oritentation = lowerHull.get(removeIndex - 2).getX() * lowerHull.get(removeIndex - 1).getY()
						+ lowerHull.get(removeIndex).getX() * lowerHull.get(removeIndex - 2).getY()
						+ lowerHull.get(removeIndex - 1).getX() * lowerHull.get(removeIndex).getY()
						- lowerHull.get(removeIndex).getX() * lowerHull.get(removeIndex - 1).getY()
						- lowerHull.get(removeIndex - 1).getX() * lowerHull.get(removeIndex - 2).getY()
						- lowerHull.get(removeIndex - 2).getX() * lowerHull.get(removeIndex).getY();

				if (oritentation <= 0) {
					Node n = lowerHull.remove(removeIndex - 1);
					lines.remove(lines.size() - 1);
					lines.remove(lines.size() - 1);
					lines.add(new Line(lowerHull.get(removeIndex - 2), lowerHull.get(removeIndex - 1)));
				}
				removeIndex--;
			}
		}
		
		List<Node> newList = new ArrayList<Node>(upperHull);
		newList.addAll(lowerHull.subList(1, lowerHull.size()));
		return newList;
		
	}// andrews
}
