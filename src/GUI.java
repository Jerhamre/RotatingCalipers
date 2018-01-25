import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class GUI extends JFrame {

	//AndrewsAlgorithm andrew = new AndrewsAlgorithm();
	//MelkmansAlgorithm melkman = new MelkmansAlgorithm();
	Bruteforce brute = new Bruteforce();
	Minidisc minidisc = new Minidisc();
	SmallestEnclosingRectangle ser = new SmallestEnclosingRectangle();

	private JFrame frame;
	private JButton btnRandom;
	private JButton btnCompute;
	private JButton btnLoad;

	private Circle cmin;
	private List<Node> nodes = new ArrayList<Node>();
	private List<Line> lines = new ArrayList<Line>();
	private java.awt.geom.Point2D.Double[] rectangle;

	private int padding = 50;

	public GUI(String frameName) {
		setPreferredSize(new Dimension(800, 800));
		setTitle(frameName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container buttonContainer = new Container();
		buttonContainer.setLayout(new GridLayout(1, 3));
		btnRandom = new JButton("Random");
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nodes.clear();
				clickRandom();
				repaint();
			}
		});
		buttonContainer.add(btnRandom);
		btnCompute = new JButton("Compute");
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickCompute();
			}
		});
		buttonContainer.add(btnCompute);
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickLoad();
			}
		});
		buttonContainer.add(btnLoad);

		Container gridContainer = new Container();
		gridContainer.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				
			}
		});
		gridContainer.add(panel, BorderLayout.CENTER);
		gridContainer.add(buttonContainer, BorderLayout.SOUTH);
		setContentPane(gridContainer);

		pack();
		setVisible(true);

	}

	private void clickRandom() {

		int maxX = this.getPreferredSize().width - padding * 2;
		int maxY = this.getPreferredSize().height - btnRandom.getPreferredSize().height - padding * 2;
		
		//brute.clickRandom(nodes, maxX, maxY, padding);
		//minidisc.clickRandom(nodes, maxX, maxY, padding);
		ser.clickRandom(nodes, maxX, maxY, padding);
	}

	private void clickCompute() {

		if (nodes.size() == 0)
			return;
		
		long startTime = System.nanoTime();
		
		//cmin = brute.bruteForceAlgorithm(nodes);
		//cmin = minidisc.minidisc(nodes);
		rectangle = ser.smallestEnclosingRectangle(nodes, lines);
		
		
		long stopTime = System.nanoTime();
	    long elapsedTime = stopTime - startTime;
	    double seconds = (double) elapsedTime / 1000000000.0;
	    System.out.println("execution time: "+ seconds +" seconds");
		repaint();
	}

	private void clickLoad() {
		brute.clickLoad(nodes);
		//minidisc.clickLoad(nodes);
		repaint();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(5));
		g2.setColor(Color.BLACK);
		for (Node n : nodes) {
			g2.drawLine((int)n.getX(), (int)n.getY(), (int)n.getX(), (int)n.getY());
		}
		
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.BLUE);
		for (Node n : nodes) {
			if(n.getNextNode()!=null){
				g2.drawLine((int)n.getX(), (int)n.getY(), (int)n.getNextNode().getX(), (int)n.getNextNode().getY());
			}
		}
		
		if(rectangle !=null){
			g2.setColor(Color.RED);
			g2.drawLine((int) rectangle[0].getX(), (int) rectangle[0].getY(),
					(int) rectangle[1].getX(), (int) rectangle[1].getY());
		
			g2.drawLine((int) rectangle[1].getX(), (int) rectangle[1].getY(),
					(int) rectangle[2].getX(), (int) rectangle[2].getY());
			
			g2.drawLine((int) rectangle[2].getX(), (int) rectangle[2].getY(),
					(int) rectangle[3].getX(), (int) rectangle[3].getY());
			
			g2.drawLine((int) rectangle[3].getX(), (int) rectangle[3].getY(),
					(int) rectangle[0].getX(), (int) rectangle[0].getY());
		}
		
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.BLUE);
		for (Line l : lines) {
			g2.drawLine((int)l.node1.getX(), (int)l.node1.getY(), (int)l.node2.getX(), (int)l.node2.getY());
		}

		if(cmin !=null){
			g2.setColor(Color.RED);
			g2.drawLine((int)cmin.getX(), (int)cmin.getY(), (int)cmin.getX(), (int)cmin.getY());
			g2.drawString("circle "+"  x:"+cmin.getX()+"y:"+cmin.getY(), cmin.getX(), cmin.getY());
	        g2.drawOval((int)cmin.getX() - (int)cmin.getR(), (int)cmin.getY() - (int)cmin.getR(), 2 * (int)cmin.getR(), 2 * (int)cmin.getR());
		}
		
		
		
			
	}


}
