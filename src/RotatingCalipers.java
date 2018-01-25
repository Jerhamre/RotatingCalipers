import java.awt.geom.Point2D;
import java.util.*;

public class RotatingCalipers {

    protected enum Corner { UPPER_RIGHT, UPPER_LEFT, LOWER_LEFT, LOWER_RIGHT }

    public static double getArea(Point2D.Double[] rectangle) {

        double deltaXAB = rectangle[0].x - rectangle[1].x;
        double deltaYAB = rectangle[0].y - rectangle[1].y;

        double deltaXBC = rectangle[1].x - rectangle[2].x;
        double deltaYBC = rectangle[1].y - rectangle[2].y;

        double lengthAB = Math.sqrt((deltaXAB * deltaXAB) + (deltaYAB * deltaYAB));
        double lengthBC = Math.sqrt((deltaXBC * deltaXBC) + (deltaYBC * deltaYBC));

        return lengthAB * lengthBC;
    }


    public List<Point2D.Double[]> getAllBoundingRectangles(List<Node> nodes){

        List<Point2D.Double[]> rectangles = new ArrayList<Point2D.Double[]>();

        List<Node> convexHull = nodes;

        Caliper I = new Caliper(convexHull, getIndex(convexHull, Corner.UPPER_RIGHT), 90);
        Caliper J = new Caliper(convexHull, getIndex(convexHull, Corner.UPPER_LEFT), 180);
        Caliper K = new Caliper(convexHull, getIndex(convexHull, Corner.LOWER_LEFT), 270);
        Caliper L = new Caliper(convexHull, getIndex(convexHull, Corner.LOWER_RIGHT), 0);

        while(L.getcurrentAngle() < 90.0) {

            rectangles.add(new Point2D.Double[]{
                    L.getIntersection(I),
                    I.getIntersection(J),
                    J.getIntersection(K),
                    K.getIntersection(L)
            });

            double smallestTheta = getSmallestTheta(I, J, K, L);

            I.rotateBy(smallestTheta);
            J.rotateBy(smallestTheta);
            K.rotateBy(smallestTheta);
            L.rotateBy(smallestTheta);
        }

        return rectangles;
    }


    public Point2D.Double[] getMinimumBoundingRectangle(List<Node> nodes){

        List<Point2D.Double[]> rectangles = getAllBoundingRectangles(nodes);

        Point2D.Double[] minimum = null;
        double area = Long.MAX_VALUE;

        for (Point2D.Double[] rectangle : rectangles) {

            double tempArea = getArea(rectangle);

            if (minimum == null || tempArea < area) {
                minimum = rectangle;
                area = tempArea;
            }
        }

        return minimum;
    }

    public double getSmallestTheta(Caliper I, Caliper J, Caliper K, Caliper L) {

        double thetaI = I.getDeltaAngleNextPoint();
        double thetaJ = J.getDeltaAngleNextPoint();
        double thetaK = K.getDeltaAngleNextPoint();
        double thetaL = L.getDeltaAngleNextPoint();

        if(thetaI <= thetaJ && thetaI <= thetaK && thetaI <= thetaL) {
            return thetaI;
        }
        else if(thetaJ <= thetaK && thetaJ <= thetaL) {
            return thetaJ;
        }
        else if(thetaK <= thetaL) {
            return thetaK;
        }
        else {
            return thetaL;
        }
    }

    public int getIndex(List<Node> convexHull, Corner corner) {

        int index = 0;
        Node point = convexHull.get(index);

        for(int i = 1; i < convexHull.size() - 1; i++) {

            Node temp = convexHull.get(i);
            boolean change = false;

            switch(corner) {
                case UPPER_RIGHT:
                    change = (temp.getX() > point.getX() || (temp.getX() == point.getX() && temp.getY() > point.getY()));
                    break;
                case UPPER_LEFT:
                    change = (temp.getY() > point.getY() || (temp.getY() == point.getY() && temp.getX() < point.getX()));
                    break;
                case LOWER_LEFT:
                    change = (temp.getX() < point.getX() || (temp.getX() == point.getX() && temp.getY() < point.getY()));
                    break;
                case LOWER_RIGHT:
                    change = (temp.getY() < point.getY() || (temp.getY() == point.getY() && temp.getX() > point.getX()));
                    break;
            }

            if(change) {
                index = i;
                point = temp;
            }
        }

        return index;
    }
}